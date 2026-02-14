package com.yakumosakura.yakumoblade.specialeffects.starSE.re;

import com.yakumosakura.yakumoblade.entity.drive.StarDriveEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class StarDriveEdge extends SpecialEffect {

    public StarDriveEdge() {
        super(30);
    }

    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event, float roll) {
        // 主手检查
        checkAndTrigger(event.getSlashBladeState(), event);

        // 副手检查（新增逻辑）
        if (event.getUser() instanceof Player player) {
            SlashBladeUtil.getState(player.getOffhandItem()).ifPresent(offhandState -> {
                checkAndTrigger2(offhandState, event, roll);
            });
        }
    }

    private static void checkAndTrigger(ISlashBladeState state, SlashBladeEvent.DoSlashEvent event) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.StarDriveEdges))) {
            if (!(event.getUser() instanceof Player player)) return;

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.StarDriveEdges), level)) {
                doSlash(player, event.getRoll(), player.getMainHandItem());
            }
        }
    }

    private static void checkAndTrigger2(ISlashBladeState state, SlashBladeEvent.DoSlashEvent event, float roll) {
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.StarDriveEdges))) {
            if (!(event.getUser() instanceof Player player)) return;

            int level = player.experienceLevel;
            if (SpecialEffect.isEffective(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(YASpecialEffectsRegistry.StarDriveEdges), level)) {
                doSlash(player, roll, player.getOffhandItem());
            }
        }
    }


    public static void doSlash(LivingEntity playerIn, float roll, ItemStack stack) {

        Vec3 centerOffset = Vec3.ZERO;
        int ColorCode =
                CapabilitySlashBlade.BLADESTATE.maybeGet(stack)
                        .map(ISlashBladeState::getColorCode).orElse(-13421569);

        if (playerIn.level().isClientSide()) {
            return;
        }


        int Refine = CapabilitySlashBlade.BLADESTATE.maybeGet(stack)
                .map(ISlashBladeState::getRefine).orElse(0);
        Vec3 lookAngle = playerIn.getLookAngle();
        Vec3 pos = playerIn.position().add(0.0F,
                (double) playerIn.getEyeHeight()
                        * (double) 0.75F, 0.0F).add(lookAngle.scale(0.3F));
        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F,
                        playerIn.getViewYRot(0.0F)).scale(centerOffset.y))
                .add(VectorHelper.getVectorForRotation(0.0F, playerIn.getViewYRot(0.0F) + 90.0F)
                        .scale(centerOffset.z)).add(lookAngle.scale(centerOffset.z));
        StarDriveEntity drive = new StarDriveEntity(YAEntitiesRegistry.StarDrive, playerIn.level());
        drive.setPos(pos.x, pos.y, pos.z);
        drive.setDamage(2.0f + (float) Math.sqrt(Refine) * 0.125f);
        drive.setSpeed(1f);
        drive.shoot(lookAngle.x, lookAngle.y, lookAngle.z, drive.getSpeed(), 0.0F);
        drive.setOwner(playerIn);
        drive.setRotationRoll(roll);
        drive.setColor(ColorCode);
        drive.setIsCritical(false);
        drive.setLifetime((float) 60);
        if (playerIn != null) {
            CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).ifPresent((rank) -> drive.setRank(rank.getRankLevel(playerIn.level().getGameTime())));
        }

        playerIn.level().addFreshEntity(drive);
    }


}
