package com.yakumosakura.yakumoblade.entity.drive;

import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class BigDriveEntitySun extends BigDriveEnity {
    public BigDriveEntitySun(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();


        float radius = 2F;
        if (getOwner() instanceof Player owner) {
            this.level().getEntitiesOfClass(
                    LivingEntity.class,
                    this.getBoundingBox().inflate(radius)).forEach(livingEntity -> {
                if (livingEntity != owner) {
                    livingEntity.knockback(1, 1, 1);
                    StunManager.setStun(livingEntity, 20);
                    var damageSource = new DamageSource(owner.level().registryAccess()
                            .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(DamageTypes.IN_FIRE), this, owner);
                    for (int i = 0; i < 10; i++) {
                        livingEntity.invulnerableTime = 0;
                        livingEntity.hurt(damageSource, (float) ((float) getDamage() * owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() / 50));
                    }
                    onHitEntity(new EntityHitResult(livingEntity));

                }
            });

        }
    }
}
