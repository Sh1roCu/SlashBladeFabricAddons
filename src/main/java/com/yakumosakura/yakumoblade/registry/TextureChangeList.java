package com.yakumosakura.yakumoblade.registry;

import com.yakumosakura.yakumoblade.Yakumoblade;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextureChangeList {
    public static final List<ResourceLocation> DRAGON_GRAMTEXTURE = new ArrayList<>(
            Arrays.asList(
                    Yakumoblade.prefix("model/hexgram/dragon_six/dragonhexagram.png"),
                    Yakumoblade.prefix("model/hexgram/dragon_six/dragonhexagram_kb.png"),
                    Yakumoblade.prefix("model/hexgram/dragon_six/dragonhexagram_szhr.png"),
                    Yakumoblade.prefix("model/hexgram/dragon_six/dragonhexagram_cyz.png")
            )
    );
    public static final List<ResourceLocation> FOX_GRAMTEXTURE = new ArrayList<>(
            Arrays.asList(
                    Yakumoblade.prefix("model/hexgram/fox_six/foxhexagram.png"),
                    Yakumoblade.prefix("model/hexgram/fox_six/foxhexagram_kb.png"),
                    Yakumoblade.prefix("model/hexgram/fox_six/foxhexagram_szhr.png"),
                    Yakumoblade.prefix("model/hexgram/fox_six/foxhexagram_cyz.png")
            )
    );
}
