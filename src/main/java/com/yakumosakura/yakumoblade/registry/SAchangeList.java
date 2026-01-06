package com.yakumosakura.yakumoblade.registry;

import cn.mmf.slashblade_addon.registry.SBASlashArtsRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASlashArtRegistry;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.slasharts.SlashArts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SAchangeList {
    public static final List<SlashArts> CROW_SA = new ArrayList<>(
            Arrays.asList(
                    YASlashArtRegistry.TEN_DRIVE,
                    SlashArtsRegistry.CIRCLE_SLASH,
                    SlashArtsRegistry.WAVE_EDGE,
                    YASlashArtRegistry.HIGHSLASH,
                    YASlashArtRegistry.WSS,
                    YASlashArtRegistry.thoo
            )
    );
    public static final List<SlashArts> YUKARI_SA = new ArrayList<>(
            Arrays.asList(
                    YASlashArtRegistry.DeadOrLifes,
                    YASlashArtRegistry.VoidSlashJust,
                    SlashArtsRegistry.SAKURA_END,
                    YASlashArtRegistry.WaveAndTinys
            )
    );
    public static final List<SlashArts> YUYUKO_SA = new ArrayList<>(
            Arrays.asList(
                    YASlashArtRegistry.CSOULEADGE,
                    YASlashArtRegistry.SoulGaleSwords,
                    YASlashArtRegistry.SakuraSlash,
                    SlashArtsRegistry.VOID_SLASH
            )
    );
    public static final List<SlashArts> VERGIL_SA = new ArrayList<>(
            Arrays.asList(
                    SlashArtsRegistry.JUDGEMENT_CUT,
                    SlashArtsRegistry.VOID_SLASH,
                    YASlashArtRegistry.randomjudgementCut,
                    YASlashArtRegistry.GigantjudgementCut
            )
    );
    public static final List<SlashArts> FOX_HEXGRAM_SA = new ArrayList<>(
            Arrays.asList(
                    YASlashArtRegistry.FoxJustices,
                    YASlashArtRegistry.FoxJusticesEX,
                    YASlashArtRegistry.TEN_DRIVE,
                    SlashArtsRegistry.JUDGEMENT_CUT
            )
    );
    public static final List<SlashArts> DRAGON_HEXGRAM_SA = new ArrayList<>(
            Arrays.asList(
                    YASlashArtRegistry.DragonJustices,
                    YASlashArtRegistry.DragonJusticesEX,
                    YASlashArtRegistry.TEN_DRIVE,
                    SlashArtsRegistry.JUDGEMENT_CUT
            )
    );


    static {
        YUKARI_SA.add(SBASlashArtsRegistry.RAPID_BLISTERING_SWORDS);
        YUYUKO_SA.add(SBASlashArtsRegistry.SPIRAL_EDGE);
    }

    public static void init(){

    }
}
