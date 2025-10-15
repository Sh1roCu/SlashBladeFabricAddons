package cn.mmf.energyblade.compat;

import cn.mmf.energyblade.Energyblade;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.registration.ISubtypeRegistration;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@JeiPlugin
public class JEICompat implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Energyblade.MODID, Energyblade.MODID);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(Energyblade.FORGE_ENERGY_BLADE, new ISubtypeInterpreter<ItemStack>() {
            @Override
            public @Nullable Object getSubtypeData(ItemStack itemStack, UidContext uidContext) {
                return CapabilitySlashBlade.getBladeState(itemStack).map(ISlashBladeState::getTranslationKey).orElse("");
            }

            @Override
            public String getLegacyStringSubtypeInfo(ItemStack itemStack, UidContext uidContext) {
                return CapabilitySlashBlade.getBladeState(itemStack).map(ISlashBladeState::getTranslationKey).orElse("");
            }
        });
    }

}
