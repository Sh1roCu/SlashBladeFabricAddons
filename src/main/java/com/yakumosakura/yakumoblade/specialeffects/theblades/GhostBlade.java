package com.yakumosakura.yakumoblade.specialeffects.theblades;

import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;

public class GhostBlade extends SpecialEffect {
    public GhostBlade() {
        super(20);
    }

    public static void onKillCountAdd(SlashBladeEvent.AddKillCountEvent event) {
        ISlashBladeState state = SlashBladeUtil.getState(event.getBlade()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();
        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof GhostBlade Effect) {
                event.setNewCount(event.getOriginCount() * 2);
            }
        }
    }

    public static void onProudCountAdd(SlashBladeEvent.AddProudSoulEvent event) {
        ISlashBladeState state = SlashBladeUtil.getState(event.getBlade()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();
        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof GhostBlade Effect) {
                event.setNewCount(event.getOriginCount() * 10);
            }
        }
    }


}
