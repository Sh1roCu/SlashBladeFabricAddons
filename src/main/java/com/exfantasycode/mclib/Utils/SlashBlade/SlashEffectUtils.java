package com.exfantasycode.mclib.Utils.SlashBlade;

import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class SlashEffectUtils {
    public static boolean hasSpecialEffect(ItemStack stack, String effect) {
        Optional<ISlashBladeState> bladeState = CapabilitySlashBlade.getBladeState(stack);
        return bladeState.map(state -> state.hasSpecialEffect(ResourceLocation.parse(effect))).orElse(false);
    }
}