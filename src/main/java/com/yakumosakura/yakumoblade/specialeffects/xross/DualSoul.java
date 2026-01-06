package com.yakumosakura.yakumoblade.specialeffects.xross;

import com.yakumosakura.yakumoblade.registry.slashblade.YAItem;
import com.yakumosakura.yakumoblade.specialattacks.v2.SlashEffect;
import com.yakumosakura.yakumoblade.specialeffects.utils.SeEX;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;


public class DualSoul extends SeEX {


    public DualSoul() {
        super(0);
    }

    public static void onDoDualSlash(SlashBladeEvent.DoSlashEvent event, float roll) {
        if (!(event.getUser() instanceof Player player)) return;
        if (player.getMainHandItem().is(YAItem.getItem(YAItem.SlashBladeOfYakumoBlade))
                && player.getOffhandItem().is(YAItem.getItem(YAItem.SlashBladeOfYakumoBlade))
        ) {

            ISlashBladeState state = SlashBladeUtil.getState(player.getOffhandItem()).get();
            SlashEffect.SakuraEnd.doSlash(
                    player,
                    roll,
                    state.getColorCode(),
                    Vec3.ZERO,
                    false,
                    false,
                    event.getDamage(),
                    KnockBacks.cancel
            );
        }

    }
}
