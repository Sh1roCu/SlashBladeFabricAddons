package com.yakumosakura.yakumoblade.specialeffects.starSE;


import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.specialattacks.v2.SlashEffect;
import com.yakumosakura.yakumoblade.utils.WaitingTick;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;


public class Highfrequency extends SpecialEffect {
    public Highfrequency() {
        super(30, false, false);
    }

    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.highfrequency))) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.highfrequency, level)) {
                WaitingTick.schedule(1, () -> {
                    SlashEffect.SakuraEnd.doSlash(player, event.getRoll() - 10F, Vec3.ZERO, false, false, event.getDamage() / 2);
                });
                WaitingTick.schedule(2, () -> {
                    SlashEffect.SakuraEnd.doSlash(player, event.getRoll() - 20F, Vec3.ZERO, false, false, event.getDamage() / 2);
                });
            }
        }

    }

    public static void onslashbladehit(SlashBladeEvent.HitEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.highfrequency))) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.highfrequency, level)) {
                event.getTarget().invulnerableTime = 0;
            }
        }
    }
}
