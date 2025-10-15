package cn.mmf.slashblade_addon.specialattacks;

import cn.mmf.slashblade_addon.entity.GaleSwordsEntity;
import cn.mmf.slashblade_addon.registry.SBAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GaleSwords {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide())
            return;

        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent((state) -> {

            Level worldIn = playerIn.level();

            int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn)
                    .map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
            int count = IConcentrationRank.ConcentrationRanks.S.level <= rank ? 10 : 6;

            for (int i = 0; i < count; i++) {
                GaleSwordsEntity ss = new GaleSwordsEntity(SBAEntitiesRegistry.BlisteringSwords, worldIn);

                worldIn.addFreshEntity(ss);

                ss.setSpeed(speed);
                ss.setIsCritical(critical);
                ss.setOwner(playerIn);
                ss.setColor(0xf7892d);
                ss.setRoll(0);
                ss.setDamage(damage);
                // force riding
                ss.startRiding(playerIn, true);

                ss.setDelay(20 + i);

                boolean isRight = ss.getDelay() % 2 == 0;
                RandomSource random = worldIn.getRandom();

                double xOffset = random.nextDouble() * 2.5 * (isRight ? 1 : -1);
                double yOffset = random.nextFloat() * 2;
                double zOffset = random.nextFloat() * 0.5;

                ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                ss.setOffset(new Vec3(xOffset, yOffset, zOffset));

                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }
        });
    }
}
