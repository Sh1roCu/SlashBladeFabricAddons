package cn.sh1rocu.sfaddons.client;

import cn.mmf.slashblade_addon.client.SJAPClientHandler;
import cn.mmf.slashblade_addon.compat.PlayerAnimationRegisterEvent;
import cn.mmf.slashblade_addon.registry.SBAEntitiesRegistry;
import mods.flammpfeil.slashblade.client.renderer.entity.DriveRenderer;
import mods.flammpfeil.slashblade.client.renderer.entity.SummonedSwordRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class SFAddonsClient implements ClientModInitializer, ModelLoadingPlugin {

    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register(this);
        onRegisterRenderers();

        // SJAP
        PlayerAnimationRegisterEvent.onRegisterPlayerAnim();
        SJAPClientHandler.doClientStuff();
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(SJAPClientHandler::addCreative);

    }

    @Override
    public void onInitializeModelLoader(Context plugin) {
        // SJAP
        SJAPClientHandler.Baked(plugin);
    }

    private static void onRegisterRenderers() {
        // SJAP
        EntityRendererRegistry.register(SBAEntitiesRegistry.BlisteringSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.SpiralEdgeSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.GaleSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.LightingSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.WaterDrive, DriveRenderer::new);
    }
}
