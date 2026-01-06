package com.yakumosakura.yakumoblade.client.renderer.slasheffectrender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.client.renderer.entity.SlashEffectRenderer;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.Face;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class BigWriteRender<T extends EntitySlashEffect> extends SlashEffectRenderer<T> {
    public BigWriteRender(EntityRendererProvider.Context context) {
        super(context);
    }

    private static final ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath("slashblade", "model/util/slash.obj");
    private static final ResourceLocation textureLocation = ResourceLocation.fromNamespaceAndPath("slashblade", "model/util/slash.png");

    @Nullable
    public ResourceLocation getTextureLocation(T entity) {
        return textureLocation;
    }

    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entity.getOwner() instanceof Player player) {
            if (SlashBladeUtil.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.SwordArtOnCrow)) {
                renderis(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            } else {
            }
        }
    }


    public static void renderis(EntitySlashEffect entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStackIn)) {
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(-Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
            matrixStackIn.mulPose(Axis.XP.rotationDegrees(entity.getRotationRoll()));
            WavefrontObject model = BladeModelManager.getInstance().getModel(modelLocation);
            int lifetime = entity.getLifetime();
            float progress = Math.min((float) lifetime, (float) entity.tickCount + partialTicks) / (float) lifetime;
            double deathTime = lifetime;
            double baseAlpha = Math.min(deathTime, Math.max(0.0F, (float) (lifetime - entity.tickCount) - partialTicks)) / deathTime;
            baseAlpha = -Math.pow(baseAlpha - (double) 1.0F, 4.0F) + (double) 1.0F;
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(entity.getRotationOffset() - 135.0F * progress));
            matrixStackIn.scale(1.0F, 0.25F, 1.0F);
            float baseScale = 1.2F;
            matrixStackIn.scale(baseScale, baseScale, baseScale);
            float yscale = 0.03F;
            float scale = entity.getBaseSize() * Mth.lerp(progress, 0.03F, 0.035F);
            int color = entity.getColor() & 16777215;
            IConcentrationRank.ConcentrationRanks rank = entity.getRankCode();
            if (rank.level < IConcentrationRank.ConcentrationRanks.C.level) {
                color = 5592405;
            }

            ResourceLocation rl = textureLocation;
            int alpha = (255 & (int) ((double) 255.0F * baseAlpha)) << 24;
            if (IConcentrationRank.ConcentrationRanks.S.level <= rank.level) {
                try (MSAutoCloser msacb = MSAutoCloser.pushMatrix(matrixStackIn)) {
                    float windscale = entity.getBaseSize() * Mth.lerp(progress, 0.035F, 0.03F);
                    matrixStackIn.scale(windscale, yscale, windscale);
                    Face.setAlphaOverride(Face.alphaOverrideYZZ);
                    Face.setUvOperator(1.0F, 1.0F, 0.0F, -0.8F + progress * 0.3F);
                    BladeRenderState.setCol(2236962 | alpha);
                    BladeRenderState.renderOverridedColorWrite(ItemStack.EMPTY, model, "base", rl, matrixStackIn, bufferIn, packedLightIn);
                }
            }

            if (IConcentrationRank.ConcentrationRanks.D.level <= rank.level) {
                try (MSAutoCloser msacb = MSAutoCloser.pushMatrix(matrixStackIn)) {
                    matrixStackIn.scale(scale, yscale, scale);
                    Face.setAlphaOverride(Face.alphaOverrideYZZ);
                    Face.setUvOperator(1.0F, 1.0F, 0.0F, -0.35F + progress * -0.15F);
                    BladeRenderState.setCol(color | alpha);
                    BladeRenderState.renderOverridedColorWrite(ItemStack.EMPTY, model, "base", rl, matrixStackIn, bufferIn, packedLightIn);
                }
            }

            if (IConcentrationRank.ConcentrationRanks.B.level <= rank.level) {
                try (MSAutoCloser msacb = MSAutoCloser.pushMatrix(matrixStackIn)) {
                    float windscale = entity.getBaseSize() * Mth.lerp(progress, 0.03F, 0.0375F);
                    matrixStackIn.scale(windscale, yscale, windscale);
                    Face.setAlphaOverride(Face.alphaOverrideYZZ);
                    Face.setUvOperator(1.0F, 1.0F, 0.0F, -0.5F + progress * -0.2F);
                    BladeRenderState.setCol(4210752 | alpha);
                    BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "base", rl, matrixStackIn, bufferIn, packedLightIn);
                }
            }
            if (IConcentrationRank.ConcentrationRanks.A.level <= rank.level) {
                try (MSAutoCloser msacb = MSAutoCloser.pushMatrix(matrixStackIn)) {
                    float windscale = entity.getBaseSize() * Mth.lerp(progress, 0.03F, 0.0375F);
                    matrixStackIn.scale(windscale, yscale, windscale);
                    Face.setAlphaOverride(Face.alphaOverrideYZZ);
                    Face.setUvOperator(1.0F, 1.0F, 0.0F, -0.5F + progress * -0.2F);
                    BladeRenderState.setCol(0 | alpha);
                    BladeRenderState.renderOverrided(ItemStack.EMPTY, model, "base", rl, matrixStackIn, bufferIn, packedLightIn);
                }
            }
            if (IConcentrationRank.ConcentrationRanks.S.level <= rank.level) {
                try (MSAutoCloser msacb = MSAutoCloser.pushMatrix(matrixStackIn)) {
                    float windscale = entity.getBaseSize() * Mth.lerp(progress, 0.03F, 0.0375F);
                    matrixStackIn.scale(windscale, yscale, windscale);
                    Face.setAlphaOverride(Face.alphaOverrideYZZ);
                    Face.setUvOperator(1.0F, 1.0F, 0.0F, -0.5F + progress * -0.2F);
                    BladeRenderState.setCol(color | alpha);
                    BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "base", rl, matrixStackIn, bufferIn, packedLightIn);
                }
            }

            try (MSAutoCloser msacb = MSAutoCloser.pushMatrix(matrixStackIn)) {
                matrixStackIn.scale(scale, yscale, scale);
                Face.setAlphaOverride(Face.alphaOverrideYZZ);
                Face.setUvOperator(1.0F, 1.0F, 0.0F, -0.35F + progress * -0.15F);
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "base", rl, matrixStackIn, bufferIn, packedLightIn);
            }
        }

    }
}
