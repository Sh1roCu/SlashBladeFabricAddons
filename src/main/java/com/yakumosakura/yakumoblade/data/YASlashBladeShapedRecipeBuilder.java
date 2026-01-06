package com.yakumosakura.yakumoblade.data;

import com.yakumosakura.yakumoblade.compat.YATouHouMaidItem;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class YASlashBladeShapedRecipeBuilder extends SlashBladeShapedRecipeBuilder {
    public YASlashBladeShapedRecipeBuilder(ItemLike item, int count) {
        super(item, count);
    }

    public static SlashBladeShapedRecipeBuilder shaped(ResourceLocation blade) {
        return shaped(YATouHouMaidItem.getItem(YATouHouMaidItem.YELLOW_FOX), 1).blade(blade);
    }


}
