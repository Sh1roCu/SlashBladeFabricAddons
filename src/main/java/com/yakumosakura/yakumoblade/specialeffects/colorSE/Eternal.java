package com.yakumosakura.yakumoblade.specialeffects.colorSE;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

// 修改为继承BaseSpecialEffect
public class Eternal extends SpecialEffect {
    public Eternal() {
        super(20);
    }


    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // 主手检查
        checkAndApplyEffect(event.getSlashBladeState(), player, event);

        // 副手检查
        SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {

            checkAndApplyEffect(offhandState, player, event);
        });
    }

    private static void checkAndApplyEffect(ISlashBladeState state, Player player, SlashBladeEvent.UpdateEvent event) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.ETERNAL))) {
            if (!event.isSelected())
                return;
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.ETERNAL, level)) {
                player.addEffect(new MobEffectInstance(MobEffects.LUCK, 100, 1));
            }
        }
    }

}
