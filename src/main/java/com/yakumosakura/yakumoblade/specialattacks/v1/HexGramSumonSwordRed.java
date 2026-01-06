package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.hexgram.a.HexGramSumonSwordEntitySeven;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class HexGramSumonSwordRed {
    public static void doSlash(LivingEntity playerIn) {
        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).map(state -> state.getColorCode()).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode) {
        doSlash(playerIn, colorCode, 16);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, int count) {
        if (playerIn.level().isClientSide()) return;
        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent((state) -> {
            Level worldIn = playerIn.level();
            for (int i = 0; i < count; i++) {
                HexGramSumonSwordEntitySeven ss = new HexGramSumonSwordEntitySeven(YAEntitiesRegistry.HexGramSumonSword, worldIn);
                worldIn.addFreshEntity(ss);
                ss.setSpeed(4F);
                ss.setIsCritical(true);
                ss.setOwner(playerIn);
                ss.setColor(colorCode);
                ss.setRoll(90F);
                ss.setDamage(2);
                ss.startRiding(playerIn, true);
                ss.setDelay(20 + i);
                boolean isRight = ss.getDelay() % 2 == 0;
                RandomSource random = worldIn.getRandom();
                double xOffset = random.nextDouble() * (double) 2.5F * (double) (isRight ? 1 : -1);
                double yOffset = random.nextFloat() * 2.0F;
                double zOffset = (double) random.nextFloat() * (double) 0.5F;
                ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));
                ss.setOffset(new Vec3(xOffset, yOffset, zOffset));
                playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            }
        });
    }
}
