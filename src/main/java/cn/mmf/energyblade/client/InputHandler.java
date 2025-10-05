package cn.mmf.energyblade.client;

import cn.mmf.energyblade.PowerSwitchPacket;
import com.mojang.blaze3d.platform.InputConstants;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class InputHandler {
    public static final KeyMapping KEY_CHARGE = new KeyMapping("key.slahblade_fabric_addons.energyblade.charge_switch",
            /*KeyConflictContext.IN_GAME, KeyModifier.SHIFT, */InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V,
            "key.category.slashblade");

    @Environment(EnvType.CLIENT)
    public static void onPlayerPostTick(int key, int scancode, int action, int mods) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null)
            return;

        if (player.getMainHandItem().isEmpty() || CapabilitySlashBlade.BLADESTATE.maybeGet(player.getMainHandItem()).isEmpty())
            return;

        if (InputHandler.KEY_CHARGE.isDown()) {
            FriendlyByteBuf buf = PacketByteBufs.create();
            PowerSwitchPacket.toBytes(buf, "triggered");
            ClientPlayNetworking.send(PowerSwitchPacket.ID, buf);
        }
    }
}
