package cn.sh1rocu.sfaddons.api.extension;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IDamageable {
    default boolean isDamageable(ItemStack stack) {
        return ((Item) this).canBeDepleted();
    }
}