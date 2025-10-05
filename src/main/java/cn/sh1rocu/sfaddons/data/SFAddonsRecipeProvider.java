package cn.sh1rocu.sfaddons.data;

import cn.mmf.energyblade.data.SlashBladeRecipeProvider;
import cn.mmf.slashblade_addon.data.SlashBladeAddonRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class SFAddonsRecipeProvider extends FabricRecipeProvider {
    public SFAddonsRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // SJAP
        new SlashBladeAddonRecipeProvider(output).buildRecipes(consumer);
        // EnergyBlade(HF Blade)
        new SlashBladeRecipeProvider(output).buildRecipes(consumer);
    }
}
