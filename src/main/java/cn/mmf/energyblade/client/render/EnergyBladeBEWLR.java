package cn.mmf.energyblade.client.render;

import cn.mmf.energyblade.energy.FEBladeStorage;
import cn.sh1rocu.sfaddons.util.ItemEnergyStorageHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.client.renderer.SlashBladeTEISR;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.client.renderer.model.obj.WavefrontObject;
import mods.flammpfeil.slashblade.client.renderer.util.BladeRenderState;
import mods.flammpfeil.slashblade.init.DefaultResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.awt.*;

public class EnergyBladeBEWLR extends SlashBladeTEISR {

    public static final BlockEntityWithoutLevelRenderer INSTANCE = new EnergyBladeBEWLR(
            Minecraft.getInstance().getBlockEntityRenderDispatcher(),
            Minecraft.getInstance().getEntityModels());

    public EnergyBladeBEWLR(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
        super(p_172550_, p_172551_);
    }

    // 修改物品栏图标（若设置了能量替代耐久，物品栏图标的耐久显示则更改电量显示）
    @Override
    public void renderIcon(ItemStack stack, PoseStack matrixStack, MultiBufferSource bufferIn, int lightIn, float scale,
                           boolean renderDurability) {
        ItemEnergyStorageHelper.fromStack(stack).filter(FEBladeStorage.class::isInstance)
                .map(FEBladeStorage.class::cast).ifPresentOrElse(energy -> {
                    if (!energy.isEnergyDurability()) {
                        super.renderIcon(stack, matrixStack, bufferIn, lightIn, scale, renderDurability);
                        return;
                    }
                    matrixStack.scale(scale, scale, scale);

                    ResourceLocation modelLocation = CapabilitySlashBlade.getBladeState(stack)
                            .filter(s -> s.getModel().isPresent()).map(s -> s.getModel().get())
                            .orElseGet(() -> stackDefaultModel(stack));
                    WavefrontObject model = BladeModelManager.getInstance().getModel(modelLocation);
                    ResourceLocation textureLocation = CapabilitySlashBlade.getBladeState(stack)
                            .filter(s -> s.getTexture().isPresent()).map(s -> s.getTexture().get())
                            .orElseGet(() -> stackDefaultTexture(stack));

                    String renderTarget = "item_blade";

                    BladeRenderState.renderOverrided(stack, model, renderTarget, textureLocation, matrixStack, bufferIn,
                            lightIn);
                    BladeRenderState.renderOverridedLuminous(stack, model, renderTarget + "_luminous", textureLocation,
                            matrixStack, bufferIn, lightIn);

                    if (renderDurability) {

                        WavefrontObject durabilityModel = BladeModelManager.getInstance()
                                .getModel(DefaultResources.resourceDurabilityModel);

                        float durability = 1 - (energy.getAmount() / (float) energy.getCapacity());
                        matrixStack.translate(0.0F, 0.0F, 0.1f);
                        Color aCol = new Color(0x404040);
                        Color bCol = new Color(0xA52C63);
                        int r = 0xFF & (int) Mth.lerp(aCol.getRed(), bCol.getRed(), durability);
                        int g = 0xFF & (int) Mth.lerp(aCol.getGreen(), bCol.getGreen(), durability);
                        int b = 0xFF & (int) Mth.lerp(aCol.getBlue(), bCol.getBlue(), durability);

                        BladeRenderState.setCol(new Color(r, g, b));
                        BladeRenderState.renderOverrided(stack, durabilityModel, "base",
                                DefaultResources.resourceDurabilityTexture, matrixStack, bufferIn, lightIn);

                        matrixStack.translate(0.0F, 0.0F, -2.0f * durability);
                        BladeRenderState.renderOverrided(stack, durabilityModel, "color",
                                DefaultResources.resourceDurabilityTexture, matrixStack, bufferIn, lightIn);

                    }
                }, () -> {
                    super.renderIcon(stack, matrixStack, bufferIn, lightIn, scale, renderDurability);
                });

    }
}
