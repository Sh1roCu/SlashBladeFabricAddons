package com.yakumosakura.yakumoblade.specialattacks.v2;

import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtils;
import com.yakumosakura.yakumoblade.utils.WaitingTick;
import mods.flammpfeil.slashblade.capability.concentrationrank.CapabilityConcentrationRank;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class CrisonSword {

    private static float X = 0;
    private static float Z = 0;

    public static void createSlashPattern(LivingEntity playerIn) {
        float yaw = playerIn.getViewYRot(0.0F);

        // 创建六个slash实体，每个都会基于玩家当前朝向计算位置
        for (int i = 0; i < 9; i++) {
            int finalI = i;
            WaitingTick.schedule(i * 2, () -> {
                // 计算当前tick的旋转角度偏移
                float angleOffset = finalI * 5.0F; // 每次迭代增加旋转角度

                // 重新计算所有偏移向量，使其基于玩家当前朝向
                Vec3 offsetA1 = calculateOffset(yaw + angleOffset, 2.0D); // 前方
                Vec3 offsetA2 = calculateOffset(yaw + 180.0F + angleOffset, 2.0D); // 后方
                Vec3 offsetB1 = calculateOffset(yaw - 45.0F + angleOffset, 1.5D); // 左前方
                Vec3 offsetB2 = calculateOffset(yaw + 45.0F + angleOffset, 1.5D); // 右前方
                Vec3 offsetC = calculateOffset(yaw + angleOffset, 1.0D); // 正前方

                // 创建朝向玩家前方的slash效果
                doSlash(playerIn, 0F + finalI, SlashBladeUtils.getColorCode(playerIn.getMainHandItem()), offsetA1, false, true, 0.0D, KnockBacks.cancel);
                doSlash(playerIn, 45F + finalI, SlashBladeUtils.getColorCode(playerIn.getMainHandItem()), offsetB1, false, true, 0.0D, KnockBacks.cancel);
                doSlash(playerIn, 90F + finalI, SlashBladeUtils.getColorCode(playerIn.getMainHandItem()), offsetC, false, true, 0.0D, KnockBacks.cancel);
                doSlash(playerIn, 135F + finalI, SlashBladeUtils.getColorCode(playerIn.getMainHandItem()), offsetB2, false, true, 0.0D, KnockBacks.cancel);
                doSlash(playerIn, 180F + finalI, SlashBladeUtils.getColorCode(playerIn.getMainHandItem()), offsetA2, false, true, 0.0D, KnockBacks.cancel);
            });
        }

        // 中心位置的slash效果
        Vec3 zeroOffset = new Vec3(0, 0, 0);
        doSlash(playerIn, 225F, SlashBladeUtils.getColorCode(playerIn.getMainHandItem()), zeroOffset, false, true, 0.0D, KnockBacks.cancel);

        WaitingTick.schedule(20, () -> {
            X = 0;
            Z = 0;
        });
    }

    // 辅助方法：基于玩家朝向和距离计算偏移向量
    private static Vec3 calculateOffset(float yaw, double distance) {
        return VectorHelper.getVectorForRotation(0.0F, yaw).scale(distance);
    }

    public static EntitySlashEffect doSlash(LivingEntity playerIn, float roll, int colorCode, Vec3 centerOffset, boolean mute, boolean critical, double comboRatio, KnockBacks knockback) {
        if (playerIn.level().isClientSide()) {
            return null;
        } else {
            ItemStack blade = playerIn.getMainHandItem();
            if (CapabilitySlashBlade.getBladeState(blade).isEmpty()) {
                return null;
            } else {
                var event = new SlashBladeEvent.DoSlashEvent(blade, CapabilitySlashBlade.getBladeState(blade).orElseThrow(NullPointerException::new), playerIn, roll, critical, comboRatio, knockback);
                SlashBladeEvent.DO_SLASH.invoker().onDoSlash(event);
                if (event.isCanceled()) {
                    return null;
                }

                float a = (float) -playerIn.getLookAngle().x;
                float b = (float) -playerIn.getLookAngle().z;


                // 获取玩家的旋转矩阵
                Vec3 playerForward = playerIn.getLookAngle();
                Vec3 playerRight = playerForward.cross(new Vec3(0, 1, 0));
                Vec3 playerUp = playerRight.cross(playerForward);
                // 将 centerOffset 从玩家本地坐标系转换到世界坐标系
                Vec3 worldOffset = playerRight.scale(centerOffset.x)
                        .add(playerUp.scale(centerOffset.y))
                        .add(playerForward.scale(centerOffset.z));

                // 基础位置
                Vec3 pos = playerIn.position().add(0, playerIn.getEyeHeight(), 0).add(worldOffset);

                EntitySlashEffect jc = new EntitySlashEffect(SBEntityTypes.SLASH_EFFECT, playerIn.level());
                jc.setPos(pos.x + X * a, pos.y, pos.z + Z * b);
                jc.setOwner(playerIn);
                jc.setRotationRoll(roll);
                jc.setYRot(playerIn.getYRot());
                jc.setXRot(0.0F);
                jc.setColor(colorCode);
                jc.setMute(mute);
                jc.setIsCritical(critical);
                jc.setDamage(comboRatio);
                jc.setKnockBack(knockback);

                jc.level().getEntitiesOfClass(
                        LivingEntity.class,
                        jc.getBoundingBox().inflate(10)).forEach(livingEntity -> {
                    if (livingEntity != jc.getOwner()) {
                        var damageSource = new DamageSource(jc.level().registryAccess()
                                .registryOrThrow(net.minecraft.core.registries.Registries.DAMAGE_TYPE)
                                .getHolderOrThrow(DamageTypes.PLAYER_ATTACK), jc.getOwner());
                        if (playerIn instanceof Player player) {
                            livingEntity.invulnerableTime = 0;
                            livingEntity.hurt(damageSource, (float) playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE) * (3 + (float) SlashBladeUtil.getRefine(player) / 100));
                        }
                    }
                });


                if (playerIn != null) {
                    CapabilityConcentrationRank.RANK_POINT.maybeGet(playerIn).ifPresent((rank) -> jc.setRank(rank.getRankLevel(playerIn.level().getGameTime())));
                }

                playerIn.level().addFreshEntity(jc);
                X--;
                Z--;
                return jc;
            }
        }
    }
}