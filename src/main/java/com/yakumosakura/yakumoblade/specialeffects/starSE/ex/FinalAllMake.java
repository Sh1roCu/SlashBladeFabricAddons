package com.yakumosakura.yakumoblade.specialeffects.starSE.ex;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.combostate.YAComboRegistry;
import com.yakumosakura.yakumoblade.specialattacks.v1.BlackSlash;
import com.yakumosakura.yakumoblade.specialattacks.v1.StormSwordsHexGram;
import com.yakumosakura.yakumoblade.specialeffects.starSE.re.StarDriveEdge;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class FinalAllMake extends SpecialEffect {
    public FinalAllMake() {
        super(120);
    }


    public static void Update(SlashBladeEvent.UpdateEvent event) {
        if (!event.isSelected())
            return;
        if (!(event.getEntity() instanceof Player player)) return;
        ISlashBladeState state = event.getSlashBladeState();
        if (!state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FinalAllMake)))
            return;
        if (player.level().isNight()) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 3));
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0));
            if (player.hasEffect(MobEffects.ABSORPTION)) {
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 8));
            }
        }
    }

    public static void DoSlashRvent(SlashBladeEvent.DoSlashEvent event) {

        if (!(event.getUser() instanceof Player player)) return;
        ISlashBladeState state = event.getSlashBladeState();


        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FinalAllMake))) {


            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.FinalAllMake, player.experienceLevel)) {
                if (state.getComboSeq() == ComboStateRegistry.getId(ComboStateRegistry.COMBO_A1)) {
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 1));
                }
                if (state.getComboSeq().equals(SlashBlade.prefix("combo_a2"))) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 1));
                    if (player.isShiftKeyDown()) {
                        state.setComboSeq(ComboStateRegistry.getId(YAComboRegistry.THO));
                    }
                }
                if (state.getComboSeq().equals(SlashBlade.prefix("combo_a3"))) {
                    BlackSlash.doSlash(player);
                    BlackSlash.doSlash(player);
                }
                if (state.getComboSeq().equals(SlashBlade.prefix("combo_a4_ex"))) {
                    StarDriveEdge.doSlash(player, event.getRoll(), player.getMainHandItem());
                }
                if (state.getComboSeq().equals(SlashBlade.prefix("combo_a4_ex"))) {
                    state.setComboSeq(SlashBlade.prefix("wave_edge_vertical"));
                }

                if (state.getComboSeq().equals(SlashBlade.prefix("combo_c"))) {
                    state.setComboSeq(SlashBlade.prefix("combo_b1"));
                }
                if (state.getComboSeq().equals(SlashBlade.prefix("combo_b3"))) {

                    state.setComboSeq(SlashBlade.prefix("circle_slash"));


                }
                if (state.getComboSeq().equals(SlashBlade.prefix("upperslash"))) {

                    state.setComboSeq(SlashBlade.prefix("drive_vertical"));
                }
            }


        }


    }


    public static void DoHitEvent(SlashBladeEvent.HitEvent event) {
        if (!(event.getUser() instanceof Player player)) return;

        ISlashBladeState state = event.getSlashBladeState();


        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.FinalAllMake))) {
            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.FinalAllMake, player.experienceLevel)) {
                if (player.level().isNight()) {
                    List<LivingEntity> targets = new ArrayList<>();
                    Deque<LivingEntity> queue = new LinkedList<>();
                    LivingEntity owner = player;
                    float radius = 10F;

                    owner.level().getEntitiesOfClass(
                            LivingEntity.class,
                            owner.getBoundingBox().inflate(radius)).forEach(livingEntity -> {
                        if (livingEntity != owner) {
                            targets.add(livingEntity);
                        }
                    });

                    // Start the chain from owner
                    queue.add(owner);
                    while (!queue.isEmpty()) {
                        LivingEntity current = queue.poll();
                        LivingEntity next = null;
                        double minDistance = Double.MAX_VALUE;

                        // Find the nearest target to current entity
                        for (LivingEntity target : targets) {
                            double distance = current.distanceToSqr(target);
                            if (distance < minDistance) {
                                minDistance = distance;
                                next = target;
                            }
                        }

                        if (next != null) {
                            // Apply hit effects
                            next.knockback(1, 1, 1);
                            StunManager.setStun(next, 20);
                            var damageSource = new DamageSource(current.level().registryAccess()
                                    .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                                    .getHolderOrThrow(DamageTypes.MAGIC), owner);

                            next.hurt(damageSource, ((float) (owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue()) * 0.3F));

                            if (next.level() instanceof ServerLevel serverLevel) {
                                serverLevel.sendParticles(ParticleTypes.END_ROD,
                                        next.getX(), next.getY(), next.getZ(), 5, 0.2, 0.2, 0.2, 0.2);
                            }

                            // Draw particle chain between current and next
                            double step = 0.1;
                            for (double t = 0; t <= 1.0; t += step) {
                                double x = net.minecraft.util.Mth.lerp(t, current.getX(), next.getX());
                                double y = net.minecraft.util.Mth.lerp(t, current.getY(), next.getY());
                                double z = net.minecraft.util.Mth.lerp(t, current.getZ(), next.getZ());
                                if (current.level() instanceof ServerLevel serverLevel) {
                                    serverLevel.sendParticles(ParticleTypes.END_ROD,
                                            x, y + 1, z, 30, 0.0, 0.0, 0.0, 0.0);
                                }
                            }

                            // Remove next from available targets and enqueue it
                            targets.remove(next);
                            queue.add(next);
                        }
                    }
                } else {
                    StormSwordsHexGram.doSlash(player, 4);
                }


            }
        }
    }
}