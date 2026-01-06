package com.yakumosakura.yakumoblade.registry.slashblade;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.registry.slashblade.combostate.YAComboRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.combostate.YAComboRegistry2;
import mods.flammpfeil.slashblade.registry.ComboStateRegistry;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class YASlashArtRegistry {
    public static final SlashArts WASC;
    public static final SlashArts DeadOrLifeBySoul;
    public static final SlashArts WSS;
    public static final SlashArts TEN_DRIVE;
    public static final SlashArts CSOULEADGE;
    public static final SlashArts SoulGaleSwords;
    public static final SlashArts thoo;
    public static final SlashArts HIGHSLASH;
    public static final SlashArts TEN_DRIVEEX;
    public static final SlashArts GigantjudgementCut;
    public static final SlashArts randomjudgementCut;
    public static final SlashArts judgementCut;
    public static final SlashArts judgementCutLow;

    public static final SlashArts VoidSlashJust;
    public static final SlashArts THO2;
    public static final SlashArts FIRE_BOOST;
    public static final SlashArts FIRE_BOOST2;
    public static final SlashArts RetreatFlyingknife;
    public static final SlashArts Thrustswords;
    public static final SlashArts SwordRainlLightning;
    public static final SlashArts SwordRainlLightning_ex;
    public static final SlashArts SelfNoall;
    public static final SlashArts RideOn;
    public static final SlashArts SelfNoall_FIRE_BOOST2;
    public static final SlashArts HexGramSummonSwordyellow;
    public static final SlashArts HexGramSummonSwordred;
    public static final SlashArts HexGramSummonSwordBlue;
    public static final SlashArts SpiralSwordex;
    public static final SlashArts Reincarnationheaven;
    public static final SlashArts HotDrives;
    public static final SlashArts CrisonSwords;
    public static final SlashArts WaveAndTinys;
    public static final SlashArts DeadOrLifes;
    public static final SlashArts LetSlash;
    public static final SlashArts FoxJustices;
    public static final SlashArts DragonJustices;
    public static final SlashArts FoxJusticesEX;
    public static final SlashArts DragonJusticesEX;
    public static final SlashArts SakuraSlash;
    public static final SlashArts StarRider;

    static {
        WASC = register("wither_attack", () -> new SlashArts((e) ->
                getId(YAComboRegistry.WITHER_ATTACK)));

        DeadOrLifeBySoul = register("soul_edge", () -> new SlashArts((e) ->
                getId(YAComboRegistry.SOUL_EDGE)));
        WSS = register("wither_summond_sword", () -> new SlashArts((e) ->
                getId(YAComboRegistry.WITHERSUMOONSWORD)));
        TEN_DRIVE = register("ten_drive", () -> new SlashArts((e) ->
                getId(YAComboRegistry.TEN_DRIVE)));
        CSOULEADGE = register("csoul_edge", () -> new SlashArts((e) ->
                getId(YAComboRegistry.CSOUL_EDGE)));
        SoulGaleSwords = register("soul_galegwords", () -> new SlashArts((e) ->
                getId(YAComboRegistry.SOULGALESWORDS)));
        thoo = register("thoo", () -> new SlashArts((e) ->
                getId(YAComboRegistry.THO)));
        HIGHSLASH = register("high_slash", () -> new SlashArts((e) ->
                getId(YAComboRegistry.HIGHSLASH)));
        TEN_DRIVEEX = register("ten_drive_ex", () -> new SlashArts((e) ->
                getId(YAComboRegistry.TEN_DRIVEEX)));
        GigantjudgementCut = register("gigantjudgement_cut", () -> new SlashArts((e) ->
                getId(YAComboRegistry.GigantjudgementCut)));
        randomjudgementCut = register("randomjudgement_cut", () -> new SlashArts((e) ->
                getId(YAComboRegistry.randomjudgementCut)));
        judgementCut = register("judgement_cut", () -> new SlashArts((e) ->
                getId(YAComboRegistry.judgementCut)));
        judgementCutLow = register("judgement_cut_low", () -> new SlashArts((e) -> getId(YAComboRegistry.judgementCutLow)));

        VoidSlashJust = register("combo_a5", () -> new SlashArts((e) -> getId(YAComboRegistry.COMBO_A5)));
        THO2 = register("tho2", () -> new SlashArts((e) -> getId(YAComboRegistry.THO2)));
        FIRE_BOOST = register("fire_boost", () -> new SlashArts((e) -> getId(YAComboRegistry.FIRE_BOOST)));
        FIRE_BOOST2 = register("fire_boost2", () -> new SlashArts((e) -> getId(YAComboRegistry.FIRE_BOOST2)));
        RetreatFlyingknife = register("retreat_flying_knife", () -> new SlashArts((e) -> getId(YAComboRegistry.RetreatFlyingknife)));
        Thrustswords = register("thrust_swords", () -> new SlashArts((e) -> getId(YAComboRegistry.Thrustswords)));
        SwordRainlLightning = register("sword_rain_lightning", () -> new SlashArts((e) -> getId(YAComboRegistry.SwordRainlLightning)));
        SwordRainlLightning_ex = register("sword_rain_lightning_ex", () -> new SlashArts((e) -> getId(YAComboRegistry.SwordRainlLightning_ex)));
        SelfNoall = register("self_noall", () -> new SlashArts((e) -> getId(YAComboRegistry.SelfNoall)));
        RideOn = register("sword_rain_ride_on", () -> new SlashArts((e) -> getId(YAComboRegistry.SwordRainRideOn)));
        SelfNoall_FIRE_BOOST2 = register("self_noall_fire_boost2", () -> new SlashArts((e) -> getId(YAComboRegistry.SelfNoall_FIRE_BOOST2)));
        HexGramSummonSwordyellow = register("hex_gram_summon_sword_yellow", () -> new SlashArts((e) -> getId(YAComboRegistry.HexGramSummonSwordyellow)));
        HexGramSummonSwordred = register("hex_gram_summon_sword_red", () -> new SlashArts((e) -> getId(YAComboRegistry.HexGramSummonSwordred)));
        HexGramSummonSwordBlue = register("hex_gram_summon_sword_blue", () -> new SlashArts((e) -> getId(YAComboRegistry.HexGramSummonSwordBlue)));
        SpiralSwordex = register("spiral_sword_ex", () -> new SlashArts((e) -> getId(YAComboRegistry.SpiralSwordex)));
        Reincarnationheaven = register("reincarnation_heaven", () -> new SlashArts((e) -> getId(YAComboRegistry.Reincarnationheaven)));
        HotDrives = register("hot_drive", () -> new SlashArts((e) -> getId(YAComboRegistry.HotDrives)));
        CrisonSwords = register("crison_swords", () -> new SlashArts((e) -> getId(YAComboRegistry.CrisonSwords)));
        WaveAndTinys = register("wave_and_tinys", () -> new SlashArts((e) -> getId(YAComboRegistry.WaveAndTinys)));
        DeadOrLifes = register("dead_or_life", () -> new SlashArts((e) -> getId(YAComboRegistry.DeadOrLifes)));
        LetSlash = register("let_slash", () -> new SlashArts((e) -> getId(YAComboRegistry2.LetSlash)));
        FoxJustices = register("fox_justices", () -> new SlashArts((e) -> getId(YAComboRegistry2.FoxJustices)));
        DragonJustices = register("dragon_justices", () -> new SlashArts((e) -> getId(YAComboRegistry2.DragonJustices)));
        FoxJusticesEX = register("fox_justices_ex", () -> new SlashArts((e) -> getId(YAComboRegistry2.FoxJusticesEX)));
        DragonJusticesEX = register("dragon_justices_ex", () -> new SlashArts((e) -> getId(YAComboRegistry2.DragonJusticesEX)));
        SakuraSlash = register("sakura_slash", () -> new SlashArts((e) -> getId(YAComboRegistry2.Sakura_Slash)));
        StarRider = register("star_rider", () -> new SlashArts((e) -> getId(YAComboRegistry2.StarRider)));
    }

    private static ResourceLocation getId(ComboState comboState) {
        return ComboStateRegistry.COMBO_STATE.getKey(comboState);
    }

    private static SlashArts register(String name, Supplier<SlashArts> slashArts) {
        return Registry.register(SlashArtsRegistry.SLASH_ARTS, Yakumoblade.prefix(name), slashArts.get());
    }

    public static void init() {

    }
}
