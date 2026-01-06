package com.yakumosakura.yakumoblade.specialeffects.swordart;

import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.Collection;
import java.util.List;

public class LostHeart extends SpecialEffect {

    public LostHeart() {
        super(20, true, false);
    }

    public static Boolean HasLostHeart(LivingEntity entity) {
        if (!(entity.getMainHandItem().getItem() instanceof ItemSlashBlade)) return false;
        ISlashBladeState state = SlashBladeUtil.getState(entity.getMainHandItem()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();
        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof LostHeart Effect && entity instanceof Player player) {
                if (state.hasSpecialEffect(effectId)) {
                    return SpecialEffect.isEffective(effect, player.experienceLevel);
                }

            }
        }
        return false;
    }

    public static void onLivingDeathEvent(LivingEntity entity, DamageSource damageSource) {
        if ((damageSource.getEntity() instanceof Player player)) {
            onKill(player);
        }
    }

    public static void onKill(Player player) {
        if (!HasLostHeart(player)) return;
        if (player.hasEffect(MobEffects.DAMAGE_BOOST)) {
            int level = player.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() + 1;
            if (level > 2) {
                level = 2;
            }
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, level));
        }
        // 给予玩家力量效果，持续10秒
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0, false, false));

        // 获取周围20格范围内的生物
        AABB area = new AABB(player.getX() - 20, player.getY() - 20, player.getZ() - 20,
                player.getX() + 20, player.getY() + 20, player.getZ() + 20);
        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, area);

        // 给予范围内生物发光效果
        for (LivingEntity entity : entities) {
            if (entity instanceof Player) continue; // 排除其他玩家
            entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0, false, false));
        }
    }


}
