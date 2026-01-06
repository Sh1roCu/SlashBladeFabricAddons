package com.yakumosakura.yakumoblade.registry;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.item.StarSoulCrystal;
import com.yakumosakura.yakumoblade.item.StarTreasureScripture;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final Item UMLE = regmaker("umle");
    public static final Item Star_Soul_Crystal = regmaker("star_soul_crystal",
            StarSoulCrystal::new);
    public static final Item StarTreasurescripture = regmaker("star_treasure_scripture",
            StarTreasureScripture::new);
    public static final Item powercore = regmaker("powercore");
    public static final Item powercoreex = regmaker("powercoreex");
    public static final Item ghost_ingot = regmaker("ghost_ingot");
    public static final Item red_eyes = regmaker("red_eyes");
    public static final Item beyond_flower = regmaker("beyond_flower");//地狱彼岸花
    public static final Item legend_od_the_star = regmaker("legend_od_the_star");//星之传说
    public static final Item ungod_s_crown = regmaker("ungod_s_crown");//未成的神格
    public static final Item red_iron_ingot = regmaker("red_iron_ingot");//泛红的铁锭
    public static final Item ark_metal_ingot = regmaker("ark_metal_ingot");//炼狱钢锭
    public static final Item blaze_umle = regmaker("blaze_umle");//烈焰锻造模板
    public static final Item the_god_s_soul = regmaker("the_god_s_soul");//神录魂
    public static final Item blood_sakura = regmaker("blood_sakura");//血樱
    public static final Item westward_youkai_wood = regmaker("westward_youkai_wood");//西行妖树枝
    public static final Item metal_ingot = regmaker("metal_ingot");//钢锭


    public static void init() {
    }


    public static Item regmaker(String name) {
        return regmaker(name, () -> new Item(new Item.Properties().stacksTo(555)));
    }

    public static Item regmaker(String name, Supplier<? extends Item> sup) {
        return Registry.register(BuiltInRegistries.ITEM, Yakumoblade.prefix(name), sup.get());
    }
}
