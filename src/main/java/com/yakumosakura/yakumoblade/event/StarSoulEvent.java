package com.yakumosakura.yakumoblade.event;

import com.yakumosakura.yakumoblade.registry.ItemRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.RandomUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import net.minecraft.world.entity.player.Player;

public class StarSoulEvent {
    public static void hitBladeShelf(SlashBladeEvent.BladeStandAttackEvent event) {
        if (event.getDamageSource().getEntity() instanceof Player player) {
            if (player.getMainHandItem().getItem() == ItemRegistry.Star_Soul_Crystal) {
                event.setCanceled(true);
                ISlashBladeState slashBladeState = event.getSlashBladeState();


                if (RandomUtil.randomnum(100) <= 5
                        && !slashBladeState.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.STAR_SOUL))) {
                    slashBladeState.addSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.STAR_SOUL));

                }

                slashBladeState.setKillCount(slashBladeState.getKillCount() + 200);
                slashBladeState.setProudSoulCount(slashBladeState.getProudSoulCount() + 600);
                slashBladeState.setRefine(slashBladeState.getRefine() + 1);
                slashBladeState.setMaxDamage(slashBladeState.getMaxDamage() + 2);
                player.getMainHandItem().shrink(1);


            }
        }
    }
}
