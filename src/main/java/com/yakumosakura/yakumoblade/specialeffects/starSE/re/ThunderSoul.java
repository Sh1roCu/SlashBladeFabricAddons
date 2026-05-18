package com.yakumosakura.yakumoblade.specialeffects.starSE.re;

import com.exfantasycode.mclib.Utils.Dash.DashMessage;
import com.exfantasycode.mclib.Utils.Dash.SMoveUtil;
import com.exfantasycode.mclib.Utils.EntityPointer;
import com.yakumosakura.yakumoblade.entity.exer.SwordRainEntityLightning;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.specialeffects.utils.SeEX;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Optional;


public class ThunderSoul extends SeEX {

    public ThunderSoul() {
        super(30);
    }


    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.ThunderSoul))) {
            if (!(event.getEntity() instanceof Player player)) {
                return;
            }
            if (!event.isSelected())
                return;

            Level inlevel = player.level();
            int level = player.experienceLevel;

            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.ThunderSoul, level)) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 6));

            }

        }
    }

    public static void riseUp(LivingHurtEvent event) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (!(hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.ThunderSoul))) {
            return;
        }
        int level = player.experienceLevel;

        if (SpecialEffect.isEffective(YASpecialEffectsRegistry.ThunderSoul, level)) {
            if (event.getSource().is(DamageTypes.LIGHTNING_BOLT)) {

                event.setAmount(0);
            }

        }


    }


    public static void onHit(SlashBladeEvent.HitEvent event) {
        if (!(event.getUser() instanceof Player player)) return;
        if (event.getTarget() == null) return;

        if (hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.ThunderSoul)) {
            if (player.onGround()) {
                SMoveUtil.sendDashMessage(player, 2, 0);
                DashMessage.vmove(event.getTarget(), 2, 0);
                event.getTarget().addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 6));
            } else {

                if (Math.random() < 0.2) {
                    SMoveUtil.sendDashMessage(player, 2, 0);
                    DashMessage.vmove(event.getTarget(), 2, 0);
                    event.getTarget().addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 6));
                }

            }
        }


    }

    public static void onDoSlash(SlashBladeEvent.DoSlashEvent event) {
        if (!(event.getUser() instanceof Player player)) return;


        if (hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.ThunderSoul)) {
            if (!player.onGround()) {
                LivingEntity target = EntityPointer.raycastForEntityTo(player.level(), player, 32, true);
                if (target == null) {
                    Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(player, 100);
                    if (targetedEntity.isEmpty()) return;
                    target = targetedEntity.get();
                }


                doslash2(player, target);


            }
        }


    }


    public static void doslash2(LivingEntity livingEntity, LivingEntity target) {

        double baseY = target.position().y + 1;

// 圆周参数
        double radius = 5.0; // 半径5*i1格
        int count = 4; // 总数20个
        double angleStep = 360.0 / count; // 每18度一个实体


        for (int i = 0; i < count; i++) {
            Level worldIn = livingEntity.level();
            SwordRainEntityLightning ss = new SwordRainEntityLightning(YAEntitiesRegistry.swordRainFire, worldIn);

            ss.setIsCritical(false);
            ss.setOwner(livingEntity);
            ss.setColor(CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem())
                    .map(ISlashBladeState::getColorCode).get());
            ss.setRoll(0);
            ss.setForward(true);
            ss.doFire();

            // 计算圆周坐标
            double angle = Math.toRadians(angleStep * i);
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);

            // 设置位置
            ss.setPos(
                    target.position().x + xOffset,
                    baseY, // 统一高度
                    target.position().z + zOffset
            );

            worldIn.addFreshEntity(ss);

            // force riding
            ss.startRiding(livingEntity, true);

            livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
    }


}
