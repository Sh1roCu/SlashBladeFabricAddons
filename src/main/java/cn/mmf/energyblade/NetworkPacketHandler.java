package cn.mmf.energyblade;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class NetworkPacketHandler {
    public static void registerMessage() {
        PayloadTypeRegistry.playC2S().register(PowerSwitchPacket.TYPE, PowerSwitchPacket.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(PowerSwitchPacket.TYPE, PowerSwitchPacket::handle);
    }
}
