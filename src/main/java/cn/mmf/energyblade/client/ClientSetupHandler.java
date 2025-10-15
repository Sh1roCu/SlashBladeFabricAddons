package cn.mmf.energyblade.client;

import cn.mmf.energyblade.Energyblade;
import cn.mmf.energyblade.item.ItemFEBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModel;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

import static mods.flammpfeil.slashblade.client.ClientHandler.bakeBlade;

@Environment(EnvType.CLIENT)
public class ClientSetupHandler {
    public static void setModelUser() {
        BuiltInRegistries.ITEM.keySet().stream().filter(loc -> loc.getPath().startsWith(Energyblade.MODID + "/")).forEach(res -> {
            if (BuiltInRegistries.ITEM.get(res) instanceof ItemSlashBlade blade) {
                ItemProperties.register(blade, SlashBlade.prefix("user"), (stack, level, entity, seed) -> {
                    BladeModel.user = entity;
                    return 0;
                });
            }
        });
    }

    public static void registerKeyMapping() {
        KeyBindingHelper.registerKeyBinding(InputHandler.KEY_CHARGE);
    }


    public static void baked(ModelLoadingPlugin.Context plugin) {
        BuiltInRegistries.ITEM.stream().filter(item -> item instanceof ItemFEBlade).forEach(item -> {
            plugin.modifyModelAfterBake().register((bakedModel, context) -> {
                ModelResourceLocation modelLoc = ModelResourceLocation.inventory(BuiltInRegistries.ITEM.getKey(item));
                ModelResourceLocation id = context.topLevelId();
                if (id != null && id.equals(modelLoc)) {
                    return bakeBlade(bakedModel, context.loader());
                }
                return bakedModel;
            });
        });
    }
}
