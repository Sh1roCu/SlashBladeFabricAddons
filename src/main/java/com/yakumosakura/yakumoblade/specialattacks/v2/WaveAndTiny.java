package com.yakumosakura.yakumoblade.specialattacks.v2;

import com.yakumosakura.yakumoblade.entity.exer.EntitySpiralSwords2;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import com.yakumosakura.yakumoblade.utils.WaitingTick;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.entity.EntitySpiralSwords;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class WaveAndTiny {
    public static void doSlash(LivingEntity playerIn) {
        for (int i = 0; i < 108; i++) {
            WaitingTick.schedule(i + i + 1, () -> {
                doslashs(playerIn, colorcodes(playerIn.getRandom().nextInt(4)));
            });
            WaitingTick.schedule(i + i + 5, () -> {
                doslashs(playerIn, colorcodes(playerIn.getRandom().nextInt(4)));
            });
        }
        for (int i = 0; i < 16; i++) {
            EntitySpiralSwords2 ss = new EntitySpiralSwords2(
                    YAEntitiesRegistry.BlueFox, playerIn.level());

            ss.setPos(playerIn.position());
            ss.setOwner(playerIn);
            ss.setColor(SlashBladeUtil.getColorCode(playerIn));
            ss.setRoll(0);
            ss.setDamage(12);

            playerIn.level().addFreshEntity(ss);

            // force riding
            ss.startRiding(playerIn, true);

            ss.setDelay(360 / 16 * i);


        }
    }


    public static int colorcodes(int i) {
        switch (i) {
            case 0:
                return 15898112;
            case 1:
                return 16027064;
            case 2:
                return 11010303;
            case 3:
                return 16758725;
        }
        return 0;
    }


    public static void doslashs(LivingEntity sender, int colorcode) {

        boolean alreadySummoned = sender.getPassengers().stream()
                .anyMatch(e -> e instanceof EntitySpiralSwords);


        if (alreadySummoned) {
            List<Entity> list = sender.getPassengers().stream()
                    .filter(e -> e instanceof EntitySpiralSwords).toList();

            list.stream().forEach(e -> {

                ((EntitySpiralSwords) e).doFire();
            });
        } else {
            CapabilitySlashBlade.getBladeState(sender.getMainHandItem()).ifPresent((state) -> {

                Level worldIn = sender.level();


                int count = 12;


                for (int i = 0; i < count; i++) {
                    EntitySpiralSwords ss = new EntitySpiralSwords(
                            SBEntityTypes.SPIRAL_SWORDS, worldIn);

                    ss.setPos(sender.position());
                    ss.setOwner(sender);
                    ss.setColor(colorcode);
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

    public static void doslash(LivingEntity sender, int colorcode, int count) {
        boolean alreadySummoned = sender.getPassengers().stream()
                .anyMatch(e -> e instanceof EntitySpiralSwords);
        if (alreadySummoned) {
            List<Entity> list = sender.getPassengers().stream()
                    .filter(e -> e instanceof EntitySpiralSwords).toList();
            list.stream().forEach(e -> {
                ((EntitySpiralSwords) e).doFire();
            });
        } else {
            CapabilitySlashBlade.getBladeState(sender.getMainHandItem()).ifPresent((state) -> {
                Level worldIn = sender.level();
                for (int i = 0; i < count; i++) {
                    EntitySpiralSwords ss = new EntitySpiralSwords(
                            SBEntityTypes.SPIRAL_SWORDS, worldIn);
                    ss.setPos(sender.position());
                    ss.setOwner(sender);
                    ss.setColor(colorcode);
                    ss.setRoll(0);
                    ss.setDamage(5);
                    worldIn.addFreshEntity(ss);
                    ss.startRiding(sender, true);
                    ss.setDelay(360 / count * i);
                }

            });
        }
    }
}