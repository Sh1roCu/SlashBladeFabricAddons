package cn.sh1rocu.sfaddons.api.extension;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public interface IDamageable {
    default boolean isDamageable(ItemStack stack) {
        return stack.has(DataComponents.MAX_DAMAGE) && !stack.has(DataComponents.UNBREAKABLE) && stack.has(DataComponents.DAMAGE);
    }
}