package com.exfantasycode.mclib.Utils.Item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class GetItemUtil {
    public Item getItem(ResourceLocation item) {
        return BuiltInRegistries.ITEM.get(item);
    }
}
