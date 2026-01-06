//package com.yakumosakura.yakumoblade.specialeffects.colorSE;
//
//import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
//import mods.flammpfeil.slashblade.event.SlashBladeEvent;
//import net.minecraft.world.entity.player.Player;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraft.resources.ResourceLocation;
//
//public abstract class BaseSpecialEffect extends SpecialEffect {
//
//    public BaseSpecialEffect(int priority) {
//        super(priority);
//    }
//
//    @SubscribeEvent
//    public final void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
//        if (!(event.getEntity() instanceof Player player)) return;
//
//        // 主手检查
//        checkAndApplyEffect(event.getSlashBladeState(), player, event);
//
//        // 副手检查
//        SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
//            checkAndApplyEffect(offhandState, player, event);
//        });
//    }
//
//    private void checkAndApplyEffect(ISlashBladeState state, Player player, SlashBladeEvent.UpdateEvent event) {
//        if (state.hasSpecialEffect(getEffectId())) {
//            handleUpdate(event, player);
//        }
//    }
//
//    protected abstract ResourceLocation getEffectId();
//    protected abstract void handleUpdate(SlashBladeEvent.UpdateEvent event, Player player);
//}
package com.yakumosakura.yakumoblade.specialeffects.colorSE;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;


public class VeryBlue extends SpecialEffect {

    public VeryBlue() {
        super(10);
    }


    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // 主手检查
        checkAndApplyEffect(event.getSlashBladeState(), player, event);

        // 副手检查
        SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {

            checkAndApplyEffect(offhandState, player, event);
        });
    }

    private static void checkAndApplyEffect(ISlashBladeState state, Player player, SlashBladeEvent.UpdateEvent event) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.VERY_BLUE))) {
            if (!event.isSelected())
                return;
            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.VERY_BLUE, level)) {
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100, 1));
            }
        }
    }


}
