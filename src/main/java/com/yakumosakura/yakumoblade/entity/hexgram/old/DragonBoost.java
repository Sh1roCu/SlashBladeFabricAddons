package com.yakumosakura.yakumoblade.entity.hexgram.old;

import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class DragonBoost extends EntityAbstractSummonedSword {
    public DragonBoost(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
}
