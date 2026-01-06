package com.yakumosakura.yakumoblade.item;

import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.SwordType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.List;

public class TouHouMaid extends ItemSlashBlade {
    public TouHouMaid(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    public void appendSwordType(ItemStack stack, @javax.annotation.Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        EnumSet<SwordType> swordType = SwordType.from(stack);

        if (swordType.contains(SwordType.BEWITCHED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.bewitched").withStyle(ChatFormatting.GOLD));
        } else if (swordType.contains(SwordType.ENCHANTED)) {
            tooltip.add(Component.translatable("slashblade.sword_type.enchanted").withStyle(ChatFormatting.GOLD));
        } else {
            tooltip.add(Component.translatable("slashblade.sword_type.noname").withStyle(ChatFormatting.GOLD));
        }
        tooltip.add(Component.translatable("slashblade.sword_type.tips").withStyle(ChatFormatting.GOLD));
    }

    @Override
    public void appendKillCount(List<Component> tooltip, @NotNull ItemStack stack, ISlashBladeState state) {

        int killCount = state.getKillCount();
        if (killCount > 0) {
            MutableComponent killCountComponent = Component.translatable("slashblade.tooltip.killcount", killCount).withStyle(ChatFormatting.GOLD);
            if (killCount > 1000) {
                killCountComponent = killCountComponent.withStyle(ChatFormatting.GOLD);
            }

            tooltip.add(killCountComponent);
        }

    }

    @Override
    public void appendProudSoulCount(List<Component> tooltip, @NotNull ItemStack stack, ISlashBladeState state) {
        int proudsoul = state.getProudSoulCount();
        if (proudsoul > 0) {
            MutableComponent countComponent = Component.translatable("slashblade.tooltip.proud_soul", proudsoul).withStyle(ChatFormatting.GOLD);
            if (proudsoul > 1000) {
                countComponent = countComponent.withStyle(ChatFormatting.GOLD);
            }

            tooltip.add(countComponent);
        }
    }


    @Override
    public void appendSlashArt(ItemStack stack, List<Component> tooltip, @NotNull ISlashBladeState s) {
        EnumSet<SwordType> swordType = SwordType.from(stack);
        super.appendSlashArt(stack, tooltip, s);
        if (swordType.contains(SwordType.BEWITCHED) && !swordType.contains(SwordType.SEALED)) {
            tooltip.add(Component.translatable("slashblade.tooltip.slash_art", s.getSlashArts().getDescription()).withStyle(ChatFormatting.GOLD));
        }
    }
}
