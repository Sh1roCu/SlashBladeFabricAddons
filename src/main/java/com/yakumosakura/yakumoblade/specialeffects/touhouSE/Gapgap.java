package com.yakumosakura.yakumoblade.specialeffects.touhouSE;

import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtils;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class Gapgap extends SpecialEffect {
    public Gapgap() {
        super(30, false, false);
    }
    // private static final Random rand = new Random();

    public static void atDeath(LivingHurtEvent event) {
        LivingEntity e = event.getEntity();
        if (!SlashBladeUtils.hasSpecialEffect(e.getMainHandItem(), YASpecialEffectsRegistry.gapgap))
            return;
        if (event.getAmount() > 5 && Math.random() < 0.5) {

            ItemStack stack = new ItemStack(Items.CHORUS_FRUIT);
            stack.getItem().finishUsingItem(stack, e.level(), e);
            Level level = e.level();
            event.setAmount(0);
            if (!level.isClientSide) {
                // ((ServerPlayer) e).awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                level.playSound(null, e.getX(), e.getY(), e.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 2, 2.0F);
                ((ServerLevel) level).sendParticles(ParticleTypes.TOTEM_OF_UNDYING, e.getX(), e.getY(), e.getZ(), 20, 0.50, 0.5, 0.5, 0.1);

            }
            event.setCanceled(true);
        }
    }

    public static void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.gapgap))) {
            if (!(event.getEntity() instanceof Player player)) {
                return;
            }

            if (!event.isSelected())
                return;

            int level = player.experienceLevel;

            if (SpecialEffect.isEffective(YASpecialEffectsRegistry.gapgap, level)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                if (!player.hasEffect(MobEffects.REGENERATION)) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
                }
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));

            }
        }

    }


}
