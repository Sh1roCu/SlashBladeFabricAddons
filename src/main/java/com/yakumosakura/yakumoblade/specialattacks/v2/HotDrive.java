package com.yakumosakura.yakumoblade.specialattacks.v2;

import com.yakumosakura.yakumoblade.utils.SlashBladeUtils;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class HotDrive {


    public static void onDoSlash(LivingEntity livingEntity) {
        float DamageCount = SlashBladeUtils.getRefine(livingEntity.getMainHandItem()) * 0.1f;
        float Damage = 20 * (1 + DamageCount);
        spawnCircularDrives(livingEntity, 10, Damage, 0, 30);

    }


    public static void spawnCircularDrives(LivingEntity playerIn, int numPoints, float damage, float roll, float lifetime) {

        int colorCode =
                (Integer) CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem())
                        .map(ISlashBladeState::getColorCode).orElse(-13421569);

        // 获取玩家视角方向和位置
        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = playerIn.position().add(0.0F, playerIn.getEyeHeight() * 0.75F, 0.0F).add(lookAngle.scale(0.3F));
        for (int i = 0; i < numPoints; i++) {
            // 计算当前点在圆上的角度
            double angleDeg = (360.0 / numPoints) * i;
            double angleRad = Math.toRadians(angleDeg);

            // 计算该点在水平面上的偏移量
            double offsetX = Math.cos(angleRad);
            double offsetZ = Math.sin(angleRad);

            // 创建新的剑气实体 (修复多个实体使用同一个实例的问题)
            EntityDrive drive = new EntityDrive(SBEntityTypes.DRIVE, playerIn.level());
            drive.setPos(pos.x, pos.y, pos.z);
            DriveBaseSet(drive, damage / numPoints, playerIn, colorCode, roll, lifetime);

            // 设置剑气方向
            Vec3 driveDirection = new Vec3(offsetX, 0, offsetZ).normalize();
            drive.shoot(driveDirection.x, driveDirection.y, driveDirection.z, drive.getSpeed(), 0.0F);
            playerIn.level().addFreshEntity(drive);
        }
    }

    public static void DriveBaseSet(EntityDrive drive, float damage, LivingEntity playerIn, int colorCode, float roll, float lifetime) {
        drive.setDamage(damage);
        drive.setSpeed(2f);
        drive.setOwner(playerIn);
        drive.setRotationRoll(roll);
        drive.setColor(colorCode);
        drive.setLifetime(lifetime);
    }
}
