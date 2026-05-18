package com.yakumosakura.yakumoblade.entity.drive;


import io.github.fabricators_of_create.porting_lib.entity.PartEntity;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
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

public class StarDriveEntity extends EntityDrive {

    public StarDriveEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);

    }

    @Override
    public void tick() {
        super.tick();

    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity targetEntity = entityHitResult.getEntity();
        double i = this.getDamage();
        if (this.getIsCritical()) {
            i += this.random.nextDouble();
        }

        Entity shooter = this.getShooter();
        DamageSource damagesource;
        if (shooter == null) {
            damagesource = new DamageSource(this.level().registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.WITHER), this, this);
        } else {
            damagesource = new DamageSource(shooter.level().registryAccess()
                    .registryOrThrow(Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.WITHER), this, shooter);
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
            targetEntity.igniteForSeconds(5);
        }

        targetEntity.invulnerableTime = 0;
        float damageValue = (float) i;
        Entity var9 = this.getOwner();
        if (var9 instanceof LivingEntity living) {
            damageValue = (float) ((double) damageValue * CapabilitySlashBlade.getBladeState(living.getMainHandItem())
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
                if (this.level() instanceof ServerLevel serverLevel && shooter instanceof LivingEntity) {
                    EnchantmentHelper.doPostAttackEffects(serverLevel, targetLivingEntity, damagesource);
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
