package cn.sh1rocu.sfaddons.client;

import cn.mmf.slashblade_addon.data.SBAEntityDropRegistry;
import cn.mmf.slashblade_addon.data.SlashAddonDataGen;
import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import cn.mmf.slashblade_addon.data.SlashBladeAddonRecipeProvider;
import mods.flammpfeil.slashblade.event.drop.EntityDropEntry;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class SFAddonsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // SJAP
        pack.addProvider(SlashAddonDataGen::new);
        pack.addProvider(SlashBladeAddonRecipeProvider::new);
        // FIXME 不知道怎么在dataGen阶段将自定义的registry的Condition写入Json，暂时生成完后手动写入
//        final RegistrySetBuilder bladeBuilder = new RegistrySetBuilder().add(SlashBladeDefinition.REGISTRY_KEY, SlashBladeAddonBuiltInRegistry::registerAll);
//        pack.addProvider((packOutput, registries) -> new AbstractConditionalDatapackEntriesProvider(packOutput, registries, bladeBuilder, Set.of(SlashBladeAddon.MODID)) {
//            @Override
//            public Map<ResourceKey<SlashBladeDefinition>, List<ConditionJsonProvider>> getConditions() {
//                return SlashBladeAddonBuiltInRegistry.getConditions();
//            }
//
//            @Override
//            public String getName() {
//                return "SJAP registry";
//            }
//        });
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registrySetBuilder) {
        // SJAP
        registrySetBuilder.add(SlashBladeDefinition.REGISTRY_KEY, SlashBladeAddonBuiltInRegistry::registerAll);
        registrySetBuilder.add(EntityDropEntry.REGISTRY_KEY, SBAEntityDropRegistry::registerAll);

    }
}
