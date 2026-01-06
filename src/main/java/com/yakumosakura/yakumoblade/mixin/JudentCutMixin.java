package com.yakumosakura.yakumoblade.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yakumosakura.yakumoblade.client.renderer.slasheffectrender.fast_style.FastStyleJudgementCut;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.client.renderer.entity.JudgementCutRenderer;
import mods.flammpfeil.slashblade.entity.EntityJudgementCut;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JudgementCutRenderer.class)
public class JudentCutMixin {
    @Inject(method = "render*", at = @At("HEAD"), cancellable = true, remap = false)
    public void render(EntityJudgementCut entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, CallbackInfo ci) {
        if (entity.getOwner() instanceof Player player) {

            if (SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnDMC)
                    || SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnFOX)
                    || SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnDRAGON)
            ) {
                FastStyleJudgementCut.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
                ci.cancel();
            }
        }
    }
}
