package com.yakumosakura.yakumoblade.utils;

import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.item.ItemStack;


public class SlashBladeUtils {
    public static ISlashBladeState getState(ItemStack stack) {
        return CapabilitySlashBlade.getBladeState(stack)
                .orElseThrow(() -> new IllegalStateException("ItemStack 缺失 ISlashBladeState 能力"));
    }

    public static boolean hasSpecialEffect(ItemStack stack, SpecialEffect effect) {
        if (!(stack.getItem() instanceof ItemSlashBlade)) {
            return false;
        }
        return getState(stack).hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(effect));
    }

    //获取杀敌数
    public static int getKillCount(ItemStack stack) {

        return getState(stack).getKillCount();
    }

    //获取耀魂数
    public static int getproudSoul(ItemStack stack) {

        return getState(stack).getProudSoulCount();
    }

    //获取锻造数
    public static int getRefine(ItemStack stack) {
        return getState(stack).getRefine();
    }

    //获取最大耐久
    public static int getMaxDamge(ItemStack stack) {
        return getState(stack).getMaxDamage();
    }

    //获取刀自身面板
    public static float getBaseAttackModifier(ItemStack stack) {
        return getState(stack).getBaseAttackModifier();
    }

    //获取刀的本地化名称
    public static String getTranslationKey(ItemStack stack) {
        return getState(stack).getTranslationKey();
    }

    //获取刀的刀光颜色
    public static int getColorCode(ItemStack stack) {
        return getState(stack).getColorCode();
    }
}
