package com.yakumosakura.yakumoblade.specialattacks.v2;

import com.yakumosakura.yakumoblade.entity.SoulEdgeEntityButerfly;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class YuYuKo {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed, int cc1, int cc2) {
        int colorCode = cc1;
        int colorCode2 = cc2;
        doSlash(playerIn, colorCode, colorCode2, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, int colorCode3, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;
        Level worldIn = playerIn.level();


        int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
        float rounds = IConcentrationRank.ConcentrationRanks.S.level <= rank ? 4f : 1.5f;
        int count = 50;
        double radius = 1.0; // Radius for the circular spread
        CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).ifPresent
                (
                        (state) -> {
                            for (int i = 0; i < count / 2; i++) {
                                SoulEdgeEntityButerfly ss = new SoulEdgeEntityButerfly(YAEntitiesRegistry.SoulEdgeB, worldIn);
                                worldIn.addFreshEntity(ss);

                                ss.setSpeed(speed);
                                ss.setIsCritical(critical);
                                ss.setOwner(playerIn);
                                ss.setColor(colorCode);
                                ss.setRoll(0);
                                ss.setDamage(damage);
                                ss.startRiding(playerIn, true);
                                ss.setDelay(0);

                                // Calculate angle for clockwise and counterclockwise distribution
                                double angleClockwise = Math.toRadians(i * (360.0 / (count / 2)));
                                double angleCounterclockwise = Math.toRadians(-i * (360.0 / (count / 2)));

                                // Set positions based on the angles and radius
                                double yOffset = i * 0.005 + 0.5;
                                double xClockwise = Math.cos(angleClockwise) * radius;
                                double zClockwise = Math.sin(angleClockwise) * radius;

                                // Set the position for clockwise entities
                                ss.setPos(playerIn.position().add(xClockwise, yOffset, zClockwise));
                                ss.setOffset(new Vec3(xClockwise, yOffset, zClockwise));
                                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                            }

                            for (int i = 0; i < count / 2; i++) {
                                SoulEdgeEntityButerfly ss = new SoulEdgeEntityButerfly(YAEntitiesRegistry.SoulEdgeB, worldIn);
                                worldIn.addFreshEntity(ss);

                                ss.setSpeed((float) (speed * 1.2));
                                ss.setIsCritical(critical);
                                ss.setOwner(playerIn);
                                ss.setColor(colorCode3);
                                ss.setRoll(0);
                                ss.setDamage(damage);
                                ss.startRiding(playerIn, true);
                                ss.setDelay(1);

                                // Calculate positions using angles for the second half
                                double angleCounterclockwise = Math.toRadians(i * (360.0 / (count / 2)));

                                double yOffset = i * 0.005 + 0.5;
                                double xCounterclockwise = Math.cos(angleCounterclockwise) * radius;
                                double zCounterclockwise = Math.sin(angleCounterclockwise) * radius;

                                // Set position for counterclockwise entities
                                ss.setPos(playerIn.position().add(xCounterclockwise, yOffset, -zCounterclockwise)); // Negative Z for the opposite direction
                                ss.setOffset(new Vec3(xCounterclockwise, yOffset, -zCounterclockwise));
                                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                            }
                        }
                );
    }

}
