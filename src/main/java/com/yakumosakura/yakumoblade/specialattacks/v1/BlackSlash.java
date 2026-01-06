package com.yakumosakura.yakumoblade.specialattacks.v1;


import com.yakumosakura.yakumoblade.entity.BlackSlashEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlackSlash {
    public static void doSlash(LivingEntity playerIn) {
        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode) {
        if (playerIn.level().isClientSide()) return;

        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent((state) -> {

            Level worldIn = playerIn.level();

            int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
            int count = 3;

            for (int i = 0; i < count; i++) {
                BlackSlashEntity ss = new BlackSlashEntity(YAEntitiesRegistry.BlackSlash, worldIn);

                worldIn.addFreshEntity(ss);

                ss.setSpeed(4F);
                ss.setIsCritical(false);
                ss.setOwner(playerIn);
                ss.setColor(colorCode);
                ss.setRoll(0);
                ss.setDamage(2);
                // force riding
                ss.startRiding(playerIn, true);

                ss.setDelay(10 + i);

                boolean isRight = ss.getDelay() % 2 == 0;
                RandomSource random = worldIn.getRandom();

                double xOffset = random.nextDouble() * 7 * (double) (isRight ? 1 : -1);
                double zOffset = (double) random.nextFloat() * 5;
                double yOffset = random.nextFloat() * 7;

                ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                ss.setOffset(new Vec3(xOffset, yOffset, zOffset));

                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }
        });
    }
}
