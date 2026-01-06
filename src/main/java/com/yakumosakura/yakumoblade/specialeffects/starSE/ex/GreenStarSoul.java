package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.RandomUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GreenStarSoul {
    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.Greenstarsoul))) {
            if (!(event.getEntity() instanceof Player player)) {
                return;
            }


            if (!event.isSelected())
                return;

            Level inlevel = player.level();
            int level = player.experienceLevel;
            if (inlevel.isNight()) {
                if (SpecialEffect.isEffective(YASpecialEffectsRegistry.Greenstarsoul, level)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
                    if (!player.hasEffect(MobEffects.REGENERATION)) {
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 2));
                    }
                    int a = RandomUtil.randomnum(2);
                    Yakumoblade.queueServerWork(20, () -> {
                        switch (a) {
                            case 1:
                                state.setColorCode(3729097);
                                break;
                            case 0, 2:
                                state.setColorCode(2424576);
                        }
                    });

                }

            }

        }
    }
}
