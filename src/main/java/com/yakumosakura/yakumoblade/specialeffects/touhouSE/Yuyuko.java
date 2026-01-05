package com.yakumosakura.yakumoblade.specialeffects.touhouSE;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;


public class Yuyuko extends SpecialEffect {
    public Yuyuko() {
        super(30, false, false);
    }

    public static void onSlashBladeHit(SlashBladeEvent.HitEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.yuyuko))) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.yuyuko, level)) {
                event.getTarget().addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                event.getTarget().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
                event.getTarget().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            }
        }

    }
}
