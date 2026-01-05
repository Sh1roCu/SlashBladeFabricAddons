package com.yakumosakura.yakumoblade.entity.hexgram.old;

import com.yakumosakura.yakumoblade.entity.exer.SwordRainEntity;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class StarEntityDragon extends SwordRainEntity {
    private static final EntityDataAccessor<Boolean> IT_FIRED = SynchedEntityData.defineId(StarEntityDragon.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(StarEntityDragon.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Vector3f> OFFSET = SynchedEntityData.defineId(StarEntityDragon.class, EntityDataSerializers.VECTOR3);

    public StarEntityDragon(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPierce((byte) 5);
        this.noCulling = true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(IT_FIRED, false);
        this.entityData.define(SPEED, 3.0f);
        this.entityData.define(OFFSET, Vec3.ZERO.toVector3f());
    }

    public void doFire() {
        this.getEntityData().set(IT_FIRED, true);
    }

    public boolean itFired() {
        return this.getEntityData().get(IT_FIRED);
    }

    public void setSpeed(float speed) {
        this.getEntityData().set(SPEED, speed);
    }

    public float getSpeed() {
        return this.getEntityData().get(SPEED);
    }

    public void setOffset(Vec3 offset) {
        this.getEntityData().set(OFFSET, offset.toVector3f());
    }

    @Override
    public void baseTick() {

        if (!level().isClientSide) {
            CompoundTag persistentData = sb$getPersistentData();

            if (persistentData.getInt("tick1") < 120) {
                persistentData.putInt("tick1", persistentData.getInt("tick1") + 1);

            } else this.remove(RemovalReason.DISCARDED);
        }
        super.baseTick();
    }

    @Override
    public void tick() {


        // 自旋转逻辑（绕Y轴旋转）
        float rotationSpeed = 0.1f; // 每秒旋转速度（度/tick）
        this.setYRot(this.getYRot() + rotationSpeed);

        // 保持角度在0-360之间
        if (this.getYRot() >= 360) {
            this.setYRot(0);
        }
        // 保持原有的XRot设置
        super.tick();

        if (super.tickCount > 3 * 20) {
            this.discard();
        }
    }

    @Override
    public void rideTick() {
        super.rideTick();
    }


}
