package com.yakumosakura.yakumoblade.data.definiton;

import com.yakumosakura.yakumoblade.Yakumoblade;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class YASlashBladeDefinition extends SlashBladeDefinition {
    public YASlashBladeDefinition(ResourceLocation name, RenderDefinition renderDefinition, PropertiesDefinition stateDefinition, List<EnchantmentDefinition> enchantments) {
        super(Yakumoblade.prefix("slashblade"), name, renderDefinition, stateDefinition, enchantments);
    }

    public static final ResourceKey<Registry<YASlashBladeDefinition>> REGISTRY_KEY =
            ResourceKey.createRegistryKey(Yakumoblade.prefix("named_blades"));

}
