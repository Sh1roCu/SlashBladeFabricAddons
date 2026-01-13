package com.yakumosakura.yakumoblade.network;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.specialeffects.touhouSE.MakeGap;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class MakeGapMessage implements CustomPacketPayload {
    public static final Type<MakeGapMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Yakumoblade.MODID, "make_gap"));

    public static final MakeGapMessage DUMMY = new MakeGapMessage();

    public static final StreamCodec<RegistryFriendlyByteBuf, MakeGapMessage> STREAM_CODEC = StreamCodec.unit(DUMMY);

    public static void handler(MakeGapMessage message, ServerPlayNetworking.Context context) {
        var player = context.player();
        context.server().execute(() -> MakeGap.handleSpecialMove(player));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
