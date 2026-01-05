package cn.sh1rocu.sfaddons.mixin.common;

import cn.sh1rocu.sfaddons.api.extension.IEntityListener;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentEntitySectionManager.class)
public class PersistentEntitySectionManagerMixin {
    @Inject(method = {"method_31854", "method_31863", "method_31864"}, at = @At("TAIL"))
    private static void sb$addedToWorld(EntityAccess entityAccess, CallbackInfo ci) {
        if (entityAccess instanceof IEntityListener entity)
            entity.onAddedToWorld();
    }
}