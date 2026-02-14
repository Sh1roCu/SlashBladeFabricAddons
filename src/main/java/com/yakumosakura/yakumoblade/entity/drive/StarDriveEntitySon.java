package com.yakumosakura.yakumoblade.entity.drive;


import io.github.fabricators_of_create.porting_lib.entity.PartEntity;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class StarDriveEntitySon extends StarDriveEntity {

    public StarDriveEntitySon(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);

    }

    @Override
    public void tick() {
        super.tick();


    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity targetEntity = entityHitResult.getEntity();
        double i = this.getDamage();
        if (this.getIsCritical()) {
            i += this.random.nextDouble();
        }

        Entity shooter = this.getShooter();
        DamageSource damagesource;
        if (shooter == null) {
            damagesource = new DamageSource(getOwner().level().registryAccess()
                    .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.WITHER), this, this);
        } else {
            damagesource = new DamageSource(getOwner().level().registryAccess()
                    .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.WITHER), this, getOwner());
            if (shooter instanceof LivingEntity) {
                Entity hits = targetEntity;
                if (targetEntity instanceof PartEntity) {
                    hits = ((PartEntity) targetEntity).getParent();
                }

                ((LivingEntity) shooter).setLastHurtMob(hits);
            }
        }

        int fireTime = targetEntity.getRemainingFireTicks();
        if (this.isOnFire() && !(targetEntity instanceof EnderMan)) {
            targetEntity.setSecondsOnFire(5);
        }

        targetEntity.invulnerableTime = 0;
        float damageValue = (float) i;
        Entity var9 = this.getOwner();
        if (var9 instanceof LivingEntity living) {
            damageValue = (float) ((double) damageValue * CapabilitySlashBlade.BLADESTATE.maybeGet(living.getMainHandItem())
                    .map(ISlashBladeState::getBaseAttackModifier).orElse(0f));
            damageValue = (float) ((double) damageValue
                    * (double) AttackManager.getSlashBladeDamageScale(living)
            );
        }

        if (targetEntity.hurt(damagesource, damageValue)) {
            Entity hits = targetEntity;
            if (targetEntity instanceof PartEntity) {
                hits = ((PartEntity) targetEntity).getParent();
            }

            if (hits instanceof LivingEntity targetLivingEntity) {
                StunManager.setStun(targetLivingEntity);
                if (!this.level().isClientSide() && shooter instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(targetLivingEntity, shooter);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) shooter, targetLivingEntity);
                }

                this.affectEntity(targetLivingEntity, this.getPotionEffects(), 1.0F);
                if (shooter != null && targetLivingEntity != shooter && targetLivingEntity instanceof Player && shooter instanceof ServerPlayer) {
                    ((ServerPlayer) shooter).playNotifySound(this.getHitEntityPlayerSound(), SoundSource.PLAYERS, 0.18F, 0.45F);
                }
            }

            this.playSound(this.getHitEntitySound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        } else {
            targetEntity.setRemainingFireTicks(fireTime);
        }

    }
}
