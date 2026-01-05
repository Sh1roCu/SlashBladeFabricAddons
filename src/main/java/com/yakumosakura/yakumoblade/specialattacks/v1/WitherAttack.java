package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.WitherAttackEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.concentrationrank.IConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WitherAttack {
    //sa实现
    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        int colorCode = CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).map(ISlashBladeState::getColorCode).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (!playerIn.level().isClientSide()) {
            CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).ifPresent((state) -> {
                Level worldIn = playerIn.level();
                int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).map((r) -> {
                    return r.getRank(worldIn.getGameTime()).level;
                }).orElse(0);
                int count = IConcentrationRank.ConcentrationRanks.S.level <= rank ? 24 : 12;

                for (int i = 0; i < count; ++i) {
                    WitherAttackEntity ss = new WitherAttackEntity(YAEntitiesRegistry.wither_attack, worldIn);
                    worldIn.addFreshEntity(ss);
                    ss.setSpeed(speed);
                    ss.setIsCritical(critical);
                    ss.setOwner(playerIn);
                    ss.setColor(colorCode);
                    ss.setRoll(0.0F);
                    ss.setDamage(damage);
                    ss.startRiding(playerIn, true);
                    ss.setDelay(20 + i);
                    boolean isRight = ss.getDelay() % 2 == 0;
                    RandomSource random = worldIn.getRandom();
                    double xOffset = random.nextDouble() * 2.5 * (double) (isRight ? 1 : -1);
                    double yOffset = random.nextFloat() * 2.0F;
                    double zOffset = (double) random.nextFloat() * 0.5;
                    ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                    ss.setOffset(new Vec3(xOffset, yOffset, zOffset));
                    playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
                }

            });
        }
    }
}
