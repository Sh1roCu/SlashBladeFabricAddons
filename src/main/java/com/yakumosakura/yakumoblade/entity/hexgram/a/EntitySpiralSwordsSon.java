package com.yakumosakura.yakumoblade.entity.hexgram.a;

import io.github.fabricators_of_create.porting_lib.entity.EntityHooks;
import mods.flammpfeil.slashblade.entity.EntitySpiralSwords;
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

public class EntitySpiralSwordsSon extends EntitySpiralSwords {
    // 同步实体数据：标记是否已发射
    private static final EntityDataAccessor<Boolean> IT_FIRED;

    public EntitySpiralSwordsSon(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPierce((byte) 5);
    }

    // 定义同步数据字段
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IT_FIRED, false);
    }

    /**
     * 触发发射状态
     */
    public void doFire() {
        this.getEntityData().set(IT_FIRED, true);
    }

    /**
     * 获取发射状态
     */
    public boolean itFired() {
        return this.getEntityData().get(IT_FIRED);
    }

    // 实体每帧更新逻辑
    public void tick() {
        if (!this.itFired() && this.level().isClientSide() && this.getVehicle() == null) {
            this.startRiding(this.getOwner(), true);
        }

        super.tick();
    }

    // 骑乘状态下的更新逻辑
    public void rideTick() {
        if (this.itFired()) {
            this.faceEntityStandby();
            Entity target = this.getVehicle();
            this.stopRiding();
            this.tickCount = 0;
            Vec3 dir = this.getViewVector(1.0F);
            if (target != null) {
                dir = this.position().subtract(target.position()).multiply(1.0F, 0.0F, 1.0F).normalize();
            }

            this.shoot(dir.x, dir.y, dir.z, 3.0F, 1.0F);
        } else {
            this.setDeltaMovement(Vec3.ZERO);
            // if (this.canUpdate()) {
            this.baseTick();
            // }

            this.faceEntityStandby();
            if (200 < this.tickCount) {
                this.burst();
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

    /**
     * 执行环绕玩家的运动轨迹
     */
    private void faceEntityStandby() {
        // 基础运动参数设置
        long cycle = 30L;          // 完整旋转周期（30游戏刻=1.5秒）
        long tickOffset = 0L;      // 时间偏移量

        // 客户端特殊处理（可能用于动画同步）
        if (this.level().isClientSide()) {
            tickOffset = 1L;
        }

        // 时间计算
        int ticks = (int) ((this.level().getGameTime() + tickOffset) % cycle); // 当前周期内经过的tick数
        double rotParTick = (double) 360.0F / (double) cycle; // 每tick旋转角度（12度/tick）
        double offset = this.getDelay(); // 初始相位偏移
        double degYaw = ((double) ticks * rotParTick + offset) % 360.0F; // 当前总旋转角度

        // 坐标变换
        double yaw = Math.toRadians(degYaw); // 转换为弧度
        Vec3 dir = new Vec3(0.0F, 0.0F, 1.0F); // 初始方向向量（Z轴正方向）
        dir = dir.yRot((float) (-yaw)); // 绕Y轴旋转（创建圆周运动）
        dir = dir.normalize().scale(4.0F); // 标准化后放大2倍（设置旋转半径）

        // 绑定宿主实体
        if (this.getVehicle() != null) { // 如果存在宿主（玩家）
            // 将相对坐标转换为世界坐标
            dir = dir.add(this.getVehicle().position()); // 叠加宿主位置
            dir = dir.add(0.0F, this.getVehicle().getEyeHeight() / 2.0F, 0.0F); // 调整到宿主眼部高度中点
        }

        // 更新实体状态
        this.xRotO = this.getXRot(); // 记录旧X轴旋转
        this.yRotO = this.getYRot(); // 记录旧Y轴旋转
        this.setPos(dir);           // 设置新位置
        this.setRot((float) (-degYaw), 0.0F); // 设置实体朝向（面向圆心）
    }

    // 碰撞处理逻辑
    protected void onHitBlock(BlockHitResult blockraytraceresult) {
        this.burst();
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity targetEntity = entityHitResult.getEntity();
        if (targetEntity instanceof LivingEntity) {
            KnockBacks.cancel.action.accept((LivingEntity) targetEntity);
        }
        super.onHitEntity(entityHitResult);
    }

    static {
        // 定义数据同步字段
        IT_FIRED = SynchedEntityData.defineId(EntitySpiralSwordsSon.class, EntityDataSerializers.BOOLEAN);
    }
}
