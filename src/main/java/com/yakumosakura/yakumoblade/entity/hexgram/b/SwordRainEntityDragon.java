package com.yakumosakura.yakumoblade.entity.hexgram.b;

import com.yakumosakura.yakumoblade.entity.exer.absNeoSummonSword;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class SwordRainEntityDragon extends absNeoSummonSword {
    public SwordRainEntityDragon(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        this.setYRot(-90);
        this.yRotO = -90;
        super.tick();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockraytraceresult) {
        super.onHitBlock(blockraytraceresult);
        if (this.tickCount > 10) {
            var damageSource = new DamageSource(this.level().registryAccess()
                    .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.MAGIC), this.getOwner());
            this.level().getEntitiesOfClass(
                    LivingEntity.class,
                    this.getBoundingBox().inflate(5f)).forEach(livingEntity -> {
                if (livingEntity != this.getOwner()) {
                    livingEntity.invulnerableTime = 0;
                    livingEntity.hurt(damageSource, (float) (getDamage() * 2f));
                }
            });

            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        this.discard();

    }
}
