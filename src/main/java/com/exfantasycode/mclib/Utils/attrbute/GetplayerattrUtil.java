package com.exfantasycode.mclib.Utils.attrbute;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class GetplayerattrUtil {
    public static void setdamage(Entity entity, int num) {

        LivingEntity playerIn = (LivingEntity) entity;
        playerIn.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(num);

    }

    public static int getdamage(Entity entity) {
        if (entity == null)
            return 0;
        LivingEntity playerIn = (LivingEntity) entity;
        int a = (int) playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return a;
        //* Config.attenuation_term;
    }
}
