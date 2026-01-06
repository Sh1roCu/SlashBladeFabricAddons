package cn.sh1rocu.sfaddons.client;

import cn.mmf.energyblade.client.ClientSetupHandler;
import cn.mmf.energyblade.client.InputHandler;
import cn.mmf.slashblade_addon.client.SJAPClientHandler;
import cn.mmf.slashblade_addon.compat.PlayerAnimationRegisterEvent;
import cn.mmf.slashblade_addon.registry.SBAEntitiesRegistry;
import com.exfantasycode.mclib.Utils.Dash.DashMessage;
import com.yakumosakura.yakumoblade.client.ClientHandler;
import com.yakumosakura.yakumoblade.client.YakumoBladeCilent;
import com.yakumosakura.yakumoblade.registry.YAModKeyMappings;
import io.github.fabricators_of_create.porting_lib.event.client.KeyInputCallback;
import mods.flammpfeil.slashblade.client.renderer.entity.DriveRenderer;
import mods.flammpfeil.slashblade.client.renderer.entity.SummonedSwordRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class SFAddonsClient implements ClientModInitializer, ModelLoadingPlugin {

    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register(this);
        onRegisterRenderers();

        // SJAP
        PlayerAnimationRegisterEvent.onRegisterPlayerAnim();
        SJAPClientHandler.doClientStuff();
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(SJAPClientHandler::addCreative);

        // EnergyBlade(HF Blade)
        ClientSetupHandler.setModelUser();
        ClientSetupHandler.registerKeyMapping();

        // YakumoBlade
        ClientHandler.doClientStuff();
        YAModKeyMappings.registerKeyMappings();

        registerNetworkReceivers();
        subscribeEvents();
    }

    @Override
    public void onInitializeModelLoader(Context plugin) {
        // SJAP
        SJAPClientHandler.baked(plugin);

        // EnergyBlade(HF Blade)
        ClientSetupHandler.baked(plugin);

        // YakumoBlade
        ClientHandler.baked(plugin);
    }

    private static void onRegisterRenderers() {
        // SJAP
        EntityRendererRegistry.register(SBAEntitiesRegistry.BlisteringSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.SpiralEdgeSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.GaleSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.LightingSwords, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(SBAEntitiesRegistry.WaterDrive, DriveRenderer::new);
        // YakumoBlade
        YakumoBladeCilent.registerEntityRenderers();
    }

    private static void subscribeEvents() {
        // EnergyBlade(HF Blade)
        KeyInputCallback.EVENT.register(InputHandler::onPlayerPostTick);

        // YakumoBlade
        ClientTickEvents.START_CLIENT_TICK.register(YAModKeyMappings.KeyEventListener::onClientTick);
        ClientTickEvents.END_CLIENT_TICK.register(YAModKeyMappings.KeyEventListener::onClientTick);
    }

    private static void registerNetworkReceivers() {
        // YakumoBlade
        registerNetworkReceiver(DashMessage.TYPE, DashMessage::handle);
    }

    private static <T extends CustomPacketPayload> void registerNetworkReceiver(CustomPacketPayload.Type<T> type, ClientPlayNetworking.PlayPayloadHandler<T> handler) {
        ClientPlayNetworking.registerGlobalReceiver(type, handler);
    }
}
