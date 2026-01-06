package com.yakumosakura.yakumoblade.client.renderer.hexgram;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.entity.hexgram.neo.FoxHexGramExEntity;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class FoxHexGramExRender<T extends FoxHexGramExEntity> extends EntityRenderer<T> {
    public FoxHexGramExRender(EntityRendererProvider.Context context) {
        super(context);
    }

    private static final ResourceLocation TEXTURE = Yakumoblade.prefix("model/util/hexgram/hz.png");
    private static final ResourceLocation MODEL = Yakumoblade.prefix("model/util/hexgram/hz.obj");

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int packedLight) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStack)) {
            // 计算透明度
            float lifetime = entity.getLifeTime();
            double deathTime = lifetime;
            double baseAlpha = Math.min(deathTime, Math.max(0.0F, lifetime - entity.tickCount)) / deathTime;
            baseAlpha = Math.max(0.0F, -Math.pow(baseAlpha - 1.0F, 4.0F) + 0.75F);


            float rotationAngle = Mth.lerp(partialTicks, entity.getPrevRotationAngle(), entity.getRotationAngle());
            matrixStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));


            // 缩放
            float scale = 0.015F;
            matrixStack.scale(scale, scale, scale);

            // 设置颜色和透明度
            int color = 16722600;
            int alpha = (255 & (int) (255.0F * baseAlpha)) << 24;

            // 获取并渲染模型的各个部分
            WavefrontObject model = BladeModelManager.getInstance().getModel(MODEL);
            BladeRenderState.setCol(color | alpha);

            try (MSAutoCloser clockwiseMsac = MSAutoCloser.pushMatrix(matrixStack)) {
                matrixStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z1", TEXTURE, matrixStack, buffer, packedLight);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z2", TEXTURE, matrixStack, buffer, packedLight);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z4", TEXTURE, matrixStack, buffer, packedLight);
            }


            try (MSAutoCloser counterClockwiseMsac = MSAutoCloser.pushMatrix(matrixStack)) {
                matrixStack.mulPose(Axis.YP.rotationDegrees(-rotationAngle * 2)); // 负角度实现逆时针旋转
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z3", TEXTURE, matrixStack, buffer, packedLight);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z5", TEXTURE, matrixStack, buffer, packedLight);
            }
        }
    }
}