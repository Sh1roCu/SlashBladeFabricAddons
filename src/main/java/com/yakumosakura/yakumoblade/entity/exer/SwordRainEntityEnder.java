package com.yakumosakura.yakumoblade.entity.exer;

import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class SwordRainEntityEnder extends SwordRainEntity {

    private int FireCount = 30;

    public SwordRainEntityEnder(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel serverLevel) {


            serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH,
                    this.getX(), this.getY(), this.getZ(),
                    1,
                    0.3f, 0.1f, 0.3f,
                    0.05f);
        }


    }


    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);

        if (this.level() instanceof ServerLevel serverLevel) {


            serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH,
                    this.getX(), this.getY(), this.getZ(),
                    getFireCount(),
                    0.3f, 0.1f, 0.3f,
                    0.05f);


        }

    }

    public void setFireCount(int fireCount) {
        FireCount = fireCount;
    }

    public int getFireCount() {
        return FireCount;
    }
}
