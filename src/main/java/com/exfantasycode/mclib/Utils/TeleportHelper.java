package com.exfantasycode.mclib.Utils;

import com.yakumosakura.yakumoblade.Yakumoblade;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class TeleportHelper {

    /**
     * 将LivingEntity随机传送到给定立方体范围内的有效位置。
     *
     * @param level  世界实例（必须是服务端级别）
     * @param entity 要传送的实体
     * @param center 立方体中心点的位置
     * @param size   立方体的边长
     */
    public static void teleportEntityInCube(ServerLevel level, LivingEntity entity, Vec3 center, float size) {
        if (entity == null || !level.isLoaded(entity.blockPosition())) {
            return; // 如果实体无效或不在加载的区块内，则不进行传送
        }

        float halfSize = size / 2f;
        Vec3 randomPos = null;

        // 尝试多次以找到一个有效的位置
        for (int attempts = 0; attempts < 10; attempts++) {
            randomPos = generateRandomPosition(center, halfSize);

            // 检查新位置是否有效（例如不在固体方块中）
            //   if (isValidTeleportPosition(level, entity, randomPos)) {
            //       break;
            //    }
        }

        if (randomPos != null) {
            // 设置新的位置并调整Y轴以避免陷入地面
            teleportToValidYPosition(level, entity, randomPos);
        }
    }

    private static Vec3 generateRandomPosition(Vec3 center, float halfSize) {
        double minX = center.x - halfSize;
        double maxX = center.x + halfSize;
        double minY = center.y - halfSize;
        double maxY = center.y + halfSize;
        double minZ = center.z - halfSize;
        double maxZ = center.z + halfSize;

        return new Vec3(
                minX + Math.random() * (maxX - minX),
                minY + Math.random() * (maxY - minY),
                minZ + Math.random() * (maxZ - minZ)
        );
    }

   /* private static boolean isValidTeleportPosition(ServerLevel level, LivingEntity entity, Vec3 pos) {
        BlockPos blockPos = BlockPos.containing(pos);
        return level.noCollision(entity, entity.getBoundingBox().move(pos))
                && level.isEmptyBlock(blockPos.below()) // 确保下方不是空气
                && !level.getBlockState(blockPos).getMaterial().blocksMotion(); // 确保当前位置不是固体方块
    }*/

    private static void teleportToValidYPosition(ServerLevel level, LivingEntity entity, Vec3 pos) {
        double y = pos.y;

        // 找到最近的有效Y轴位置（例如地面）
        while (!level.isEmptyBlock(BlockPos.containing(pos.x, y, pos.z).below()) && y > level.getMinBuildHeight()) {
            y--;
        }

        // 确保不会传送到过低的地方
        if (y >= level.getMinBuildHeight()) {
            entity.teleportTo(pos.x, y, pos.z);
        } else {
            // 如果找不到合适的位置，则不进行传送
            Yakumoblade.LOGGER.info("Failed to find a valid Y position for teleportation.");
        }
    }
}
