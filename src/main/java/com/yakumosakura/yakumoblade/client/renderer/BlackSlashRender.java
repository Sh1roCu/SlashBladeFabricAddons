package com.yakumosakura.yakumoblade.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.Yakumoblade;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

@Environment(EnvType.CLIENT)
public class BlackSlashRender<T extends EntityAbstractSummonedSword> extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE = Yakumoblade.prefix("model/named/util/ss.png");
    private static final ResourceLocation MODEL = Yakumoblade.prefix("model/named/util/diosss.obj");

    @Nullable
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getTextureLoc();
    }

    public BlackSlashRender(EntityRendererProvider.Context context) {
        super(context);
    }

    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {

        try (MSAutoCloser ignored = MSAutoCloser.pushMatrix(matrixStack)) {
            float lifetime = 10;
            double deathTime = lifetime;
            double baseAlpha = (Math.min(deathTime, Math.max(0, (lifetime - (entity.tickCount))))
                    / deathTime);
            baseAlpha = Math.max(0, -Math.pow(baseAlpha - 1, 4.0) + 0.75);

            matrixStack.mulPose(
                    Axis.YP.rotationDegrees(Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
            matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(partialTicks, entity.xRotO, entity.getXRot())));
            matrixStack.mulPose(Axis.XP.rotationDegrees(entity.getRoll()));

            float scale = 0.015f;
            matrixStack.scale(scale, scale, scale);
            matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            int color = entity.getColor() & 0xFFFFFF;
            int alpha = ((0xFF & (int) (0xFF * baseAlpha)) << 24);
            WavefrontObject model = BladeModelManager.getInstance().getModel(MODEL);

            BladeRenderState.setCol(color | alpha);
            BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "blade", TEXTURE, matrixStack, bufferIn,
                    packedLightIn);
        }
    }
}