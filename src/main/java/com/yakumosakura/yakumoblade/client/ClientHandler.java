package com.yakumosakura.yakumoblade.client;

import com.yakumosakura.yakumoblade.compat.YATouHouMaidItem;
import com.yakumosakura.yakumoblade.registry.slashblade.YAItem;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
public class ClientHandler {
    public static void doClientStuff() {
        ItemProperties.register(YATouHouMaidItem.getItem(YATouHouMaidItem.YELLOW_FOX),
                new ResourceLocation("slashblade:user"), (p_174564_, p_174565_, p_174566_, p_174567_) -> {
                    BladeModel.user = p_174566_;
                    return 0;
                });
        ItemProperties.register(YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                new ResourceLocation("slashblade:user"), (p_174564_, p_174565_, p_174566_, p_174567_) -> {
                    BladeModel.user = p_174566_;
                    return 0;
                });
    }

    private static final Set<Item> blades = new HashSet<>() {{
        add(YATouHouMaidItem.getItem(YATouHouMaidItem.YELLOW_FOX));
        add(YAItem.getItem(YAItem.SlashBladeOfYakumoBlade));
    }};

    public static void baked(ModelLoadingPlugin.Context plugin) {
        plugin.modifyModelAfterBake().register((ClientHandler::baked));
    }

    private static BakedModel baked(BakedModel bakedModel, ModelModifier.AfterBake.Context context) {
        for (Item blade : blades) {
            ModelResourceLocation modelLoc = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(blade), "inventory");
            if (context.id() instanceof ModelResourceLocation contextModelId && contextModelId.equals(modelLoc)) {
                return bakeBlade(bakedModel, context.loader());
            }
        }
        return bakedModel;
    }

    private static BakedModel bakeBlade(BakedModel bakedModel, ModelBakery bakery) {
        return new BladeModel(bakedModel, bakery);
    }

}