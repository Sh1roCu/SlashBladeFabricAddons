package com.yakumosakura.yakumoblade.client.renderer.hexgram;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.entity.hexgram.neo.LaserCircleEntity;
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

public class LaserCircleRender<T extends LaserCircleEntity> extends EntityRenderer<T> {
    public LaserCircleRender(EntityRendererProvider.Context context) {
        super(context);
    }

    private static final ResourceLocation TEXTURE = Yakumoblade.prefix("model/util/hexgram/p.png");
    private static final ResourceLocation MODEL = Yakumoblade.prefix("model/util/hexgram/p.obj");

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return TEXTURE;
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int packedLight) {
        try (MSAutoCloser msac = MSAutoCloser.pushMatrix(matrixStack)) {
            float rotationSpeed = entity.hasLanded() ? 10.0f : 5.0f;
            float rotationAngle = Mth.lerp(partialTicks, entity.getPrevRotationAngle(), entity.getRotationAngle() * rotationSpeed);
            matrixStack.mulPose(Axis.YP.rotationDegrees(rotationAngle));

            float scale = entity.hasLanded() ? 0.02F : 0.01F;
            matrixStack.scale(scale, scale, scale);


            int color = 0x9932CD; // 紫罗兰色
            int alpha = 255 << 24; // 完全不透明


            WavefrontObject model = BladeModelManager.getInstance().getModel(MODEL);
            BladeRenderState.setCol(color | alpha);

            BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "p", TEXTURE, matrixStack, buffer, packedLight);
        }
    }
}