package com.yakumosakura.yakumoblade.registry.slashblade;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.specialeffects.colorSE.Eternal;
import com.yakumosakura.yakumoblade.specialeffects.colorSE.VeryBlue;
import com.yakumosakura.yakumoblade.specialeffects.colorSE.VeryGreen;
import com.yakumosakura.yakumoblade.specialeffects.hexgam.ExMode;
import com.yakumosakura.yakumoblade.specialeffects.ses.HexGam;
import com.yakumosakura.yakumoblade.specialeffects.ses.ProudSoulDrive;
import com.yakumosakura.yakumoblade.specialeffects.starSE.Highfrequency;
import com.yakumosakura.yakumoblade.specialeffects.starSE.ex.BlueFoxSoul;
import com.yakumosakura.yakumoblade.specialeffects.starSE.ex.FinalAllMake;
import com.yakumosakura.yakumoblade.specialeffects.starSE.ex.GreatSunSoul;
import com.yakumosakura.yakumoblade.specialeffects.starSE.ex.ObsdianFoxSoul;
import com.yakumosakura.yakumoblade.specialeffects.starSE.re.*;
import com.yakumosakura.yakumoblade.specialeffects.swordart.LostHeart;
import com.yakumosakura.yakumoblade.specialeffects.theblades.FoxBlade;
import com.yakumosakura.yakumoblade.specialeffects.theblades.GhostBlade;
import com.yakumosakura.yakumoblade.specialeffects.theblades.SharpnessBlade;
import com.yakumosakura.yakumoblade.specialeffects.theblades.YouKai;
import com.yakumosakura.yakumoblade.specialeffects.touhouSE.*;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.Registry;

import java.util.function.Supplier;

public class YASpecialEffectsRegistry {
    public static final SpecialEffect VERY_GREEN;
    public static final SpecialEffect ETERNAL;
    public static final SpecialEffect VERY_BLUE;
    public static final SpecialEffect STAR_SOUL;
    public static final SpecialEffect SUN_SOUL;
    public static final SpecialEffect highfrequency;
    public static final SpecialEffect gapgap;
    public static final SpecialEffect yuyuko;
    public static final SpecialEffect blackslashbreak;//暗星魔斩魂
    public static final SpecialEffect Greenstarsoul;//暗星魔斩魂
    public static final SpecialEffect StarSname;//史达之名
    public static final SpecialEffect HexGamFox;//狐·神佑
    public static final SpecialEffect HexGamDragon;//龙·神佑
    public static final SpecialEffect Foxsoul;//狐星魂
    public static final SpecialEffect FoxsoulEX;//炽狐星烈魂
    public static final SpecialEffect ThunderSoul;//天雷魂
    public static final SpecialEffect FoxSoulBlue;//寒狐极致魂
    public static final SpecialEffect StarDriveEdges;//星刃风暴
    public static final SpecialEffect ProudSoulDrive;//耀魂刃旋
    public static final SpecialEffect FinalAllMake;//终局永劫魂
    public static final SpecialEffect Dream;//梦符·双灵追尾
    public static final SpecialEffect Lwt;//嗜血
    public static final SpecialEffect ExMode;//幻刃连斩
    public static final SpecialEffect YouKai;//妖性
    public static final SpecialEffect FoxBlade;//招财狐
    public static final SpecialEffect SwordArtOnCrow;//鸦羽流法
    public static final SpecialEffect SwordArtOnGap;//隙间流法
    public static final SpecialEffect SwordArtOnDead;//亡蝶流法
    public static final SpecialEffect SwordArtOnDMC;//力量流法
    public static final SpecialEffect SwordArtOnFOX;//六芒流法·狐
    public static final SpecialEffect SwordArtOnDRAGON;//六芒流法·龙
    public static final SpecialEffect MakeGaps;//境界 · 神隠
    public static final SpecialEffect LostHeart;//残心
    public static final SpecialEffect YuyukoSe;//死蝶 · 樱吹雪葬
    public static final SpecialEffect BeyondYuyuko;//彼岸 · 噬魂之契
    public static final SpecialEffect SharpnessBlade;//利刃
    public static final SpecialEffect GhostBlade;//灵性
    public static final SpecialEffect GreatSunSoul;//崇高赤日魂
    public static final SpecialEffect ObsdianFoxSoul;//黑曜神狐魂

    static {
        VERY_GREEN = register("very_green", VeryGreen::new);
        ETERNAL = register("eternal", Eternal::new);
        VERY_BLUE = register("very_blue", VeryBlue::new);
        STAR_SOUL = register("star_soul", StarSoul::new);
        SUN_SOUL = register("sun_soul", SunSoul::new);
        highfrequency = register("highfrequency", Highfrequency::new);
        gapgap = register("gapgap", Gapgap::new);
        yuyuko = register("yuyuko", Yuyuko::new);
        blackslashbreak = register("blackslashbreak", StarSoulEX::new);
        Greenstarsoul = register("greenstarsoul", StarSoulEX::new);
        StarSname = register("starsname", StarSoulEX::new);
        HexGamFox = register("hexgamfox", HexGam::new);
        HexGamDragon = register("hexgamdragon", HexGam::new);
        Foxsoul = register("foxsoul", FoxSoul::new);
        FoxsoulEX = register("foxsoulex", StarSoulEX::new);
        ThunderSoul = register("thundersoul", ThunderSoul::new);
        FoxSoulBlue = register("foxsoulblue", BlueFoxSoul::new);
        StarDriveEdges = register("stardriveedge", StarDriveEdge::new);
        ProudSoulDrive = register("proud_soul_drive", ProudSoulDrive::new);
        FinalAllMake = register("final_all_make", FinalAllMake::new);
        Dream = register("dream", DreamSE::new);
        Lwt = register("lwt", LwtSE::new);
        ExMode = register("exmode", ExMode::new);
        YouKai = register("youkai", YouKai::new);
        FoxBlade = register("foxblade", FoxBlade::new);
        SwordArtOnCrow = register("sword_art_on_dinzeer", Eternal::new);
        SwordArtOnGap = register("sword_art_on_gap", Eternal::new);
        SwordArtOnDead = register("sword_art_on_dead", Eternal::new);
        SwordArtOnDMC = register("sword_art_on_dmc", Eternal::new);
        SwordArtOnFOX = register("sword_art_on_fox", Eternal::new);
        SwordArtOnDRAGON = register("sword_art_on_dragon", Eternal::new);
        MakeGaps = register("make_gap", MakeGap::new);
        LostHeart = register("lost_heart", LostHeart::new);
        YuyukoSe = register("yuyukose", YuyukoSe::new);
        BeyondYuyuko = register("beyond_yuyuko", BeyondYuyuko::new);
        SharpnessBlade = register("sharpness_blade", SharpnessBlade::new);
        GhostBlade = register("ghost_blade", GhostBlade::new);
        GreatSunSoul = register("great_sun_soul", GreatSunSoul::new);
        ObsdianFoxSoul = register("obsidian_fox_soul", ObsdianFoxSoul::new);
    }

    private static SpecialEffect register(String name, Supplier<SpecialEffect> effect) {
        return Registry.register(SpecialEffectsRegistry.SPECIAL_EFFECT, Yakumoblade.prefix(name), effect.get());
    }

    public static void init() {

    }
}