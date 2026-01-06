package com.yakumosakura.yakumoblade.entity.hexgram.old;

import mods.flammpfeil.slashblade.entity.EntityHeavyRainSwords;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class SwordRainEntityFox extends EntityHeavyRainSwords {
    public SwordRainEntityFox(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockraytraceresult) {
        float radius = 4.3f;
        float distance = 2.3f;
        var entity = this;

        Level level = this.level();
        var entities = level.getEntities(entity, AABB.ofSize(this.position(), radius * 2, radius, radius * 2));
        var damageSource = new DamageSource(level.registryAccess()
                .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.MAGIC), this, getOwner());
        for (Entity targetEntity : entities) {
            if (targetEntity instanceof LivingEntity livingEntity) {
                if (targetEntity != getOwner()) {
                    if (targetEntity != this) {
                        livingEntity.hurt(damageSource, (float) getDamage());
                    }
                }
            }
        }
        super.onHitBlock(blockraytraceresult);
    }
}
