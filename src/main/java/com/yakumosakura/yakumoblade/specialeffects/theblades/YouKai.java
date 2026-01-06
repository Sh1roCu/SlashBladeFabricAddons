package com.yakumosakura.yakumoblade.specialeffects.theblades;

import com.yakumosakura.yakumoblade.specialattacks.v1.StormSwordsHexGram;
import com.yakumosakura.yakumoblade.specialattacks.v2.WaveAndTiny;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public class YouKai extends SpecialEffect {
    public YouKai() {
        super(60, false, true);
    }

    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = SlashBladeUtil.getState(event.getBlade()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();

        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof YouKai Effect && event.getEntity() instanceof Player player) {
                if (state.hasSpecialEffect(effectId)) {
                    if (SpecialEffect.isEffective(effect, player.experienceLevel)) {
                        if (state.getDamage() != -state.getMaxDamage()) {
                            state.setDamage(state.getDamage() - 1);
                            state.setProudSoulCount(state.getProudSoulCount() - 1);
                        }
                        if (state.getProudSoulCount() < 1000) {
                            state.setProudSoulCount(1000);
                        }
                    }
                }

            }
        }
    }

    public static void doSlashBladeDoSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = SlashBladeUtil.getState(event.getBlade()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();

        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof YouKai Effect && event.getUser() instanceof Player player) {
                if (state.hasSpecialEffect(effectId) && Effect.dois()) {
                    if (SpecialEffect.isEffective(effect, player.experienceLevel)) {
                        WaveAndTiny.doslash(player, state.getColorCode(), Effect.counter());
                        state.setProudSoulCount(state.getProudSoulCount() - 100);
                        if (player.getRandom().nextInt(100) > 50) {
                            StormSwordsHexGram.doSlash(player, Effect.counter());
                        }
                    }
                }

            }
        }
    }

    public int counter() {
        return 6;
    }

    public Boolean dois() {
        return false;
    }
}
