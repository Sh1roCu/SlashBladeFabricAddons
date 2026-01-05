package com.yakumosakura.yakumoblade.entity;

import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class MagicCircleEntity extends Projectile {
    // 旋转角度和速度控制
    private float rotationAngle = 0.0f;
    private float prevRotationAngle = 0.0f;
    private final float rotationSpeed = 10.0f;
    private static final EntityDataAccessor<Float> LIFE_TIME = SynchedEntityData.defineId(MagicCircleEntity.class, EntityDataSerializers.FLOAT);

    public MagicCircleEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setXRot(90);
    }

    @Override
    public void tick() {
        super.tick();

        // 更新上一帧的旋转角度
        prevRotationAngle = rotationAngle;

        // 更新当前旋转角度
        rotationAngle += rotationSpeed;
        if (rotationAngle >= 360.0f) {
            rotationAngle -= 360.0f;
        }

        // 调用抽象方法，由子类实现具体效果
        doEffect();

        if (this.tickCount > getLifeTime()) {
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LIFE_TIME, 240.0f);
    }

    public float getLifeTime() {
        return this.getEntityData().get(LIFE_TIME);
    }

    public void setLifeTime(float value) {
        this.getEntityData().set(LIFE_TIME, value);
    }

    // 获取当前旋转角度，供渲染器使用
    public float getRotationAngle() {
        return rotationAngle;
    }

    public float getPrevRotationAngle() {
        return prevRotationAngle;
    }

    // 设置自定义的旋转效果
    public void setRotationAngle(float angle) {
        this.rotationAngle = angle;
    }

    // 获取旋转速度
    public float getRotationSpeed() {
        return rotationSpeed;
    }

    // 抽象方法，由子类实现具体效果
    protected abstract void doEffect();
}
