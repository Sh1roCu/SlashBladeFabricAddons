package com.yakumosakura.yakumoblade.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.entity.exer.absNeoSummonSword;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.client.renderer.util.MSAutoCloser;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Environment(EnvType.CLIENT)
public class BlueFoxRenderer<T extends absNeoSummonSword> extends EntityRenderer<T> {
    private static ResourceLocation TEXTURE = Yakumoblade.prefix("model/named/sange/blue.png");
    private static ResourceLocation MODEL = Yakumoblade.prefix("model/named/sange/sange.obj");

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getTextureLoc();
    }

    public BlueFoxRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(T entity, float entityYaw, float partialTicks, PoseStack matrixStack, @NotNull MultiBufferSource bufferIn,
                       int packedLightIn) {
        try (MSAutoCloser ignored = MSAutoCloser.pushMatrix(matrixStack)) {
            // 矩阵变换保持不变
            matrixStack.mulPose(
                    Axis.YP.rotationDegrees(Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot()) - 90.0F));
            matrixStack.mulPose(Axis.ZP.rotationDegrees(Mth.rotLerp(partialTicks, entity.xRotO, entity.getXRot())));
            matrixStack.mulPose(Axis.XP.rotationDegrees(entity.getRoll()));


            if (entity.getOwner() instanceof Player player) {
                CapabilitySlashBlade.getBladeState(player.getMainHandItem())
                        .map(
                                state ->
                                        TEXTURE = state.getTexture().get()
                        );
                CapabilitySlashBlade.getBladeState(player.getMainHandItem())
                        .map(
                                state ->
                                        MODEL = state.getModel().get()
                        );
            }


            float scale = 0.007f;
            matrixStack.scale(scale, scale, scale);
            matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            matrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            // 修改颜色处理
            int color = entity.getColor() & 0xFFFFFF;
            int alpha = 0xFF000000; // 固定不透明
            WavefrontObject model = BladeModelManager.getInstance().getModel(MODEL);

            BladeRenderState.setCol(color | alpha);
            BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "blade", TEXTURE, matrixStack, bufferIn,
                    packedLightIn);
            BladeRenderState.renderOverridedLuminous(ItemStack.EMPTY, model, "blade_luminous", TEXTURE, matrixStack, bufferIn,
                    packedLightIn);
        }
    }

}
