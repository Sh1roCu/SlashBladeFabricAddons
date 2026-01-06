package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.exfantasycode.mclib.Utils.Dash.SMoveUtil;
import com.yakumosakura.yakumoblade.entity.exer.SwordRainEntityEnder;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class RetreatFlyingKnife {


    public static void doSlash(LivingEntity playerIn) {

        double baseY = playerIn.position().y + 1;

// 圆周参数
        double radius = 5.0; // 半径5*i1格
        int count = 5;
        double angleStep = 360.0 / count;


        for (int i = 0; i < count; i++) {
            Level worldIn = playerIn.level();
            SwordRainEntityEnder ss = new SwordRainEntityEnder(YAEntitiesRegistry.swordRainFire, worldIn);
            ss.setColor(CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                    .map(ISlashBladeState::getColorCode).get());
            worldIn.addFreshEntity(ss);
            ss.setIsCritical(false);
            ss.setOwner(playerIn);
            ss.setRoll(0);
            ss.setForward(true);
            ss.setDamage(2);
            // force riding
            ss.startRiding(playerIn, true);

            ss.doFire();

            // 计算圆周坐标
            double angle = Math.toRadians(angleStep * i);
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);

            // 设置位置
            ss.setPos(
                    playerIn.position().x + xOffset,
                    baseY, // 统一高度
                    playerIn.position().z + zOffset
            );
            playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
        SMoveUtil.sendDashMessage((Player) playerIn, 0, -0.5F);
    }
}
