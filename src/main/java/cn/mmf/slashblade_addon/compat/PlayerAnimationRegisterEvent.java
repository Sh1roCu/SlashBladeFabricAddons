package cn.mmf.slashblade_addon.compat;

import cn.mmf.slashblade_addon.compat.botania.SBABotaniaCompat;
import cn.mmf.slashblade_addon.registry.SBAComboStateRegistry;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.compat.playerAnim.PlayerAnimationOverrider;
import mods.flammpfeil.slashblade.compat.playerAnim.VmdAnimation;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class PlayerAnimationRegisterEvent {
    private static final ResourceLocation MotionLocation =  ResourceLocation.fromNamespaceAndPath(SlashBlade.MODID,
            "model/pa/player_motion.vmd");

    public static void onRegisterPlayerAnim() {
        if (FabricLoader.getInstance().isModLoaded("player-animator")) {
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.getId(SBAComboStateRegistry.RAPID_BLISTERING_SWORDS),
                    new VmdAnimation(MotionLocation, 400, 488, false));
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.getId(SBAComboStateRegistry.SPIRAL_EDGE),
                    new VmdAnimation(MotionLocation, 400, 488, false));
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.getId(SBAComboStateRegistry.GALE_SWORDS),
                    new VmdAnimation(MotionLocation, 400, 488, false));

            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.getId(SBAComboStateRegistry.WATER_DRIVE),
                    new VmdAnimation(MotionLocation, 400, 488, false));
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.getId(SBAComboStateRegistry.LIGHTING_SWORDS),
                    new VmdAnimation(MotionLocation, 400, 488, false));
            PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.getId(SBAComboStateRegistry.FIRE_SPIRAL),
                    new VmdAnimation(MotionLocation, 400, 488, false));

            if (FabricLoader.getInstance().isModLoaded("botania")) {
                PlayerAnimationOverrider.getInstance().getAnimation().put(ComboStateRegistry.getId(SBABotaniaCompat.BLISTERING_TERRA_SWORDS),
                        new VmdAnimation(MotionLocation, 400, 488, false));
            }
        }
    }
}
