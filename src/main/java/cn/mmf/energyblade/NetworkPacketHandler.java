package cn.mmf.energyblade;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class NetworkPacketHandler {
    public static void registerMessage() {
        ServerPlayNetworking.registerGlobalReceiver(PowerSwitchPacket.ID, PowerSwitchPacket::handle);
    }
}
