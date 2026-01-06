package com.yakumosakura.yakumoblade.event;

import com.yakumosakura.yakumoblade.registry.ItemRegistry;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingDropsEvent;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class WitherDrop {
    public static void WitherDead(LivingDropsEvent e) {
        if (e.getEntity() instanceof WitherBoss b) {
            Level level = b.level();
            if (level.isClientSide) {
                return;
            }

            if (level.isNight()) {
                ItemStack p_32005_ = new ItemStack(ItemRegistry.Star_Soul_Crystal);
                Random r = new Random();
                p_32005_.setCount(r.nextInt(2));
                level.addFreshEntity(new ItemEntity(level, b.getX(), b.getY() + 1, b.getZ()
                        , p_32005_));
                if (b.getY() >= 130) {
                    if (level.isRaining()) {
                        ItemStack p_32005_1 = new ItemStack(ItemRegistry.StarTreasurescripture);
                        p_32005_1.setCount(1);
                        level.addFreshEntity(new ItemEntity(level, b.getX(), b.getY() + 1, b.getZ()
                                , p_32005_1));
                    }
                }
            }
        }
    }

}
