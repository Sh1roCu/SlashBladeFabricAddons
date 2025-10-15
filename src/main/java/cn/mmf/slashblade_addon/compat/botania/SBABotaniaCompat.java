package cn.mmf.slashblade_addon.compat.botania;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.core.Registry;
import net.minecraft.world.phys.Vec3;

public class SBABotaniaCompat {
    public static void init() {

    }

    public static final SpecialEffect MANA_BURST = register("mana_burst",
            new ManaBurst());

    public static final SpecialEffect MANA_REPAIR = register("mana_repair",
            new ManaRapair());


    public static final ComboState BLISTERING_TERRA_SWORDS = register(
            "blistering_terra_swords",
            ComboState.Builder.newInstance().startAndEnd(400, 459).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                    .nextOfTimeout(entity -> SlashBladeAddon.prefix("blistering_terra_swords_swords_end"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(2, (entityIn) -> AttackManager.doSlash(entityIn, -30F, Vec3.ZERO, false, false, 2F))
                            .put(3, (entityIn) -> BlisteringTerraSwords.doSlash(entityIn, false, 7, 2f)).build())
                    .addHitEffect(StunManager::setStun).build());

    public static final ComboState BLISTERING_TERRA_SWORDS_END = register(
            "blistering_terra_swords_swords_end",
            ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                    .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                    .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(0, AttackManager::playQuickSheathSoundAction).build())
                    .releaseAction(ComboState::releaseActionQuickCharge).build());


    public static final SlashArts BLISTERING_TERRA_SWORDS_SA = register("blistering_terra_swords",
            new SlashArts((e) -> ComboStateRegistry.getId(BLISTERING_TERRA_SWORDS)));

    private static ComboState register(String name, ComboState comboState) {
        return Registry.register(ComboStateRegistry.COMBO_STATE, SlashBladeAddon.prefix(name), comboState);
    }

    private static SlashArts register(String name, SlashArts slashArts) {
        return Registry.register(SlashArtsRegistry.SLASH_ARTS, SlashBladeAddon.prefix(name), slashArts);
    }

    private static SpecialEffect register(String name, SpecialEffect specialEffect) {
        return Registry.register(SpecialEffectsRegistry.SPECIAL_EFFECT, SlashBladeAddon.prefix(name), specialEffect);
    }
}
