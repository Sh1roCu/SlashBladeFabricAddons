package com.exfantasycode.mclib.Utils.Dash;


import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SMoveUtil {
    /**
     * 让一个实体围绕另一个实体做圆周运动。
     *
     * @param follower 跟随的实体。
     * @param leader   被跟随的实体。
     * @param radius   圆周运动的半径。
     * @param speed    旋转速度（每秒的旋转角度）。
     */
    public static void orbitAroundEntity(Entity follower, Entity leader, double radius, float speed) {
        Vec3 leaderPos = leader.position();
        Vec3 followerPos = follower.position();

        // 计算当前的偏移角度
        double offsetX = followerPos.x - leaderPos.x;
        double offsetZ = followerPos.z - leaderPos.z;
        double currentAngle = Math.toDegrees(Math.atan2(offsetZ, offsetX)) - 90.0D;

        // 计算新的角度
        double newAngle = currentAngle + speed;

        // 根据新角度计算新的位置
        double newX = leaderPos.x + radius * Math.cos(Math.toRadians(newAngle));
        double newZ = leaderPos.z + radius * Math.sin(Math.toRadians(newAngle));

        // 设置跟随实体的新位置
        follower.setPos(newX, leaderPos.y, newZ);

        // 设置跟随实体的朝向
        float yaw = (float) newAngle;
        follower.setYRot(yaw);
        follower.setYHeadRot(yaw);
    }

    public static void sendDashMessage(Player player, double dy, double dashDistance) {
        DashMessage message = new DashMessage(dy, dashDistance);
        if (player.level().isClientSide) return;
        ServerPlayNetworking.send((ServerPlayer) player, DashMessage.ID,message.encode());
    }//


}
