package com.yakumosakura.yakumoblade.specialeffects.compat;

import com.exfantasycode.mclib.Utils.RandomUtil.RandomNumber;
import com.yakumosakura.yakumoblade.compat.YATouHouMaidItem;
import com.yakumosakura.yakumoblade.entity.SummonSwordEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.specialattacks.v2.SlashEffect;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class YellowFoxBoost {
    private static final Random RANDOM = new Random();

    public static void yellowfoxdoslash(SlashBladeEvent.DoSlashEvent event) {
        if ((event.getUser() instanceof Player)) {
            return;
        }
        LivingEntity user = event.getUser();

        if (user.getMainHandItem().getItem() == YATouHouMaidItem.getItem(YATouHouMaidItem.YELLOW_FOX)) {
            if (RANDOM.nextFloat() < 0.3f) {
                for (int i = 0; i < 7; i++) {
                    SlashEffect.SakuraEnd.doSlash(
                            user,
                            RandomNumber.randomint(360),
                            Vec3.ZERO,
                            false,
                            false,
                            1F);
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    SummonSwordEntity ss = new SummonSwordEntity(YAEntitiesRegistry.SummonSword, user.level());
                    ss.setSpeed(4F);
                    ss.setIsCritical(false);
                    ss.setOwner(user);
                    ss.setColor(16754944);
                    ss.setRoll(0);
                    ss.setDamage(5);
                    ss.setDelay(10 + i);
                    boolean isRight = ss.getDelay() % 2 == 0;
                    RandomSource random = user.level().getRandom();
                    double xOffset = random.nextDouble() * 1 * (double) (isRight ? 1 : -1);
                    double zOffset = (double) random.nextFloat() * 3;
                    double yOffset = random.nextFloat() * 2;
                    ss.setPos(user.position().add(xOffset, yOffset, zOffset));
                    ss.setOffset(new Vec3(xOffset, yOffset, zOffset));
                    user.level().addFreshEntity(ss);
                    ss.startRiding(user, true);
                }
            }

        }

    }


    public static void yellowfoxupdate(SlashBladeEvent.UpdateEvent event) {
        if ((event.getEntity() instanceof Player)) {
            return;
        }
        if (event.getEntity() instanceof LivingEntity entity) {
            if (entity.getMainHandItem().getItem() == YATouHouMaidItem.getItem(YATouHouMaidItem.YELLOW_FOX)) {
                // 创建10格检测范围
                AABB detectionArea = new AABB(entity.blockPosition()).inflate(10);
                boolean hasValidHostile = !entity.level().getEntitiesOfClass(
                        LivingEntity.class,
                        detectionArea,
                        e -> {
                            // 排除自己且判断敌对
                            if (e == entity || !isHostile(e)) return false;

                            // 计算精确距离（使用平方距离优化性能）
                            double distanceSq = e.distanceToSqr(entity);
                            return distanceSq > 3 * 3 && distanceSq <= 10 * 10;
                        }
                ).isEmpty();
                LivingEntity user = entity;
                if (hasValidHostile) {
                    if (RANDOM.nextFloat() < 0.5f) {

                    }
                }
            }
        }
    }

    // 敌对生物判断辅助方法
    private static boolean isHostile(LivingEntity e) {
        // 根据实际需求调整判断逻辑
        return e.getType().getCategory() == MobCategory.MONSTER
                || e instanceof Enemy; // 包含所有实现了Enemy接口的生物
    }
}
