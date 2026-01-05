package com.yakumosakura.yakumoblade;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    public static final ForgeConfigSpec.DoubleValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 0, 0, Double.MAX_VALUE);

    public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty(split("items"), () -> List.of("minecraft:iron_ingot"), Config::validateItemName);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean logDirtBlock;
    public static Double magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && BuiltInRegistries.ITEM.containsKey(new ResourceLocation(itemName));
    }

    public static void onLoad(ModConfig config) {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());
    }

    private static Splitter DOT_SPLITTER = Splitter.on(".");

    private static List<String> split(String path) {
        if (DOT_SPLITTER == null) {
            DOT_SPLITTER = Splitter.on(".");
        }
        return Lists.newArrayList(DOT_SPLITTER.split(path));
    }
}
