package com.yakumosakura.yakumoblade.registry;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class YakumoBladeItemTier implements Tier {
    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;

    public YakumoBladeItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
    }

    public int getLevel() {
        return this.level;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int level() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return Ingredient.of();
    }

}
