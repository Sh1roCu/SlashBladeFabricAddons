package com.yakumosakura.yakumoblade.item;

import com.yakumosakura.yakumoblade.Yakumoblade;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import twilightforest.TFConfig;
import twilightforest.item.recipe.UncraftingRecipe;

import java.util.Objects;

public class Other {
    public static UncraftingRecipe createNineDirtRecipe() {
        ItemStack output = new ItemStack(Items.DIRT, 9);
        Ingredient input = Ingredient.of(BuiltInRegistries.ITEM.get(new ResourceLocation("slashblade", "slashblade")));
        NonNullList<Ingredient> outputs = NonNullList.of(Ingredient.of(output));

        return new UncraftingRecipe(
                Yakumoblade.prefix("uncrafting_recipe_dirt_aaaa"),
                1,
                3,
                3,
                input,
                1,
                outputs // 修改为包装后的NonNullList
        );
    }

    public static boolean matches(ItemStack input, ItemStack output) {
        return input.is(output.getItem()) && input.getCount() >= output.getCount();
    }

    public static boolean isRecipeSupported(Recipe<?> recipe) {
        return TFConfig.COMMON_CONFIG.UNCRAFTING_STUFFS.allowShapelessUncrafting.get() ? recipe instanceof CraftingRecipe : recipe instanceof ShapedRecipe;
    }


    public static boolean compareNbtString(CompoundTag nbt1, CompoundTag nbt2, String key) {
        String value1 = nbt1.contains(key, Tag.TAG_STRING) ? nbt1.getString(key) : null;
        String value2 = nbt2.contains(key, Tag.TAG_STRING) ? nbt2.getString(key) : null;
        return Objects.equals(value1, value2);
    }
}
