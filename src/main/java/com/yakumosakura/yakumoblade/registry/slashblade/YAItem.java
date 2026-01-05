package com.yakumosakura.yakumoblade.registry.slashblade;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.item.YakumBladeSlashItem;
import com.yakumosakura.yakumoblade.registry.YakumoBladeItemTier;
import mods.flammpfeil.slashblade.SlashBladeCreativeGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class YAItem {

    public static final ResourceLocation SlashBladeOfYakumoBlade = Yakumoblade.prefix("slashblade");

    public final static YakumoBladeItemTier ya(Supplier repairIngredient) {
        return new YakumoBladeItemTier(1, 200, 0, 0, 30, repairIngredient);
    }

    private static void register() {
        Registry.register(BuiltInRegistries.ITEM, SlashBladeOfYakumoBlade,
                new YakumBladeSlashItem(
                        ya(() -> BuiltInRegistries.ITEM.get(SlashBladeOfYakumoBlade)),
                        25,
                        -2.4F,
                        new Item.Properties())
        );

    }

    private static void addCreative(CreativeModeTab group, FabricItemGroupEntries entries) {
        if (group == SlashBladeCreativeGroup.SLASHBLADE_GROUP) {
            entries.accept(BuiltInRegistries.ITEM.get(SlashBladeOfYakumoBlade));
        }
    }

    public static Item getItem(ResourceLocation item) {
        return BuiltInRegistries.ITEM.get(item);
    }

    public static void init() {
        register();
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(YAItem::addCreative);
    }
}
