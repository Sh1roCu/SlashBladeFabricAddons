package com.yakumosakura.yakumoblade.compat;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.item.TouHouMaid;
import com.yakumosakura.yakumoblade.registry.YakumoBladeItemTier;
import mods.flammpfeil.slashblade.SlashBladeCreativeGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class YATouHouMaidItem {

    public static final ResourceLocation YELLOW_FOX = Yakumoblade.prefix("slashblade_yellowfox");

    public final static YakumoBladeItemTier ya(Supplier repairIngredient) {
        return new YakumoBladeItemTier(BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 0, 0, 30, repairIngredient);
    }

    private static void register() {
        Registry.register(BuiltInRegistries.ITEM, YELLOW_FOX,
                new TouHouMaid(
                        ya(() -> BuiltInRegistries.ITEM.get(YELLOW_FOX)),
                        25,
                        -2.4F,
                        new Item.Properties())
        );
    }

    public static void addCreative(CreativeModeTab group, FabricItemGroupEntries entries) {
        if (group == SlashBladeCreativeGroup.SLASHBLADE_GROUP) {
            entries.accept(BuiltInRegistries.ITEM.get(YELLOW_FOX));
        }
    }

    public static Item getItem(ResourceLocation item) {
        return BuiltInRegistries.ITEM.get(item);
    }

    public static void init() {
        register();
    }
}
