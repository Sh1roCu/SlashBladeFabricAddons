package com.yakumosakura.yakumoblade.entity.exer;

import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SwordRainEntity extends absNeoSummonSword {
    private static final EntityDataAccessor<Boolean> IT_FIRED;
    private static final EntityDataAccessor<Boolean> Forword;
    private static final EntityDataAccessor<Boolean> Vis;
    long fireTime = -1L;
    int ON_GROUND_LIFE_TIME = 20;
    int ticksInGround = 0;
    private float roll = -90.0F;


    @Override
    public float getRoll() {
        return roll;
    }

    @Override
    public void setRoll(float roll) {
        this.roll = roll;
    }


    static {
        IT_FIRED = SynchedEntityData.defineId(SwordRainEntity.class, EntityDataSerializers.BOOLEAN);
        Forword = SynchedEntityData.defineId(SwordRainEntity.class, EntityDataSerializers.BOOLEAN);
        Vis = SynchedEntityData.defineId(SwordRainEntity.class, EntityDataSerializers.BOOLEAN);
    }

    @Override
    public boolean isInvisible() {
        if (this.getEntityData().get(Forword) && !this.getEntityData().get(Vis)) {
            return true;
        }
        return super.isInvisible();
    }

    public void setForward(boolean fboolean) {
        this.getEntityData().set(Forword, fboolean);
    }

    public SwordRainEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPierce((byte) 5);
        CompoundTag compoundtag = this.sb$getPersistentData();
        ListTag listtag = compoundtag.getList("CustomPotionEffects", 9);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 10);
        listtag.add(mobeffectinstance.save());
        this.sb$getPersistentData().put("CustomPotionEffects", listtag);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IT_FIRED, false);
        builder.define(Forword, false);
        builder.define(Vis, false);

    }

    public void doFire() {
        this.getEntityData().set(IT_FIRED, true);
    }

    public boolean itFired() {
        return this.getEntityData().get(IT_FIRED);
    }

    @Override
    public void tick() {
        if (!this.itFired() && this.getVehicle() == null && this.getOwner() != null) {
            this.startRiding(this.getOwner(), true);
        }

        super.tick();
    }

    public void doride() {

        if (this.itFired() && this.fireTime <= (long) this.tickCount) {
            this.faceEntityStandby();
            this.stopRiding();
            this.tickCount = 0;
            if (this.entityData.get(Forword)) {
                Entity sender = getOwner();
                if (sender != null) {
                    this.entityData.set(Vis, true);
                    Vec3 dir = this.getViewVector(0);
                    Level worldIn = sender.level();
                    final Vec3 _center = new Vec3(this.getX(), this.getY(), this.getZ());
                    List<Entity> _entfound = this.level().getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(60 / 2d), a -> true)
                            .stream()
                            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                            .toList();

                    Optional<Entity> entityiterator = _entfound.stream()
                            .filter(e -> e instanceof LivingEntity && ((LivingEntity) e).getHealth() > 0 && e != getOwner() && e != this)
                            .findFirst();
                    Vec3 targetPos = entityiterator.map((e) -> new Vec3(e.getX(), e.getY() + e.getEyeHeight() * 0.5, e.getZ()))
                            .orElseGet(() ->
                            {
                                Vec3 start = sender.getEyePosition(1.0f);
                                Vec3 end = start.add(sender.getLookAngle().scale(40));
                                HitResult result = worldIn.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, sender));
                                return result.getLocation();
                            });
                    Vec3 pos = this.getPosition(0.0f);
                    dir = targetPos.subtract(pos).normalize();
                    this.shoot(dir.x, dir.y, dir.z, 2f, 1f);
                }
            } else {

                Vec3 dir = new Vec3(0.0, -1.0, 0.0);
                this.shoot(dir.x, dir.y, dir.z, 4.0F, 2.0F);
            }


        } else {
            this.setDeltaMovement(Vec3.ZERO);
            // if (this.canUpdate()) {
            this.baseTick();
            // }

            this.faceEntityStandby();
            if (!this.itFired()) {
                int basedelay = 10;
                this.fireTime = this.tickCount + basedelay + this.getDelay();
                this.doFire();
            }

        }
    }

    @Override
    public void rideTick() {


        doride();

    }

    private void faceEntityStandby() {
        this.setPos(this.position());
        this.setRot(this.getYRot(), getRoll());
    }


    public void setSpread(Vec3 basePos) {
        double areaSize = 2.5;
        double offsetX = (this.random.nextDouble() * 2.0 - 1.0) * areaSize;
        double offsetZ = (this.random.nextDouble() * 2.0 - 1.0) * areaSize;
        this.setPos(basePos.x + offsetX, basePos.y, basePos.z + offsetZ);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        Entity targetEntity = p_213868_1_.getEntity();
        if (targetEntity instanceof LivingEntity a) {
            KnockBacks.cancel.action.accept(a);
            StunManager.setStun(a);
            a.invulnerableTime = 0;
        }

        super.onHitEntity(p_213868_1_);
    }

    @Override
    protected void tryDespawn() {
        ++this.ticksInGround;
        if (this.ON_GROUND_LIFE_TIME <= this.ticksInGround) {
            this.burst();
        }

    }


}
