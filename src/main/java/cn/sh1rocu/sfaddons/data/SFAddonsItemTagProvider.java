package cn.sh1rocu.sfaddons.data;

import cn.mmf.energyblade.Energyblade;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class SFAddonsItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public SFAddonsItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.getOrCreateTagBuilder(ItemTags.SWORDS).add(
                Energyblade.FORGE_ENERGY_BLADE
        );
    }
}
