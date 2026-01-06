package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.yakumosakura.yakumoblade.entity.exer.EntitySpiralSwords2;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.specialeffects.utils.SeEX;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class BlueFoxSoul extends SeEX {
    public BlueFoxSoul() {
        super(120);
    }

    public static void DoSlashEvent(SlashBladeEvent.DoSlashEvent event) {
        // 主手检查
        checkAndTrigger(event.getSlashBladeState(), event.getUser(), 6);

        // 副手检查（新增逻辑）
        if (event.getUser() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
                checkAndTrigger(offhandState, player, 2);
            });
        }
    }

    private static void checkAndTrigger(ISlashBladeState state, LivingEntity user, int count) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FoxSoulBlue))) {
            if (!(user instanceof Player player)) return;

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FoxSoulBlue), level)) {
                doslash(player, count);
            }
        }
    }


    public static void doslash(LivingEntity livingEntity, int count) {
        boolean alreadySummoned = livingEntity.getPassengers().stream()
                .anyMatch(e -> e instanceof EntitySpiralSwords2);


        if (alreadySummoned) {
            List<Entity> list = livingEntity.getPassengers().stream()
                    .filter(e -> e instanceof EntitySpiralSwords2).toList();

            list.stream().forEach(e -> {
                ((EntitySpiralSwords2) e).doFire();
            });
            return;
        }
        CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem()).ifPresent((state) -> {
            Level WorldIn = livingEntity.level();

            for (int i = 0; i < count; i++) {
                EntitySpiralSwords2 ss = new EntitySpiralSwords2(
                        YAEntitiesRegistry.BlueFox, WorldIn);

                WorldIn.addFreshEntity(ss);

                ss.setOwner(livingEntity);
                ss.setColor(state.getColorCode());

                // force riding
                ss.startRiding(livingEntity, true);

                ss.setDelay(360 / count * i);
            }
        });
    }
}
