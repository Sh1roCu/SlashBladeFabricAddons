package com.exfantasycode;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ServerSideHelper {

    /**
     * 模拟服务器端玩家右键点击。
     *
     * @param player 玩家实例
     */
    public static void simulateRightClick(ServerPlayer player) {
        // 获取玩家当前使用的物品手（主手或副手）
        InteractionHand hand = InteractionHand.MAIN_HAND; // 或 InteractionHand.OFF_HAND

        // 创建一个BlockHitResult以模拟玩家指向的位置
        Vec3 lookVec = player.getLookAngle();
        Vec3 startVec = player.getEyePosition(1.0F);
        Vec3 endVec = startVec.add(lookVec.x * 5, lookVec.y * 5, lookVec.z * 5); // 假设最大射程为5个方块

        BlockHitResult hitResult = player.level().clip(new ClipContext(startVec, endVec, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            // 执行右键点击（交互）动作
            Direction direction = hitResult.getDirection();
            BlockPos blockPos = hitResult.getBlockPos();

            // 调用玩家的交互方法
            player.interactOn(player, hand);

            // 发送动画包给所有玩家显示玩家正在交互
            player.level().broadcastEntityEvent(player, (byte) 0);
        }
    }
}