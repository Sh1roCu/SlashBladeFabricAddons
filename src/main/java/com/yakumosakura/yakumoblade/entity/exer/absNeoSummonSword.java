package com.yakumosakura.yakumoblade.entity.exer;

import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class absNeoSummonSword extends EntityAbstractSummonedSword {
    public absNeoSummonSword(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void setDamage(double damageIn) {
        float extra = 1;
        if (super.getOwner() instanceof LivingEntity player) {
            extra += (float) ((Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).getValue()) * 0.1f);
        }
        super.setDamage(damageIn * extra);
    }
}
