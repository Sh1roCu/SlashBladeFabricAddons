package com.yakumosakura.yakumoblade.entity.touhou;

import com.yakumosakura.yakumoblade.entity.MagicCircleEntity;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class YukariEntity extends MagicCircleEntity {
    public YukariEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void doEffect() {

    }
}
