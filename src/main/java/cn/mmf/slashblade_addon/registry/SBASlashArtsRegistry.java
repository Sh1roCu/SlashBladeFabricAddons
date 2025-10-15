package cn.mmf.slashblade_addon.registry;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.core.Registry;

public class SBASlashArtsRegistry {
    public static void init() {

    }

    public static final SlashArts RAPID_BLISTERING_SWORDS = register(
            "rapid_blistering_swords",
            new SlashArts((e) -> ComboStateRegistry.getId(SBAComboStateRegistry.RAPID_BLISTERING_SWORDS)));
    public static final SlashArts SPIRAL_EDGE = register("spiral_edge",
            new SlashArts((e) -> ComboStateRegistry.getId(SBAComboStateRegistry.SPIRAL_EDGE)));
    public static final SlashArts GALE_SWORDS = register("gale_swords",
            new SlashArts((e) -> ComboStateRegistry.getId(SBAComboStateRegistry.GALE_SWORDS)));

    public static final SlashArts WATER_DRIVE = register("water_drive",
            new SlashArts((e) -> ComboStateRegistry.getId(SBAComboStateRegistry.WATER_DRIVE)));
    public static final SlashArts FIRE_SPIRAL = register("fire_spiral",
            new SlashArts((e) -> ComboStateRegistry.getId(SBAComboStateRegistry.FIRE_SPIRAL)));
    public static final SlashArts LIGHTING_SWORDS = register("lighting_swords",
            new SlashArts((e) -> ComboStateRegistry.getId(SBAComboStateRegistry.LIGHTING_SWORDS)));

    private static SlashArts register(String name, SlashArts slashArts) {
        return Registry.register(SlashArtsRegistry.SLASH_ARTS, SlashBladeAddon.prefix(name), slashArts);
    }
}
