package cn.sh1rocu.sfaddons.data;

import cn.mmf.energyblade.data.BuiltInSlashBladeRegistry;
import cn.mmf.slashblade_addon.data.SBAEntityDropRegistry;
import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import mods.flammpfeil.slashblade.event.drop.EntityDropEntry;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class SFAddonsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(DynamicRegistry::new);

        // Recipe
        pack.addProvider(SFAddonsRecipeProvider::new);
        // Tag
        pack.addProvider(SFAddonsItemTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registrySetBuilder) {
        // SJAP
        registrySetBuilder.add(SlashBladeDefinition.REGISTRY_KEY, SlashBladeAddonBuiltInRegistry::registerAll);
        registrySetBuilder.add(EntityDropEntry.REGISTRY_KEY, SBAEntityDropRegistry::registerAll);
        // EnergyBlade(HF Blade)
        registrySetBuilder.add(SlashBladeDefinition.REGISTRY_KEY, BuiltInSlashBladeRegistry::registerAll);
    }
}
