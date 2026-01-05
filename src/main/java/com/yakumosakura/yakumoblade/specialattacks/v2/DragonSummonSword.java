package com.yakumosakura.yakumoblade.specialattacks.v2;

import com.exfantasycode.mclib.Utils.EntityPointer;
import com.yakumosakura.yakumoblade.entity.hexgram.old.SwordRainEntityDragon2;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class DragonSummonSword {
    // 常量定义，提高代码可读性
    private static final int TARGET_RANGE = 300;
    private static final int SLOW_DURATION = 20; // 缓慢效果持续时间(ticks)
    private static final int SLOW_AMPLIFIER = 7; // 缓慢效果等级
    private static final double VERTICAL_OFFSET = 13.5; // 垂直偏移量 (10 + 3.5)
    private static final float DAMAGE = 25.0f;
    private static final float SOUND_VOLUME = 0.2F;
    private static final float SOUND_PITCH = 1.45F;

    public static void doSlash(LivingEntity playerIn) {
        // 检查是否在客户端，如果是则直接返回
        if (playerIn.level().isClientSide()) return;

        // 获取目标实体
        Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(playerIn, TARGET_RANGE);
        if (targetedEntity.isEmpty()) return;

        LivingEntity target = targetedEntity.get();
        Level worldIn = playerIn.level();

        // 给目标添加缓慢效果
        target.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                SLOW_DURATION,
                SLOW_AMPLIFIER
        ));

        try {
            // 创建剑雨实体
            SwordRainEntityDragon2 swordRain = new SwordRainEntityDragon2(
                    YAEntitiesRegistry.swordRainEntityDragon,
                    worldIn
            );

            // 设置实体属性
            swordRain.setIsCritical(false);
            swordRain.setOwner(playerIn);
            swordRain.setColor(SlashBladeUtils.getColorCode(playerIn.getMainHandItem()));
            swordRain.setRoll(0);
            swordRain.setDamage(DAMAGE);

            // 计算位置 - 直接设置在目标上方
            swordRain.setPos(
                    target.getX(),
                    target.getY() + VERTICAL_OFFSET,
                    target.getZ()
            );

            // 添加到世界并执行攻击
            worldIn.addFreshEntity(swordRain);
            swordRain.doFire();

            // 播放音效
            playerIn.playSound(
                    SoundEvents.CHORUS_FRUIT_TELEPORT,
                    SOUND_VOLUME,
                    SOUND_PITCH
            );
        } catch (Exception e) {
            // 异常处理，可根据需要记录日志
            System.err.println("Failed to execute DragonSummonSword: " + e.getMessage());
        }
    }
}