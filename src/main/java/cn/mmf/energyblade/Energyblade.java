package cn.mmf.energyblade;

import cn.mmf.energyblade.item.ItemFEBlade;
import cn.sh1rocu.sfaddons.SFAddons;
import com.mojang.logging.LogUtils;
import mods.flammpfeil.slashblade.item.ItemTierSlashBlade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

public class Energyblade {
    public static final String MODID = "energyblade";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String path) {
        return SFAddons.prefix(path).withPrefix(MODID + "/");
    }

    // FE能量拔刀剑
    // Fabric使用TechReborn的EnergyAPI 单位为E
    public static final Item FORGE_ENERGY_BLADE = Registry.register(BuiltInRegistries.ITEM, prefix("forge_energy_blade"),
            new ItemFEBlade(new ItemTierSlashBlade(40, 4F), 4, -2.4F, (new Item.Properties())));

    public static void init() {
        NetworkPacketHandler.registerMessage();
    }

    public static Logger getLogger() {
        return LOGGER;
    }

}
