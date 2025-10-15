package cn.sh1rocu.sfaddons.data;

import cn.mmf.energyblade.data.SlashBladeRecipeProvider;
import cn.mmf.slashblade_addon.data.SlashBladeAddonRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

import java.util.concurrent.CompletableFuture;

public class SFAddonsRecipeProvider extends FabricRecipeProvider {
    private final CompletableFuture<HolderLookup.Provider> registryFuture;

    public SFAddonsRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        this.registryFuture = registriesFuture;
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        // SJAP
        new SlashBladeAddonRecipeProvider(output, registryFuture).buildRecipes(consumer);
        // EnergyBlade(HF Blade)
        new SlashBladeRecipeProvider(output, registryFuture).buildRecipes(consumer);
    }
}
