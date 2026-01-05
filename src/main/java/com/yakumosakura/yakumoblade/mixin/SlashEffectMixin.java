package com.yakumosakura.yakumoblade.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yakumosakura.yakumoblade.client.renderer.slasheffectrender.BigWriteRender;
import com.yakumosakura.yakumoblade.client.renderer.slasheffectrender.fast_style.FastStyleSlashEffectRender;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.client.renderer.entity.SlashEffectRenderer;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlashEffectRenderer.class)
public class SlashEffectMixin {
    @Inject(method = "render*", at = @At("HEAD"), cancellable = true, remap = false)
    public void render(EntitySlashEffect entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, CallbackInfo ci) {
        if (entity.getOwner() instanceof Player player) {
            if (SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnCrow)) {
                BigWriteRender.renderis(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                ci.cancel();
            }
            if (SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnDMC)
                    || SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnFOX)
                    || SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnDRAGON)
            ) {
                FastStyleSlashEffectRender.renderis(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                ci.cancel();
            }
        }
    }
}
