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
        CapabilitySlashBlade.BLADESTATE.maybeGet(livingEntity.getMainHandItem()).ifPresent((state) -> {
            Level WorldIn = livingEntity.level();
            int count = 16;
            for (int i = 0; i < count; i++) {
                EntitySpiralSwords ss = new EntitySpiralSwords(
                        YAEntitiesRegistry.BlueFox, WorldIn);

                // 【坐标前置】
                // 必须最先执行！把实体从默认的 (0,0,0) 强行拉到玩家身边
                // 这样它的出生包就会在当前已加载的区块发送，彻底解决跑出 14 个区块就消失的 Bug
                ss.setPos(livingEntity.getX(), livingEntity.getY() + 1.0, livingEntity.getZ());


                ss.setOwner(livingEntity);
                ss.setColor(state.getColorCode());
                ss.setDamage(3);
                ss.setDelay(360 / count * i);


                // 强制在生成前优先绑定坐标
                WorldIn.addFreshEntity(ss);

                // 骑乘部分必须在实体生成后执行，否则会因为坐标未绑定而导致骑乘失败
                ss.startRiding(livingEntity, true);
            }
        });
    }
}
