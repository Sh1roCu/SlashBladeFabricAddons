package com.yakumosakura.yakumoblade.registry.slashblade.combostate;

import com.exfantasycode.mclib.Utils.Dash.SMoveUtil;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.entity.VoidSlash;
import com.yakumosakura.yakumoblade.specialattacks.v1.*;
import com.yakumosakura.yakumoblade.specialattacks.v2.*;
import com.yakumosakura.yakumoblade.specialeffects.starSE.ex.BlueFoxSoul;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.event.client.UserPoseOverrider;
import mods.flammpfeil.slashblade.event.handler.FallHandler;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.init.SBEntityTypes;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.Drive;
import mods.flammpfeil.slashblade.slasharts.JudgementCut;
import mods.flammpfeil.slashblade.slasharts.SakuraEnd;
import mods.flammpfeil.slashblade.util.*;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class YAComboRegistry {
    public static final ComboState WITHER_ATTACK = register
            (
                    "winther_attck",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, (entityIn) -> WitherAttack.doSlash(entityIn, false, 7, 2f)).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );


    public static final ComboState CrisonSwords = register
            (
                    "crison_swords",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, CrisonSword::createSlashPattern).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );


    public static final ComboState Reincarnationheaven = register
            (
                    "rh",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, ReincarnationHeaven::doslash).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );


    public static final ComboState HexGramSummonSwordyellow = register
            (
                    "hex_gram_summon_sword_yellow",
                    ComboState.Builder.newInstance()
                            .startAndEnd(400, 459)
                            .priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, HexGramSumonSwordYellow::doSlash).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final ComboState HexGramSummonSwordred = register
            (
                    "hex_gram_summon_sword_red",
                    ComboState.Builder.newInstance()
                            .startAndEnd(400, 459)
                            .priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, HexGramSumonSwordRed::doSlash).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final ComboState HexGramSummonSwordBlue = register
            (
                    "hex_gram_summon_sword_blue",
                    ComboState.Builder.newInstance()
                            .startAndEnd(400, 459)
                            .priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, StormSwordsHexGram::doSlash).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final ComboState SpiralSwordex = register
            (
                    "spiral_sword_ex",
                    ComboState.Builder.newInstance()
                            .startAndEnd(400, 459)
                            .priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, SpiralSwordEx::doslash).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );

    public static final ComboState WITHER_ATTACK_END = register
            (
                    "winther_attck_end",
                    ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                            .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder().put(0, AttackManager::playQuickSheathSoundAction).build())
                            .releaseAction(ComboState::releaseActionQuickCharge)::build
            );

    public static final ComboState SOUL_EDGE = register
            (
                    "soul_edge_o",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, (entityIn) -> SoulEdge.doSlash(entityIn, false, 7, 2f)).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );

    public static final ComboState WITHERSUMOONSWORD = register
            (
                    "wither_summond_sword",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, (entityIn) -> WitherSummonSword.doSlash(entityIn, false, 7, 2f)).build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final ComboState TEN_DRIVE = register("ten_drive",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
//                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> TenDrive.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 5, 2f, 1f, 2)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final ComboState TEN_DRIVEEX = register("ten_driveex",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
//                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> TenDrive.doSlashJust(entityIn, 90F, 20, Vec3.ZERO, false, 10, 2f, 1f, 2))
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final ComboState HIGHSLASH = register("high_slash",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, SuperSlash::doit)
                            .put(4, SuperSlash::doit)
                            .put(6, SuperSlash::doit)
                            .put(8, SuperSlash::doit)
                            .put(10, SuperSlash::doit)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final ComboState CSOUL_EDGE = register
            (
                    "csoul_edge",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, (entityIn) -> YuYuKo.doSlash(entityIn, false, 7, 1f, 25343, 65313))
                                                    .put(4, (entityIn) -> YuYuKo.doSlash(entityIn, false, 7, 1f, 13504014, 16722600))
                                                    .build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );


    public static final ComboState DeadOrLifes = register
            (
                    "dead_or_life",
                    ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction
                                    (
                                            ComboState.TimeLineTickAction.getBuilder()
                                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 2F))
                                                    .put(3, DeadOrLife::doSlash)
                                                    .build()
                                    )
                            .addHitEffect(StunManager::setStun)
                            ::build
            );


    public static final ComboState SOULGALESWORDS = register("soul_galegwords",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> SoulGaleSwords.doSlash(entityIn, false, 7, 1f)).build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );


    public static final ComboState SwordRainRideOn = register("sword_rain_ride_on",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))

                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );


    public static final ComboState THO = register("tho",
            ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .priority(100)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -61))
                            .put(6, (entityIn) -> SakuraEnd.doSlash(entityIn, 180 - 42, Vec3.ZERO, false, false, 10))
                            .put(7, (entityIn) -> Drive.doSlash(entityIn, -10F, 10, Vec3.ZERO, false, 3F, 2f))
                            .put(8, (entityIn) -> Drive.doSlash(entityIn, -10F, 10, Vec3.ZERO, false, 3F, 2f))
                            .build())
                    .addHitEffect(StunManager::setStun)::build);
    public static final ComboState GigantjudgementCut = register("gigantjudgementcut",
            ComboState.Builder.newInstance().startAndEnd(1923, 1928).speed(0.4F).priority(50)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7, GigantJudycutSum::doJudgementCutJust)
                            .build())
                    .addTickAction(FallHandler::fallDecrease).addHitEffect(StunManager::setStun)::build);
    public static final ComboState randomjudgementCut = register("randomjudgementcut",
            ComboState.Builder.newInstance().startAndEnd(1923, 1928).speed(0.4F).priority(50)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7, RandomjudgementCut::doSlash)
                            .build())
                    .addTickAction(FallHandler::fallDecrease).addHitEffect(StunManager::setStun)::build);
    public static final ComboState judgementCut = register("judgementcut",
            ComboState.Builder.newInstance().startAndEnd(1923, 1928).speed(0.4F).priority(50)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7, JudgementCut::doJudgementCutJust)
                            .build())
                    .addTickAction(FallHandler::fallDecrease).addHitEffect(StunManager::setStun)::build);
    public static final ComboState judgementCutLow = register("judgementcutlow",
            ComboState.Builder.newInstance().startAndEnd(1923, 1928).speed(0.4F).priority(50)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(7, JudgementCut::doJudgementCut)
                            .build())
                    .addTickAction(FallHandler::fallDecrease).addHitEffect(StunManager::setStun)::build);

    public static final ComboState THO2 = register("tho2",
            ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .priority(100)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("combo_b1_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -61))
                            .put(6, (entityIn) -> SakuraEnd.doSlash(entityIn, 180 - 42, Vec3.ZERO, false, false, 1))
                            .put(7, (entityIn) -> Drive.doSlash(entityIn, 90F, 10, Vec3.ZERO, false, 3F, 2f))
                            .put(8, (entityIn) -> Drive.doSlash(entityIn, 90F, 10, Vec3.ZERO, false, 3F, 2f))
                            .build())
                    .addHitEffect(StunManager::setStun)::build);


    public static final ComboState COMBO_B1_END = register("combo_b1_end",
            ComboState.Builder.newInstance().startAndEnd(720, 743).priority(100)
                    .next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(12 - 3, (entityIn) -> AttackManager.doSlash(entityIn, 0,
                                    new Vec3(entityIn.getRandom().nextFloat() - 0.5f, 0.8f, 0), false, true, 1))
                            .put(13 - 3, (entityIn) ->
                                    AttackManager.doSlash(entityIn, 0,
                                            new Vec3(entityIn.getRandom().nextFloat() - 0.5f, 0.8f, 0), false, true, 1))
                            .build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> BigDriveSummon.doSlash(entityIn, 180F, 1000, Vec3.ZERO, false, 2, 0.5F))
                            .build())
                    .addHitEffect(StunManager::setStun)::build);


    public static final ComboState COMBO_A5 = register("combo_a5ex",
            () -> ComboState.Builder.newInstance().startAndEnd(900, 1013).priority(100)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(33, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(10, (entityIn) -> {
                                Vec3 pos = entityIn.position().add(0.0, (double) entityIn.getEyeHeight() * 0.75, 0.0).add(entityIn.getLookAngle().scale(0.30000001192092896));
                                pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, entityIn.getViewYRot(0.0F))
                                                .scale(Vec3.ZERO.y))
                                        .add(VectorHelper.getVectorForRotation(0.0F, entityIn.getViewYRot(0.0F) + 90.0F)
                                                .scale(Vec3.ZERO.z))
                                        .add(entityIn.getLookAngle().scale(Vec3.ZERO.z));

                                Level level = entityIn.level();
                                EntitySlashEffect a = new VoidSlash(SBEntityTypes.SLASH_EFFECT, level);
                                a.setDamage(2F);
                                a.setColor(16711903);
                                a.setCycleHit(true);
                                a.setRotationRoll(35);
                                a.setPos(pos.x, pos.y, pos.z);
                                a.setOwner(entityIn);
                                a.setLifetime(36);
                                entityIn.level().addFreshEntity(a);
                            })
                            .put(12, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(14, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, false, true);
                            }).build())
                    .addHitEffect(StunManager::setStun).build());


    public static final ComboState FIRE_BOOST = register("fire_boost",
            () -> ComboState.Builder.newInstance().startAndEnd(900, 1013).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(33, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()


                            .put(12, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(14, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, false, true);
                            })
                            .put(16, SwordRainFire::dois)

                            .build())
                    .addHitEffect(StunManager::setStun).build());


    public static final ComboState FIRE_BOOST2 = register("fire_boost2",
            () -> ComboState.Builder.newInstance().startAndEnd(900, 1013).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(33, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()


                            .put(12, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(14, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, false, true);
                            })
                            .put(16, SwordRainFire::dois2)

                            .build())
                    .addHitEffect(StunManager::setStun).build());


    public static final ComboState AERIAL_RAVE_B4 = register("aerial_rave_b4",
            ComboState.Builder.newInstance().startAndEnd(1500, 1537).priority(80).aerial()
                    .next(ComboState.TimeoutNext.buildFromFrame(9, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("aerial_rave_a3"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put((int) TimeValueHelper.getTicksFromFrames(10),
                                    (entityIn) -> AttackManager.doSlash(entityIn, 45, Vec3.ZERO, false, false, 1.0,
                                            KnockBacks.meteor))
                            .put((int) TimeValueHelper.getTicksFromFrames(10) + 1,
                                    (entityIn) -> AttackManager.doSlash(entityIn, 50, Vec3.ZERO, true, true, 1.0,
                                            KnockBacks.meteor))
                            .build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(5, (entityIn) -> UserPoseOverrider.setRot(entityIn, 90, true))
                            .put(5 + 1, (entityIn) -> UserPoseOverrider.setRot(entityIn, 90, true))
                            .put(5 + 2, (entityIn) -> UserPoseOverrider.setRot(entityIn, 90, true))
                            .put(5 + 3, (entityIn) -> UserPoseOverrider.setRot(entityIn, 90, true))
                            .put(5 + 4, (entityIn) -> UserPoseOverrider.resetRot(entityIn)).build())
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                SMoveUtil.sendDashMessage((Player) entityIn, 2, 0);
                            })
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, RetreatFlyingKnife::doSlash)

                            .build())
                    .addTickAction(FallHandler::fallDecrease).addHitEffect(StunManager::setStun).clickAction(
                            a -> AdvancementHelper.grantCriterion(a, AdvancementHelper.ADVANCEMENT_AERIAL_B))::build);

    public static final ComboState RetreatFlyingknife = AERIAL_RAVE_B4;


    public static final ComboState AERIAL_RAVE_A3 = register("aerial_rave_a3",
            ComboState.Builder.newInstance().startAndEnd(1300, 1328).priority(80).aerial()
                    .next(ComboState.TimeoutNext.buildFromFrame(9, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1,
                                    (entityIn) -> Drive.doSlash(entityIn, 180F, 10, Vec3.ZERO, false, 3F, 2f))
                            .put((int) TimeValueHelper.getTicksFromFrames(4),
                                    (entityIn) -> AttackManager.doSlash(entityIn, 0, Vec3.ZERO, false, false, 1.0,
                                            KnockBacks.smash))
                            .put((int) TimeValueHelper.getTicksFromFrames(4) + 1,
                                    (entityIn) -> AttackManager.doSlash(entityIn, -3, Vec3.ZERO, true, true, 1.0,
                                            KnockBacks.smash))
                            .build())
                    .addTickAction(FallHandler::fallDecrease).addHitEffect(StunManager::setStun)
                    .addTickAction((entityIn) -> UserPoseOverrider.resetRot(entityIn)).clickAction(
                            a -> AdvancementHelper.grantCriterion(a, AdvancementHelper.ADVANCEMENT_AERIAL_A))::build);


    public static final ComboState Thrustswords = register("thrust_swords",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()

                            .put(1, (entityIn) -> {
                                SMoveUtil.sendDashMessage((Player) entityIn, 2, 0);
                            })
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -80F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> ThrustSwords.doSlashFantasy(entityIn, 7, 5f))
                            .put(5, (entityIn) -> {
                                SMoveUtil.sendDashMessage((Player) entityIn, 3, -20F);

                            })
                            .put(10, (entityIn) -> {
                                SMoveUtil.sendDashMessage((Player) entityIn, 3, 20F);

                            })
                            .put(15, (entityIn) -> {
                                SMoveUtil.sendDashMessage((Player) entityIn, 0, 20F);

                            })
                            .put(20, (entityIn) -> {
                                SMoveUtil.sendDashMessage((Player) entityIn, 3, -20F);

                            })


                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    public static final ComboState SwordRainlLightning = register("sword_rainllightning",
            () -> ComboState.Builder.newInstance().startAndEnd(900, 1013).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(33, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()


                            .put(12, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(14, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, false, true);
                            })
                            .put(16, SwordRainLightning::dois)

                            .build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState SwordRainlLightning_ex = register("sword_rainl_lightning_ex",
            () -> ComboState.Builder.newInstance().startAndEnd(900, 1013).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(33, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()


                            .put(12, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(14, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, false, true);
                            })
                            .put(16, SwordRainLightning::dois2)

                            .build())
                    .addHitEffect(StunManager::setStun).build());


    public static final ComboState HotDrives = register("hot_drive",
            () -> ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(2, HotDrive::onDoSlash)
                            .build())
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addHitEffect(StunManager::setStun)
                    .build());


    public static final ComboState SelfNoall = register("self_no_all",
            () -> ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(2, SelfNoAll::doslash)
                            .build())
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addHitEffect(StunManager::setStun)
                    .build());

    public static final ComboState WaveAndTinys = register("wave_and_tiny",
            () -> ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(2, WaveAndTiny::doSlash)
                            .build())
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addHitEffect(StunManager::setStun)
                    .build());


    public static final ComboState SelfNoall_FIRE_BOOST2 = register("self_no_all_fire_boost",
            () -> ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(100)
                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(2, (entityIn) -> BlueFoxSoul.doslash(entityIn, 20))
                            .put(4, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, false, true);
                            })
                            .put(5, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, false, true);
                            })
                            .put(9, SwordRainFire::dois2)
                            .put(10, (entityIn) -> BlueFoxSoul.doslash(entityIn, 20))
                            .build())
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addHitEffect(StunManager::setStun)
                    .build());

    private static ComboState register(String name, Supplier<ComboState> combo) {
        return Registry.register(ComboStateRegistry.COMBO_STATE, Yakumoblade.prefix(name), combo.get());
    }

    public static void init() {

    }
}
