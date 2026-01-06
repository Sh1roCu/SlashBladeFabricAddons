package com.yakumosakura.yakumoblade.specialattacks.v1;

import com.yakumosakura.yakumoblade.entity.hexgram.b.DragonHexGramStar;
import com.yakumosakura.yakumoblade.entity.hexgram.old.SwordRainEntityFox;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.util.AdvancementHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import static mods.flammpfeil.slashblade.ability.SummonedSwordArts.ADVANCEMENT_HEAVY_RAIN_SWORDS;

public class SwordRain {


    public static void doSlash(LivingEntity playerIn) {
        if (playerIn.level().isClientSide()) {
            return;
        } // 确保只在服务器端执行
        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent((state) -> {
            DragonHexGramStar star = new DragonHexGramStar(YAEntitiesRegistry.starEntityDragon, playerIn.level());

            star.setOwner(playerIn);
            Vec3 spawnPos = playerIn.position().add(0, 10, 0);
            star.setPos(spawnPos);
            star.setColor(state.getColorCode());
            playerIn.level().addFreshEntity(star);
        });
    }


    public static void doSlash(LivingEntity playerIn, boolean critical, double damage, float speed) {
        int colorCode = CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);
        doSlash(playerIn, colorCode, critical, damage, speed);
    }

    public static void doSlash(LivingEntity playerIn, int colorCode, boolean critical, double damage, float speed) {
        if (playerIn.level().isClientSide()) {
            return;
        } // 确保只在服务器端执行
        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem()).ifPresent((state) -> {

            Level worldIn = playerIn.level();
            Entity target = state.getTargetEntity(worldIn);
            if (state.isBroken() || state.isSealed()
                    || !SwordType.from(playerIn.getMainHandItem()).contains(SwordType.BEWITCHED))
                return;

            var holder = worldIn.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.POWER);
            int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(holder, playerIn.getMainHandItem());

            state.setProudSoulCount(
                    state.getProudSoulCount());

            AdvancementHelper.grantCriterion(playerIn, ADVANCEMENT_HEAVY_RAIN_SWORDS);

            int rank = CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn)
                    .map(r -> r.getRank(worldIn.getGameTime()).level).orElse(0);

            Vec3 basePos;

            if (target != null) {
                basePos = target.position();
            } else {
                Vec3 forwardDir = calculateViewVector(0, playerIn.getYRot());
                basePos = playerIn.getPosition(0).add(forwardDir.scale(5));
            }


            {// no random pos
                SwordRainEntityFox ss = new SwordRainEntityFox(
                        YAEntitiesRegistry.swordRainEntity, worldIn);

                worldIn.addFreshEntity(ss);

                ss.setOwner(playerIn);
                ss.setColor(state.getColorCode());
                ss.setRoll(0);
                ss.setDamage(powerLevel);
                // force riding
                ss.startRiding(playerIn, true);

                ss.setDelay(10);

                ss.setPos(basePos);

                ss.setXRot(-90);
            }


            int count = 40;
            int multiplier = 2;
            for (int i = 0; i < count; i++) {

                for (int l = 0; l < multiplier; l++) {
                    SwordRainEntityFox ss = new SwordRainEntityFox(
                            YAEntitiesRegistry.swordRainEntity, worldIn);


                    ss.setOwner(playerIn);
                    ss.setColor(state.getColorCode());
                    ss.setRoll(0);
                    ss.setDamage((5 * (powerLevel + 1) * 2));
                    // force riding
                    ss.startRiding(playerIn, true);

                    ss.setDelay(i);
                    ss.setBoundingBox(new AABB(
                            new Vec3(16, 16, 16),
                            new Vec3(-16, -16, -16)
                    ));
                    ss.setSpread(basePos);

                    ss.setXRot(-90);
                    boolean isRight = ss.getDelay() % 2 == 0;
                    RandomSource random = worldIn.getRandom();

                    double xOffset = random.nextDouble() * 10 * (isRight ? 1 : -1);
                    double yOffsetRandom = random.nextFloat() * i * 0.01 + 20;
                    double zOffset = random.nextDouble() * 10 * (random.nextBoolean() ? 1 : -1);

                    Vec3 spreadPos = basePos.add(xOffset, yOffsetRandom, zOffset);
                    ss.setSpread(spreadPos);  // 或 ss.setPos(spreadPos)
                    worldIn.addFreshEntity(ss);
                }
            }
        });
    }


    static Vec3 calculateViewVector(float x, float y) {
        float f = x * ((float) Math.PI / 180F);
        float f1 = -y * ((float) Math.PI / 180F);
        float f2 = Mth.cos(f1 * 2);
        float f3 = Mth.sin(f1 * 2);
        float f4 = Mth.cos(f * 2);
        float f5 = Mth.sin(f * 2);
        return new Vec3(f3 * f4, -f5, f2 * f4);
    }
}
