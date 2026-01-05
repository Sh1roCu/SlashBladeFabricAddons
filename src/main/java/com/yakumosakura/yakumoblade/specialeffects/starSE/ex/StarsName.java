package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.specialattacks.v2.SlashEffect;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class StarsName {
    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();


        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.StarSname))) {
            if (!(event.getEntity() instanceof Player player)) {
                return;
            }


            if (!event.isSelected())
                return;

            Level inlevel = player.level();
            int level = player.experienceLevel;

            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.StarSname, level)) {

                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 3));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 3));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0));
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, 2));

            }


        }
    }

    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.StarSname))) {
            if (!(event.getUser() instanceof Player player)) {
                return;
            }

            RandomSource random = player.getRandom();
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.StarSname), level)) {
                SlashEffect.SakuraEnd.doSlash(player, event.getRoll() - 10F, Vec3.ZERO, false, false, event.getDamage());
                SlashEffect.SakuraEnd.doSlash(player, event.getRoll() - 20F, Vec3.ZERO, false, false, event.getDamage());
                Drive.doSlash(player, event.getRoll(), 30, Vec3.ZERO, false, event.getDamage(), 3F);
            }
        }
    }
}
