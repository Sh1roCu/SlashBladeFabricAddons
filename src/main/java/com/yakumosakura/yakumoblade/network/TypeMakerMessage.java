package com.yakumosakura.yakumoblade.network;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.event.TypeMakerCanChange;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TypeMakerMessage {
    public static final ResourceLocation ID = new ResourceLocation(Yakumoblade.MODID, "yakumoblade");

    int type, pressedms;

    public TypeMakerMessage(int type, int pressedms) {
        this.type = type;
        this.pressedms = pressedms;
    }

    public TypeMakerMessage(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressedms = buffer.readInt();
    }

    public FriendlyByteBuf buffer() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(type);
        buffer.writeInt(pressedms);
        return buffer;
    }

    public static void handler(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        TypeMakerMessage message = new TypeMakerMessage(buf.readInt(), buf.readInt());
        server.execute(() -> pressAction(player, message.type, message.pressedms));
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
}
