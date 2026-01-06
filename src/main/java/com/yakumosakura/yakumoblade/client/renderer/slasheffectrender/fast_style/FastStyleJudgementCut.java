package com.yakumosakura.yakumoblade.client.renderer.slasheffectrender.fast_style;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.Yakumoblade;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import mods.flammpfeil.slashblade.entity.EntityJudgementCut;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class FastStyleJudgementCut {
    private static final ResourceLocation modelLocation = Yakumoblade.prefix("model/util/specialrender/fast_style/slashdim.obj");
    private static final ResourceLocation textureLocation = ResourceLocation.fromNamespaceAndPath("slashblade", "model/util/slashdim.png");


    public static void render(EntityJudgementCut entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStackIn)) {
            matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
            WavefrontObject model = BladeModelManager.getInstance().getModel(modelLocation);
            int lifetime = entity.getLifetime();
            double deathTime = lifetime;
            double baseAlpha = Math.min(deathTime, Math.max(0.0F, (float) (lifetime - entity.tickCount) - partialTicks)) / deathTime;
            baseAlpha = -Math.pow(baseAlpha - (double) 1.0F, 4.0F) + (double) 1.0F;
            int seed = entity.getSeed();
            matrixStackIn.mulPose(Axis.YP.rotationDegrees((float) seed));
            float scale = 0.01F;
            matrixStackIn.scale(scale, scale, scale);
            int color = entity.getColor() & 16777215;
            Color col = new Color(color);
            float[] hsb = Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), null);
            int baseColor = Color.HSBtoRGB(0.5F + hsb[0], hsb[1], 0.2F) & 16777215;

            try (MSAutoCloser msacB = MSAutoCloser.pushMatrix(matrixStackIn)) {
                for (int l = 0; l < 5; ++l) {
                    matrixStackIn.scale(0.95F, 0.95F, 0.95F);
                    BladeRenderState.setCol(baseColor | (255 & (int) ((double) 102.0F * baseAlpha)) << 24);
                    BladeRenderState.renderOverridedReverseLuminous(ItemStack.EMPTY, model, "base", textureLocation, matrixStackIn, bufferIn, packedLightIn);
                }
            }

            int loop = 3;

            for (int l = 0; l < loop; ++l) {
                try (MSAutoCloser msacB = MSAutoCloser.pushMatrix(matrixStackIn)) {
                    float cycleTicks = 15.0F;
                    float wave = ((float) entity.tickCount + cycleTicks / (float) loop * (float) l + partialTicks) % cycleTicks;
                    float waveScale = 1.0F + 0.03F * wave;
                    matrixStackIn.scale(waveScale, waveScale, waveScale);
                    BladeRenderState.setCol(baseColor | (int) ((double) (136.0F * ((cycleTicks - wave) / cycleTicks)) * baseAlpha) << 24);
                    BladeRenderState.renderOverridedReverseLuminous(ItemStack.EMPTY, model, "base", textureLocation, matrixStackIn, bufferIn, packedLightIn);
                }
            }

            int windCount = 5;

            for (int l = 0; l < windCount; ++l) {
                try (MSAutoCloser msacB = MSAutoCloser.pushMatrix(matrixStackIn)) {
                    matrixStackIn.mulPose(Axis.XP.rotationDegrees(360.0F / (float) windCount * (float) l));
                    matrixStackIn.mulPose(Axis.YP.rotationDegrees(30.0F));
                    double rotWind = 18.0F;
                    double offsetBase = 7.0F;
                    double offset = (double) l * offsetBase;
                    double motionLen = offsetBase * (double) (windCount - 1);
                    double ticks = (float) entity.tickCount + partialTicks + (float) seed;
                    double offsetTicks = ticks + offset;
                    double progress = offsetTicks % motionLen / motionLen;
                    double rad = (Math.PI * 2D);
                    rad *= progress;
                    float windScale = (float) (0.4 + progress);
                    matrixStackIn.scale(windScale, windScale, windScale);
                    matrixStackIn.mulPose(Axis.ZP.rotationDegrees((float) (rotWind * offsetTicks)));
                    Color cc = new Color(col.getRed(), col.getGreen(), col.getBlue(), 255 & (int) Math.min(0.0F, (double) 255.0F * Math.sin(rad) * baseAlpha));
                    BladeRenderState.setCol(cc);
                    BladeRenderState.renderOverridedColorWrite(ItemStack.EMPTY, model, "wind", textureLocation, matrixStackIn, bufferIn, 15728864);
                }
            }
        }

    }
}
