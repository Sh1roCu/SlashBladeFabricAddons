package com.yakumosakura.yakumoblade.entity.touhou;

import com.yakumosakura.yakumoblade.entity.exer.EntitySpiralSwords;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class YuYuKoSpiralSwordsEntity extends EntitySpiralSwords {
    public YuYuKoSpiralSwordsEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity targetEntity = entityHitResult.getEntity();
        if (targetEntity instanceof LivingEntity lv) {
            lv.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 2));
            var damageSource = new DamageSource(lv.level().registryAccess()
                    .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                    .getHolderOrThrow(DamageTypes.MAGIC), getOwner());
            lv.hurt(damageSource, lv.getMaxHealth() * 0.001f);
        }
    }
}
