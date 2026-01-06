package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.specialattacks.v2.SunBigDriveSummon;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.slasharts.Drive;
import mods.flammpfeil.slashblade.util.AttackManager;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class TenDrive {
    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, float minSpeed, float maxSpeed, int count) {
        doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, minSpeed, maxSpeed, count);
    }

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count) {

        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);

        doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed, maxSpeed, count);
    }

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count) {
        AttackManager.doSlash(playerIn, 0.0f, Vec3.ZERO, false, false, 2F);
        AttackManager.doSlash(playerIn, -90F, Vec3.ZERO, false, false, 2F);
        Drive.doSlash(playerIn, roll, 0, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed);
        Drive.doSlash(playerIn, roll - 90F, 0, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed);
    }

    public static void doSlashJust(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                   boolean critical, double damage, float minSpeed, float maxSpeed, int count) {
        doSlashJust(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, minSpeed, maxSpeed, count);
    }

    public static void doSlashJust(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                   boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count) {

        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);

        doSlashJust(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed);
    }

    public static void doSlashJust(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                                   boolean critical, double damage, KnockBacks knockback, float minSpeed) {

        AttackManager.doSlash(playerIn, 0.0f, Vec3.ZERO, false, false, 2F);
        AttackManager.doSlash(playerIn, -90F, Vec3.ZERO, false, false, 2F);
        SunBigDriveSummon.doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed);
        SunBigDriveSummon.doSlash(playerIn, roll - 90F, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed);
    }
}
