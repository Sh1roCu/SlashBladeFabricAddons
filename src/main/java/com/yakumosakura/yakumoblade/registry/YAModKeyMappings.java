package com.yakumosakura.yakumoblade.registry;

import com.yakumosakura.yakumoblade.network.TypeMakerMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class YAModKeyMappings {
    public static final KeyMapping TypeMaker = new KeyMapping("key.yakumoblade.typemaker", GLFW.GLFW_KEY_Z, "key.yakumoblade.neotype") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                ClientPlayNetworking.send(TypeMakerMessage.ID, new TypeMakerMessage(0, 0).buffer());
                TypeMakerMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };

    public static void registerKeyMappings() {
        KeyBindingHelper.registerKeyBinding(TypeMaker);
    }

    public static class KeyEventListener {
        public static void onClientTick(Minecraft client) {
            if (client.screen == null) {
                TypeMaker.consumeClick();
            }
        }
    }
}
