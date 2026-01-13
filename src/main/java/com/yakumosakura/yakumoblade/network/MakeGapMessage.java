package com.yakumosakura.yakumoblade.network;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.specialeffects.touhouSE.MakeGap;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class MakeGapMessage {
    public static final ResourceLocation ID = new ResourceLocation(Yakumoblade.MODID, "make_gap");

    public static final FriendlyByteBuf DUMMY = PacketByteBufs.empty();

    public static void handler(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> MakeGap.handleSpecialMove(player));
    }
}
