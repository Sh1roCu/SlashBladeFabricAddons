package com.yakumosakura.yakumoblade.specialeffects.colorSE;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;
import java.util.stream.Stream;

public class VeryGreen extends SpecialEffect {

    public VeryGreen() {
        super(10);
    }

    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // 使用Stream合并主手/副手状态检查
        Stream.of(event.getSlashBladeState(),
                        SlashBladeUtil.getState(player.getOffhandItem()).orElse(null))
                .filter(Objects::nonNull)
                .filter(state -> state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.VERY_GREEN)))
                .forEach(state -> {
                    if (!event.isSelected())
                        return;
                    applyEffectIfValid(player);
                });
    }

    private static void applyEffectIfValid(Player player) {
        int level = player.experienceLevel;
        if (player.hasEffect(MobEffects.REGENERATION)) return;
        if (SpecialEffect.isEffective(YASpecialEffectsRegistry.VERY_GREEN, level)) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.REGENERATION,
                    300,
                    2,
                    false,  // 添加环境参数
                    false,  // 关闭粒子效果
                    true)); // 显示图标
        }
    }


}

