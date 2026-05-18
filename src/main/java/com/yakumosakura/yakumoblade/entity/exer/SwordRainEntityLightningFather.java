package com.yakumosakura.yakumoblade.entity.exer;

import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class SwordRainEntityLightningFather extends SwordRainEntity {

    private int FireCount = 30;

    public SwordRainEntityLightningFather(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel serverLevel) {


            double baseY = getOwner().position().y + (6.0) + 3.5; // 总高度提升8.5格

// 圆周参数

            int count = 2;
            double angleStep = 360.0 / count; // 每18度一个实体
            if (this.tickCount == 5) {
                for (int i = 0; i < count; i++) {
                    Level worldIn = getOwner().level();
                    SwordRainEntityLightning ss = new SwordRainEntityLightning(YAEntitiesRegistry.swordRainFire, worldIn);
                    double radius = 5.0 + i;

                    ss.setIsCritical(false);
                    ss.setOwner(this.getOwner());
                    ss.setColor(16776960);
                    ss.setRoll(0);
                    ss.setForward(true);
                    ss.doFire();

                    // 计算圆周坐标
                    double angle = Math.toRadians(angleStep * i);
                    double xOffset = radius * Math.cos(angle);
                    double zOffset = radius * Math.sin(angle);

                    // 设置位置
                    ss.setPos(
                            getOwner().position().x + xOffset,
                            baseY, // 统一高度
                            getOwner().position().z + zOffset
                    );

                    worldIn.addFreshEntity(ss);

                    // force riding
                    ss.startRiding(getOwner(), true);

                    getOwner().playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


                }
            }

            serverLevel.sendParticles(ParticleTypes.GLOW,
                    this.getX(), this.getY(), this.getZ(),
                    1,
                    0.3f, 0.1f, 0.3f,
                    0.05f);
        }


    }


    @Override
    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);

        if (this.level() instanceof ServerLevel serverLevel) {


            serverLevel.sendParticles(ParticleTypes.GLOW,
                    this.getX(), this.getY(), this.getZ(),
                    getFireCount(),
                    0.3f, 0.1f, 0.3f,
                    0.05f);


        }
        Entity targetEntity = p_213868_1_.getEntity();
        if (targetEntity instanceof LivingEntity livingEntity) {
            double baseY = livingEntity.position().y + (6.0) + 3.5; // 总高度提升8.5格

// 圆周参数
            double radius = 5.0; // 半径5*i1格
            int count = 2;
            double angleStep = 360.0 / count; // 每18度一个实体

            for (int i = 0; i < count; i++) {
                Level worldIn = livingEntity.level();
                SwordRainEntityLightning ss = new SwordRainEntityLightning(YAEntitiesRegistry.swordRainFire, worldIn);

                worldIn.addFreshEntity(ss);


                ss.setIsCritical(false);
                ss.setOwner(this.getOwner());
                ss.setColor(16776960);
                ss.setRoll(0);
                ss.setForward(true);
                ss.setDamage(2);
                // force riding
                ss.startRiding(livingEntity, true);

                ss.doFire();

                // 计算圆周坐标
                double angle = Math.toRadians(angleStep * i);
                double xOffset = radius * Math.cos(angle);
                double zOffset = radius * Math.sin(angle);

                // 设置位置
                ss.setPos(
                        livingEntity.position().x + xOffset,
                        baseY, // 统一高度
                        livingEntity.position().z + zOffset
                );
                livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);


            }
        }

    }

    public void setFireCount(int fireCount) {
        FireCount = fireCount;
    }

    public int getFireCount() {
        return FireCount;
    }
}
