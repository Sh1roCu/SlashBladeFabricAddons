package cn.sh1rocu.sfaddons.api.extension;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface IDamageable {
    default boolean isDamageable(ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem().getMaxDamage() > 0) {
            CompoundTag compoundTag = stack.getTag();
            return compoundTag == null || !compoundTag.getBoolean("Unbreakable");
        } else {
            return false;
        }
    }
}