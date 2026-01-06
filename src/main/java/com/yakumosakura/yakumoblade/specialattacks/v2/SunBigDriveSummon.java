package com.yakumosakura.yakumoblade.specialattacks.v2;


import com.yakumosakura.yakumoblade.entity.drive.BigDriveEntitySun;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class SunBigDriveSummon {
    public static BigDriveEntitySun doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                            boolean critical, double damage, float speed) {
        return doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, speed);
    }

    public static BigDriveEntitySun doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                            boolean critical, double damage, KnockBacks knockback, float speed) {

        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        damage = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(ISlashBladeState::getBaseAttackModifier).get() * damage;

        return doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, speed);
    }

    public static BigDriveEntitySun doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                                            boolean critical, double damage, KnockBacks knockback, float speed) {

        if (playerIn.level().isClientSide())
            return null;

        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = playerIn.position().add(0.0D, (double) playerIn.getEyeHeight() * 0.75D, 0.0D)
                .add(lookAngle.scale(0.3f));

        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0)).scale(centerOffset.y))
                .add(VectorHelper.getVectorForRotation(0, playerIn.getViewYRot(0) + 90).scale(centerOffset.z))
                .add(lookAngle.scale(centerOffset.z));
        BigDriveEntitySun drive = new BigDriveEntitySun(YAEntitiesRegistry.BigDrive, playerIn.level());
        drive.setPos(pos.x, pos.y, pos.z);
        drive.setDamage(damage);
        drive.setSpeed(speed);
        drive.shoot(lookAngle.x, lookAngle.y, lookAngle.z, drive.getSpeed(),
                0);

        drive.setOwner(playerIn);
        drive.setRotationRoll(roll);
        drive.setBaseSize(9F);
        drive.setColor(colorCode);
        drive.setIsCritical(critical);
        drive.setKnockBack(knockback);
        drive.setLifetime(lifetime);

        if (playerIn != null)
            CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn)
                    .ifPresent(rank -> drive.setRank(rank.getRankLevel(playerIn.level().getGameTime())));

        playerIn.level().addFreshEntity(drive);


        return drive;
    }
}
