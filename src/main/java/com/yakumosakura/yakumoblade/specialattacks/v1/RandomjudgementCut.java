package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.utils.RandomUtil;
import mods.flammpfeil.slashblade.slasharts.JudgementCut;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class RandomjudgementCut {
    public static void doSlash(LivingEntity playerIn) {
        int a = RandomUtil.randomnum(100);

        if (a < 70) {
            JudgementCut.doJudgementCutJust(playerIn);
        } else {
            GigantJudycutSumWhite.doJudgementCutJust(playerIn);
        }

        if (!playerIn.onGround()) {
            playerIn.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 6));
        }
    }
}
