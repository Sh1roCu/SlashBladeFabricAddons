package com.exfantasycode.mclib.Utils.Dash;

import com.yakumosakura.yakumoblade.Yakumoblade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class DashMessage {
    public static final ResourceLocation ID = new ResourceLocation(Yakumoblade.MODID, "dash");

    final double dy;
    final double dashDistance;

    public DashMessage(double dy, double dashDistance) {
        this.dy = dy;
        this.dashDistance = dashDistance;
    }

    public FriendlyByteBuf encode() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeDouble(dy);
        buffer.writeDouble(dashDistance);
        return buffer;

    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
//        ctx.get().enqueueWork(() -> {
//            // 在这里调用vmove方法
//            if (ctx.get().getDirection().getReceptionSide() == LogicalSide.CLIENT ) {
//                // 获取实体
//                LivingEntity entity = Minecraft.getInstance().player;
//
//                if (entity != null) {
//                    vmove(entity, msg.dy, msg.dashDistance);
//                }
//            }
//        });
        DashMessage msg = new DashMessage(buf.readDouble(), buf.readDouble());
        client.execute(() -> ClientPacketHandler.handledash(msg));
    }

    public static DashMessage decode(FriendlyByteBuf buffer) {
        return new DashMessage(buffer.readDouble(), buffer.readDouble());
    }


    public static void vmove(LivingEntity livingEntity, double dy, double dashDistance) {
        float yaw = livingEntity.getYRot();
        double dx = -Math.sin(Math.toRadians(yaw)) * dashDistance;
        double dz = Math.cos(Math.toRadians(yaw)) * dashDistance;
        livingEntity.setDeltaMovement(new Vec3(dx, dy, dz));
    }
}