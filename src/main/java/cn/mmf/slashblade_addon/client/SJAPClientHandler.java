package cn.mmf.slashblade_addon.client;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import cn.mmf.slashblade_addon.compat.SBATofuCraftItems;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class SJAPClientHandler {
    public static void doClientStuff() {
        if (FabricLoader.getInstance().isModLoaded("tofucraft")) {
            ItemProperties.register(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUISHI_SLASHBLADE),
                    ResourceLocation.parse("slashblade:user"), (p_174564_, p_174565_, p_174566_, p_174567_) -> {
                        BladeModel.user = p_174566_;
                        return 0;
                    });
            ItemProperties.register(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUMETAL_SLASHBLADE),
                    ResourceLocation.parse("slashblade:user"), (p_174564_, p_174565_, p_174566_, p_174567_) -> {
                        BladeModel.user = p_174566_;
                        return 0;
                    });
            ItemProperties.register(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUDIAMOND_SLASHBLADE),
                    ResourceLocation.parse("slashblade:user"), (p_174564_, p_174565_, p_174566_, p_174567_) -> {
                        BladeModel.user = p_174566_;
                        return 0;
                    });
        }
    }

    private static final Set<Item> blades = new HashSet<>() {{
        add(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUISHI_SLASHBLADE));
        add(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUMETAL_SLASHBLADE));
        add(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUDIAMOND_SLASHBLADE));
    }};

    public static void Baked(ModelLoadingPlugin.Context plugin) {
        if (FabricLoader.getInstance().isModLoaded("tofucraft")) {
            plugin.modifyModelAfterBake().register((SJAPClientHandler::Baked));
        }

    }

    private static BakedModel Baked(BakedModel bakedModel, ModelModifier.AfterBake.Context context) {
        for (Item blade : blades) {
            ModelResourceLocation modelLoc = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(blade), "inventory");
            ModelResourceLocation id = context.topLevelId();
            if (id != null && id.equals(modelLoc)) {
                return bakeBlade(bakedModel, context.loader());
            }
        }
        return bakedModel;
    }

    private static BakedModel bakeBlade(BakedModel bakedModel, ModelBakery bakery) {
        return new BladeModel(bakedModel, bakery);
    }

    public static void addCreative(CreativeModeTab itemGroup, FabricItemGroupEntries entries) {
        if (FabricLoader.getInstance().isModLoaded("tofucraft")) {
            if (itemGroup == SlashBladeAddon.SJAP_TAB) {
                entries.accept(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUISHI_SLASHBLADE));
                entries.accept(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUMETAL_SLASHBLADE));
                entries.accept(SBATofuCraftItems.getItem(SBATofuCraftItems.TOFUDIAMOND_SLASHBLADE));
            }
        }
    }
}
