package com.yakumosakura.yakumoblade.specialeffects.touhouSE;

import com.yakumosakura.yakumoblade.network.MakeGapMessage;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.client.SlashBladeKeyMappings;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

public class MakeGap extends SpecialEffect {
    public MakeGap() {
        super(50);
    }


    public static void onLivingDamage(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (HasMakeGap(player)) {
                LivingEntity target = event.getEntity();


                if (player.level() instanceof ServerLevel serverLevel) {
                    for (int i = 0; i < 20; i++) {
                        double x = target.getX() + (target.getRandom().nextDouble() - 0.5) * 2.0;
                        double y = target.getY() + target.getRandom().nextDouble() * 2.0;
                        double z = target.getZ() + (target.getRandom().nextDouble() - 0.5) * 2.0;

                        if (target.getRandom().nextBoolean()) {
                            serverLevel.sendParticles(ParticleTypes.PORTAL, x, y, z, 1, 0, 0, 0, 0.1);
                        } else {
                            serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 1, 0, 0, 0, 0.1);
                        }
                    }
                }


                if (target.getRandom().nextFloat() < 0.46f) {

                    float originalDamage = event.getAmount();
                    float armorReduction = target.getArmorValue() * 0.04f; // 估算护甲减免
                    event.setAmount(originalDamage + armorReduction * originalDamage);
                }


                if (target.getRandom().nextFloat() < 0.32f) {
                    target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 600, 0)); // 30秒黑暗I
                    target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 2)); // 30秒虚弱III
                    target.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 240, 0)); // 12秒反胃I
                    target.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 4800, 2)); // 120秒霉运III

                    // 5秒内丢失仇恨目标
                    target.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
                }
            }
        }
    }


    public static void handleSpecialMove(Player player) {
        if (HasMakeGap(player) && !player.isShiftKeyDown()) {
            // 向前瞬移10格，但检测路径上的方块
            Vec3 look = player.getLookAngle();
            Vec3 startPos = player.position();
            Vec3 endPos = startPos.add(look.scale(10));

            // 使用射线检测来找到路径上的第一个方块
            BlockHitResult hitResult = player.level().clip(new ClipContext(
                    startPos,
                    endPos,
                    ClipContext.Block.COLLIDER,  // 只检测有碰撞箱的方块
                    ClipContext.Fluid.NONE,      // 不检测流体
                    player
            ));

            Vec3 teleportPos;
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                // 如果击中方块，则停在方块前
                teleportPos = hitResult.getLocation().subtract(look.scale(0.5)); // 后退一点避免卡在方块里
            } else {
                // 如果没有击中方块，则传送到最大距离
                teleportPos = endPos;
            }

            // 确保传送位置安全（不在方块内）
            BlockPos blockPos = new BlockPos((int) teleportPos.x, (int) teleportPos.y, (int) teleportPos.z);
            if (!player.level().isEmptyBlock(blockPos)) {
                // 如果目标位置有方块，向上寻找安全位置
                while (!player.level().isEmptyBlock(blockPos.above()) && blockPos.getY() < player.level().getMaxBuildHeight()) {
                    blockPos = blockPos.above();
                }
                teleportPos = new Vec3(teleportPos.x, blockPos.getY() + 1, teleportPos.z);
            }

            player.teleportTo(teleportPos.x, player.getY(), teleportPos.z);

            // 添加瞬移粒子效果
            if (player.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.PORTAL,
                        player.getX(), player.getY() + 1, player.getZ(),
                        50, 0.5, 0.5, 0.5, 0.1);
            }
        }
    }


    @Environment(EnvType.CLIENT)
    public static void onUpdate(SlashBladeEvent.UpdateEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (IsSpecialMove()) {
                ClientPlayNetworking.send(MakeGapMessage.ID, MakeGapMessage.DUMMY);
            }
        }
    }


    @Environment(EnvType.CLIENT)
    public static Boolean IsSpecialMove() {
        return SlashBladeKeyMappings.KEY_SPECIAL_MOVE.isDown();
    }


    public static Boolean HasMakeGap(LivingEntity entity) {
        if (CapabilitySlashBlade.BLADESTATE.maybeGet(entity.getMainHandItem()).isEmpty()) return false;
        ISlashBladeState state = SlashBladeUtil.getState(entity.getMainHandItem()).get();
        Collection<ResourceLocation> effects = state.getSpecialEffects();
        for (ResourceLocation effectId : effects) {
            SpecialEffect effect = SpecialEffectsRegistry.SPECIAL_EFFECT.get(effectId);
            if (effect instanceof MakeGap Effect && entity instanceof Player player) {
                if (state.hasSpecialEffect(effectId)) {
                    return SpecialEffect.isEffective(effect, player.experienceLevel);
                }

            }
        }
        return false;
    }
}
