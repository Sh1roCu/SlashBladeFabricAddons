package com.yakumosakura.yakumoblade.entity.exer;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import io.github.fabricators_of_create.porting_lib.entity.EntityHooks;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraft.world.phys.Vec3;

public class EntitySpiralSwords extends absNeoSummonSword {
    // 同步实体数据：标记是否已发射
    private static final EntityDataAccessor<Boolean> IT_FIRED;
    private int lastHitTick = 0; // 记录上次造成伤害的时间
    private static final int HIT_COOLDOWN = 10; // 冷却时间（20 ticks = 1秒）

    public EntitySpiralSwords(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
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
        if (getOwner() instanceof LivingEntity playerIn) {
            if (!(playerIn.getMainHandItem().getItem() instanceof ItemSlashBlade)) {
                return;
            }
            if (!(SlashBladeUtil.hasSpecialEffect(playerIn.getMainHandItem(), YASpecialEffectsRegistry.FoxSoulBlue))) {
            }


        }

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

            if (super.tickCount > 5 && this.tickCount - lastHitTick >= HIT_COOLDOWN) {
                lastHitTick = this.tickCount;

                float radius = 10F;
                if (getOwner() instanceof Player owner) {
                    owner.level().getEntitiesOfClass(
                            LivingEntity.class,
                            owner.getBoundingBox().inflate(radius)).forEach(livingEntity -> {
                        if (livingEntity != owner) {
                            livingEntity.knockback(1, 1, 1);
                            StunManager.setStun(livingEntity, 20);
                            var damageSource = new DamageSource(owner.level().registryAccess()
                                    .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                                    .getHolderOrThrow(DamageTypes.MAGIC), this, owner);

                            livingEntity.hurt(damageSource, (float) getDamage());
                            onHitEntity(new EntityHitResult(livingEntity));

                        }
                    });


                }

            }


            this.setDeltaMovement(Vec3.ZERO);
            // if (this.canUpdate()) {
            this.baseTick();
            // }

            this.faceEntityStandby();
            if (2000 < this.tickCount) {
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
        long cycle = 30L;
        long tickOffset = 0L;
        if (this.level().isClientSide()) {
            tickOffset = 1L;
        }

        int ticks = (int) ((this.level().getGameTime() + tickOffset) % cycle);
        double rotParTick = (double) 360.0F / (double) cycle;
        double offset = this.getDelay();
        double degYaw = ((double) ticks * rotParTick + offset) % (double) 360.0F;
        double yaw = Math.toRadians(degYaw);
        Vec3 dir = new Vec3(0.0F, 0.0F, 1.0F);
        dir = dir.yRot((float) (-yaw));
        dir = dir.normalize().scale(2.0F);
        if (this.getVehicle() != null) {
            dir = dir.add(this.getVehicle().position());
            dir = dir.add(0.0F, (double) this.getVehicle().getEyeHeight() / (double) 2.0F, 0.0F);
        }

        this.xRotO = this.getXRot();
        this.yRotO = this.getYRot();
        this.setPos(dir);
        this.setRot((float) (-degYaw), 0.0F);
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
        IT_FIRED = SynchedEntityData.defineId(EntitySpiralSwords.class, EntityDataSerializers.BOOLEAN);
    }
}
