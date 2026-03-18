package com.yakumosakura.yakumoblade.entity.exer;

import io.github.fabricators_of_create.porting_lib.entity.EntityHooks;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraft.world.phys.Vec3;

/**
 * 实现风暴飞剑的实体类
 * 特性：自动发射、带击退和眩晕效果、动态旋转半径
 */
public class EntityStormSwords extends absNeoSummonSword {
    // 同步字段：标记是否已发射（同螺旋飞剑）
    private static final EntityDataAccessor<Boolean> IT_FIRED;

    public EntityStormSwords(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);

        this.setPierce((byte) 1);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IT_FIRED, false);
    }

    public void doFire() {
        this.getEntityData().set(IT_FIRED, true);
    }

    public boolean itFired() {
        return this.getEntityData().get(IT_FIRED);
    }

    public void rideTick() {
        if (this.itFired()) {
            this.faceEntityStandby();
            Entity target = this.getVehicle();
            this.stopRiding();
            this.tickCount = 0;

            Vec3 dir = this.getViewVector(1.0F);
            if (target != null) {
                dir = target.position().subtract(this.position()).multiply(1.0F, 0.0F, 1.0F).normalize();
            }

            this.shoot(dir.x, dir.y, dir.z, 3.0F, 1.0F);
        } else {


            this.setDeltaMovement(Vec3.ZERO);
            // if (this.canUpdate()) {
            this.baseTick();
            // }

            this.faceEntityStandby();
            if (20 <= this.tickCount) {
                this.doFire();
            }

            if (!this.level().isClientSide()) {
                this.hitCheck();
            }

        }
    }

    private void hitCheck() {
        Vec3 positionVec = this.position();
        Vec3 dirVec = this.getViewVector(1.0F);
        EntityHitResult raytraceresult = null;
        EntityHitResult entityraytraceresult = this.getRayTrace(positionVec, dirVec);
        if (entityraytraceresult != null) {
            raytraceresult = entityraytraceresult;
        }

        if (raytraceresult != null && raytraceresult.getType() == Type.ENTITY) {
            Entity entity = raytraceresult.getEntity();
            Entity entity1 = this.getShooter();
            if (entity instanceof Player && entity1 instanceof Player && !((Player) entity1).canHarmPlayer((Player) entity)) {
                raytraceresult = null;
                EntityHitResult var7 = null;
            }
        }

        if (raytraceresult != null && raytraceresult.getType() == Type.ENTITY && !EntityHooks.onProjectileImpact(this, raytraceresult)) {
            this.onHit(raytraceresult);
            this.resetAlreadyHits();
            this.hasImpulse = true;
        }

    }


    private void faceEntityStandby() {
        long cycle = 5 + this.tickCount;
        long tickOffset = 0L;
        if (this.level().isClientSide()) {
            tickOffset = 1L;
        }

        int ticks = (int) (((long) this.tickCount + tickOffset) % cycle);
        double rotParTick = (double) 360.0F / (double) cycle;
        double offset = this.getDelay();
        double degYaw = ((double) ticks * rotParTick + offset) % (double) 360.0F;
        double yaw = Math.toRadians(degYaw);
        Vec3 dir = new Vec3(0.0F, 0.0F, 1.0F);
        dir = dir.yRot((float) (-yaw));
        dir = dir.normalize().scale(4.0F);
        if (this.getVehicle() != null) {
            dir = dir.add(this.getVehicle().position());
            dir = dir.add(0.0F, (double) this.getVehicle().getEyeHeight() / (double) 2.0F, 0.0F);
        }

        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
        this.setPos(dir);
        this.setRot((float) (-degYaw) - 180.0F, 0.0F);
    }


    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity targetEntity = entityHitResult.getEntity();
        if (targetEntity instanceof LivingEntity) {
            KnockBacks.toss.action.accept((LivingEntity) targetEntity);
            StunManager.setStun((LivingEntity) targetEntity);
        }

        super.onHitEntity(entityHitResult);
    }

    protected void onHitBlock(BlockHitResult blockraytraceresult) {
        this.burst();
    }

    static {
        IT_FIRED = SynchedEntityData.defineId(EntityStormSwords.class, EntityDataSerializers.BOOLEAN);
    }
}
