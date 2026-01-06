package com.yakumosakura.yakumoblade.specialeffects.touhouSE;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtils;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.Objects;

public class LwtSE extends SpecialEffect {
    public LwtSE() {
        super(30);
    }


    // 添加静态方法处理伤害事件
    public static void onLivingHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        Entity attacker = source.getDirectEntity();
        if (attacker instanceof EntityDrive projectile) {
            if (projectile.getOwner() instanceof LivingEntity player) {
                if (SlashBladeUtils.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.Lwt)) {
                    LivingEntity target = event.getEntity();
                    // 添加缓慢和虚弱效果（最多3层）
                    addStackableEffect(target, MobEffects.MOVEMENT_SLOWDOWN, 3); // 编号2为SLOWNESS
                    addStackableEffect(target, MobEffects.WEAKNESS, 3); // 编号18为WEAKNESS
                }
            }
        }
        // 检查是否是玩家攻击且攻击带有LwtSE效果
        if (attacker instanceof LivingEntity player &&
                SlashBladeUtils.hasSpecialEffect(player.getMainHandItem(), YASpecialEffectsRegistry.Lwt)) {
            LivingEntity target = event.getEntity();

            if (target.hasEffect(Objects.requireNonNull(MobEffects.MOVEMENT_SLOWDOWN))) {
                int amplifier = target.getEffect(Objects.requireNonNull(MobEffects.MOVEMENT_SLOWDOWN)).getAmplifier();
                float healAmount = event.getAmount() * 0.1f * (amplifier + 1); // 10%每级缓慢
                player.heal(healAmount);
            }
        }
    }

    // 添加可叠加的药水效果
    private static void addStackableEffect(LivingEntity entity, Holder<MobEffect> effect, int maxAmplifier) {
        if (effect == null) return;

        MobEffectInstance existing = entity.getEffect(effect);
        int newAmplifier = existing != null ? Math.min(existing.getAmplifier() + 1, maxAmplifier) : 0;
        int duration = existing != null ? existing.getDuration() + 200 : 200; // 每次叠加200tick

        entity.addEffect(new MobEffectInstance(effect, duration, newAmplifier, false, true));
    }
}
