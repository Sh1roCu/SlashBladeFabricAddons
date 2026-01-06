package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.exfantasycode.mclib.Utils.RandomUtil.RandomNumber;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.world.entity.LivingEntity;

public class SuperSlash {
    public static void doit(LivingEntity entityIn) {
        for (int i = 0; i < RandomNumber.randomint(5) + 1; i++) {
            AttackManager.doSlash(entityIn, RandomNumber.randomint(360), false, false, 0.2F);
        }
    }
}
