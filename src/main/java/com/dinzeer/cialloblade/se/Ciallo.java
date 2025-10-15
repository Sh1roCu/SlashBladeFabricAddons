package com.dinzeer.cialloblade.se;

import com.dinzeer.cialloblade.sound.SoundRegsitry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Ciallo extends SpecialEffect {
    public Ciallo() {
        super(30);
    }

    public static void onHitEntity(SlashBladeEvent.HitEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(SERegsitry.CIALLO))) {
            if (!(event.getUser() instanceof Player player)) return;
            if (SpecialEffect.isEffective(SERegsitry.CIALLO, player.experienceLevel)) {
                Level world = player.level();

                if (player.getRandom().nextInt(2) == 1) {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegsitry.DOCIALLO, SoundSource.PLAYERS,
                            player.getRandom().nextFloat(), 1.0F);
                } else {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegsitry.DOCIALLO2, SoundSource.PLAYERS,
                            player.getRandom().nextFloat(), 1.0F);
                }

            }
        }
    }

    public static void onUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(SERegsitry.CIALLO))) {
            if (!(event.getEntity() instanceof Player player)) return;
            if (SpecialEffect.isEffective(SERegsitry.CIALLO, player.experienceLevel)) {
                if (state.getProudSoulCount() < 1000) {
                    Level world = player.level();
                    state.setProudSoulCount(1000);

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegsitry.DOCIALLO, SoundSource.PLAYERS,
                            player.getRandom().nextFloat(), 1.0F);

                }
            }
        }
    }

    public static void doSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(SERegsitry.CIALLO))) {
            if (!(event.getUser() instanceof Player player)) return;
            if (SpecialEffect.isEffective(SERegsitry.CIALLO, player.experienceLevel)) {
                Level world = player.level();

                if (state.getComboSeq() == (ComboStateRegistry.getId(ComboStateRegistry.WAVE_EDGE_VERTICAL))) {

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegsitry.DOSLASH, SoundSource.PLAYERS,
                            player.getRandom().nextFloat(), 1.0F);

                }
                if (state.getComboSeq() == (ComboStateRegistry.getId(ComboStateRegistry.AERIAL_RAVE_A1))) {

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegsitry.SENPAI, SoundSource.PLAYERS,
                            player.getRandom().nextFloat(), 1.0F);

                }

                if (player.isShiftKeyDown()) {

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegsitry.DOCIALLO2, SoundSource.PLAYERS,
                            player.getRandom().nextFloat(), 1.0F);

                }

            }
        }
    }
}