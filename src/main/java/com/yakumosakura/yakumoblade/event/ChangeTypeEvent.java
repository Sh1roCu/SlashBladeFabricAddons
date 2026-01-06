package com.yakumosakura.yakumoblade.event;

import com.yakumosakura.yakumoblade.registry.SAchangeList;
import com.yakumosakura.yakumoblade.registry.TextureChangeList;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.Optional;

public class ChangeTypeEvent {
    public static Boolean change = true;

    public static void ChangeType(TypeMakerCanChange event) {
        Player player = event.getEntity();
        Optional<ISlashBladeState> stateOptional = SlashBladeUtil.getState(player.getMainHandItem());

        if (stateOptional.isEmpty()) return;

        ISlashBladeState state = stateOptional.get();
        SlashArts sa = state.getSlashArts();

        changeCrow(sa, state, player);
        changeGap(sa, state, player);
        changeDead(sa, state, player);
        changeYamato(sa, state, player);
        changeDragon(sa, state, player);
        changeFox(sa, state, player);
        change = true;
    }

    private static void changeSlashArts(SlashArts currentArts, ISlashBladeState state, Player player,
                                        List<SlashArts> artsList, ResourceLocation effectId) {
        changeSlashArts(currentArts, state, player, artsList, effectId, null);
    }

    private static void changeSlashArts(SlashArts currentArts, ISlashBladeState state, Player player,
                                        List<SlashArts> artsList, ResourceLocation effectId, List<ResourceLocation> texture) {
        if (!state.hasSpecialEffect(effectId)) return;
        int currentIndex = -1;
        for (int i = 0; i < artsList.size(); i++) {
            if (currentArts == artsList.get(i)) {
                currentIndex = i;
                break;
            }
        }
        if (currentIndex != -1) {
            int nextIndex = (currentIndex + 1) % artsList.size();
            SlashArts nextArts = artsList.get(nextIndex);
            if (texture != null) {
                state.setTexture(texture.get(nextIndex));
            }
            state.setSlashArtsKey(getId(nextArts));
            if (getId(nextArts) != null) {
                player.displayClientMessage(Component.literal(createMessage(player, getId(nextArts), state)), true);
            }
        } else if (!artsList.isEmpty()) {
            state.setSlashArtsKey(getId(artsList.get(0)));
            if (texture != null) {
                state.setTexture(texture.get(0));
            }

            player.displayClientMessage(Component.literal(createMessage(player, getId(artsList.get(0)), state)), true);

        }
    }

    public static void changeDead(SlashArts currentArts, ISlashBladeState state, Player player) {
        changeSlashArts(currentArts, state, player, SAchangeList.YUYUKO_SA,
                getId(YASpecialEffectsRegistry.SwordArtOnDead));
    }

    public static void changeGap(SlashArts currentArts, ISlashBladeState state, Player player) {
        changeSlashArts(currentArts, state, player, SAchangeList.YUKARI_SA,
                getId(YASpecialEffectsRegistry.SwordArtOnGap));
    }

    public static void changeCrow(SlashArts currentArts, ISlashBladeState state, Player player) {
        changeSlashArts(currentArts, state, player, SAchangeList.CROW_SA,
                getId(YASpecialEffectsRegistry.SwordArtOnCrow));
    }

    public static void changeYamato(SlashArts currentArts, ISlashBladeState state, Player player) {
        changeSlashArts(currentArts, state, player, SAchangeList.VERGIL_SA,
                getId(YASpecialEffectsRegistry.SwordArtOnDMC));
    }

    public static void changeDragon(SlashArts currentArts, ISlashBladeState state, Player player) {
        changeSlashArts(currentArts, state, player, SAchangeList.DRAGON_HEXGRAM_SA,
                getId(YASpecialEffectsRegistry.SwordArtOnDRAGON), TextureChangeList.DRAGON_GRAMTEXTURE);
    }

    public static void changeFox(SlashArts currentArts, ISlashBladeState state, Player player) {
        changeSlashArts(currentArts, state, player, SAchangeList.FOX_HEXGRAM_SA,
                getId(YASpecialEffectsRegistry.SwordArtOnFOX), TextureChangeList.FOX_GRAMTEXTURE);
    }

    public static String createMessage(Player player, ResourceLocation sa, ISlashBladeState state) {
        return Component.translatable(state.getTranslationKey()).getString()
                + Component.translatable("yakumoblade.types.open").getString()
                + Component.translatable("slash_art." + sa.toLanguageKey().replace("/",".")).getString();
    }

    private static ResourceLocation getId(SpecialEffect effect) {
        return SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(effect);
    }

    private static ResourceLocation getId(SlashArts slashArts) {
        return SlashArtsRegistry.SLASH_ARTS.getKey(slashArts);
    }
}