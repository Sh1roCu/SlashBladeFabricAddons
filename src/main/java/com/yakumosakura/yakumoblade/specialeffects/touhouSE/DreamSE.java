package com.yakumosakura.yakumoblade.specialeffects.touhouSE;

import com.yakumosakura.yakumoblade.entity.exer.SwordRainEntityEnder;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtils;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DreamSE extends SpecialEffect {
    public DreamSE() {
        super(30);
    }


    public static void OnDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        if (SlashBladeUtils.hasSpecialEffect(event.getBlade(), YASpecialEffectsRegistry.Dream)
                || hasOffHand(event.getUser())
        ) {
            if (event.getUser().getRandom().nextInt(100) > 51) {
                doSlash(event.getUser());
            }
        }

    }

    public static Boolean hasOffHand(LivingEntity entity) {

        if (entity.getOffhandItem().getItem() instanceof ItemSlashBlade) {
            Boolean hasOffHand = SlashBladeUtils.hasSpecialEffect(entity.getOffhandItem(), YASpecialEffectsRegistry.Dream);
            return hasOffHand;
        }


        return false;
    }

    public static void onLivingHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        Entity attacker = source.getEntity();
        if (attacker instanceof SwordRainEntityEnder e) {
            if (e.getOwner() instanceof LivingEntity o) {
                if (event.getEntity().getType().is(EntityTypeTags.UNDEAD) &&
                        SlashBladeUtils.hasSpecialEffect(o.getMainHandItem(), YASpecialEffectsRegistry.Dream)
                ) {
                    event.setAmount(event.getAmount() * 1.5F); // 对亡灵生物造成1.5倍伤害
                }
            }
        }
    }

    public static void doSlash(LivingEntity playerIn) {
        if (!(playerIn.getMainHandItem().getItem() instanceof ItemSlashBlade)) return;
        double baseY = playerIn.position().y + 1;

        double radius = 5.0;
        int count = 2;
        double angleStep = 360.0 / count;


        for (int i = 0; i < count; i++) {
            Level worldIn = playerIn.level();
            SwordRainEntityEnder ss = new SwordRainEntityEnder(YAEntitiesRegistry.swordRainFire, worldIn);
            ss.setColor(48895);
            ss.setIsCritical(false);
            ss.setOwner(playerIn);
            ss.setRoll(0);
            ss.setForward(true);
            ss.setDamage(10);
            ss.doFire();

            // 计算圆周坐标
            double angle = Math.toRadians(angleStep * i);
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);

            // 设置位置
            ss.setPos(
                    playerIn.position().x + xOffset,
                    baseY, // 统一高度
                    playerIn.position().z + zOffset
            );

            worldIn.addFreshEntity(ss);

            // force riding
            ss.startRiding(playerIn, true);

            playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
    }


}
