package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.exer.EntitySpiralSwords;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class SelfNoAll {

    public static void doslash(LivingEntity livingEntity) {
        boolean alreadySummoned = livingEntity.getPassengers().stream()
                .anyMatch(e -> e instanceof EntitySpiralSwords);

        if (alreadySummoned) {
            List<Entity> list = livingEntity.getPassengers().stream()
                    .filter(e -> e instanceof EntitySpiralSwords).toList();

            list.stream().forEach(e -> {
                ((EntitySpiralSwords) e).doFire();
            });
            return;
        }

        CapabilitySlashBlade.getBladeState(livingEntity.getMainHandItem()).ifPresent((state) -> {
            Level worldIn = livingEntity.level();
            int count = 16;
            for (int i = 0; i < count; i++) {
                EntitySpiralSwords ss = new EntitySpiralSwords(
                        YAEntitiesRegistry.BlueFox, worldIn);

                ss.setPos(livingEntity.position());
                ss.setOwner(livingEntity);
                ss.setColor(state.getColorCode());
                ss.setDamage(3);

                worldIn.addFreshEntity(ss);

                // force riding
                ss.startRiding(livingEntity, true);

                ss.setDelay(360 / count * i);
            }
        });
    }
}
