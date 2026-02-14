package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.drive.StarDriveEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ReincarnationHeaven {


    public static void doSlash(LivingEntity playerIn) {
        Vec3 centerOffset = Vec3.ZERO;
        int colorCode =
                CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                        .map(ISlashBladeState::getColorCode).orElse(-13421569);

        // 获取玩家视角方向和位置
        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = playerIn.position().add(0.0F, playerIn.getEyeHeight() * 0.75F, 0.0F).add(lookAngle.scale(0.3F));

        // 创建圆形发射剑气
        int numPoints = 120; // 整圆取90个点

        for (int i = 0; i < numPoints; i++) {
            // 计算当前点在圆上的角度（从0度到360度）
            double angleDeg = (360.0 / numPoints) * i;
            double angleRad = Math.toRadians(angleDeg);

            // 计算该点在水平面上的偏移量
            double offsetX = Math.cos(angleRad);
            double offsetZ = Math.sin(angleRad);

            // 创建新的剑气实体
            StarDriveEntity drive = new StarDriveEntity(YAEntitiesRegistry.StarDrive, playerIn.level());
            drive.setPos(pos.x, pos.y, pos.z);
            drive.setDamage(3f);
            drive.setSpeed(2f);
            drive.setOwner(playerIn);
            drive.setRotationRoll(90);
            drive.setColor(colorCode);
            drive.setLifetime(40f);
            // 设置剑气方向，使其沿着圆周切线方向
            Vec3 driveDirection = new Vec3(offsetX, 0, offsetZ).normalize();
            drive.shoot(driveDirection.x, driveDirection.y, driveDirection.z, drive.getSpeed(), 0.0F);


            if (playerIn != null) {
                CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).ifPresent((rank) ->
                        drive.setRank(rank.getRankLevel(playerIn.level().getGameTime())));
            }

            playerIn.level().addFreshEntity(drive);
        }
    }


    public static void doslash(LivingEntity playerIn) {
        doSlash(playerIn);
        // 获取玩家位置
        double x = playerIn.getX();
        double y = playerIn.getY() + playerIn.getEyeHeight() - 1;
        double z = playerIn.getZ();

        // 定义圆环半径和分割数量
        double radius = 18.0;
        int segments = 180;

        // 生成圆形粒子效果
        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            double xPos = x + radius * Math.cos(angle);
            double zPos = z + radius * Math.sin(angle);

            // 使用ServerLevel的粒子系统
            if (playerIn.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.END_ROD,
                        xPos, y, zPos,
                        1, // 粒子数量
                        0, 0, 0, // 偏移量
                        0 // 随机速度
                );
            }
        }

        // 创建六芒星（两个方向相反的三角形）
        // 定义三角形顶点索引
        int[] triangleIndices = {0, 2, 4}; // 第一个三角形的顶点索引

        // 绘制第一个三角形（顺时针方向）
        for (int i = 0; i < 3; i++) {
            int startIdx = triangleIndices[i];
            int endIdx = triangleIndices[(i + 1) % 3];

            double startX = x + radius * Math.cos(2 * Math.PI * startIdx / 6);
            double startZ = z + radius * Math.sin(2 * Math.PI * startIdx / 6);
            double endX = x + radius * Math.cos(2 * Math.PI * endIdx / 6);
            double endZ = z + radius * Math.sin(2 * Math.PI * endIdx / 6);

            drawLine(playerIn, x, y, z, startX, y, startZ, endX, y, endZ);
        }

        // 绘制第二个三角形（逆时针方向，旋转30度）
        for (int i = 0; i < 3; i++) {
            int idx = i * 2 + 1; // 取不同的顶点组成第二个三角形
            double startX = x + radius * Math.cos(2 * Math.PI * idx / 6);
            double startZ = z + radius * Math.sin(2 * Math.PI * idx / 6);
            double endX = x + radius * Math.cos(2 * Math.PI * ((idx + 2) % 6) / 6);
            double endZ = z + radius * Math.sin(2 * Math.PI * ((idx + 2) % 6) / 6);

            drawLine(playerIn, x, y, z, startX, y, startZ, endX, y, endZ);


        }

        // 创建套娃效果的更小六芒星
        double shrinkFactor = 0.5; // 缩小因子
        for (int layer = 1; layer <= 3; layer++) { // 套娃三层
            double currentRadius = radius * Math.pow(shrinkFactor, layer);

            // 绘制当前层的六芒星
            // 绘制第一个三角形（顺时针方向）
            for (int i = 0; i < 3; i++) {
                int startIdx = triangleIndices[i];
                int endIdx = triangleIndices[(i + 1) % 3];

                double startX = x + currentRadius * Math.cos(2 * Math.PI * startIdx / 6);
                double startZ = z + currentRadius * Math.sin(2 * Math.PI * startIdx / 6);
                double endX = x + currentRadius * Math.cos(2 * Math.PI * endIdx / 6);
                double endZ = z + currentRadius * Math.sin(2 * Math.PI * endIdx / 6);

                drawLine(playerIn, x, y, z, startX, y, startZ, endX, y, endZ);
            }

            // 绘制第二个三角形（逆时针方向，旋转30度）
            for (int i = 0; i < 3; i++) {
                int idx = i * 2 + 1; // 取不同的顶点组成第二个三角形
                double startX = x + currentRadius * Math.cos(2 * Math.PI * idx / 6);
                double startZ = z + currentRadius * Math.sin(2 * Math.PI * idx / 6);
                double endX = x + currentRadius * Math.cos(2 * Math.PI * ((idx + 2) % 6) / 6);
                double endZ = z + currentRadius * Math.sin(2 * Math.PI * ((idx + 2) % 6) / 6);

                drawLine(playerIn, x, y, z, startX, y, startZ, endX, y, endZ);


            }
        }


    }

    // 绘制两点之间的粒子连线
    private static void drawLine(LivingEntity playerIn, double centerX, double centerY, double centerZ, double startX, double startY, double startZ, double endX, double endY, double endZ) {
        if (playerIn.level() instanceof ServerLevel serverLevel) {
            int segments = 90; // 线段分段数量

            // 在起点和终点之间生成一系列粒子
            for (int j = 0; j <= segments; j++) {
                double fraction = (double) j / segments;
                double currentX = startX + (endX - startX) * fraction;
                double currentY = startY + (endY - startY) * fraction;
                double currentZ = startZ + (endZ - startZ) * fraction;

                // 发送粒子
                serverLevel.sendParticles(
                        ParticleTypes.END_ROD,
                        currentX, currentY, currentZ,
                        1,
                        0, 0, 0,
                        0
                );
            }
        }
    }


}