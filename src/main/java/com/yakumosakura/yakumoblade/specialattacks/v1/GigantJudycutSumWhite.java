package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.GigantjudgementCut;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.util.RayTraceHelper;
import mods.flammpfeil.slashblade.util.TargetSelector;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class GigantJudycutSumWhite {

    public GigantJudycutSumWhite() {
    }

    public static GigantjudgementCut doJudgementCutJust(LivingEntity user) {
        GigantjudgementCut sa = doJudgementCut(user);
        sa.setDamage(sa.getDamage());
        sa.setIsCritical(true);
        return sa;
    }

    public static GigantjudgementCut doJudgementCutJustEX(LivingEntity user) {
        GigantjudgementCut sa = doJudgementCutEX(user);
        sa.setDamage(6);
        sa.setIsCritical(true);
        sa.getDimensions(Pose.STANDING).scale(10, 10);
        sa.setPos(user.getX(), user.getY(), user.getZ());
        return sa;
    }

    public static GigantjudgementCut doJudgementCutEX(LivingEntity user) {
        Level worldIn = user.level();
        Vec3 eyePos = user.getEyePosition(1.0F);
        double airReach = 5.0;
        double entityReach = 7.0;
        ItemStack stack = user.getMainHandItem();
        var resultPos = CapabilitySlashBlade.BLADESTATE.maybeGet(stack).filter((s) -> s.getTargetEntity(worldIn) != null)
                .map(s -> s.getTargetEntity(worldIn).getEyePosition(1.0F));
        if (resultPos.isEmpty()) {
            Optional<HitResult> raytraceresult = RayTraceHelper.rayTrace(worldIn, user, eyePos, user.getLookAngle(), 5.0, 7.0, (entity) -> {
                return !entity.isSpectator() && entity.isAlive() && entity.isPickable() && entity != user;
            });
            resultPos = raytraceresult.map((rtr) -> {
                Vec3 pos = null;
                HitResult.Type type = rtr.getType();
                switch (type) {
                    case ENTITY:
                        Entity target = ((EntityHitResult) rtr).getEntity();
                        pos = target.position().add(0.0, target.getEyeHeight() / 2.0F, 0.0);
                        break;
                    case BLOCK:
                        Vec3 hitVec = rtr.getLocation();
                        pos = hitVec;
                }

                return pos;
            });
        }

        Vec3 pos = resultPos.orElseGet(() -> eyePos.add(user.getLookAngle().scale(5.0)));
        GigantjudgementCut jc = new GigantjudgementCut(YAEntitiesRegistry.GigantjudgementCuts, worldIn);
        jc.setPos(user.getX(), user.getY(), user.getZ());
        jc.setOwner(user);
        CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent((state) -> {
            jc.setColor(16777215);
            jc.setLifetime(40);
            jc.setDamage(3);
        });
        if (user != null) {
            CapabilityConcentrationRank.RANK_POINT.maybeGet(user).ifPresent((rank) -> {
                jc.setRank(rank.getRankLevel(worldIn.getGameTime()));
            });
        }

        worldIn.addFreshEntity(jc);
        worldIn.playSound(null, jc.getX(), jc.getY(), jc.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.5F, 0.8F / (user.getRandom().nextFloat() * 0.4F + 0.8F));
        return jc;
    }

    public static GigantjudgementCut doJudgementCut(LivingEntity user) {
        Level worldIn = user.level();
        Vec3 eyePos = user.getEyePosition(1.0F);
        double airReach = 5.0;
        double entityReach = 7.0;
        ItemStack stack = user.getMainHandItem();
        var resultPos = CapabilitySlashBlade.BLADESTATE.maybeGet(stack).filter((s) -> s.getTargetEntity(worldIn) != null)
                .map(s -> s.getTargetEntity(worldIn).getEyePosition(1.0F));
        if (!resultPos.isPresent()) {
            Optional<HitResult> raytraceresult = RayTraceHelper.rayTrace(worldIn, user, eyePos, user.getLookAngle(), 5.0, 7.0, (entity) -> {
                return !entity.isSpectator() && entity.isAlive() && entity.isPickable() && entity != user;
            });
            resultPos = raytraceresult.map((rtr) -> {
                Vec3 pos = null;
                HitResult.Type type = rtr.getType();
                switch (type) {
                    case ENTITY:
                        Entity target = ((EntityHitResult) rtr).getEntity();
                        pos = target.position().add(0.0, target.getEyeHeight() / 2.0F, 0.0);
                        break;
                    case BLOCK:
                        Vec3 hitVec = rtr.getLocation();
                        pos = hitVec;
                }

                return pos;
            });
        }

        Vec3 pos = resultPos.orElseGet(() -> {
            return eyePos.add(user.getLookAngle().scale(5.0));
        });
        GigantjudgementCut jc = new GigantjudgementCut(YAEntitiesRegistry.GigantjudgementCuts, worldIn);
        jc.setPos(pos.x, pos.y, pos.z);
        jc.setOwner(user);
        CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent((state) -> {
            jc.setColor(16777215);
            jc.setLifetime(15);
            jc.setDamage(3);
        });
        if (user != null) {
            CapabilityConcentrationRank.RANK_POINT.maybeGet(user).ifPresent((rank) -> {
                jc.setRank(rank.getRankLevel(worldIn.getGameTime()));
            });
        }

        worldIn.addFreshEntity(jc);
        worldIn.playSound(null, jc.getX(), jc.getY(), jc.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.5F, 0.8F / (user.getRandom().nextFloat() * 0.4F + 0.8F));
        return jc;
    }

    public static void doJudgementCutSuper(LivingEntity owner) {
        doJudgementCutSuper(owner, null);
    }

    public static void doJudgementCutSuper(LivingEntity owner, List<Entity> exclude) {
        Level level = owner.level();
        ItemStack stack = owner.getMainHandItem();
        List<Entity> founds = TargetSelector.getTargettableEntitiesWithinAABB(level, owner, owner.getBoundingBox().inflate(48.0), TargetSelector.getResolvedReach(owner) + 32.0);
        if (exclude != null) {
            founds.removeAll(exclude);
        }

        Iterator var5 = founds.iterator();

        while (var5.hasNext()) {
            Entity entity = (Entity) var5.next();
            if (!(entity instanceof LivingEntity)) {
                founds.remove(entity);
            } else {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 10));
                GigantjudgementCut judgementCut = new GigantjudgementCut(YAEntitiesRegistry.GigantjudgementCuts, level);
                judgementCut.setPos(entity.getX(), entity.getY(), entity.getZ());
                judgementCut.setOwner(owner);
                CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent((state) -> {
                    judgementCut.setColor(state.getColorCode());
                });
                CapabilityConcentrationRank.RANK_POINT.maybeGet(owner).ifPresent((rank) -> {
                    judgementCut.setRank(rank.getRankLevel(level.getGameTime()));
                });
                level.addFreshEntity(judgementCut);
            }
        }

        level.playSound(owner, owner.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

}
