package com.yakumosakura.yakumoblade.specialeffects.utils;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.utils.SlashBladeUtils;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SeEX extends SpecialEffect {
    public SeEX(int questlevel) {
        super(questlevel);
    }


    public static Optional<ResourceLocation> gettext(Player playerIn) {
        return CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(state -> state.getTexture()).get();
    }

    public static void settext(Player playerIn, String text) {
        CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .ifPresent(state -> state.setTexture(Yakumoblade.prefix(text)));
    }

    public static SlashArts getsa(Player playerIn) {
        return CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(state -> state.getSlashArts()).get();
    }

    public static void setsa(Player playerIn, ResourceLocation sa) {
        if (playerIn == null) {
            Yakumoblade.LOGGER.warn("Player is null, cannot set slash arts key.");
            return;
        }

        ItemStack mainHandItem = playerIn.getMainHandItem();
        if (mainHandItem.isEmpty()) {
            Yakumoblade.LOGGER.warn("Player's main hand item is empty, cannot set slash arts key.");
            return;
        }

        @NotNull var capability = CapabilitySlashBlade.getBladeState(mainHandItem);
        capability.ifPresent(state -> {
            try {
                state.setSlashArtsKey(sa);
            } catch (Exception e) {
                Yakumoblade.LOGGER.error("Failed to set slash arts key", e);
            }
        });

        if (capability.isEmpty()) {
            Yakumoblade.LOGGER.warn("Item does not have BLADESTATE capability, cannot set slash arts key.");
        }
    }

    public static String gettran(Player playerIn) {
        return CapabilitySlashBlade.getBladeState(playerIn.getMainHandItem())
                .map(ISlashBladeState::getTranslationKey).get();
    }

    public static String gettransa(ResourceLocation sa) {
        return "slash_art." + sa.toLanguageKey().replace("/",".");
    }

    public static String createis(Player _player, ResourceLocation sa) {
        return Component.translatable(gettran(_player)).getString()
                + Component.translatable("yakumoblade.types.open").getString()
                + Component.translatable(gettransa(sa)).getString();
    }


    public static boolean hasSpecialEffect(ItemStack stack, SpecialEffect effect) {
        return SlashBladeUtils.hasSpecialEffect(stack, effect);// 没有找到指定的特殊效果
    }


}
