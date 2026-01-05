package com.yakumosakura.yakumoblade.specialeffects.ses;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ProudSoulDrive extends SpecialEffect {
    public ProudSoulDrive() {
        super(30);
    }

    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event,
                                    float roll) {
        checkAndTrigger(event.getSlashBladeState(), event, true, event.getRoll());
        if (event.getUser() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {

                checkAndTrigger(offhandState, event, false, roll);

            });
        }
    }

    private static void checkAndTrigger(ISlashBladeState state, SlashBladeEvent.DoSlashEvent event,
                                        boolean isMainHand,
                                        float roll) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.ProudSoulDrive))) {
            if (!(event.getUser() instanceof Player player)) return;
            if (player.getRandom().nextFloat() > 0.58f) return;
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.ProudSoulDrive, level)) {
                Drive.doSlash(player, roll, 10, Vec3.ZERO, false, event.getDamage(), 1.5F);
            }
        }
    }

}
