package com.yakumosakura.yakumoblade.blade;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.registry.slashblade.YAItem;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@JeiPlugin
public class JEICompat implements IModPlugin {
    public JEICompat() {
    }


    public ResourceLocation getPluginUid() {
        return Yakumoblade.prefix("slashblade");
    }

    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(YAItem.getItem(YAItem.SlashBladeOfYakumoBlade), new ISubtypeInterpreter<ItemStack>() {
            @Override
            public @Nullable String getSubtypeData(ItemStack stack, UidContext uidContext) {
                return getLegacyStringSubtypeInfo(stack, uidContext);
            }

            @Override
            public String getLegacyStringSubtypeInfo(ItemStack stack, UidContext uidContext) {
                return mods.flammpfeil.slashblade.compat.jei.JEICompat.syncSlashBlade(stack, uidContext);
            }
        });
    }
}
