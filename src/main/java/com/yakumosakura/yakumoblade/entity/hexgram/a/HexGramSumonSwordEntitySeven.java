package com.yakumosakura.yakumoblade.entity.hexgram.a;

import com.yakumosakura.yakumoblade.entity.exer.absNeoSummonSword;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.entity.IShootable;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.RayTraceHelper;
import mods.flammpfeil.slashblade.util.TargetSelector;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.Optional;
import java.util.stream.Stream;

public class HexGramSumonSwordEntitySeven extends absNeoSummonSword {
    private static final EntityDataAccessor<Boolean> IT_FIRED = SynchedEntityData.defineId(HexGramSumonSwordEntitySeven.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(HexGramSumonSwordEntitySeven.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Vector3f> OFFSET = SynchedEntityData.defineId(HexGramSumonSwordEntitySeven.class, EntityDataSerializers.VECTOR3);
    long fireTime = -1;

    public HexGramSumonSwordEntitySeven(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);

        this.setPierce((byte) 5);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IT_FIRED, false);
        this.entityData.define(SPEED, 3.0F);
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

    public Vec3 getOffset() {
        return new Vec3(this.getEntityData().get(OFFSET));
    }

    @Override
    public void tick() {
        if (!this.itFired() && this.getVehicle() == null && this.getOwner() != null) {
            this.startRiding(this.getOwner(), true);
        }

        super.tick();
    }

    @Override
    public void rideTick() {
        if (this.itFired() && this.fireTime <= (long) this.tickCount) {
            this.faceEntityStandby();
            Entity vehicle = this.getVehicle();
            Vec3 dir = this.getViewVector(0.0F);
            if (!(vehicle instanceof LivingEntity)) {
                this.shoot(dir.x, dir.y, dir.z, this.getSpeed(), 1.0F);
            } else {
                LivingEntity sender = (LivingEntity) this.getVehicle();
                this.stopRiding();
                this.tickCount = 0;
                Level worldIn = sender.level();
                Entity lockTarget = null;
                if (sender instanceof LivingEntity) {
                    lockTarget = CapabilitySlashBlade.BLADESTATE.maybeGet(sender.getMainHandItem()).filter((state) -> state.getTargetEntity(worldIn) != null).map((state) -> state.getTargetEntity(worldIn))
                            .orElse(null);
                }

                Optional<Entity> foundTarget = Stream.of(Optional.ofNullable(lockTarget), RayTraceHelper.rayTrace(sender.level(), sender, sender.getEyePosition(1.0F), sender.getLookAngle(), 12.0F, 12.0F, (e) -> true).filter((r) -> r.getType() == HitResult.Type.ENTITY).filter((r) -> {
                    EntityHitResult er = (EntityHitResult) r;
                    Entity target = er.getEntity();
                    boolean isMatch = true;
                    if (target instanceof LivingEntity) {
                        isMatch = TargetSelector.lockon.test(sender, (LivingEntity) target);
                    }

                    if (target instanceof IShootable) {
                        isMatch = ((IShootable) target).getShooter() != sender;
                    }

                    return isMatch;
                }).map((r) -> ((EntityHitResult) r).getEntity())).filter(Optional::isPresent).map(Optional::get).findFirst();
                Vec3 targetPos = foundTarget.map((e) -> new Vec3(e.getX(), e.getY() + (double) e.getEyeHeight() * (double) 0.5F, e.getZ())).orElseGet(() -> {
                    Vec3 start = sender.getEyePosition(1.0F);
                    Vec3 end = start.add(sender.getLookAngle().scale(40.0F));
                    HitResult result = worldIn.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, sender));
                    return result.getLocation();
                });
                Vec3 pos = this.getPosition(0.0F);
                dir = targetPos.subtract(pos).normalize();
                this.shoot(dir.x, dir.y, dir.z, this.getSpeed(), 1.0F);
                if (sender instanceof ServerPlayer) {
                    ((ServerPlayer) sender).playNotifySound(SoundEvents.ENDER_DRAGON_FLAP, SoundSource.PLAYERS, 1.0F, 1.0F);
                }

            }
        } else {
            this.setDeltaMovement(Vec3.ZERO);
            // if (this.canUpdate()) {
            this.baseTick();
            // }

            this.faceEntityStandby();
            if (!this.itFired() && this.getVehicle() instanceof LivingEntity && this.tickCount >= this.getDelay()) {
                this.fireTime = this.tickCount + this.getDelay();
                this.doFire();
            }

        }
    }

    protected void faceEntityStandby() {
        Vec3 pos = this.getVehicle().position();
        Vec3 offset = this.getOffset();
        if (this.getVehicle() == null) {
            this.doFire();
        } else {
            offset = offset.xRot((float) Math.toRadians(-this.getVehicle().getXRot()));
            offset = offset.yRot((float) Math.toRadians(-this.getVehicle().getYRot()));
            pos = pos.add(offset);
            this.xRotO = this.getXRot();
            this.yRotO = this.getYRot();
            this.setPos(pos);
            this.setRot(-this.getVehicle().getYRot(), -this.getVehicle().getXRot());
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        Entity targetEntity = p_213868_1_.getEntity();
        if (targetEntity instanceof LivingEntity a) {
            KnockBacks.cancel.action.accept(a);
            StunManager.setStun(a);
            a.level().explode(a, a.getX(), a.getY(), a.getZ(), 2.0F, Level.ExplosionInteraction.NONE);
            a.invulnerableTime = 0;
        }

        super.onHitEntity(p_213868_1_);
    }


}
