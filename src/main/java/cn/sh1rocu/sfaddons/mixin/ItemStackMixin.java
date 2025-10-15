package cn.sh1rocu.sfaddons.mixin;

import cn.sh1rocu.sfaddons.api.extension.IDamageable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Shadow
    public abstract boolean isEmpty();

    @Shadow
    @Nullable
    public abstract CompoundTag getTag();

    @Inject(method = "isDamageableItem", at = @At("HEAD"), cancellable = true)
    private void sf$isDamageable(CallbackInfoReturnable<Boolean> cir) {
        if (this.getItem() instanceof IDamageable damageable) {
            if (this.isEmpty())
                return;
            if (damageable.isDamageable((ItemStack) (Object) this)) {
                CompoundTag compoundtag = this.getTag();
                cir.setReturnValue(compoundtag == null || !compoundtag.getBoolean("Unbreakable"));
            } else {
                cir.setReturnValue(false);
            }
        }
    }
}