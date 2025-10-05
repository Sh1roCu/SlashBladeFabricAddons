package cn.mmf.energyblade.data;

import cn.mmf.energyblade.Energyblade;
import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;

import java.util.List;

public class BuiltInSlashBladeRegistry {
    public static final ResourceKey<SlashBladeDefinition> HF_BLADE = register("hf_blade");

    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {

        bootstrap.register(HF_BLADE, new SlashBladeDefinition(BuiltInRegistries.ITEM.getKey(Energyblade.FORGE_ENERGY_BLADE),
                Energyblade.prefix("hf_blade"),
                RenderDefinition.Builder.newInstance()
                        .textureName(Energyblade.prefix("model/hf_blade.png"))
                        .modelName(Energyblade.prefix("model/hf_blade.obj"))
                        .standbyRenderType(CarryType.PSO2).build(),
                PropertiesDefinition.Builder.newInstance().baseAttackModifier(7.0F).maxDamage(50)
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.CIRCLE_SLASH)).build(),
                List.of()));

    }

    private static ResourceKey<SlashBladeDefinition> register(String id) {
        ResourceKey<SlashBladeDefinition> loc = ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY,
                Energyblade.prefix(id));
        return loc;
    }
}
