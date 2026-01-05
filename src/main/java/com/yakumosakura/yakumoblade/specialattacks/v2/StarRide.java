package com.yakumosakura.yakumoblade.specialattacks.v2;

import com.exfantasycode.mclib.Utils.EntityPointer;
import com.yakumosakura.yakumoblade.entity.star.StarRiderEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class StarRide {
    public static void doSlash(LivingEntity playerIn) {
        // 检查是否在客户端，如果是则直接返回
        if (playerIn.level().isClientSide()) return;

        // 获取目标实体
        Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(playerIn, 60);
        if (targetedEntity.isEmpty()) return;

        LivingEntity target = targetedEntity.get();
        Level worldIn = playerIn.level();


        StarRiderEntity sr = new StarRiderEntity(YAEntitiesRegistry.StarRider, worldIn);
        sr.setOwner(playerIn);
        sr.setPos(
                target.getX(),
                target.getY() + 10,
                target.getZ()
        );
        sr.setLifeTime(50);
        playerIn.level().addFreshEntity(sr);


    }
}
