package com.yakumosakura.yakumoblade.entity.hexgram.old;

import com.exfantasycode.mclib.Utils.RandomUtil.RandomNumber;
import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class StarEntityFox extends EntityAbstractSummonedSword {
    private static final EntityDataAccessor<Boolean> IT_FIRED = SynchedEntityData.defineId(StarEntityFox.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(StarEntityFox.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Vector3f> OFFSET = SynchedEntityData.defineId(StarEntityFox.class, EntityDataSerializers.VECTOR3);

    public StarEntityFox(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPierce((byte) 5);
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
    public void tick() {
        super.tick();
        if (RandomNumber.randomint(super.getDelay() * 2) > super.getDelay()) {
            this.kill();
        }


        this.setXRot(10);
    }

    @Override
    public void rideTick() {
    }


}
