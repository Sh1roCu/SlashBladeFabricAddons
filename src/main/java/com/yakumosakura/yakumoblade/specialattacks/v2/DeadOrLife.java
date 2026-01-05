package com.yakumosakura.yakumoblade.specialattacks.v2;

import com.yakumosakura.yakumoblade.entity.touhou.YukariEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Random;
import java.util.WeakHashMap;

public class DeadOrLife {
    // 用于跟踪每个结界的位置和创建时间
    private static final WeakHashMap<BlockPos, Long> activeCircles = new WeakHashMap<>();
    private static final Random random = new Random();

    public static void doSlash(LivingEntity playerIn) {
        Level level = playerIn.level();
        BlockPos circlePos = playerIn.blockPosition();

        // 检查是否已有活跃结界
        if (activeCircles.containsKey(circlePos)) {
            long createTime = activeCircles.get(circlePos);
            // 如果结界还在30秒有效期内，则不创建新结界
            if (level.getGameTime() - createTime < 600) { // 30秒 = 600 ticks (20 ticks/秒)
                return;
            }
        }
        YukariEntity yukari = new YukariEntity(YAEntitiesRegistry.Yukari, playerIn.level());
        yukari.setOwner(playerIn);
        yukari.setLifeTime(600);
        yukari.setPos(playerIn.getX(), playerIn.getY() + 0.01, playerIn.getZ());
        playerIn.level().addFreshEntity(yukari);
        // 记录结界创建时间和位置
        activeCircles.put(circlePos, level.getGameTime());

        // 只在服务器端执行效果和状态施加
        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;

            // 生成六芒星结界
            generateHexagram(serverLevel, circlePos);

            // 开始持续效果检测和粒子生成
            startCircleEffects(serverLevel, circlePos, playerIn);
        }
    }

    private static void generateHexagram(ServerLevel level, BlockPos centerPos) {
        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI * i / 6;
            double x = centerPos.getX() + 0.5 + 10 * Math.cos(angle);
            double z = centerPos.getZ() + 0.5 + 10 * Math.sin(angle);


            createParticleLine(level, centerPos.getX() + 0.5, centerPos.getY(), centerPos.getZ() + 0.5, x, centerPos.getY(), z);


            if (i % 2 == 0) {
                int oppositeIndex = (i + 3) % 6;
                double oppX = centerPos.getX() + 0.5 + 10 * Math.cos(2 * Math.PI * oppositeIndex / 6);
                double oppZ = centerPos.getZ() + 0.5 + 10 * Math.sin(2 * Math.PI * oppositeIndex / 6);
                createParticleLine(level, x, centerPos.getY(), z, oppX, centerPos.getY(), oppZ);
            }
        }


    }

    private static void createParticleLine(ServerLevel level, double x1, double y1, double z1, double x2, double y2, double z2) {
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        int particles = (int) (distance * 5); // 每格距离生成5个粒子

        for (int i = 0; i <= particles; i++) {
            double ratio = (double) i / particles;
            double x = Mth.lerp(ratio, x1, x2);
            double z = Mth.lerp(ratio, z1, z2);
            level.sendParticles(ParticleTypes.ENCHANT, x, y1 + 0.1, z, 1, 0, 0, 0, 0);
        }
    }

    private static void startCircleEffects(ServerLevel level, BlockPos centerPos, LivingEntity caster) {
        // 创建一个任务来持续更新结界效果
        new Thread(() -> {
            long startTime = level.getGameTime();

            while (level.getGameTime() - startTime < 600) { // 持续30秒
                try {
                    Thread.sleep(50); // 每tick更新一次 (20 ticks/秒)
                } catch (InterruptedException e) {
                    break;
                }

                // 在主线程执行游戏操作
                level.getServer().execute(() -> {
                    if (level.getGameTime() - startTime >= 600) {
                        activeCircles.remove(centerPos);
                        return;
                    }

                    double rotation = (level.getGameTime() - startTime) * 2 * Math.PI / 100; // 旋转角度

                    // 生成旋转的六芒星和粒子效果
                    generateRotatingHexagram(level, centerPos, rotation);

                    // 生成樱花和末地烛粒子
                    generateDecorationParticles(level, centerPos);

                    // 检测范围内的实体并施加效果
                    applyEffectsToEntities(level, centerPos, caster);
                });
            }

            activeCircles.remove(centerPos);
        }).start();
    }

    private static void generateRotatingHexagram(ServerLevel level, BlockPos centerPos, double rotation) {
        // 生成旋转的六芒星
        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI * i / 6 + rotation;
            double x = centerPos.getX() + 0.5 + 10 * Math.cos(angle);
            double z = centerPos.getZ() + 0.5 + 10 * Math.sin(angle);

            // 创建顶点粒子效果
            level.sendParticles(ParticleTypes.ENCHANT, x, centerPos.getY() + 0.1, z, 3, 0, 0, 0, 0.1);

            // 创建六芒星的内部连线
            if (i % 2 == 0) {
                int oppositeIndex = (i + 3) % 6;
                double oppAngle = 2 * Math.PI * oppositeIndex / 6 + rotation;
                double oppX = centerPos.getX() + 0.5 + 10 * Math.cos(oppAngle);
                double oppZ = centerPos.getZ() + 0.5 + 10 * Math.sin(oppAngle);

                createParticleLine(level, x, centerPos.getY() + 0.1, z, oppX, centerPos.getY() + 0.1, oppZ);
            }
        }

        // 生成圆形边界
        for (int i = 0; i < 36; i++) { // 增加粒子数量以适应更大的半径
            double angle = 2 * Math.PI * i / 36 + rotation;
            double x = centerPos.getX() + 0.5 + 10 * Math.cos(angle);
            double z = centerPos.getZ() + 0.5 + 10 * Math.sin(angle);
            level.sendParticles(ParticleTypes.ENCHANT, x, centerPos.getY() + 0.1, z, 1, 0, 0, 0, 0);
        }
    }

    private static void generateDecorationParticles(ServerLevel level, BlockPos centerPos) {
        // 生成樱花粒子
        for (int i = 0; i < 15; i++) { // 增加粒子数量以适应更大的半径
            double x = centerPos.getX() + 0.5 + (random.nextDouble() * 20 - 10);
            double z = centerPos.getZ() + 0.5 + (random.nextDouble() * 20 - 10);
            double y = centerPos.getY() + random.nextDouble() * 2;
            level.sendParticles(ParticleTypes.CHERRY_LEAVES, x, y, z, 1, 0, 0, 0, 0.1);
        }

        // 生成末地烛粒子
        for (int i = 0; i < 8; i++) { // 增加粒子数量以适应更大的半径
            double x = centerPos.getX() + 0.5 + (random.nextDouble() * 20 - 10);
            double z = centerPos.getZ() + 0.5 + (random.nextDouble() * 20 - 10);
            double y = centerPos.getY() + random.nextDouble() * 2;
            level.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 1, 0, 0, 0, 0.1);
        }
    }

    private static void applyEffectsToEntities(ServerLevel level, BlockPos centerPos, LivingEntity caster) {
        // 定义结界的AABB范围 (半径10格，高度3格)
        AABB effectArea = new AABB(
                centerPos.getX() - 10, centerPos.getY() - 1, centerPos.getZ() - 10,
                centerPos.getX() + 10, centerPos.getY() + 2, centerPos.getZ() + 10
        );

        // 获取范围内的所有实体
        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, effectArea);

        for (LivingEntity entity : entities) {
            // 对敌人施加负面效果
            if ((entity instanceof Mob && !entity.isAlliedTo(caster)) || (!(caster instanceof Player) && !entity.isAlliedTo(caster))) {
                entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 0)); // 15秒黑暗I
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0)); // 15秒反胃I
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 0)); // 15秒缓慢I
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 300, 1)); // 15秒虚弱II
            }
            // 对玩家和驯服实体施加正面效果
            else if (entity == caster || entity.isAlliedTo(caster)) {
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 120, 1)); // 6秒生命恢复II
                entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 120, 1)); // 6秒跳跃提升II
                entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0)); // 6秒缓降I
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, 0)); // 6秒力量I
            }
        }
    }
}