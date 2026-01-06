package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.exfantasycode.mclib.Utils.EntityPointer;
import com.exfantasycode.mclib.Utils.RandomUtil.RandomNumber;
import com.yakumosakura.yakumoblade.entity.exer.SwordRainEntityFire;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class SwordRainFire {
    public static void dois(LivingEntity livingEntity) {

        LivingEntity target = EntityPointer.raycastForEntityTo(livingEntity.level(), livingEntity, 32, true);
        if (target == null) {
            Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(livingEntity, 100);
            if (targetedEntity.isEmpty()) return;
            target = targetedEntity.get();
        }

        doslash(livingEntity, target);

    }


    public static void dois2(LivingEntity livingEntity) {

        LivingEntity target = EntityPointer.raycastForEntityTo(livingEntity.level(), livingEntity, 32, true);
        if (target == null) {
            Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(livingEntity, 100);
            if (targetedEntity.isEmpty()) return;
            target = targetedEntity.get();
        }

        doslash(livingEntity, target);
        doslash2(livingEntity, target);
        doslash3(livingEntity, target);
        doslash4(livingEntity, target);
    }


    public static void doslash(LivingEntity livingEntity, LivingEntity target) {

        double baseY = target.position().y + (5.0) + 3.5; // 总高度提升8.5格

// 圆周参数
        double radius = 5.0; // 半径5*i1格
        int count = 20; // 总数20个
        double angleStep = 360.0 / count; // 每18度一个实体


        for (int i = 0; i < count; i++) {
            Level worldIn = livingEntity.level();
            SwordRainEntityFire ss = new SwordRainEntityFire(YAEntitiesRegistry.swordRainFire, worldIn);

            worldIn.addFreshEntity(ss);


            ss.setIsCritical(false);
            ss.setOwner(livingEntity);
            ss.setColor(CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem())
                    .map(ISlashBladeState::getColorCode).get());
            ss.setRoll(0);
            ss.setForward(true);
            ss.setDamage(2);
            // force riding
            ss.startRiding(livingEntity, true);

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
            livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
    }

    public static void doslash2(LivingEntity livingEntity, LivingEntity target) {

        double baseY = target.position().y + 1;

// 圆周参数
        double radius = 5.0; // 半径5*i1格
        int count = 20; // 总数20个
        double angleStep = 360.0 / count; // 每18度一个实体


        for (int i = 0; i < count; i++) {
            Level worldIn = livingEntity.level();
            SwordRainEntityFire ss = new SwordRainEntityFire(YAEntitiesRegistry.swordRainFire, worldIn);

            worldIn.addFreshEntity(ss);


            ss.setIsCritical(false);
            ss.setOwner(livingEntity);
            ss.setColor(CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem())
                    .map(ISlashBladeState::getColorCode).get());
            ss.setRoll(0);
            ss.setForward(true);

            // force riding
            ss.startRiding(livingEntity, true);

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
            livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
    }

    public static void doslash3(LivingEntity livingEntity, LivingEntity target) {

        double baseY = target.position().y + (7.0) + 3.5; // 总高度提升8.5格

// 圆周参数
        double radius = 10.0; // 半径5*i1格
        int count = 20; // 总数20个
        double angleStep = 360.0 / count; // 每18度一个实体


        for (int i = 0; i < count; i++) {
            Level worldIn = livingEntity.level();
            SwordRainEntityFire ss = new SwordRainEntityFire(YAEntitiesRegistry.swordRainFire, worldIn);

            worldIn.addFreshEntity(ss);


            ss.setIsCritical(false);
            ss.setOwner(livingEntity);
            ss.setColor(CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem())
                    .map(ISlashBladeState::getColorCode).get());
            ss.setRoll(0);
            ss.setForward(true);

            // force riding
            ss.startRiding(livingEntity, true);

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
            livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
    }

    public static void doslash4(LivingEntity livingEntity, LivingEntity target) {

        double baseY = target.position().y + 2;

// 圆周参数
        double radius = 7.0; // 半径5*i1格
        int count = 20; // 总数20个
        double angleStep = 360.0 / count; // 每18度一个实体


        for (int i = 0; i < count; i++) {
            Level worldIn = livingEntity.level();
            SwordRainEntityFire ss = new SwordRainEntityFire(YAEntitiesRegistry.swordRainFire, worldIn);

            worldIn.addFreshEntity(ss);


            ss.setIsCritical(false);
            ss.setOwner(livingEntity);
            ss.setColor(CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem())
                    .map(ISlashBladeState::getColorCode).get());
            ss.setRoll(0);
            ss.setForward(true);

            // force riding
            ss.startRiding(livingEntity, true);

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
            livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
    }


    public static void doslashse(LivingEntity livingEntity, LivingEntity target) {

        double baseY = target.position().y + 1;

// 圆周参数

        int a = RandomNumber.randomint(10);


        double radius = 5.0; // 半径5*i1格
        int count = a; // 总数20个
        double angleStep = 360.0 / count;


        for (int i = 0; i < count; i++) {
            Level worldIn = livingEntity.level();
            SwordRainEntityFire ss = new SwordRainEntityFire(YAEntitiesRegistry.swordRainFire, worldIn);

            worldIn.addFreshEntity(ss);


            ss.setIsCritical(false);
            ss.setOwner(livingEntity);
            ss.setColor(CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem())
                    .map(ISlashBladeState::getColorCode).get());
            ss.setRoll(0);
            ss.setForward(true);

            ss.setFireCount(1);
            // force riding
            ss.startRiding(livingEntity, true);

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
            livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


        }
    }

}
