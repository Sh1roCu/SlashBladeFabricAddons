package com.yakumosakura.yakumoblade.specialeffects.starSE.re;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SunSoul extends SpecialEffect {

    public SunSoul() {
        super(10, false, false);
    }

    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        // 主手检查（保留选中状态要求）
        if (event.isSelected()) {
            checkAndApplyEffect(event.getSlashBladeState(), event);
        }

        // 副手检查（新增逻辑，不要求选中状态）
        if (event.getEntity() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
                checkAndApplyEffect(offhandState, event);
            });
        }
    }

    private static void checkAndApplyEffect(ISlashBladeState state, SlashBladeEvent.UpdateEvent event) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.SUN_SOUL))) {
            if (!(event.getEntity() instanceof Player player)) return;

            Level inlevel = player.level();
            int level = player.experienceLevel;
            if (inlevel.isDay() && SpecialEffect.isEffective(YASpecialEffectsRegistry.SUN_SOUL, level)) {
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0));
            }
        }
    }


}
