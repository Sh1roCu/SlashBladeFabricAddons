package com.yakumosakura.yakumoblade.entity.hexgram.old;

import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntityHeavyRainSwords;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;

import java.util.HashMap;
import java.util.Map;

public class SwordRainEntityDragon2 extends EntityHeavyRainSwords {
    private final Map<Entity, Integer> hitEntites = new HashMap();

    public SwordRainEntityDragon2(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        Entity targetEntity = p_213868_1_.getEntity();
        if (hitEntites.containsKey(targetEntity) && hitEntites.get(targetEntity) > 0) {
            hitEntites.put(targetEntity, hitEntites.get(targetEntity) - 1);
            return;
        }
        hitEntites.put(targetEntity, 10);

        if (targetEntity instanceof LivingEntity) {
            KnockBacks.cancel.action.accept((LivingEntity) targetEntity);
            StunManager.setStun((LivingEntity) targetEntity);
        }

        super.onHitEntity(p_213868_1_);

    }

    @Override
    public void tick() {
        Level level = this.level();
        float radius = 30F;
        if (!level().isClientSide) {

            CompoundTag persistentData = sb$getPersistentData();
            if (persistentData.getInt("tick1") < 10) {
                persistentData.putInt("tick1", persistentData.getInt("tick1") + 1);


            } else this.remove(RemovalReason.DISCARDED);
        }
        if (getOwner() instanceof LivingEntity owner) {
            var entities = level.getEntities(this, AABB.ofSize(this.position(), radius, radius, radius));
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity != owner) {
                        livingEntity.knockback(1, 1, 1);
                        StunManager.setStun(livingEntity, 20);
                        livingEntity.invulnerableTime = 10;
                        var damageSource = new DamageSource(level.registryAccess()
                                .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                                .getHolderOrThrow(DamageTypes.MAGIC), this, owner);
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 10));

                        livingEntity.hurt(damageSource, 30);
                        onHitEntity(new EntityHitResult(livingEntity));

                    }
                }
            }
        }

        super.tick();


        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                    this.getX(), this.getY(), this.getZ(), 2, 2, 2, 2, 2);
        }


    }


}
