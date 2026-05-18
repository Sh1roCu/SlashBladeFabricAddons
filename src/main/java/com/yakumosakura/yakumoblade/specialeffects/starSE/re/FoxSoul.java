package com.yakumosakura.yakumoblade.specialeffects.starSE.re;

import cn.sh1rocu.slashblade.api.extension.EntityExtension;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.specialeffects.utils.SeEX;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class FoxSoul extends SeEX {

    public FoxSoul() {
        super(30);
    }


    private static class Config {
        static final int DAY_THRESHOLD = 3;
        static final int NIGHT_THRESHOLD = 2;
        static final int COOLDOWN_TICKS = 0;
        static final float DRIVE_SPAWN_CHANCE = 0.1f;
        static final int FIRE_DURATION = 30;
        static final int MAX_PARTICLES = 100;
        static final String HEAT_MARK_KEY = "YakumoBlade.HeatMark";
        static final String LAST_TICK_KEY = HEAT_MARK_KEY + "_LastTick";
    }


    public static void onSlashHit(SlashBladeEvent.HitEvent event) {
        Player player = validateEvent(event);
        if (player == null || !validTarget(event.getTarget())) return;

        processHeatMark(event.getTarget(), event.getSlashBladeState(), player);
    }

    private static Player validateEvent(SlashBladeEvent.HitEvent event) {
        if (!(event.getUser() instanceof Player player)) return null;
        if (hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.Foxsoul)) return player;
        if (hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.FoxsoulEX)) return player;
        return null;
    }

    private static boolean validTarget(Entity target) {
        return target instanceof LivingEntity && target != null;
    }

    private static void processHeatMark(Entity target, ISlashBladeState state, Player player) {
        CompoundTag data = ((EntityExtension) target).sb$getPersistentData();
        long currentTick = target.tickCount;

        if (isOnCooldown(data, currentTick)) return;

        int stacks = updateHeatStacks(data, target);
        if (shouldTriggerEffect(stacks, target)) {
            triggerCombinedEffects(player, target, state, data, currentTick);
        }
    }
    // endregion

    // region 效果实现
    private static void triggerCombinedEffects(Player player, Entity target, ISlashBladeState state,
                                               CompoundTag data, long currentTick) {
        float damage = calculateDamage(state);

        executeAsyncEffect(target, () -> {
            applyFireEffect(target, damage);
            trySpawnDrives(player, target);
        });

        resetHeatData(data, currentTick);
        target.save(data);
    }

    private static float calculateDamage(ISlashBladeState state) {
        return state.getBaseAttackModifier() * 3;
    }

    private static void executeAsyncEffect(Entity target, Runnable effect) {
        if (target.level().getServer() != null) {
            target.level().getServer().execute(() -> {
                if (target.isAlive()) effect.run();
            });
        }
    }

    private static void applyFireEffect(Entity target, float damage) {
        target.igniteForSeconds(Config.FIRE_DURATION);
        target.hurt(target.damageSources().onFire(), damage);

        if (target.level() instanceof ServerLevel serverLevel) {
            int particleCount = Math.min((int) (damage + 30), Config.MAX_PARTICLES);
            serverLevel.sendParticles(ParticleTypes.FLAME,
                    target.getX(), target.getY() + 1, target.getZ(),
                    particleCount, 0.5, 0.5, 0.5, 0.1);
        }
    }

    private static void trySpawnDrives(Player player, Entity target) {
        if (player.hasEffect(MobEffects.DAMAGE_BOOST) &&
                player.getRandom().nextFloat() < Config.DRIVE_SPAWN_CHANCE) {
            spawnDirectionalDrives(player, target);
        }
    }
    // endregion

    // region 数据管理
    private static boolean isOnCooldown(CompoundTag data, long currentTick) {
        if (Config.COOLDOWN_TICKS > 0) {
            data.getLong(Config.LAST_TICK_KEY);
        }
        return false;
    }

    private static int updateHeatStacks(CompoundTag data, Entity target) {
        int stacks = Math.max(data.getInt(Config.HEAT_MARK_KEY), 0) + 1;
        int required = isNight(target) ? Config.NIGHT_THRESHOLD : Config.DAY_THRESHOLD;
        stacks = Math.min(stacks, required);

        data.putInt(Config.HEAT_MARK_KEY, stacks);
        return stacks;
    }

    private static boolean shouldTriggerEffect(int stacks, Entity target) {
        int required = isNight(target) ? Config.NIGHT_THRESHOLD : Config.DAY_THRESHOLD;
        return stacks >= required;
    }

    private static void resetHeatData(CompoundTag data, long currentTick) {
        data.putInt(Config.HEAT_MARK_KEY, 0);
        data.putLong(Config.LAST_TICK_KEY, currentTick);
    }

    private static boolean isNight(Entity entity) {
        Level level = entity != null ? entity.level() : null;
        return level != null && !level.isDay();
    }
    // endregion

    // region 驱动实体生成
    private static void spawnDirectionalDrives(Player player, Entity target) {
        for (int i = 0; i < 4; i++) {
            EntityDrive drive = createDriveEntity(player, target, i);
            if (drive == null) continue;

            configureDriveMovement(drive, target);
            setupDriveCombat(drive, player);
            syncConcentrationRank(drive, player);

            player.level().addFreshEntity(drive);
        }
    }

    private static EntityDrive createDriveEntity(Player player, Entity target, int angleIndex) {
        if (SBEntityTypes.DRIVE == null) return null;

        Vec3 spawnPos = calculateSpawnPosition(target, angleIndex);
        return new EntityDrive(SBEntityTypes.DRIVE, player.level());
    }

    private static Vec3 calculateSpawnPosition(Entity target, int angleIndex) {
        float angle = angleIndex * 90.0F;
        Vec3 basePos = target.position().add(0, target.getBbHeight() / 2, 0);
        Vec3 direction = VectorHelper.getVectorForRotation(0, angle)
                .normalize().scale(3.0);
        return basePos.add(direction);
    }

    private static void configureDriveMovement(EntityDrive drive, Entity target) {
        Vec3 targetDir = target.position().subtract(drive.position()).normalize();
        drive.shoot(targetDir.x, targetDir.y, targetDir.z, 1.5F, 0.0F);
    }

    private static void setupDriveCombat(EntityDrive drive, Player player) {
        drive.setDamage(0.5F);
        drive.setSpeed(1.5F);
        drive.setOwner(player);
        drive.setColor(0xFF4500);
        drive.setLifetime(20);
    }

    private static void syncConcentrationRank(EntityDrive drive, Player player) {
        CapabilityConcentrationRank.RANK_POINT.maybeGet(player)
                .ifPresent(rank -> drive.setRank(rank.getRankLevel(player.level().getGameTime())));
    }


    public static void onUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.Foxsoul)) {
            if (player.level() instanceof ServerLevel serverLevel && isNight(player)) {
                spawnNightParticles(serverLevel, player);
            }
        }
        if (hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.FoxsoulEX)) {
            if (player.level() instanceof ServerLevel serverLevel && isNight(player)) {
                spawnNightParticles(serverLevel, player);
            }
        }
    }

    private static void spawnNightParticles(ServerLevel level, Player player) {
        Vec3 backPos = calculateBackPosition(player);
        level.sendParticles(ParticleTypes.FLAME,
                backPos.x, backPos.y, backPos.z,
                1, 0.3f, 0.1f, 0.3f, 0.05f);
    }

    private static Vec3 calculateBackPosition(Player player) {
        return player.position()
                .add(0, 1.2, 0)
                .add(player.getLookAngle().reverse().scale(0.5));
    }


}
