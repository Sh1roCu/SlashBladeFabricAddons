package com.yakumosakura.yakumoblade.registry.slashblade.combostate;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.specialattacks.v1.SuperSlash;
import com.yakumosakura.yakumoblade.specialattacks.v2.*;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntitySlashEffect;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.util.AdvancementHelper;
import mods.flammpfeil.slashblade.util.AttackManager;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.TimeValueHelper;
import net.minecraft.core.Registry;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class YAComboRegistry2 {
    public static final ComboState LetSlash = register("let_slash",
            ComboState.Builder.newInstance().startAndEnd(1600, 1620).priority(99)
                    .next(ComboState.TimeoutNext.buildFromFrame(11, entity -> Yakumoblade.prefix("let_slash_end")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("let_slash_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put((int) TimeValueHelper.getTicksFromFrames(7),
                                    (entityIn) -> AttackManager.doSlash(entityIn, -80, Vec3.ZERO, true, true, 1.5,
                                            KnockBacks.toss))
                            .build())
                    .addHitEffect((t, a) -> StunManager.setStun(t, 15)).clickAction(
                            a -> AdvancementHelper.grantCriterion(a, AdvancementHelper.ADVANCEMENT_UPPERSLASH))::build);
    public static final ComboState LetSlashEnd = register("let_slash_end",
            () -> ComboState.Builder.newInstance().startAndEnd(900, 1013).priority(100)
//                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(33, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(12, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 40, true, true, 2.0);
                            })
                            .put(14, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, true, true, 2.1);
                            })
                            .put(15, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 30, true, true, 2.2);
                            })
                            .build())
                    .addHitEffect(StunManager::setStun).build());


    public static final ComboState FoxJustices = register("fox_justice",
            () -> ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .priority(100)
//            .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 60, true, true, 2.0);
                            })
                            .put(3, (entityIn) -> {
                                FoxJustice.doSlash(entityIn, 20);
                            })

                            .build())
                    .addHitEffect(StunManager::setStun).build());
    public static final ComboState DragonJustices = register("dragon_justice",
            () -> ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .priority(100)
//                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 60, true, true, 2.0);
                            })
                            .put(3, DragonSummonSword::doSlash)

                            .build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState FoxJusticesEX = register("fox_justice_ex",
            () -> ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .priority(100)
//                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 60, true, true, 2.0);
                            })
                            .put(3, FoxHexGramExSa::doSlash)

                            .build())
                    .addHitEffect(StunManager::setStun).build());
    public static final ComboState DragonJusticesEX = register("dragon_justice_ex",
            () -> ComboState.Builder.newInstance().startAndEnd(200, 218)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .priority(100)
//                    .speed(1)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                EntitySlashEffect a = AttackManager.doSlash(entityIn, 60, true, true, 2.0);
                            })
                            .put(3, DragonHexGramSa::doSlash)
                            .build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState Sakura_Slash = register
            (
                    "sakura_slash",
                    ComboState.Builder.newInstance().startAndEnd(200, 218).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("sakura_slash_2"))
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -61))
                                    .put(6, (entityIn) -> AttackManager.doSlash(entityIn, 180 - 42)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final ComboState Sakura_Slash_2 = register
            (
                    "sakura_slash_2",
                    ComboState.Builder.newInstance().startAndEnd(720, 743).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder().put(12 - 3,
                                            (entityIn) -> AttackManager.doSlash(entityIn, 0,
                                                    new Vec3(entityIn.getRandom().nextFloat() - 0.5f, 0.8f, 0), false, true, 1.0))
                                    .put(13 - 3, (entityIn) -> AttackManager.doSlash(entityIn, 5,
                                            new Vec3(entityIn.getRandom().nextFloat() - 0.5f, 0.8f, 0), true, false, 1.0))
                                    .build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final ComboState StarRider = register("star_ride",
            ComboState.Builder.newInstance()
                    .startAndEnd(1600, 1659)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> Yakumoblade.prefix("winther_attck_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, StarRide::doSlash)
                            .put(4, SuperSlash::doit)
                            .build())
                    .addHitEffect(StunManager::setStun)
                    ::build
    );

    private static ComboState register(String name, Supplier<ComboState> combo) {
        return Registry.register(ComboStateRegistry.COMBO_STATE, Yakumoblade.prefix(name), combo.get());
    }

    public static void init() {

    }
}
