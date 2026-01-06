package com.yakumosakura.yakumoblade.specialeffects.theblades;

import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public class SharpnessBlade extends SpecialEffect {
    public SharpnessBlade() {
        super(20, true, false);
    }


    public static void onSlashBladeHit(SlashBladeEvent.HitEvent event) {
        if (event.getUser() instanceof Player player) {
            if (HasSharpnessBlade(player)) {
                var damageSource = new DamageSource(player.level().registryAccess()
                        .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                        .getHolderOrThrow(DamageTypes.PLAYER_ATTACK), player);
                event.getTarget().invulnerableTime = 0;
                event.getTarget().hurt(damageSource, SlashBladeUtil.getBaseAttackModifier(player) * (SlashBladeUtil.getRefine(player) * 0.01f));
            }
        }
    }


    public static Boolean HasSharpnessBlade(LivingEntity entity) {
        if (CapabilitySlashBlade.getBladeState(entity.getMainHandItem()).isEmpty()) return false;
        ISlashBladeState state = SlashBladeUtil.getState(entity.getMainHandItem()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();
        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof SharpnessBlade && entity instanceof Player player) {
                if (state.hasSpecialEffect(effectId)) {
                    return SpecialEffect.isEffective(effect, player.experienceLevel);
                }

            }
        }
        return false;
    }
}
