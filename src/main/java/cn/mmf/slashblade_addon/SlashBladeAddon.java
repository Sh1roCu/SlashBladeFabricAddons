package cn.mmf.slashblade_addon;

import cn.mmf.slashblade_addon.compat.SBATofuCraftItems;
import cn.mmf.slashblade_addon.compat.botania.SBABotaniaCompat;
import cn.mmf.slashblade_addon.registry.SBAComboStateRegistry;
import cn.mmf.slashblade_addon.registry.SBAEntitiesRegistry;
import cn.mmf.slashblade_addon.registry.SBASlashArtsRegistry;
import cn.mmf.slashblade_addon.registry.SBASpecialEffectsRegistry;
import cn.sh1rocu.sfaddons.SFAddons;
import com.mojang.logging.LogUtils;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.init.SBItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

public class SlashBladeAddon {
    public static final String MODID = "slashblade_addon";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab SJAP_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, prefix("sjap_tab"),
            FabricItemGroup.builder()
                    .title(Component.translatable("itemGroup.slashblade_addon.sjap_tab"))
                    .icon(
                            () -> {
                                ItemStack stack = new ItemStack(SBItems.slashblade);
                                CapabilitySlashBlade.getBladeState(stack).ifPresent(s -> {
                                    s.setModel(prefix("model/murakumo/model.obj"));
                                    s.setTexture(prefix("model/murakumo/texture.png"));
                                });
                                return stack;
                            }


                    ).build());

    public static void init() {
        register();

        SBASlashArtsRegistry.init();
        SBAComboStateRegistry.init();
        SBASpecialEffectsRegistry.init();
        if (FabricLoader.getInstance().isModLoaded("botania")) {
            SBABotaniaCompat.init();
        }

        CommonEventHandler.onVillagerTrades();
        CommonEventHandler.onWandererTrades();
    }

    public static ResourceLocation prefix(String path) {
        return SFAddons.prefix(path).withPrefix(MODID + "/");
    }

    private static void register() {
        if (FabricLoader.getInstance().isModLoaded("tofucraft")) {
            SBATofuCraftItems.init();
        }
        SBAEntitiesRegistry.init();
    }
}
