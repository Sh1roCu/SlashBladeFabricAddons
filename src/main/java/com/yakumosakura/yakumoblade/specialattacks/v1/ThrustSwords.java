package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.exer.ThrustEdgeEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ThrustSwords {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;
        Level worldIn = playerIn.level();

        int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
        int count = 100;
        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent
                (
                        (state) ->
                        {
                            for (int i = 0; i < count; i++) {
                                ThrustEdgeEntity ss = new ThrustEdgeEntity(YAEntitiesRegistry.soul_edge, worldIn);

                                worldIn.addFreshEntity(ss);

                                ss.setSpeed(speed);
                                ss.setIsCritical(critical);
                                ss.setOwner(playerIn);

                                ss.setColor(colorCode);


                                ss.setRoll(0);
                                ss.setDamage(damage);
                                // force riding
                                ss.startRiding(playerIn, true);
                                ss.setDelay(20 + i);

                                double yOffset = i * 0.005 + 0.5;
                                double zOffset = -1.0;

                                ss.setPos(playerIn.position().add(0, yOffset, zOffset));
                                ss.setOffset(new Vec3(0, yOffset, zOffset));
                                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                            }
                        }
                );
    }


    public static void doSlashFantasy(LivingEntity playerIn, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;
        int colorCode = 999999;
        Level worldIn = playerIn.level();

        int count = 100;
        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent((state) -> {
            Vec3 playerPos = playerIn.position();

            // 随机参数配置
            final float maxRadius = 3.0f;      // 最大生成半径
            final float minHeight = 0.5f;      // 最低生成高度
            final float maxHeight = 2.0f;      // 最高生成高度
            final float speedVariation = 0.3f; // 速度变化幅度

            for (int i = 0; i < count; i++) {
                ThrustEdgeEntity ss = new ThrustEdgeEntity(YAEntitiesRegistry.soul_edge, worldIn);

                // 三维极坐标生成随机位置
                double theta = Math.random() * Math.PI * 2;        // 水平角度
                double phi = Math.random() * Math.PI;              // 垂直角度
                double r = Math.random() * maxRadius;

                double xOffset = r * Math.sin(phi) * Math.cos(theta);
                double yOffset = minHeight + Math.random() * (maxHeight - minHeight);
                double zOffset = r * Math.sin(phi) * Math.sin(theta);

                // 动态参数配置
                float individualSpeed = speed * (1 + (float) (Math.random() - 0.5) * speedVariation);
                float colorShift = (float) Math.random() * 0.2f;   // 颜色随机偏移

                // 实体配置
                ss.setPos(playerPos.add(xOffset, yOffset, zOffset));
                ss.setSpeed(individualSpeed);
                ss.setDamage(damage * (0.8 + Math.random() * 0.4)); // 伤害±20%浮动
                ss.setRoll((float) (Math.random() * 360));          // 随机旋转
                ss.setColor(shiftColor(colorCode, colorShift));    // 颜色渐变
                ss.setDelay(1 + i);                  // 随机激活延迟

                // 运动轨迹配置
                Vec3 randomDirection = new Vec3(
                        Math.random() - 0.5,
                        Math.random() - 0.5,
                        Math.random() - 0.5
                ).normalize();

                // 增加垂直发射概率
                if (Math.random() < 0.3) {
                    randomDirection = randomDirection.add(0, Math.abs(randomDirection.y) * 0.8, 0).normalize();
                }
                // 强制设置初始旋转角度
                ss.setYRot((float) Math.toDegrees(Math.atan2(randomDirection.x, randomDirection.z)));
                ss.setXRot((float) Math.toDegrees(Math.asin(randomDirection.y)));
                ss.yRotO = ss.getYRot();
                ss.xRotO = ss.getXRot();
                ss.startRiding(playerIn, true);

                worldIn.addFreshEntity(ss);
                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.0F + (float) Math.random() * 0.5F);
            }
        });

    }


    // 颜色渐变辅助方法
    private static int shiftColor(int original, float factor) {
        int r = (original >> 16) & 0xFF;
        int g = (original >> 8) & 0xFF;
        int b = original & 0xFF;

        r = (int) (r * (0.9 + Math.random() * 0.2));
        g = (int) (g * (0.9 + Math.random() * 0.2));
        b = (int) (b * (0.9 + Math.random() * 0.2));

        return (r << 16) | (g << 8) | b;
    }


}
