package com.dinzeer.cialloblade.se;

import com.dinzeer.cialloblade.Cialloblade;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.Registry;

public class SERegsitry {
    public static void init() {

    }

    public static final SpecialEffect CIALLO;

    static {
        CIALLO = register("ciallo", new Ciallo());
    }

    private static SpecialEffect register(String name, SpecialEffect effect) {
        return Registry.register(SpecialEffectsRegistry.SPECIAL_EFFECT, Cialloblade.prefix(name), effect);
    }
}
