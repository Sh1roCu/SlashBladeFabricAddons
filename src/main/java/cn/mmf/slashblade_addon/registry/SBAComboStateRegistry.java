package cn.mmf.slashblade_addon.registry;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import cn.mmf.slashblade_addon.specialattacks.*;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.core.Registry;
import net.minecraft.world.phys.Vec3;

public class SBAComboStateRegistry {
    public static void init(){

    }

    public static final ComboState RAPID_BLISTERING_SWORDS = register(
            "rapid_blistering_swords",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> SlashBladeAddon.prefix("rapid_blistering_swords_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> RapidBlisteringSwords.doSlash(entityIn, false, 7, 2f)).build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState RAPID_BLISTERING_SWORDS_END = register(
            "rapid_blistering_swords_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge).build());

    public static final ComboState SPIRAL_EDGE = register("spiral_edge",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> SlashBladeAddon.prefix("spiral_edge_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> SpiralEdge.doCircleSlash(entityIn, -30, 180))
                            .put(5, (entityIn) -> SpiralEdge.doCircleSlash(entityIn, -30, 90))
                            .put(6, (entityIn) -> SpiralEdge.doCircleSlash(entityIn, -30, 0))
                            .put(7, (entityIn) -> SpiralEdge.doCircleSlash(entityIn, -30, -90)).build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState SPIRAL_EDGE_END = register("spiral_edge_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge).build());

    public static final ComboState GALE_SWORDS = register("gale_swords",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> SlashBladeAddon.prefix("gale_swords_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 2F))
                            .put(3, (entityIn) -> GaleSwords.doSlash(entityIn, false, 7, 2f)).build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState GALE_SWORDS_END = register("gale_swords_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge).build());

    public static final ComboState LIGHTING_SWORDS = register("lighting_swords",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> SlashBladeAddon.prefix("lighting_swords_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 2F))
                            .put(3, (entityIn) -> LightingSwords.doSlash(entityIn, false, 7, 2f)).build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState LIGHTING_SWORDS_END = register("lighting_swords_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge).build());

    public static final ComboState FIRE_SPIRAL = register("fire_spiral",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> SlashBladeAddon.prefix("spiral_edge_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(4, (entityIn) -> FireSpiral.doCircleSlash(entityIn, -30, 180))
                            .put(5, (entityIn) -> FireSpiral.doCircleSlash(entityIn, -30, 90))
                            .put(6, (entityIn) -> FireSpiral.doCircleSlash(entityIn, -30, 0))
                            .put(7, (entityIn) -> FireSpiral.doCircleSlash(entityIn, -30, -90)).build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState FIRE_SPIRAL_END = register("fire_spiral_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge).build());

    public static final ComboState WATER_DRIVE = register("water_drive",
            ComboState.Builder.newInstance()
                    .startAndEnd(400, 459)
                    .priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> SlashBladeAddon.prefix("water_drive_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 0.1F))
                            .put(3, (entityIn) -> WaterDrive.doSlash(entityIn, 0, 10, Vec3.ZERO, false, 7, 2f)).build())
                    .addHitEffect(StunManager::setStun)
                    .build()
    );
    public static final ComboState DRIVE_VERTICALL_END = register("water_drive_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge).build());

    private static ComboState register(String name, ComboState state) {
        return Registry.register(ComboStateRegistry.COMBO_STATE, SlashBladeAddon.prefix(name), state);
    }
}
