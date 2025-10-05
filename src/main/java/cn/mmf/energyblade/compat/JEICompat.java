package cn.mmf.energyblade.compat;

import cn.mmf.energyblade.Energyblade;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.ISubtypeRegistration;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEICompat implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Energyblade.MODID, Energyblade.MODID);
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(Energyblade.FORGE_ENERGY_BLADE, (stack, context) -> CapabilitySlashBlade.BLADESTATE.maybeGet(stack).map(ISlashBladeState::getTranslationKey).orElse(""));
    }

}
