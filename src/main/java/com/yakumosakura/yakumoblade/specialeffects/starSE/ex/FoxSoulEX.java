package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.exfantasycode.mclib.Utils.EntityPointer;
import com.yakumosakura.yakumoblade.entity.exer.EntityStormSwords;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.specialattacks.v1.SwordRainFire;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class FoxSoulEX extends SpecialEffect {


    public FoxSoulEX() {
        super(90);
    }


    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        // 主手检查
        checkAndTrigger(event.getSlashBladeState(), event);

        // 副手检查（新增逻辑）
        if (event.getUser() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
                checkAndTriggerOffhand(offhandState, event);
            });
        }
    }

    private static void checkAndTrigger(ISlashBladeState state, SlashBladeEvent.DoSlashEvent event) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FoxsoulEX))) {
            if (!(event.getUser() instanceof Player player)) return;

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FoxsoulEX), level)) {
                LivingEntity target = EntityPointer.raycastForEntityTo(player.level(), player, 32, true);
                if (target == null) {
                    Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(player, 100);
                    if (targetedEntity.isEmpty()) return;
                    target = targetedEntity.get();
                }
                SwordRainFire.doslashse(player, target);
            }
        }
    }

    private static void checkAndTriggerOffhand(ISlashBladeState state, SlashBladeEvent.DoSlashEvent event) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FoxsoulEX))) {
            if (!(event.getUser() instanceof Player player)) return;

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FoxsoulEX), level)) {
                LivingEntity target = EntityPointer.raycastForEntityTo(player.level(), player, 32, true);
                if (target == null) {
                    Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(player, 100);
                    if (targetedEntity.isEmpty()) return;
                    target = targetedEntity.get();
                }
                doSlash(player, target);
            }
        }
    }


    public static void doSlash(LivingEntity playerIn, LivingEntity target) {
        CapabilitySlashBlade.getBladeState(playerIn.getOffhandItem()).ifPresent((state) -> {

            Level worldIn = playerIn.level();
            int count = 4;

            for (int i = 0; i < count; i++) {
                EntityStormSwords ss = new EntityStormSwords(SBEntityTypes.STORM_SWORDS,
                        worldIn);

                worldIn.addFreshEntity(ss);

                ss.setOwner(playerIn);
                ss.setColor(state.getColorCode());
                ss.setRoll(0);
                ss.setDamage(1);
                // force riding
                ss.startRiding(target, true);

                ss.setDelay(360 / count * i);

            }
        });
    }
}