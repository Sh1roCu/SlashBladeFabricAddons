package com.yakumosakura.yakumoblade.specialeffects.starSE.re;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


public class StarSoul extends SpecialEffect {
    public StarSoul() {
        super(10, false, false);
    }


    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        // 主手检查
        checkAndApplyEffect(event.getSlashBladeState(), event);

        // 副手检查（新增逻辑）
        if (event.getEntity() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
                checkAndApplyEffect(offhandState, event);
            });
        }
    }

    private static void checkAndApplyEffect(ISlashBladeState state, SlashBladeEvent.UpdateEvent event) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.STAR_SOUL))) {
            if (!(event.getEntity() instanceof Player player)) return;
            if (!(player.getMainHandItem().getItem() instanceof ItemSlashBlade slashBlade)) return;
            // 保留原选择状态判断
            if (!event.isSelected()) return;

            Level inlevel = player.level();
            int level = player.experienceLevel;
            if (inlevel.isNight() && SpecialEffect.isEffective(YASpecialEffectsRegistry.STAR_SOUL, level)) {
                if (state.getDamage() > -state.getMaxDamage()) {
                    state.setDamage(state.getDamage() - 1);
                }
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
            }
        }
    }

}
