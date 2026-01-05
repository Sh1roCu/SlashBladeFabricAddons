package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.specialattacks.v1.BlackSlash;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BlackSlashBreak {
    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.blackslashbreak))) {
            if (!(event.getEntity() instanceof Player player)) {
                return;
            }


            if (!event.isSelected())
                return;

            Level inlevel = player.level();
            int level = player.experienceLevel;
            if (inlevel.isNight()) {
                if (SpecialEffect.isEffective(YASpecialEffectsRegistry.blackslashbreak, level)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                }
            }

        }
    }

    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();

        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.blackslashbreak))) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }

            RandomSource random = player.getRandom();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.blackslashbreak), level)) {
                BlackSlash.doSlash(player);
            }
        }
    }
}
