package cn.sh1rocu.sfaddons.mixin.common;

import cn.sh1rocu.sfaddons.api.extension.IEntityListener;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "setPosRaw", at = @At("TAIL"))
    private void sb$setPosRaw(double x, double y, double z, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (self instanceof IEntityListener entity && entity.isAddedToWorld() && !self.level().isClientSide && !self.isRemoved())
            //强加载区块
            self.level().getChunk((int) Math.floor(x) >> 4, (int) Math.floor(z) >> 4);
    }
}
