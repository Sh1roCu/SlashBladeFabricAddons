package com.yakumosakura.yakumoblade.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.entity.drive.StarDriveEnity;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.client.renderer.entity.DriveRenderer;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class StarDriveRender<T extends StarDriveEnity> extends DriveRenderer<T> {
    public StarDriveRender(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    private static final ResourceLocation TEXTURE = Yakumoblade.prefix("model/util/star_end.png");
    private static final ResourceLocation MODEL = SlashBlade.prefix("model/util/drive.obj");

    @Nullable
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getTextureLoc();
    }


    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStack)) {
            float lifetime = entity.getLifetime();
            double deathTime = lifetime;
            double baseAlpha = Math.min(deathTime, Math.max(0.0F, lifetime - (float) entity.tickCount)) / deathTime;
            baseAlpha = Math.max(0.0F, -Math.pow(baseAlpha - (double) 1.0F, 4.0F) + (double) 0.75F);
            matrixStack.mulPose(Axis.YP.rotationDegrees(Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
            matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(partialTicks, entity.xRotO, entity.getXRot())));
            matrixStack.mulPose(Axis.XP.rotationDegrees(entity.getRotationRoll()));
            float scale = 0.015F;
            matrixStack.scale(scale, scale, scale);
            matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            int color = entity.getColor() & 16777215;
            int alpha = (255 & (int) ((double) 255.0F * baseAlpha)) << 24;
            WavefrontObject model = BladeModelManager.getInstance().getModel(MODEL);
            BladeRenderState.setCol(color | alpha);
            BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "base", TEXTURE, matrixStack, bufferIn, packedLightIn);
        }
    }

}
