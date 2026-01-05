package com.yakumosakura.yakumoblade.entity.exer;

import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class SwordRainEntityRideOn extends SwordRainEntity {
    public SwordRainEntityRideOn(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    private int hurtTimer = 0;

    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        LivingEntity target = (LivingEntity) p_213868_1_.getEntity();
        if (!this.level().isClientSide) {
            // 使目标骑乘到当前实体上
            target.startRiding(this);
            // 防止立即消失
            this.setDeltaMovement(0, 0, 0);
            this.setNoGravity(true);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.isAlive()) {
            // 每5tick执行一次伤害
            if (++hurtTimer >= 5) {
                for (Entity passenger : this.getPassengers()) {
                    // 造成2点伤害（1颗心）
                    var damageSource = new DamageSource(level().registryAccess()
                            .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                            .getHolderOrThrow(DamageTypes.MAGIC), this, getOwner());
                    passenger.hurt(damageSource, (float) (getDamage() * 0.2F));
                }
                hurtTimer = 0;  // 重置计时器
            }

            // 保持静止状态
            if (!this.getPassengers().isEmpty()) {
                this.setDeltaMovement(0, 0, 0);
            }
        }
    }


}
