package com.yakumosakura.yakumoblade.specialeffects.ses;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;


public class HexGam extends SpecialEffect {


    public HexGam() {
        super(30);

    }

    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.HexGamFox)) || state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.HexGamDragon))) {
            if (!(event.getEntity() instanceof Player player)) {
                return;
            }

            if (!event.isSelected())
                return;

            int level = player.experienceLevel;
            if (!player.hasEffect(MobEffects.ABSORPTION)) {
                if (SpecialEffect.isEffective(YASpecialEffectsRegistry.HexGamFox, level) || SpecialEffect.isEffective(YASpecialEffectsRegistry.HexGamDragon, level)) {
                    player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 4));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 1));
                }
            }
        }
    }
}
