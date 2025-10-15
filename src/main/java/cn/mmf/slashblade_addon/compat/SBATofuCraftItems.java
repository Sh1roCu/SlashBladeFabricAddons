package cn.mmf.slashblade_addon.compat;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class SBATofuCraftItems {
    public static final ResourceLocation TOFUISHI_SLASHBLADE = SlashBladeAddon.prefix("slashblade_tofu_ishi");
    public static final ResourceLocation TOFUMETAL_SLASHBLADE = SlashBladeAddon.prefix("slashblade_tofu_metal");
    public static final ResourceLocation TOFUDIAMOND_SLASHBLADE = SlashBladeAddon.prefix("slashblade_tofu_diamond");

    public static void init() {
        // TODO: TofuCraft Fabric移植
//        register(TOFUISHI_SLASHBLADE,
//                new TofuSlashBladeItem(TofuItemTier.SOLID, 3, -2.4F, 200, new Item.Properties()).setTexture(SlashBladeAddon.prefix("model/tofuishi_katana.png")));
//        register(TOFUMETAL_SLASHBLADE,
//                new TofuSlashBladeItem(TofuItemTier.METAL, 5, -2.4F, 500, new Item.Properties()).setTexture(SlashBladeAddon.prefix("model/tofumetal_katana.png")));
//        register(BuiltInRegistries.ITEM, TOFUDIAMOND_SLASHBLADE,
//                new TofuSlashBladeItem(TofuItemTier.TOFUDIAMOND, 9, -2.4F, 1200, new Item.Properties()).setTexture(SlashBladeAddon.prefix("model/tofudiamond_katana.png")));

    }

    public static Item getItem(ResourceLocation item) {
        return BuiltInRegistries.ITEM.get(item);
    }

    private static Item register(ResourceLocation id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, id, item);
    }
}
