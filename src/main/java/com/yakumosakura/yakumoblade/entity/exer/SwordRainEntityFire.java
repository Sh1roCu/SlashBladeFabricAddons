package com.yakumosakura.yakumoblade.entity.exer;

import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class SwordRainEntityFire extends SwordRainEntity {

    private int FireCount = 30;

    public SwordRainEntityFire(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel serverLevel) {


        }


    }


    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);

        if (this.level() instanceof ServerLevel serverLevel) {


        }

    }

    public void setFireCount(int fireCount) {
        FireCount = fireCount;
    }

    public int getFireCount() {
        return FireCount;
    }
}
