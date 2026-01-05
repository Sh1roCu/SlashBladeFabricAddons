package com.yakumosakura.yakumoblade.entity;

import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class VoidSlash extends EntitySlashEffect {
    public VoidSlash(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public void attackEntity(LivingEntity player, LivingEntity target) {
        Level level = player.level();
        var damageSource = new DamageSource(level.registryAccess()
                .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.FELL_OUT_OF_WORLD), this, super.getOwner());

        target.hurt(damageSource, (float) super.getDamage());

    }

    @Override
    protected void onHitEntity(EntityHitResult p_37259_) {
        if (p_37259_.getEntity() instanceof LivingEntity) {
            attackEntity(this.getOwner() instanceof LivingEntity ? (LivingEntity) this.getOwner() : null,
                    (LivingEntity) p_37259_.getEntity());
            super.setDamage(1);
            super.onHitEntity(p_37259_);
        }
    }
}
