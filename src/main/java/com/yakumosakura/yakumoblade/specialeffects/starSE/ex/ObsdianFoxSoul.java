package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.exfantasycode.mclib.Utils.EntityPointer;
import com.yakumosakura.yakumoblade.entity.exer.EntitySpiralSwords2;
import com.yakumosakura.yakumoblade.entity.exer.EntityStormSwords;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
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

public class ObsdianFoxSoul extends SpecialEffect {
    // 常量定义
    private static final int MAIN_HAND_COUNT = 8;
    private static final int OFF_HAND_COUNT = 4;
    private static final int TARGET_RANGE = 32;
    private static final int MAX_TARGET_RANGE = 100;

    public ObsdianFoxSoul() {
        super(90);
    }

    public static void DoSlashEvent(SlashBladeEvent.DoSlashEvent event) {
        // 主手检查
        checkAndTriggerForSpiralSwords(event.getSlashBladeState(), event.getUser(), MAIN_HAND_COUNT);

        // 副手检查
        if (event.getUser() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
                checkAndTriggerForSpiralSwords(offhandState, player, OFF_HAND_COUNT);
            });
        }
    }

    private static void checkAndTriggerForSpiralSwords(ISlashBladeState state, LivingEntity user, int count) {
        if (!isEffectValid(state, user)) return;

        Player player = (Player) user;
        summonSpiralSwords(player, count);
    }

    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        // 主手检查
        checkAndTriggerForSwordRain(event.getSlashBladeState(), event);

        // 副手检查
        if (event.getUser() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
                checkAndTriggerForStormSwords(offhandState, event);
            });
        }
    }

    private static void checkAndTriggerForSwordRain(ISlashBladeState state, SlashBladeEvent.DoSlashEvent event) {
        if (!isEffectValid(state, event.getUser())) return;

        Player player = (Player) event.getUser();
        LivingEntity target = findTargetEntity(player);
        if (target != null) {
            SwordRainFire.doslashse(player, target);
        }
    }

    private static void checkAndTriggerForStormSwords(ISlashBladeState state, SlashBladeEvent.DoSlashEvent event) {
        if (!isEffectValid(state, event.getUser())) return;

        Player player = (Player) event.getUser();
        LivingEntity target = findTargetEntity(player);
        if (target != null) {
            summonStormSwords(player, target);
        }
    }

    private static boolean isEffectValid(ISlashBladeState state, LivingEntity user) {
        if (!state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.ObsdianFoxSoul)))
            return false;
        if (!(user instanceof Player player)) return false;

        int level = player.experienceLevel;
        return SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.ObsdianFoxSoul), level);
    }

    private static LivingEntity findTargetEntity(Player player) {
        LivingEntity target = EntityPointer.raycastForEntityTo(player.level(), player, TARGET_RANGE, true);
        if (target == null) {
            Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(player, MAX_TARGET_RANGE);
            return targetedEntity.orElse(null);
        }
        return target;
    }

    private static void summonSpiralSwords(LivingEntity livingEntity, int count) {
        // 检查是否已存在螺旋剑实体
        boolean alreadySummoned = livingEntity.getPassengers().stream()
                .anyMatch(EntitySpiralSwords2.class::isInstance);

        if (alreadySummoned) {
            livingEntity.getPassengers().stream()
                    .filter(EntitySpiralSwords2.class::isInstance)
                    .map(EntitySpiralSwords2.class::cast)
                    .forEach(EntitySpiralSwords2::doFire);
            return;
        }

        CapabilitySlashBlade.BLADESTATE.maybeGet(livingEntity.getMainHandItem()).ifPresent(state -> {
            Level world = livingEntity.level();
            int angleIncrement = 360 / count;

            for (int i = 0; i < count; i++) {
                EntitySpiralSwords2 spiralSword = new EntitySpiralSwords2(YAEntitiesRegistry.BlueFox, world);
                world.addFreshEntity(spiralSword);

                spiralSword.setPos(livingEntity.position());
                spiralSword.setOwner(livingEntity);
                spiralSword.setColor(state.getColorCode());
                spiralSword.startRiding(livingEntity, true); // force riding
                spiralSword.setDelay(angleIncrement * i);
            }
        });
    }

    private static void summonStormSwords(LivingEntity player, LivingEntity target) {
        CapabilitySlashBlade.BLADESTATE.maybeGet(player.getOffhandItem()).ifPresent(state -> {
            Level world = player.level();
            int count = OFF_HAND_COUNT;
            int angleIncrement = 360 / count;

            for (int i = 0; i < count; i++) {
                EntityStormSwords stormSword = new EntityStormSwords(
                        SBEntityTypes.STORM_SWORDS, world);
                world.addFreshEntity(stormSword);

                stormSword.setOwner(player);
                stormSword.setColor(state.getColorCode());
                stormSword.setRoll(0);
                stormSword.setDamage(1);
                stormSword.startRiding(target, true); // force riding
                stormSword.setDelay(angleIncrement * i);
            }
        });
    }
}