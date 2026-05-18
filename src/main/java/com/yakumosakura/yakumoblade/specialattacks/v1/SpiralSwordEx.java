package com.yakumosakura.yakumoblade.specialattacks.v1;


import com.yakumosakura.yakumoblade.entity.hexgram.a.EntitySpiralSwordsSon;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.entity.EntitySpiralSwords;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;


public class SpiralSwordEx {


    public static void doslash(LivingEntity sender) {


        CapabilitySlashBlade.getBladeState(sender.getMainHandItem()).ifPresent((state) -> {

            Level worldIn = sender.level();


            int count = 12 + (state.getProudSoulCount() / 1000);


            for (int i = 0; i < count; i++) {
                EntitySpiralSwords ss = new EntitySpiralSwords(
                        SBEntityTypes.SPIRAL_SWORDS, worldIn);

                ss.setPos(sender.position());
                ss.setOwner(sender);
                ss.setColor(state.getColorCode());
                ss.setRoll(0);
                ss.setDamage(5);

                worldIn.addFreshEntity(ss);

                // force riding
                ss.startRiding(sender, true);

                ss.setDelay(360 / count * i);


            }
            for (int i = 0; i < count; i++) {
                EntitySpiralSwordsSon ss = new EntitySpiralSwordsSon(
                        SBEntityTypes.SPIRAL_SWORDS, worldIn);

                ss.setPos(sender.position());
                ss.setOwner(sender);
                ss.setColor(state.getColorCode());
                ss.setRoll(0);
                ss.setDamage(5);

                worldIn.addFreshEntity(ss);

                // force riding
                ss.startRiding(sender, true);

                ss.setDelay(360 / count * i);


            }
        });


    }
}