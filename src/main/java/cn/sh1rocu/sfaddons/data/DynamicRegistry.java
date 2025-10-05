package cn.sh1rocu.sfaddons.data;

import mods.flammpfeil.slashblade.event.drop.EntityDropEntry;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistry extends FabricDynamicRegistryProvider {
    public DynamicRegistry(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(SlashBladeDefinition.REGISTRY_KEY));
        entries.addAll(registries.lookupOrThrow(EntityDropEntry.REGISTRY_KEY));
    }

    @Override
    public String getName() {
        return "SJAP dynamic registry";
    }
}
