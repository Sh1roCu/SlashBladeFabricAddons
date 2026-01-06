package com.exfantasycode.mclib.Utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public interface TrackingEntity {
    Entity getEntity();

    LivingEntity getOwnerE();
}
