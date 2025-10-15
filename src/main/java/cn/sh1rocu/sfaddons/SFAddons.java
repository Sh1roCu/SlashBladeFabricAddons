package cn.sh1rocu.sfaddons;

import cn.mmf.energyblade.Energyblade;
import cn.mmf.energyblade.item.ItemFEBlade;
import cn.mmf.slashblade_addon.SlashBladeAddon;
import cn.mmf.slashblade_addon.specialeffect.BurstDrive;
import com.dinzeer.cialloblade.Cialloblade;
import com.dinzeer.cialloblade.se.Ciallo;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class SFAddons implements ModInitializer {

    public static final String MOD_ID = "slashblade_fabric_addons";

    @Override
    public void onInitialize() {
        // SJAP
        SlashBladeAddon.init();
        // CialloBlade
        Cialloblade.init();
        // EnergyBlade(HF Blade)
        Energyblade.init();

        subscribeEvents();
    }

    private void subscribeEvents() {
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
    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
