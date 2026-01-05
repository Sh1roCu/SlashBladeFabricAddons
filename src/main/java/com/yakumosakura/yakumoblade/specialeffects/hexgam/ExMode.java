package com.yakumosakura.yakumoblade.specialeffects.hexgam;

import com.yakumosakura.yakumoblade.specialattacks.v2.SlashEffect;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import com.yakumosakura.yakumoblade.utils.WaitingTick;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

public class ExMode extends SpecialEffect {
    public ExMode() {
        super(60);
    }

    public static void doSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = SlashBladeUtil.getState(event.getBlade()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();

        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof ExMode Effect && event.getUser() instanceof Player player) {
                if (state.hasSpecialEffect(effectId)) {
                    if (SpecialEffect.isEffective(effect, player.experienceLevel)) {
                        for (int i = 0; i < Effect.returnCount(); i++) {
                            WaitingTick.schedule((i + 1) * 4, () -> {
                                SlashEffect.SakuraEnd.doSlash(player, event.getRoll(), Vec3.ZERO, false, false, event.getDamage() / 2);
                            });
                        }
                    }
                }

            }
        }
    }


    public int returnCount() {
        return 3;
    }


}
