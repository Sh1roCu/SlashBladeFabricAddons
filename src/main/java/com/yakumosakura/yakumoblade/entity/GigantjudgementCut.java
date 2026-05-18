package com.yakumosakura.yakumoblade.entity;

import cn.sh1rocu.slashblade.api.extension.EntityExtension;
import cn.sh1rocu.slashblade.util.PotionUtils;
import mods.flammpfeil.slashblade.entity.EntityJudgementCut;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.entity.IShootable;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import mods.flammpfeil.slashblade.util.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class GigantjudgementCut extends EntityJudgementCut implements IShootable {
    private static final EntityDataAccessor<Integer> COLOR;
    private static final EntityDataAccessor<Integer> FLAGS;
    private static final EntityDataAccessor<Float> RANK;
    private int lifetime = 10;
    private int seed = -1;
    private double damage = 1.5;
    private boolean cycleHit = true;
    private final SoundEvent livingEntitySound;
    EnumSet<FlagsState> flags;
    int intFlags;

    @Override
    public int getSeed() {
        return this.seed;
    }

    @Override
    public boolean doCycleHit() {
        return this.cycleHit;
    }

    @Override
    public void setCycleHit(boolean cycleHit) {
        this.cycleHit = cycleHit;
    }

    @Override
    protected SoundEvent getHitEntitySound() {
        return this.livingEntitySound;
    }

    public GigantjudgementCut(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.livingEntitySound = SoundEvents.WITHER_HURT;
        this.flags = EnumSet.noneOf(FlagsState.class);
        this.intFlags = 0;
        this.setNoGravity(true);
        this.seed = this.random.nextInt(360);

    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(COLOR, 3355647);
        builder.define(FLAGS, 0);
        builder.define(RANK, 0.0F);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        NBTHelper.getNBTCoupler(compound).put("Color", new Integer[]{this.getColor()}).put("Rank", new Float[]{this.getRank()}).put("damage", new Double[]{this.damage}).put("crit", new Boolean[]{this.getIsCritical()}).put("clip", new Boolean[]{this.isNoClip()}).put("Lifetime", this.getLifetime());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        NBTHelper.getNBTCoupler(compound).get("Color", this::setColor, new Integer[0]).get("Rank", this::setRank, new Float[0]).get("damage", (v) -> {
            this.damage = v;
        }, new Double[]{this.damage}).get("crit", this::setIsCritical, new Boolean[0]).get("clip", this::setNoClip, new Boolean[0]).get("Lifetime", this::setLifetime);
    }

    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        this.setDeltaMovement(0.0, 0.0, 0.0);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        double d0 = this.getBoundingBox().getSize() * 10.0;
        if (Double.isNaN(d0)) {
            d0 = 1.0;
        }

        d0 = d0 * 64.0 * getViewScale();
        return distance < d0 * d0;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements) {
        this.setPos(x, y, z);
        this.setRot(yaw, pitch);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void lerpMotion(double x, double y, double z) {
        this.setDeltaMovement(0.0, 0.0, 0.0);
    }

    private void setFlags(FlagsState value) {
        this.flags.add(value);
        this.refreshFlags();
    }

    private void removeFlags(FlagsState value) {
        this.flags.remove(value);
        this.refreshFlags();
    }

    private void refreshFlags() {
        int newValue;
        if (this.level().isClientSide()) {
            newValue = this.entityData.get(FLAGS);
            if (this.intFlags != newValue) {
                this.intFlags = newValue;
                this.flags = EnumSetConverter.convertToEnumSet(FlagsState.class, this.intFlags);
            }
        } else {
            newValue = EnumSetConverter.convertToInt(this.flags);
            if (this.intFlags != newValue) {
                this.entityData.set(FLAGS, newValue);
                this.intFlags = newValue;
            }
        }

    }

    @Override
    public void setIsCritical(boolean value) {
        if (value) {
            this.setFlags(FlagsState.Critical);
        } else {
            this.removeFlags(FlagsState.Critical);
        }

    }

    @Override
    public boolean getIsCritical() {
        this.refreshFlags();
        return this.flags.contains(FlagsState.Critical);
    }

    @Override
    public void setNoClip(boolean value) {
        this.noPhysics = value;
        if (value) {
            this.setFlags(FlagsState.NoClip);
        } else {
            this.removeFlags(FlagsState.NoClip);
        }

    }

    @Override
    public boolean isNoClip() {
        if (!this.level().isClientSide()) {
            return this.noPhysics;
        } else {
            this.refreshFlags();
            return this.flags.contains(FlagsState.NoClip);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity targetEntity = result.getEntity();
        if (targetEntity instanceof LivingEntity e) {
            e.invulnerableTime = 0;
        }

        super.onHitEntity(result);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount < 8 && this.tickCount % 2 == 0) {
            this.playSound(this.getHitEntitySound(), 0.2F, 0.5F + 0.25F * this.random.nextFloat());
        }

        if (this.getShooter() != null) {
            if (this.tickCount % 2 == 0) {
                KnockBacks knockBackType = this.getIsCritical() ? KnockBacks.toss : KnockBacks.cancel;
                AttackManager.areaAttack(this, knockBackType.action, 4.0, this.doCycleHit(), false);
            }

            if (this.getIsCritical() && 0 < this.tickCount && this.tickCount <= 3) {
                EntitySlashEffect jc = new EntitySlashEffect(SBEntityTypes.SLASH_EFFECT, this.level());
                jc.absMoveTo(this.getX(), this.getY(), this.getZ(), 120.0F * (float) this.tickCount + (float) this.seed, 0.0F);
                jc.setRotationRoll(30.0F);
                jc.setOwner(this.getShooter());
                jc.setMute(false);
                jc.setIsCritical(true);
                jc.setDamage(0.10000000149011612);
                jc.setColor(this.getColor());
//                jc.setBaseSize(0.5F);
                jc.setKnockBack(KnockBacks.cancel);
                jc.setIndirect(true);
                jc.setRank(this.getRank());
                jc.setBaseSize(3000);
                this.level().addFreshEntity(jc);
            }
        }

        this.tryDespawn();
    }

    @Override
    protected void tryDespawn() {
        if (!this.level().isClientSide() && this.getLifetime() < this.tickCount) {
            this.burst();
        }

    }

    @Override
    public int getColor() {
        return this.getEntityData().get(COLOR);
    }

    @Override
    public void setColor(int value) {
        this.getEntityData().set(COLOR, value);
    }

    @Override
    public float getRank() {
        return this.getEntityData().get(RANK);
    }

    @Override
    public void setRank(float value) {
        this.getEntityData().set(RANK, value);
    }

    @Override
    public int getLifetime() {
        return Math.min(this.lifetime, 1000);
    }

    @Override
    public void setLifetime(int value) {
        this.lifetime = value;
    }

    @Nullable
    public Entity getShooter() {
        return this.getOwner();
    }

    @Override
    public void setShooter(Entity shooter) {
        this.setOwner(shooter);
    }

    @Override
    public List<MobEffectInstance> getPotionEffects() {
        List<MobEffectInstance> effects = PotionUtils.getAllEffects(((EntityExtension) this).sb$getPersistentData());
        if (effects.isEmpty()) {
            effects.add(new MobEffectInstance(MobEffects.POISON, 1, 1));
        }

        return effects;
    }

    @Override
    public void burst() {
        if (!this.level().isClientSide()) {
            if (this.level() instanceof ServerLevel) {
                ((ServerLevel) this.level()).sendParticles(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 16, 0.5, 0.5, 0.5, 0.25);
            }

            this.burst(this.getPotionEffects(), null);
        }

        super.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void burst(List<MobEffectInstance> effects, @Nullable Entity focusEntity) {
        List<Entity> list = TargetSelector.getTargettableEntitiesWithinAABB(this.level(), 2.0, this);
        list.stream().filter((e) -> {
            return e instanceof LivingEntity;
        }).map((e) -> {
            return (LivingEntity) e;
        }).forEach((e) -> {
            double distanceSq = this.distanceToSqr(e);
            if (distanceSq < 9.0) {
                double factor = 1.0 - Math.sqrt(distanceSq) / 4.0;
                if (e == focusEntity) {
                    factor = 1.0;
                }

                this.affectEntity(e, effects, factor);
            }

        });
    }

    @Override
    public void affectEntity(LivingEntity focusEntity, List<MobEffectInstance> effects, double factor) {
        Iterator var5 = this.getPotionEffects().iterator();

        while (var5.hasNext()) {
            MobEffectInstance effectinstance = (MobEffectInstance) var5.next();
            Holder<MobEffect> effect = effectinstance.getEffect();
            if (effect.value().isInstantenous()) {
                effect.value().applyInstantenousEffect(this, this.getShooter(), focusEntity, effectinstance.getAmplifier(), factor);
            } else {
                int duration = (int) (factor * (double) effectinstance.getDuration() + 0.5);
                if (duration > 0) {
                    focusEntity.addEffect(new MobEffectInstance(effect, duration, effectinstance.getAmplifier(), effectinstance.isAmbient(), effectinstance.isVisible()));
                }
            }
        }

    }

    @Override
    public void setDamage(double damageIn) {
        this.damage = damageIn;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Nullable
    @Override
    public EntityHitResult getRayTrace(Vec3 p_213866_1_, Vec3 p_213866_2_) {
        return ProjectileUtil.getEntityHitResult(this.level(), this, p_213866_1_, p_213866_2_, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0), (p_213871_1_) -> {
            return !p_213871_1_.isSpectator() && p_213871_1_.isAlive() && p_213871_1_.isPickable() && p_213871_1_ != this.getShooter();
        });
    }

    static {
        COLOR = SynchedEntityData.defineId(GigantjudgementCut.class, EntityDataSerializers.INT);
        FLAGS = SynchedEntityData.defineId(GigantjudgementCut.class, EntityDataSerializers.INT);
        RANK = SynchedEntityData.defineId(GigantjudgementCut.class, EntityDataSerializers.FLOAT);
    }

    enum FlagsState {
        Critical,
        NoClip;

        FlagsState() {
        }
    }
}
