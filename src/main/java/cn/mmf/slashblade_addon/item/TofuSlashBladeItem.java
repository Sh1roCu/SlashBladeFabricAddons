package cn.mmf.slashblade_addon.item;

//import baguchan.tofucraft.api.tfenergy.IEnergyContained;
//import baguchan.tofucraft.api.tfenergy.IEnergyExtractable;
//import baguchan.tofucraft.api.tfenergy.IEnergyInsertable;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.item.ItemSlashBladeDetune;
import mods.flammpfeil.slashblade.item.SwordType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;

// TODO
// TofuCraft Fabric移植
public class TofuSlashBladeItem extends ItemSlashBladeDetune /*implements IEnergyContained, IEnergyExtractable, IEnergyInsertable*/ {
    private ResourceLocation model;
    private ResourceLocation texture;
    private final float baseAttack;
    public final int tfMax;

    public TofuSlashBladeItem(Tier tier, int attackDamageIn, float attackSpeedIn, int tfMax, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.tfMax = tfMax;
        this.baseAttack = (float) attackDamageIn;
        this.model = DefaultResources.resourceDefaultModel;
        this.texture = DefaultResources.resourceDefaultTexture;
    }

    @Override
    public void appendSlashArt(ItemStack stack, List<Component> tooltip, @NotNull ISlashBladeState s) {
        super.appendSlashArt(stack, tooltip, s);
    }

    // TODO
    // @Override
    public int getEnergy(ItemStack inst) {
        return inst.getTag() != null && inst.getTag().contains("tf_energy") ? inst.getTag().getInt("tf_energy") : 0;
    }

    public int getEnergyMax(ItemStack inst) {
        return this.tfMax;
    }

    public void setEnergy(ItemStack inst, int amount) {
        inst.getOrCreateTag().putInt("tf_energy", amount);
    }

    public void setEnergyMax(ItemStack inst, int amount) {
    }

    private boolean getShowState(ItemStack stack) {
        final Minecraft mcinstance = Minecraft.getInstance();
        return mcinstance.player != null && mcinstance.player.isShiftKeyDown() && this.getEnergy(stack) != 0;
    }

    public boolean isBarVisible(ItemStack p_150899_) {
        return this.getShowState(p_150899_) || super.isBarVisible(p_150899_);
    }

    public int getBarWidth(ItemStack stack) {
        return this.getShowState(stack) ? Math.round((float) this.getEnergy(stack) / (float) this.getEnergyMax(stack) * 13.0F) : super.getBarWidth(stack);
    }

    public int getBarColor(ItemStack p_150901_) {
        return this.getShowState(p_150901_) ? Color.white.getRGB() : super.getBarColor(p_150901_);
    }

    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(Component.translatable("tooltip.tofucraft.energy", this.getEnergy(p_41421_), this.getEnergyMax(p_41421_)));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (this.getEnergy(stack) > 0 && amount > 0) {
            int damage = amount - this.drain(stack, amount, false);
            return super.damageItem(stack, damage, entity, onBroken);
        }

        return super.damageItem(stack, amount, entity, onBroken);
    }

    // TODO
    // @Override
    public int fill(ItemStack inst, int energy, boolean simulate) {
        int calculated = Math.min(energy, inst.getDamageValue());
        if (!simulate) {
            if (inst.getDamageValue() > 0) {
                inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
                return calculated * 5;
            } else {
                int calculated2 = Math.min(energy, this.getEnergyMax(inst) - this.getEnergy(inst));
                this.setEnergy(inst, this.getEnergy(inst) + calculated2);
                return calculated2;
            }
        } else {
            return 0;
        }
    }

    // TODO
    // @Override
    public int drain(ItemStack inst, int amount, boolean simulate) {
        if (!simulate) {
            int calculated2 = Math.min(this.getEnergy(inst), amount);
            this.setEnergy(inst, this.getEnergy(inst) - calculated2);
            return calculated2;
        } else {
            return 0;
        }
    }

    public void appendSwordType(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        EnumSet<SwordType> swordType = SwordType.from(stack);
        if (swordType.contains(SwordType.BEWITCHED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.bewitched").withStyle(ChatFormatting.DARK_PURPLE));
        } else if (swordType.contains(SwordType.ENCHANTED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.enchanted").withStyle(ChatFormatting.DARK_AQUA));
        } else {
            tooltip.add(Component.translatable("slashblade.sword_type.noname").withStyle(ChatFormatting.DARK_GRAY));
        }

    }

    public ResourceLocation getModel() {
        return this.model;
    }

    public TofuSlashBladeItem setModel(ResourceLocation model) {
        this.model = model;
        return this;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public TofuSlashBladeItem setTexture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    @Override
    public boolean isDestructable(ItemStack stack) {
        return false;
    }
}
