package com.yakumosakura.yakumoblade.client.renderer.hexgram;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.entity.hexgram.neo.DragonHexGramEntity;
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

public class DragonHexGramRender<T extends DragonHexGramEntity> extends EntityRenderer<T> {
    public DragonHexGramRender(EntityRendererProvider.Context context) {
        super(context);
    }

    private static final ResourceLocation TEXTURE = Yakumoblade.prefix("model/util/hexgram/lz.png");
    private static final ResourceLocation MODEL = Yakumoblade.prefix("model/util/hexgram/lz.obj");

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int packedLight) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStack)) {


            float rotationAngle = Mth.lerp(partialTicks, entity.getPrevRotationAngle(), entity.getRotationAngle());

            // 根据阶段调整旋转速度
            float rotationMultiplier = 1.0f;
            if (entity.getPhase() == 3) { // 结束阶段旋转两次
                rotationMultiplier = 2.0f;
            }
            matrixStack.mulPose(Axis.YP.rotationDegrees(rotationAngle * rotationMultiplier));
            int color = 16722600;
            // 缩放
            float scale = 0.015F;
            matrixStack.scale(scale, scale, scale);

            // 获取并渲染模型的各个部分
            WavefrontObject model = BladeModelManager.getInstance().getModel(MODEL);

            // 渲染z1、z2、z4（逆时针旋转）
            try (MSAutoCloser clockwiseMsac = MSAutoCloser.pushMatrix(matrixStack)) {
                matrixStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));


                int alpha = (int) (255.0F * entity.getPartAlpha("z1")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z1", TEXTURE, matrixStack, buffer, packedLight);

                alpha = (int) (255.0F * entity.getPartAlpha("z2")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z2", TEXTURE, matrixStack, buffer, packedLight);

                alpha = (int) (255.0F * entity.getPartAlpha("z4")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z4", TEXTURE, matrixStack, buffer, packedLight);
            }

            // 渲染z3、z5（顺时针旋转）
            try (MSAutoCloser counterClockwiseMsac = MSAutoCloser.pushMatrix(matrixStack)) {
                matrixStack.mulPose(Axis.YP.rotationDegrees(-rotationAngle * 2));


                int alpha = (int) (255.0F * entity.getPartAlpha("z3")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z3", TEXTURE, matrixStack, buffer, packedLight);

                alpha = (int) (255.0F * entity.getPartAlpha("z5")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z5", TEXTURE, matrixStack, buffer, packedLight);
            }

            // 渲染z6、z7、z8（下沉部分）
            try (MSAutoCloser Msac = MSAutoCloser.pushMatrix(matrixStack)) {


                // z6
                matrixStack.pushPose();
                matrixStack.translate(0, entity.getPartOffset("z6"), 0);
                int alpha = (int) (255.0F * entity.getPartAlpha("z6")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z6", TEXTURE, matrixStack, buffer, packedLight);
                matrixStack.popPose();

                // z7
                matrixStack.pushPose();
                matrixStack.translate(0, entity.getPartOffset("z7"), 0);
                alpha = (int) (255.0F * entity.getPartAlpha("z7")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z7", TEXTURE, matrixStack, buffer, packedLight);
                matrixStack.popPose();

                // z8
                matrixStack.pushPose();
                matrixStack.translate(0, entity.getPartOffset("z8"), 0);
                alpha = (int) (255.0F * entity.getPartAlpha("z8")) << 24;
                BladeRenderState.setCol(color | alpha);
                BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "z8", TEXTURE, matrixStack, buffer, packedLight);
                matrixStack.popPose();
            }
        }
    }
}