package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.hexgram.old.StarEntityDragon;
import com.yakumosakura.yakumoblade.entity.hexgram.old.StarEntityFox;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Star {
    public static void doSlashFox(LivingEntity playerIn, int height) {
        int colorCode = CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        doSlashFox(playerIn, colorCode, height);
    }

    public static void doSlashFox(LivingEntity playerIn, int colorCode, int height) {
        if (playerIn.level().isClientSide()) return;
        Level worldIn = playerIn.level();

        int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
        float rounds = IConcentrationRank.ConcentrationRanks.S.level <= rank ? 4f : 1.5f;
        int count = 2;
        CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).ifPresent
                (
                        (state) ->
                        {
                            for (int i = 0; i < count; i++) {
                                StarEntityFox ss = new StarEntityFox(YAEntitiesRegistry.starEntity, worldIn);

                                worldIn.addFreshEntity(ss);

                                ss.setSpeed(0);
                                ss.setIsCritical(false);
                                ss.setOwner(playerIn);

                                ss.setColor(colorCode);


                                ss.setRoll(0);
                                ss.setDamage(0);
                                // force riding
                                ss.startRiding(playerIn, true);
                                ss.setDelay(10);
                                double xOffset = 0;
                                double yOffset = height;
                                double zOffset = 0;
                                ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                                ss.setOffset(new Vec3(xOffset, yOffset, zOffset));
                                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                            }
                        }
                );
    }

    public static void doSlashDragon(LivingEntity playerIn, int colorCode, int height) {
        if (playerIn.level().isClientSide()) return;
        Level worldIn = playerIn.level();
        StarEntityDragon ss = new StarEntityDragon(YAEntitiesRegistry.starEntityDragon, worldIn);

        worldIn.addFreshEntity(ss);

        ss.setSpeed(0);
        ss.setIsCritical(false);
        ss.setOwner(playerIn);

        ss.setColor(colorCode);


        ss.setRoll(0);
        ss.setDamage(0);
        // force riding
        ss.startRiding(playerIn, true);
        ss.setDelay(10);
        double xOffset = 0;
        double yOffset = height;
        double zOffset = 0;
        ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
        ss.setOffset(new Vec3(xOffset, yOffset, zOffset));
        playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
    }
}
