package cn.mmf.energyblade.data;

import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class SlashBladeRecipeProvider extends FabricRecipeProvider {

    public SlashBladeRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        SlashBladeShapedRecipeBuilder.shaped(BuiltInSlashBladeRegistry.HF_BLADE.location())
                .pattern("SLJ")
                .pattern("LBL")
                .pattern("JLS")
                .define('B',
                        SlashBladeIngredient
                                .of(RequestDefinition.Builder.newInstance().refineCount(10).build()).toVanilla())
                .define('S', Ingredient.of(SBItems.proudsoul_sphere))
                .define('J', Ingredient.of(mods.flammpfeil.slashblade.data.SlashBladeRecipeProvider.STORAGE_BLOCKS_REDSTONE))
                .define('L', Ingredient.of(mods.flammpfeil.slashblade.data.SlashBladeRecipeProvider.STORAGE_BLOCKS_IRON))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

    }

}
