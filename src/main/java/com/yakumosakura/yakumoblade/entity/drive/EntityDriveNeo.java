package com.yakumosakura.yakumoblade.entity.drive;

import io.github.fabricators_of_create.porting_lib.entity.PartEntity;
import mods.flammpfeil.slashblade.SlashBladeConfig;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

// 处理实体命中逻辑
public class EntityDriveNeo extends EntityDrive {
    public EntityDriveNeo(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        // 获取被命中的实体
        Entity targetEntity = entityHitResult.getEntity();

        // 计算基础伤害值
        double baseDamage = this.getDamage();
        // 如果是暴击，增加随机伤害
        if (this.getIsCritical()) {
            baseDamage += this.random.nextFloat();
        }

        // 获取射击实体（攻击来源）
        Entity shooter = this.getShooter();
        DamageSource damageSource;
        // 创建伤害来源
        if (shooter == null) {
            damageSource = this.damageSources().indirectMagic(this, this);
        } else {
            damageSource = this.damageSources().indirectMagic(this, shooter);
            // 如果射击者是活体实体，设置最后攻击目标
            if (shooter instanceof LivingEntity) {
                Entity hitTarget = targetEntity;
                // 如果是部件实体，获取其父实体
                if (targetEntity instanceof PartEntity) {
                    hitTarget = ((PartEntity) targetEntity).getParent();
                }
                ((LivingEntity) shooter).setLastHurtMob(hitTarget);
            }
        }

        // 保存目标实体的火焰持续时间
        int fireDuration = targetEntity.getRemainingFireTicks();
        // 如果此实体着火且目标不是末影人，点燃目标
        if (this.isOnFire() && !(targetEntity instanceof EnderMan)) {
            targetEntity.setSecondsOnFire(5);
        }

        // 重置目标实体的无敌时间
        targetEntity.invulnerableTime = 0;

        // 计算最终伤害值
        float finalDamage = (float) (baseDamage);

        // 获取拥有者并计算额外伤害加成
        Entity owner = this.getOwner();
        if (owner instanceof LivingEntity living) {
            // 应用攻击伤害属性加成
            finalDamage = (float) ((double) finalDamage * living.getAttributeValue(Attributes.ATTACK_DAMAGE));

            // 引入对数函数创建非线性增长效果: damage = base * (1 + log(attackDamage + 1)) / (1 + log(20))
            // 这样在attackDamage=1时增长较快，在attackDamage=100时增长较慢
            double attackDamage = living.getAttributeValue(Attributes.ATTACK_DAMAGE);
            double nonLinearFactor = (1.0 + Math.log(attackDamage + 1.0)) / (1.0 + Math.log(20.0));
            finalDamage = (float) ((double) finalDamage * nonLinearFactor);

            // 如果是玩家，应用专注等级加成
            if (living instanceof Player player) {
                // 获取专注等级
                IConcentrationRank.ConcentrationRanks rankBonus =
                        CapabilityConcentrationRank.RANK_POINT.maybeGet(player)
                                .map((rp) -> rp.getRank(player.getCommandSenderWorld().getGameTime()))
                                .orElse(IConcentrationRank.ConcentrationRanks.NONE);

                // 计算等级伤害加成
                float rankDamageBonus = (float) rankBonus.level / 2.0F;

                // 如果等级达到S级或更高，应用精炼和等级相关的额外加成
                if (IConcentrationRank.ConcentrationRanks.S.level <= rankBonus.level) {
                    int refine = CapabilitySlashBlade.BLADESTATE.maybeGet(player.getMainHandItem())
                            .map(ISlashBladeState::getRefine)
                            .orElse(0);
                    int level = player.experienceLevel;
                    rankDamageBonus = (float) Math.max(
                            rankDamageBonus,
                            (double) Math.min(level, refine) * SlashBladeConfig.REFINE_DAMAGE_MULTIPLIER.get()
                    );
                }

                // 将等级加成加入最终伤害
                finalDamage += rankDamageBonus;
            }

            // 应用斩魂刀伤害比例和配置中的伤害倍率
            finalDamage = (float) ((double) finalDamage *
                    (double) AttackManager.getSlashBladeDamageScale(living) *
                    SlashBladeConfig.SLASHBLADE_DAMAGE_MULTIPLIER.get());
        }

        // 对目标造成伤害
        if (targetEntity.hurt(damageSource, finalDamage)) {
            // 获取实际命中实体（处理部件实体情况）
            Entity hitTarget = targetEntity;
            if (targetEntity instanceof PartEntity) {
                hitTarget = ((PartEntity) targetEntity).getParent();
            }

            // 如果命中的是活体实体，应用眩晕效果和其他影响
            if (hitTarget instanceof LivingEntity targetLivingEntity) {
                StunManager.setStun(targetLivingEntity);

                // 如果不是客户端且射击者是活体实体，执行后伤害和后攻击效果
                if (!this.level().isClientSide() && shooter instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(targetLivingEntity, shooter);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) shooter, targetLivingEntity);
                }

                // 应用药水效果
                this.affectEntity(targetLivingEntity, this.getPotionEffects(), 1.0F);

                // 如果射击者是服务器玩家，播放音效
                if (shooter != null && targetLivingEntity != shooter &&
                        targetLivingEntity instanceof Player &&
                        shooter instanceof ServerPlayer) {
                    ((ServerPlayer) shooter).playNotifySound(
                            this.getHitEntityPlayerSound(),
                            SoundSource.PLAYERS,
                            0.18F,
                            0.45F
                    );
                }
            }

            // 播放命中音效
            this.playSound(this.getHitEntitySound(), 1.0F,
                    1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        } else {
            // 如果未成功造成伤害，恢复目标的火焰持续时间
            targetEntity.setRemainingFireTicks(fireDuration);
        }
    }
}