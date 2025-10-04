package cn.mmf.slashblade_addon.specialattacks;

import cn.mmf.slashblade_addon.entity.EntityWaterDrive;
import cn.mmf.slashblade_addon.registry.SBAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WaterDrive {
    public static EntityDrive doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                      boolean critical, double damage, float speed) {
        return doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, speed);
    }

    public static EntityDrive doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                                      boolean critical, double damage, KnockBacks knockback, float speed) {

        int colorCode = CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem())
                .map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);

        return doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, speed);
    }

    private static void spawnParticle(ParticleOptions type, LivingEntity player, int num, double rate) {
        Level world = player.level();
        RandomSource random = player.getRandom();

        for (int i = 0; i < num; ++i) {
            if (player.level().isClientSide())
                break;
            double xDist = (random.nextFloat() * 2.0F - 1.0F);
            double yDist = (random.nextFloat() * 2.0F - 1.0F);
            double zDist = (random.nextFloat() * 2.0F - 1.0F);
            if (!(xDist * xDist + yDist * yDist + zDist * zDist > 1.0D)) {
                double x = player.getX()
                        + ((random.nextDouble() * 2 - 1) * player.getBbWidth() - random.nextGaussian() * 0.02 * 10.0)
                        * rate;
                double y = player.getY();
                double z = player.getZ()
                        + ((random.nextDouble() * 2 - 1) * player.getBbWidth() - random.nextGaussian() * 0.02 * 10.0)
                        * rate;
                ((ServerLevel) world).sendParticles(type, x, y, z, 0, xDist, yDist + 0.2D, zDist, 1);
            }
        }
    }

    public static EntityDrive doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                                      boolean critical, double damage, KnockBacks knockback, float speed) {

        if (playerIn.level().isClientSide())
            return null;

        playerIn.playSound(SoundEvents.PLAYER_SWIM, 1.0f, 1.5f);

        if (playerIn.isOnFire()) {
            playerIn.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 0.7f,
                    1.2f + 0.8f * playerIn.getRandom().nextFloat());
            playerIn.extinguishFire();
        }

        WaterDrive.spawnParticle(ParticleTypes.FALLING_WATER, playerIn, 30, 2.0F);

        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = playerIn.position().add(0.0D, (double) playerIn.getEyeHeight() * 0.75D, 0.0D)
                .add(lookAngle.scale(0.3f));

        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0)).scale(centerOffset.y))
                .add(VectorHelper.getVectorForRotation(0, playerIn.getViewYRot(0) + 90).scale(centerOffset.z))
                .add(lookAngle.scale(centerOffset.z));
        EntityWaterDrive drive = new EntityWaterDrive(SBAEntitiesRegistry.WaterDrive, playerIn.level());

        drive.setPos(pos.x, pos.y, pos.z);
        drive.setDamage(damage);
        drive.setSpeed(speed);
        drive.shoot(lookAngle.x, lookAngle.y, lookAngle.z, drive.getSpeed(), 0);

        drive.setOwner(playerIn);
        drive.setRotationRoll(roll);

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
