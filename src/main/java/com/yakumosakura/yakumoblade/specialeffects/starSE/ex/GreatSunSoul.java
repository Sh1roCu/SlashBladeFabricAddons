package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class GreatSunSoul extends SpecialEffect {
    public GreatSunSoul() {
        super(60);
    }


    public static void UpdateEvent(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.GreatSunSoul))) {
            if (event.getEntity() instanceof Player player) {
                if (isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.GreatSunSoul), player.experienceLevel)) {
                    if (!event.isSelected())
                        return;
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.LUCK, 100, 2));
                    player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 100, 0));
                }
            }
        }
    }

    public static void LivingHurtEvent(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity lv)) return;
        if (SlashBladeUtil.hasSpecialEffect(lv.getMainHandItem(), YASpecialEffectsRegistry.GreatSunSoul)) {
            if (event.getSource().getEntity() instanceof Player player) {
                if (isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.GreatSunSoul), player.experienceLevel)) {
                    if (player.level().isNight()) {
                        event.setAmount(event.getAmount() * 1.5f);
                    }
                }
            }
        }
    }
}
