package cn.mmf.energyblade.item;

import cn.mmf.energyblade.client.render.EnergyBladeBEWLR;
import cn.mmf.energyblade.energy.FEBladeStorage;
import cn.mmf.energyblade.energy.FECapabilityProvider;
import cn.sh1rocu.sfaddons.api.extension.IDamageable;
import cn.sh1rocu.sfaddons.util.ItemEnergyStorageHelper;
import com.mojang.blaze3d.platform.InputConstants;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import team.reborn.energy.api.EnergyStorage;

import java.util.List;
import java.util.function.Consumer;

// 拓展子类拔刀剑(extends ItemSlashBlade)
public class ItemFEBlade extends ItemSlashBlade implements IDamageable {

    public ItemFEBlade(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        EnergyStorage.ITEM.registerForItems((stack, context) -> {
            var bladeState = stack.getOrCreateTagElement("bladeState");
            if (bladeState.contains("Energy")) {
                var tag = bladeState.getCompound("Energy");
                long energy = tag.getLong("Energy");
                long capacity = tag.getLong("Capacity");
                long maxReceive = tag.getLong("MaxReceive");
                long maxExtract = tag.getLong("MaxExtract");
                long powerupExtract = tag.getLong("PowerupExtract");
                long standbyExtract = tag.getLong("StandbyExtract");
                boolean energyDurability = tag.getBoolean("EnergyDurability");
                boolean isPowered = tag.getBoolean("isPowered");
                FEBladeStorage storage = new FEBladeStorage(energy, capacity, powerupExtract, standbyExtract, energyDurability) {
                    @Override
                    protected void onFinalCommit() {
                        super.onFinalCommit();
                        bladeState.put("Energy", serializeNBT());
                    }
                };
                storage.setMaxReceive(maxReceive);
                storage.setMaxExtract(maxExtract);
                storage.setPowered(isPowered);
                return storage;
            } else {
                FEBladeStorage storage = new FEBladeStorage(0, 2000000, 1000, 100, false) {
                    @Override
                    protected void onFinalCommit() {
                        super.onFinalCommit();
                        bladeState.put("Energy", serializeNBT());
                    }
                };
                bladeState.put("Energy", storage.serializeNBT());
                return storage;
            }
        }, this);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return ItemEnergyStorageHelper.fromStack(stack).filter(FEBladeStorage.class::isInstance)
                .map(FEBladeStorage.class::cast).filter(FEBladeStorage::isEnergyDurability) // 当启用能量代替耐久时
                .map(energy -> false) // 禁用原版耐久机制
                .orElseGet(() -> IDamageable.super.isDamageable(stack)); // 否则继承默认逻辑
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        this.appendForgeEnergyInfo(stack, tooltip);
    }

    // 能量显示
    @Environment(EnvType.CLIENT)
    public void appendForgeEnergyInfo(ItemStack stack, List<Component> tooltip) {
        ItemEnergyStorageHelper.fromStack(stack).ifPresent(energy -> {
            Minecraft mc = Minecraft.getInstance();
            KeyMapping shift = mc.options.keyShift;
            if (!isShiftKeyDown()) {
                // 动态实时显示具体按键
                Component shiftKey = Component.literal("[").append(shift.getTranslatedKeyMessage().copy())
                        .append("]").withStyle(ChatFormatting.GOLD);
                tooltip.add(Component.translatable("tip.slashblade_fabric_addons.energyblade.energy_info", shiftKey)
                        .withStyle(ChatFormatting.GRAY));
            } else {
                Component energyTip = Component
                        .literal(energy.getAmount() + " / " + energy.getCapacity())
                        .withStyle(ChatFormatting.GOLD);
                tooltip.add(Component.translatable("tip.slashblade_fabric_addons.energyblade.forge_energy_info", energyTip)
                        .withStyle(ChatFormatting.GRAY));
            }
        });
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return ItemEnergyStorageHelper.fromStack(stack).filter(FEBladeStorage.class::isInstance)
                .map(FEBladeStorage.class::cast)
                .map(energy ->
                        isShiftKeyDown() || energy.isPowered()
                )
                .orElse(false);
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return ItemEnergyStorageHelper.fromStack(stack).filter(FEBladeStorage.class::isInstance)
                .map(FEBladeStorage.class::cast)
                .filter(energy -> energy.getCapacity() > 0) // 防止除以零
                .map(energy -> {
                    double ratio = (double) energy.getAmount() / energy.getCapacity();
                    return (int) (ratio * MAX_BAR_WIDTH);
                }).orElse(0); // 无能量存储时返回0
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0xFFAA00; // 金色能量条，避免与原本耐久条混淆
    }

    // 覆写该方法用以修改拔刀剑渲染(具体参考EnergyBladeBEWLR类)
    @Environment(EnvType.CLIENT)
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        BlockEntityWithoutLevelRenderer renderer = new EnergyBladeBEWLR(
                Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels());
        return renderer;
    }

    @Override
    public SlashBladeState initCapability(ItemStack stack) {
        return new FECapabilityProvider(stack, ItemEnergyStorageHelper.fromStack(stack).orElse(null));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack arg0, int arg1, T arg2, Consumer<T> arg3) {
        // TODO 电量耐久适配消耗
        return super.damageItem(arg0, arg1, arg2, arg3);
    }

    @Environment(EnvType.CLIENT)
    private boolean isShiftKeyDown() {
        Minecraft mc = Minecraft.getInstance();
        KeyMapping shift = mc.options.keyShift;
        InputConstants.Key key = KeyBindingHelper.getBoundKeyOf(shift);
        long window = mc.getWindow().getWindow();
        if (key.getType() == InputConstants.Type.KEYSYM) {
            return GLFW.glfwGetKey(window, key.getValue()) == GLFW.GLFW_PRESS;
        } else if (key.getType() == InputConstants.Type.MOUSE) {
            return GLFW.glfwGetMouseButton(window, key.getValue()) == GLFW.GLFW_PRESS;
        }
        return false;
    }

    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity living = (LivingEntity) event.getEntity();
        if (!event.isSelected()) {
            return;
        }

        ItemEnergyStorageHelper.fromStack(event.getBlade()).ifPresent(energy -> {
            if (energy instanceof FEBladeStorage bladeFE) {
                if (bladeFE.isPowered()) {
                    try (Transaction tx = Transaction.openOuter()) {
                        if (bladeFE.extract(bladeFE.getStandbyExtract(), tx) == bladeFE.getStandbyExtract()) {
                            CapabilityConcentrationRank.RANK_POINT.maybeGet(living)
                                    .ifPresent(cap -> cap.addRankPoint(living, cap.getMaxCapacity()));
                            tx.commit();
                        } else {
                            try (Transaction tx2 = Transaction.openNested(tx)) {
                                bladeFE.setPowered(false);
                                event.getEntity().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                // 用于调用onFinalCommit，更新nbt
                                tx2.commit();
                            }
                        }
                    }
                }
            }
        });
    }

    public static void onSlashBladeHit(SlashBladeEvent.HitEvent event) {
        ItemEnergyStorageHelper.fromStack(event.getBlade()).ifPresent(energy -> {
            if (energy instanceof FEBladeStorage bladeFE) {
                if (bladeFE.isPowered()) {
                    try (Transaction tx = Transaction.openOuter()) {
                        if (bladeFE.extract(bladeFE.getStandbyExtract(), tx) == bladeFE.getStandbyExtract()) {
                            tx.commit();
                        } else {
                            try (Transaction tx2 = Transaction.openNested(tx)) {
                                bladeFE.setPowered(false);
                                event.getUser().playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1F, 1F);
                                // 用于调用onFinalCommit，更新nbt
                                tx2.commit();
                            }
                        }
                    }
                }
            }
        });
    }

    public static void onSlashBladePowered(SlashBladeEvent.PowerBladeEvent event) {
        ItemEnergyStorageHelper.fromStack(event.getBlade()).ifPresent(energy -> {
            if (energy instanceof FEBladeStorage bladeFE) {
                if (bladeFE.isPowered()) {
                    event.setPowered(true);
                }
            }
        });
    }
}
