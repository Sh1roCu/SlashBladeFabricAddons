package com.yakumosakura.yakumoblade.network;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.event.TypeMakerCanChange;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public record TypeMakerMessage(int typeCode, int pressedms) implements CustomPacketPayload {
    public static final Type<TypeMakerMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Yakumoblade.MODID, "yakumoblade"));

    public static final StreamCodec<RegistryFriendlyByteBuf, TypeMakerMessage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            TypeMakerMessage::typeCode,
            ByteBufCodecs.INT,
            TypeMakerMessage::pressedms,
            TypeMakerMessage::new
    );


    public static void handler(TypeMakerMessage message, ServerPlayNetworking.Context context) {
        var player = context.player();
        int typeCode = message.typeCode;
        int pressedms = message.pressedms;
        context.server().execute(() -> pressAction(player, typeCode, pressedms));
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        // security measure to prevent arbitrary chunk generation
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {
            TypeMakerCanChange.EVENT.invoker().post(new TypeMakerCanChange(entity));
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
