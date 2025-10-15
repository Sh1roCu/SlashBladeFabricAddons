package com.dinzeer.cialloblade;

import cn.sh1rocu.sfaddons.SFAddons;
import com.dinzeer.cialloblade.se.SERegsitry;
import com.dinzeer.cialloblade.sound.SoundRegsitry;
import com.mojang.logging.LogUtils;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.init.SBItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

public class Cialloblade {

    public static final String MODID = "cialloblade";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String path) {
        return SFAddons.prefix(path).withPrefix(MODID + "/");
    }

    public static final CreativeModeTab CIALLO_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, prefix("ciallo_tab"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.cialloblade.ciallo_tab"))
                    .icon(
                            () -> {
                                ItemStack stack = new ItemStack(SBItems.slashblade);
                                CapabilitySlashBlade.getBladeState(stack).ifPresent(s -> {
                                    s.setModel(prefix("model/ciallo/blademaster.obj"));
                                    s.setTexture(prefix("model/ciallo/ciallo.png"));
                                });
                                return stack;
                            }


                    ).build());


    public static void init() {
        SERegsitry.init();
        // 注册声音
        SoundRegsitry.init();

    }
}
