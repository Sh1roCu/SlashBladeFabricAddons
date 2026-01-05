package com.yakumosakura.yakumoblade.entity.hexgram.neo;

import com.yakumosakura.yakumoblade.entity.MagicCircleEntity;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LaserCircleEntity extends MagicCircleEntity {
    private final boolean hasLanded = false;
    private int damageTimer = 0;
    private final float targetY = 0;

    // 粒子颜色
    private static final DustParticleOptions PURPLE_PARTICLE = new DustParticleOptions(new Vec3(0.5f, 0.0f, 0.8f).toVector3f(), 1.0f);
    private static final DustParticleOptions GOLD_PARTICLE = new DustParticleOptions(new Vec3(1.0f, 0.8f, 0.0f).toVector3f(), 1.0f);

    public LaserCircleEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setLifeTime(120);
    }

    @Override
    public void tick() {
        super.tick();


        damageTimer++;

        // 每5刻造成一次伤害
        if (damageTimer >= 5) {
            damageTimer = 0;
            applyDamage();
        }

        // 持续产生粒子效果
        spawnParticles();

    }

    @Override
    protected void doEffect() {

    }

    private void applyDamage() {
        if (this.level().isClientSide()) return;
        if (this.getOwner() == null) return;

        var damageSource = new DamageSource(this.level().registryAccess()
                .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.MAGIC), this.getOwner());
        // 计算伤害值
        float damage;
        if (this.getOwner() instanceof LivingEntity) {
            damage = (float) (((LivingEntity) this.getOwner()).getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.5f);
        } else {
            damage = 0;
        }
        this.level().getEntitiesOfClass(
                LivingEntity.class,
                this.getBoundingBox().inflate(15f)).forEach(livingEntity -> {
            if (livingEntity != this.getOwner()) {
                livingEntity.invulnerableTime = 0;
                livingEntity.hurt(damageSource, damage);
            }
        });


    }

    private void spawnParticles() {
        if (this.level().isClientSide()) {
            // 产生紫色和金色粒子
            for (int i = 0; i < 5; i++) {
                double angle = this.level().random.nextDouble() * Math.PI * 2;
                double radius = this.level().random.nextDouble() * 3.0;
                double x = this.getX() + Math.cos(angle) * radius;
                double z = this.getZ() + Math.sin(angle) * radius;
                double y = this.getY() + 0.1;

                // 随机选择紫色或金色粒子
                ParticleOptions particle = this.level().random.nextBoolean() ? PURPLE_PARTICLE : GOLD_PARTICLE;
                this.level().addParticle(particle, x, y, z, 0, 0, 0);
            }

            // 产生气流粒子效果（白色烟雾粒子）
            if (this.tickCount % 3 == 0) {
                for (int i = 0; i < 3; i++) {
                    double angle = this.level().random.nextDouble() * Math.PI * 2;
                    double radius = 2.5 + this.level().random.nextDouble() * 0.5;
                    double x = this.getX() + Math.cos(angle) * radius;
                    double z = this.getZ() + Math.sin(angle) * radius;
                    double y = this.getY() + 0.1;

                    // 向外扩散的气流效果
                    double vx = Math.cos(angle) * 0.1;
                    double vz = Math.sin(angle) * 0.1;

                    this.level().addParticle(ParticleTypes.CLOUD, x, y, z, vx, 0.05, vz);
                }
            }

            // 产生粒子光圈扩散效果
            if (this.tickCount % 10 == 0) {
                for (int i = 0; i < 36; i++) {
                    double angle = i * 10 * Math.PI / 180;
                    double radius = 3.0;
                    double x = this.getX() + Math.cos(angle) * radius;
                    double z = this.getZ() + Math.sin(angle) * radius;
                    double y = this.getY() + 0.1;

                    // 向外扩散的粒子
                    double vx = Math.cos(angle) * 0.2;
                    double vz = Math.sin(angle) * 0.2;

                    this.level().addParticle(PURPLE_PARTICLE, x, y, z, vx, 0, vz);
                }
            }
        }
    }

    private void spawnLandingParticles() {
        if (this.level().isClientSide()) {
            // 落地时产生冲击波效果
            for (int i = 0; i < 50; i++) {
                double angle = this.level().random.nextDouble() * Math.PI * 2;
                double radius = this.level().random.nextDouble() * 5.0;
                double x = this.getX() + Math.cos(angle) * radius;
                double z = this.getZ() + Math.sin(angle) * radius;
                double y = this.getY() + 0.1;

                // 向外扩散的冲击波粒子
                double vx = Math.cos(angle) * 0.5;
                double vz = Math.sin(angle) * 0.5;

                this.level().addParticle(GOLD_PARTICLE, x, y, z, vx, 0.1, vz);
            }
        }
    }

    public boolean hasLanded() {
        return hasLanded;
    }
}