package cn.mmf.slashblade_addon.specialattacks;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireSpiral {
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

    public static void doCircleSlash(LivingEntity living, float roll, float yRot) {
        if (living.level().isClientSide())
            return;

        ItemStack blade = living.getMainHandItem();
        if (CapabilitySlashBlade.BLADESTATE.maybeGet(blade).isEmpty())
            return;
        float rot = living.getYRot() - 22.5F + yRot;

        SlashBladeEvent.DoSlashEvent event = new SlashBladeEvent.DoSlashEvent(blade,
                CapabilitySlashBlade.BLADESTATE.maybeGet(blade).orElseThrow(NullPointerException::new),
                living, roll, true, 1D, KnockBacks.cancel);
        event.setYRot(yRot);
        SlashBladeEvent.DO_SLASH.invoker().onDoSlash(event);
        if (event.isCanceled())
            return;

        spawnParticle(ParticleTypes.SMALL_FLAME, living, 25, 2.0F);

        Vec3 pos = living.position().add(0.0D, (double) living.getEyeHeight() * 0.75D, 0.0D)
                .add(living.getLookAngle().scale(0.3f));

        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, living.getViewYRot(0)).scale(Vec3.ZERO.y))
                .add(VectorHelper.getVectorForRotation(0, living.getViewYRot(0) + 90).scale(Vec3.ZERO.z))
                .add(living.getLookAngle().scale(Vec3.ZERO.z));

        EntitySlashEffect jc = new EntitySlashEffect(SlashBlade.RegistryEvents.SlashEffect, living.level()) {

            @Override
            public SoundEvent getSlashSound() {
                return SoundEvents.EMPTY;
            }
        };
        jc.setPos(pos.x, pos.y, pos.z);
        jc.setOwner(event.getUser());

        jc.setRotationRoll(roll);
        jc.setYRot(rot);
        jc.setXRot(0);

        jc.setColor(0xFF0000);

        jc.setMute(false);
        jc.setIsCritical(event.isCritical());

        jc.setDamage(event.getDamage());

        jc.setKnockBack(event.getKnockback());

        if (living != null)
            CapabilityConcentrationRank.RANK_POINT.maybeGet(living)
                    .ifPresent(rank -> jc.setRank(rank.getRankLevel(living.level().getGameTime())));

        living.level().addFreshEntity(jc);
    }
}
