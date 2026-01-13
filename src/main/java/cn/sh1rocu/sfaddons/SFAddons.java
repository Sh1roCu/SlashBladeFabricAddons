package cn.sh1rocu.sfaddons;

import cn.mmf.energyblade.Energyblade;
import cn.mmf.energyblade.item.ItemFEBlade;
import cn.mmf.slashblade_addon.SlashBladeAddon;
import cn.mmf.slashblade_addon.specialeffect.BurstDrive;
import com.dinzeer.cialloblade.Cialloblade;
import com.dinzeer.cialloblade.se.Ciallo;
import com.exfantasycode.mclib.Utils.Dash.DashMessage;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.event.ChangeTypeEvent;
import com.yakumosakura.yakumoblade.event.StarSoulEvent;
import com.yakumosakura.yakumoblade.event.TypeMakerCanChange;
import com.yakumosakura.yakumoblade.event.WitherDrop;
import com.yakumosakura.yakumoblade.network.MakeGapMessage;
import com.yakumosakura.yakumoblade.network.TypeMakerMessage;
import com.yakumosakura.yakumoblade.specialeffects.SEEventBus;
import com.yakumosakura.yakumoblade.specialeffects.compat.YellowFoxBoost;
import com.yakumosakura.yakumoblade.specialeffects.theblades.GhostBlade;
import com.yakumosakura.yakumoblade.specialeffects.touhouSE.BeyondYuyuko;
import com.yakumosakura.yakumoblade.utils.WaitingTick;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingDamageEvent;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingDropsEvent;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

public class SFAddons implements ModInitializer {

    public static final String MOD_ID = "slashblade_fabric_addons";

    @Nullable
    private static MinecraftServer server;

    @Override
    public void onInitialize() {
        // SJAP
        SlashBladeAddon.init();
        // CialloBlade
        Cialloblade.init();
        // EnergyBlade(HF Blade)
        Energyblade.init();
        // YakumoBlade
        Yakumoblade.init();

        registerC2SPackets();
        registerS2CPackets();

        subscribeEvents();
    }

    private void subscribeEvents() {
        ServerLifecycleEvents.SERVER_STARTING.register((server) -> SFAddons.server = server);
        ServerLifecycleEvents.SERVER_STOPPED.register((server) -> SFAddons.server = null);

        // SJAP
        SlashBladeEvent.DO_SLASH.register(BurstDrive::onDoingSlash);
        // CialloBlade
        SlashBladeEvent.HIT.register(Ciallo::onHitEntity);
        SlashBladeEvent.UPDATE.register(Ciallo::onUpdate);
        SlashBladeEvent.DO_SLASH.register(Ciallo::doSlash);
        // EnergyBlade(HF Blade)
        SlashBladeEvent.UPDATE.register(ItemFEBlade::onSlashBladeUpdate);
        SlashBladeEvent.HIT.register(ItemFEBlade::onSlashBladeHit);
        SlashBladeEvent.POWER_BLADE.register(ItemFEBlade::onSlashBladePowered);
        // YakumoBlade
        ServerTickEvents.END_SERVER_TICK.register(Yakumoblade::tick);
        TypeMakerCanChange.EVENT.register(ChangeTypeEvent::ChangeType);
        SlashBladeEvent.BLADE_STAND_ATTACK.register(StarSoulEvent::hitBladeShelf);
        LivingDropsEvent.EVENT.register(WitherDrop::WitherDead);
        if (FabricLoader.getInstance().isModLoaded(Yakumoblade.TLM)) {
            SlashBladeEvent.DO_SLASH.register(YellowFoxBoost::yellowfoxdoslash);
            SlashBladeEvent.UPDATE.register(YellowFoxBoost::yellowfoxupdate);
        }
        SlashBladeEvent.ADD_KILL_COUNT.register(GhostBlade::onKillCountAdd);
        SlashBladeEvent.ADD_PROUD_SOUL.register(GhostBlade::onProudCountAdd);
        LivingDamageEvent.DAMAGE.register(BeyondYuyuko::onLivingDamage);
        ServerLivingEntityEvents.AFTER_DEATH.register(BeyondYuyuko::onLivingDeath);
        SlashBladeEvent.SUMMONEDSWORD_ONHIT_ENTITY.register(BeyondYuyuko::onLivingUpdate);
        SlashBladeEvent.UPDATE.register(BeyondYuyuko::onLivingUpdate);
        SlashBladeEvent.UPDATE.register(SEEventBus::OnUpdate);
        SlashBladeEvent.DO_SLASH.register(SEEventBus::DoSlash);
        SlashBladeEvent.HIT.register(SEEventBus::onSlashHit);
        LivingHurtEvent.EVENT.register(SEEventBus::onLivingHurtEvent);
        SEEventBus.onLivingDeathEvent();
        ServerTickEvents.END_SERVER_TICK.register(WaitingTick::onServerTick);
    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static @Nullable MinecraftServer getServer() {
        return server;
    }

    private static void registerC2SPackets() {
        registerC2SPacket(TypeMakerMessage.TYPE, TypeMakerMessage::handler, TypeMakerMessage.STREAM_CODEC);
        registerC2SPacket(MakeGapMessage.TYPE, MakeGapMessage::handler, MakeGapMessage.STREAM_CODEC);
    }

    private static void registerS2CPackets() {
        // YakumoBlade
        registerS2CPacket(DashMessage.TYPE, DashMessage.STREAM_CODEC);
    }

    public static <T extends CustomPacketPayload> void registerC2SPacket(CustomPacketPayload.Type<T> type, ServerPlayNetworking.PlayPayloadHandler<T> handler, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        PayloadTypeRegistry.playC2S().register(type, codec);
        ServerPlayNetworking.registerGlobalReceiver(type, handler);
    }

    public static <T extends CustomPacketPayload> void registerS2CPacket(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        PayloadTypeRegistry.playS2C().register(type, codec);
    }
}
