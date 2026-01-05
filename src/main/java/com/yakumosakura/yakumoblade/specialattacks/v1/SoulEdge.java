package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.entity.SoulEdgeEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SoulEdge {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        int colorCode = CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;
        Level worldIn = playerIn.level();

        int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
        float rounds = IConcentrationRank.ConcentrationRanks.S.level <= rank ? 4f : 1.5f;
        int count = 300;
        double radius = 1.0; // Radius for the circular spread
        CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).ifPresent
                (
                        (state) -> {
                            for (int i = 0; i < count / 2; i++) {
                                int finalI = i;
                                Yakumoblade.queueServerWork(i, () -> {
                                    SoulEdgeEntity ss = new SoulEdgeEntity(YAEntitiesRegistry.soul_edge, worldIn);
                                    worldIn.addFreshEntity(ss);

                                    ss.setSpeed(speed);
                                    ss.setIsCritical(critical);
                                    ss.setOwner(playerIn);
                                    ss.setColor(finalI < (count / 2) + 1 ? colorCode : 3825);
                                    ss.setRoll(0);
                                    ss.setDamage(damage);
                                    ss.startRiding(playerIn, true);
                                    ss.setDelay(10 + finalI);

                                    // Calculate angle for clockwise and counterclockwise distribution
                                    double angleClockwise = Math.toRadians(finalI * (360.0 / (count / 2)));
                                    double angleCounterclockwise = Math.toRadians(-finalI * (360.0 / (count / 2)));

                                    // Set positions based on the angles and radius
                                    double yOffset = finalI * 0.005 + 0.5;
                                    double xClockwise = Math.cos(angleClockwise) * radius;
                                    double zClockwise = Math.sin(angleClockwise) * radius;
                                    double xCounterclockwise = Math.cos(angleCounterclockwise) * radius;
                                    double zCounterclockwise = Math.sin(angleCounterclockwise) * radius;

                                    // Set the position for clockwise entities
                                    ss.setPos(playerIn.position().add(xClockwise, yOffset, zClockwise));
                                    ss.setOffset(new Vec3(xClockwise, yOffset, zClockwise));
                                    playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                                });

                            }

                            for (int i = 0; i < count / 2; i++) {
                                int finalI = i;
                                Yakumoblade.queueServerWork(i, () -> {
                                    SoulEdgeEntity ss = new SoulEdgeEntity(YAEntitiesRegistry.soul_edge, worldIn);
                                    worldIn.addFreshEntity(ss);

                                    ss.setSpeed((float) (speed * 1.2));
                                    ss.setIsCritical(critical);
                                    ss.setOwner(playerIn);
                                    ss.setColor(finalI < (count / 2) + 1 ? 16738656 : 4587350);
                                    ss.setRoll(0);
                                    ss.setDamage(damage);
                                    ss.startRiding(playerIn, true);
                                    ss.setDelay(10 + finalI);

                                    // Calculate positions using angles for the second half
                                    double angleCounterclockwise = Math.toRadians(finalI * (360.0 / (count / 2)));

                                    double yOffset = finalI * 0.005 + 0.5;
                                    double xCounterclockwise = Math.cos(angleCounterclockwise) * radius;
                                    double zCounterclockwise = Math.sin(angleCounterclockwise) * radius;

                                    // Set position for counterclockwise entities
                                    ss.setPos(playerIn.position().add(xCounterclockwise, yOffset, -zCounterclockwise)); // Negative Z for the opposite direction
                                    ss.setOffset(new Vec3(xCounterclockwise, yOffset, -zCounterclockwise));
                                    playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                                });

                            }
                        }
                );
    }

}
