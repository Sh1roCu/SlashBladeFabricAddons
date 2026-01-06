package com.yakumosakura.yakumoblade.utils;


import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class SlashBladeUtil {
    public static Optional<ISlashBladeState> getState(ItemStack stack) {
        return CapabilitySlashBlade.BLADESTATE.maybeGet(stack); // 保持 Optional 状态
    }

    //获取玩家手中刀的SE
    //需要传SE的注册变量
    public static boolean hasSpecialEffect(Player player, SpecialEffect effect) {
        ItemStack stack = player.getMainHandItem();

        var bladeState = getState(stack);
        ResourceLocation id = SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(effect);
        return bladeState.isPresent() && bladeState.get().hasSpecialEffect(id)
                && SpecialEffect.isEffective(id, player.experienceLevel);
    }

    public static boolean hasSpecialEffect(ItemStack stack, SpecialEffect effect) {
        if (getState(stack).isPresent()) {
            return getState(stack).get().hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(effect));
        } else {
            return false;
        }
    }

    //获取杀敌数
    public static int getKillCount(Player player) {

        return getState(player.getMainHandItem()).get().getKillCount();
    }

    //获取耀魂数
    public static int getproudSoul(Player player) {

        return getState(player.getMainHandItem()).get().getProudSoulCount();
    }

    //获取锻造数
    public static int getRefine(Player player) {
        return getState(player.getMainHandItem()).get().getRefine();
    }

    //获取最大耐久
    public static int getMaxDamge(Player player) {
        return getState(player.getMainHandItem()).get().getMaxDamage();
    }

    //获取刀自身面板
    public static float getBaseAttackModifier(Player player) {
        return getState(player.getMainHandItem()).get().getBaseAttackModifier();
    }

    //获取刀的本地化名称
    public static String getTranslationKey(Player player) {
        return getState(player.getMainHandItem()).get().getTranslationKey();
    }

    //获取刀的刀光颜色
    public static int getColorCode(LivingEntity player) {
        return getState(player.getMainHandItem()).get().getColorCode();
    }

    //耀魂增加/减少
    public static void costProudSoul(int cost, ItemStack stack) {
        getState(stack).get().setProudSoulCount(getState(stack).get().getProudSoulCount() + cost);
    }
}
