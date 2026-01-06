package com.exfantasycode.mclib.Utils.Dash;

import com.yakumosakura.yakumoblade.Yakumoblade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public record DashMessage(double dy, double dashDistance) implements CustomPacketPayload {
    public static final Type<DashMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Yakumoblade.MODID, "dash"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DashMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.DOUBLE,
            DashMessage::dy,
            ByteBufCodecs.DOUBLE,
            DashMessage::dashDistance,
            DashMessage::new
    );

    @Environment(EnvType.CLIENT)
    public static void handle(DashMessage msg, ClientPlayNetworking.Context context) {
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
        context.client().execute(() -> ClientPacketHandler.handledash(msg));
    }

    public static void vmove(LivingEntity livingEntity, double dy, double dashDistance) {
        float yaw = livingEntity.getYRot();
        double dx = -Math.sin(Math.toRadians(yaw)) * dashDistance;
        double dz = Math.cos(Math.toRadians(yaw)) * dashDistance;
        livingEntity.setDeltaMovement(new Vec3(dx, dy, dz));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}