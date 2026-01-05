package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.exfantasycode.mclib.Utils.EntityPointer;
import com.yakumosakura.yakumoblade.entity.exer.EntityStormSwords;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class StormSwordsHexGram {
    public static void doSlash(LivingEntity playerIn) {
        CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).ifPresent((state) -> {

            Level worldIn = playerIn.level();


            LivingEntity target = EntityPointer.raycastForEntityTo(playerIn.level(), playerIn, 32, true);
            if (target == null) {
                Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(playerIn, 100);
                if (targetedEntity.isEmpty()) return;
                target = targetedEntity.get();
            }


            int count = 10;

            for (int i = 0; i < count; i++) {
                EntityStormSwords ss = new EntityStormSwords(SBEntityTypes.STORM_SWORDS,
                        worldIn);

                worldIn.addFreshEntity(ss);

                ss.setOwner(playerIn);
                ss.setColor(state.getColorCode());
                ss.setRoll(0);
                ss.setDamage(10);
                // force riding
                ss.startRiding(target, true);

                ss.setDelay(360 / count * i);

            }
        });
    }


    public static void doSlash(LivingEntity playerIn, int count) {
        CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem()).ifPresent((state) -> {

            Level worldIn = playerIn.level();


            LivingEntity target = EntityPointer.raycastForEntityTo(playerIn.level(), playerIn, 32, true);
            if (target == null) {
                Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(playerIn, 100);
                if (targetedEntity.isEmpty()) return;
                target = targetedEntity.get();
            }

            for (int i = 0; i < count; i++) {
                EntityStormSwords ss = new EntityStormSwords(SBEntityTypes.STORM_SWORDS,
                        worldIn);

                worldIn.addFreshEntity(ss);

                ss.setOwner(playerIn);
                ss.setColor(state.getColorCode());
                ss.setRoll(0);
                ss.setDamage(1);
                // force riding
                ss.startRiding(target, true);

                ss.setDelay(360 / count * i);

            }
        });
    }
}
