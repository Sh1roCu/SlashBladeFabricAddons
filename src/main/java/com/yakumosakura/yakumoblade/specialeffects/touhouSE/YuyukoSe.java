package com.yakumosakura.yakumoblade.specialeffects.touhouSE;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class YuyukoSe extends SpecialEffect {
    public YuyukoSe() {
        super(50, false, false);
    }

    public static void onLivingAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (HasYuYukoSe(player)) {
                int playerLevel = player.experienceLevel;

                if (SpecialEffect.isEffective(YASpecialEffectsRegistry.YuyukoSe, playerLevel)) {
                    // 正面效果
                    LivingEntity target = event.getEntity();

                    // 给予目标负面效果
                    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 26 * 20, 4)); // 凋零V，26秒
                    target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 26 * 20, 2)); // 虚弱III
                    target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 26 * 20, 1)); // 挖掘疲劳II
                    target.addEffect(new MobEffectInstance(MobEffects.HUNGER, 26 * 20, 0)); // 饥饿I


                    Level level = player.level();
                    level.addParticle(ParticleTypes.CHERRY_LEAVES,
                            target.getX(), target.getY() + 1.0, target.getZ(), 0, 0, 0);
                    level.addParticle(ParticleTypes.END_ROD,
                            target.getX(), target.getY() + 1.0, target.getZ(), 0, 0, 0);

                    CapabilityConcentrationRank.RANK_POINT.maybeGet(player)
                            .ifPresent((rank) -> rank.setLastRankRise(5));
                }
            }
        }
    }


    public static void onLivingUpdate(SlashBladeEvent.UpdateEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (HasYuYukoSe(player)) {
                int playerLevel = player.experienceLevel;

                if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.YuyukoSe), playerLevel)) {

                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0)); // 生命恢复I
                    player.addEffect(new MobEffectInstance(MobEffects.JUMP, 100, 0)); // 跳跃提升I

                    Level level = player.level();
                    for (int i = 0; i < 2; i++) {
                        double offsetX = (player.getRandom().nextDouble() - 0.5) * 2.0;
                        double offsetY = player.getRandom().nextDouble() * 2.0;
                        double offsetZ = (player.getRandom().nextDouble() - 0.5) * 2.0;
                        level.addParticle(ParticleTypes.CHERRY_LEAVES,
                                player.getX() + offsetX, player.getY() + offsetY, player.getZ() + offsetZ,
                                0, 0, 0);
                    }
                } else {

                    player.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 4)); // 凋零V
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0)); // 饥饿I
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 2)); // 虚弱III
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2)); // 缓慢III
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 1)); // 挖掘疲劳II
                }
            }
        }
    }


    public static Boolean HasYuYukoSe(LivingEntity entity) {
        if (CapabilitySlashBlade.getBladeState(entity.getMainHandItem()).isEmpty()) return false;
        ISlashBladeState state = SlashBladeUtil.getState(entity.getMainHandItem()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();
        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof YuyukoSe Effect && entity instanceof Player player) {

                return state.hasSpecialEffect(effectId);


            }
        }
        return false;
    }
}
