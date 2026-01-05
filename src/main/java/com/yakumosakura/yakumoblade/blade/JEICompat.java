package com.yakumosakura.yakumoblade.blade;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.registry.slashblade.YAItem;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEICompat implements IModPlugin {
    public JEICompat() {
    }


    public ResourceLocation getPluginUid() {
        return Yakumoblade.prefix("slashblade");
    }

    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(YAItem.getItem(YAItem.SlashBladeOfYakumoBlade), mods.flammpfeil.slashblade.compat.jei.JEICompat::syncSlashBlade);
    }
}
