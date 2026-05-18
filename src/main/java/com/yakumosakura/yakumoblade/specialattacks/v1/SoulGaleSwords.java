package com.yakumosakura.yakumoblade.specialattacks.v1;

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

public class SoulGaleSwords {
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) return;
        Level worldIn = playerIn.level();

        int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);
        float rounds = IConcentrationRank.ConcentrationRanks.S.level <= rank ? 4f : 1.5f;
        int count = 100;
        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent
                (
                        (state) ->
                        {
                            for (int i = 0; i < count; i++) {
                                SoulEdgeEntity ss = new SoulEdgeEntity(YAEntitiesRegistry.soul_edge, worldIn);

                                ss.setSpeed(speed);
                                ss.setIsCritical(critical);
                                ss.setOwner(playerIn);

                                ss.setColor(colorCode);


                                ss.setRoll(0);
                                ss.setDamage(damage);
                                ss.setDelay(20 + i);

                                double yOffset = i * 0.005 + 0.5;
                                double zOffset = -1.0;

                                ss.setPos(playerIn.position().add(0, yOffset, zOffset));
                                ss.setOffset(new Vec3(0, yOffset, zOffset));

                                worldIn.addFreshEntity(ss);

                                // force riding
                                ss.startRiding(playerIn, true);

                                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                            }
                        }
                );
    }
}
