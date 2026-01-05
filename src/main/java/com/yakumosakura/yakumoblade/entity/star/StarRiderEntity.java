package com.yakumosakura.yakumoblade.entity.star;

import com.yakumosakura.yakumoblade.entity.MagicCircleEntity;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class StarRiderEntity extends MagicCircleEntity {
    public StarRiderEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void doEffect() {
        if (this.getOwner() instanceof LivingEntity livingEntity) {
            doSlash(livingEntity, this);
        }
    }


    public static EntityDrive doSlash(LivingEntity playerIn, StarRiderEntity starRiderEntity) {
        if (playerIn.level().isClientSide()) {
            return null;
        } else {
            Vec3 centerOffset = Vec3.ZERO;
            Vec3 lookAngle = starRiderEntity.getLookAngle();
            Vec3 pos = starRiderEntity.position().add(0.0F,
                    (double) starRiderEntity.getEyeHeight() * (double) 0.75F,
                    0.0F).add(lookAngle.scale(0.3F));
            pos = pos.add(VectorHelper.getVectorForRotation(-90.0F,
                            starRiderEntity.getViewYRot(0.0F)).scale(centerOffset.y))
                    .add(VectorHelper
                            .getVectorForRotation(0.0F, starRiderEntity.getViewYRot(0.0F) + 90.0F)
                            .scale(centerOffset.z)).add(lookAngle.scale(centerOffset.z));
            EntityDrive drive = new EntityDrive(SBEntityTypes.DRIVE, starRiderEntity.level());
            drive.setPos(pos.x, pos.y, pos.z);
            drive.setDamage(1);
            drive.setSpeed(5f);
            Vec3 resultAngle = lookAngle.yRot(0.0f);
            drive.shoot(resultAngle.x, resultAngle.y, resultAngle.z, drive.getSpeed(), 0.0F);
            drive.setOwner(playerIn);
            drive.setRotationRoll(90);
            drive.setColor(14287103);
            drive.setLifetime(20f);

            playerIn.level().addFreshEntity(drive);
            return drive;
        }
    }
}
