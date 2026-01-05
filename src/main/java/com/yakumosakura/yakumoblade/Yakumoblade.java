package com.yakumosakura.yakumoblade;

import cn.sh1rocu.sfaddons.SFAddons;
import com.mojang.logging.LogUtils;
import com.yakumosakura.yakumoblade.compat.YATouHouMaidItem;
import com.yakumosakura.yakumoblade.registry.RegistryEventBus;
import com.yakumosakura.yakumoblade.registry.SAchangeList;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YAItem;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import fuzs.forgeconfigapiport.api.config.v2.ModConfigEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Yakumoblade {

    public static final String MODID = "yakumoblade";
    public static final String TLM = "touhou_little_maid";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String path) {
        return SFAddons.prefix(path).withPrefix(MODID + "/");
    }

    public static ResourceLocation lbprefix(String path) {
        return new ResourceLocation("legendblade", path);
    }


    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();


    public static void queueServerWork(int tick, Runnable action) {
        var server = SFAddons.getServer();
        if (server != null && server.isSameThread())
            workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }

    public static void init() {
        ForgeConfigRegistry.INSTANCE.register(SFAddons.MOD_ID, ModConfig.Type.COMMON, Config.SPEC, MODID);
        ModConfigEvents.loading(SFAddons.MOD_ID).register(Config::onLoad);

        SAchangeList.init();

        register();

        RegistryEventBus.Registry();

        if (FabricLoader.getInstance().isModLoaded(TLM)) {
            ItemGroupEvents.MODIFY_ENTRIES_ALL.register(YATouHouMaidItem::addCreative);
        }


    }

    public static void tick(MinecraftServer server) {
        List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getKey().run());
        workQueue.removeAll(actions);
    }

    public static void register() {
        if (FabricLoader.getInstance().isModLoaded(TLM)) {
            YATouHouMaidItem.init();
        }
        YAItem.init();
        YAEntitiesRegistry.init();
    }

}
