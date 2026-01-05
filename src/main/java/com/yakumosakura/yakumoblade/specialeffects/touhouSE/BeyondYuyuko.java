package com.yakumosakura.yakumoblade.specialeffects.touhouSE;

import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingDamageEvent;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.Random;

public class BeyondYuyuko extends SpecialEffect {
    private static final Random RANDOM = new Random();


    public BeyondYuyuko() {
        super(50);
    }

    // 处理伤害事件
    public static void onLivingDamage(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (HasYuYukoSe(player)) {
                LivingEntity target = event.getEntity();
                int playerLevel = player.experienceLevel;
                BeyondYuyuko beyondYuyuko = new BeyondYuyuko();
                // 等级大于50时的正面效果
                if (playerLevel >= beyondYuyuko.getRequestLevel()) {
                    // 生成樱花和末地烛粒子效果
                    if (player.level() instanceof ServerLevel serverLevel) {
                        for (int i = 0; i < 15; i++) {
                            double x = target.getX() + (RANDOM.nextDouble() - 0.5) * 2.0;
                            double y = target.getY() + RANDOM.nextDouble() * 2.0;
                            double z = target.getZ() + (RANDOM.nextDouble() - 0.5) * 2.0;

                            if (RANDOM.nextBoolean()) {
                                // 樱花粒子（使用类似樱花的颜色）
                                serverLevel.sendParticles(ParticleTypes.END_ROD, x, y, z, 1, 0, 0, 0, 0.1);
                            } else {
                                // 末地烛粒子
                                serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 1, 0, 0, 0, 0.1);
                            }
                        }
                    }

                    // 给予目标负面效果
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 600, 2)); // 30秒凋零III
                    target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 2)); // 30秒虚弱III
                    target.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 1)); // 30秒饥饿II
                    target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0)); // 30秒反胃I
                }
                // 等级低于50时的反噬效果
                else {
                    // 给予玩家反噬效果
                    player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 4)); // 5秒凋零V
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0)); // 5秒饥饿I
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 2)); // 5秒虚弱III
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2)); // 5秒缓慢III
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1)); // 5秒挖掘疲劳II
                }
            }
        }
    }


    public static void onLivingDeath(LivingEntity entity, DamageSource damageSource) {
        if (damageSource.getEntity() instanceof Player player) {
            BeyondYuyuko beyondYuyuko = new BeyondYuyuko();
            if (HasYuYukoSe(player) && player.experienceLevel >= beyondYuyuko.getRequestLevel()) {


                if (player.hasEffect(MobEffects.REGENERATION)) {
                    int level = player.getEffect(MobEffects.REGENERATION).getAmplifier() + 1;
                    if (level > 2) {
                        level = 2;
                    }
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 140, level));
                }
                if (player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
                    int level = player.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() + 1;
                    if (level > 2) {
                        level = 2;
                    }
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 140, level));
                }
                if (player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                    int level = player.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() + 1;
                    if (level > 2) {
                        level = 2;
                    }
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 140, level));
                }
                // 给予玩家增益效果
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 140, 0));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 140, 0));
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 140, 0));


            }
        }
    }


    public static void onLivingUpdate(SlashBladeEvent.SummonedSwordOnHitEntityEvent event) {
        if (event.getSummonedSword().getOwner() instanceof Player player) {
            if (HasYuYukoSe(player)) {
                if (event.getTarget() instanceof LivingEntity target) {
                    if (target.level() instanceof ServerLevel serverLevel) {
                        for (int i = 0; i < 30; i++) {
                            double x = target.getX() + (RANDOM.nextDouble() - 0.5) * 2.0;
                            double y = target.getY() + RANDOM.nextDouble() * 2.0;
                            double z = target.getZ() + (RANDOM.nextDouble() - 0.5) * 2.0;
                            serverLevel.sendParticles(ParticleTypes.CHERRY_LEAVES, x, y, z, 1, 0, 0, 0, 0.1);
                        }
                    }
                    target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 600, 2));
                }

            }
        }
    }


    public static void onLivingUpdate(SlashBladeEvent.UpdateEvent event) {
        if (event.getEntity() instanceof Player player) {
            checkPlayerHealth(player);
        }
    }

    // 检查玩家生命值并在低时触发效果
    public static void checkPlayerHealth(Player player) {
        if (HasYuYukoSe(player) && player.experienceLevel >= new BeyondYuyuko().getRequestLevel()) {
            if (player.getHealth() <= player.getMaxHealth() * 0.25) {

                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0)); // 30秒速度I
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0)); // 30秒力量I
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0));


            }
        }

        // 持续应用反噬效果（如果等级低于50）
        if (HasYuYukoSe(player) && player.experienceLevel < 50) {
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 4)); // 5秒凋零V
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0)); // 5秒饥饿I
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 2)); // 5秒虚弱III
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2)); // 5秒缓慢III
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1)); // 5秒挖掘疲劳II
        }
    }


    public static Boolean HasYuYukoSe(LivingEntity entity) {
        if (!(entity instanceof Player)) return false;
        if (CapabilitySlashBlade.BLADESTATE.maybeGet(entity.getMainHandItem()).isEmpty()) return false;

        ISlashBladeState state = SlashBladeUtil.getState(entity.getMainHandItem()).orElse(null);
        if (state == null) return false;

        Collection<ResourceLocation> effects = state.getSpecialEffects();
        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof BeyondYuyuko && entity instanceof Player player) {
                return state.hasSpecialEffect(effectId);
            }
        }
        return false;
    }
}