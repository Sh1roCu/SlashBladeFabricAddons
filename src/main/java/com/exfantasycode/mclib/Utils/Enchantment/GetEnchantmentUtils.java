package com.exfantasycode.mclib.Utils.Enchantment;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class GetEnchantmentUtils {
    static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
    }
}
