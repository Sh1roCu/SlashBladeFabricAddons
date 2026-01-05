package com.yakumosakura.yakumoblade.event;

import com.yakumosakura.yakumoblade.Yakumoblade;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class ChangeType {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player _player && !_player.level().isClientSide()) {
            if ((entity instanceof LivingEntity v)) {
//                changehexgramfox(_player,entity,v);
//                changehexgramdragon(_player,entity,v);
            }
//            _player.displayClientMessage(Component.literal("\u6309\u4E0B\u4E86\u6848\u4EF6"), false);

        }
    }

    public static Boolean colcok = true;


//    public static void changehexgramfox(Player _player,Entity entity,LivingEntity _livEnt){
//        SlashArts sa=  getsa(_player);
//        if ( SlashEffectUtils.hasSpecialEffect(_livEnt.getMainHandItem(),MODID+":"+ YASpecialEffectsRegistry.HexGamFox.getId().getPath())){
//            if(gettext(_player).get().equals(prefix("model/named/custom/hexagram/foxhexagram.png"))||sa==YASlashArtRegistry.FoxSs.get()){
//                _player.displayClientMessage(Component.literal(createis(_player,YASlashArtRegistry.FoxSsEX.getId())), true);
//                setsa(_player,YASlashArtRegistry.FoxSsEX.getId());
//                settext(_player,"model/named/custom/hexagram/foxhexagram_kb.png");
//            }else if(gettext(_player).get().equals(prefix("model/named/custom/hexagram/foxhexagram_kb.png"))||sa==YASlashArtRegistry.FoxSsEX.get()){
//                setsa(_player,YASlashArtRegistry.FoxSs.getId());
//                _player.displayClientMessage(Component.literal(createis(_player,YASlashArtRegistry.FoxSs.getId())), true);
//                settext(_player,"model/named/custom/hexagram/foxhexagram.png");
//            }
//        }
//    }
//    public static void changehexgramdragon(Player _player,Entity entity,LivingEntity _livEnt){
//        SlashArts sa=  getsa(_player);
//        if ( SlashEffectUtils.hasSpecialEffect(_livEnt.getMainHandItem(),MODID+":"+ YASpecialEffectsRegistry.HexGamDragon.getId().getPath())){
//            if(gettext(_player).get().equals(prefix("model/named/custom/hexagram/dragonhexagram.png"))||sa==YASlashArtRegistry.Dragomss.get()){
//                _player.displayClientMessage(Component.literal(createis(_player,YASlashArtRegistry.Dragomssex.getId())), true);
//                setsa(_player,YASlashArtRegistry.Dragomssex.getId());
//                settext(_player,"model/named/custom/hexagram/dragonhexagram_kb.png");
//            }else if(gettext(_player).get().equals(prefix("model/named/custom/hexagram/dragonhexagram_kb.png"))||sa==YASlashArtRegistry.Dragomssex.get()){
//                setsa(_player,YASlashArtRegistry.Dragomss.getId());
//                _player.displayClientMessage(Component.literal(createis(_player,YASlashArtRegistry.Dragomss.getId())), true);
//                settext(_player,"model/named/custom/hexagram/dragonhexagram.png");
//            }
//        }
//    }

    public static Optional<ResourceLocation> gettext(Player playerIn) {
        return CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem())
                .map(ISlashBladeState::getTexture).get();
    }

    public static void settext(Player playerIn, String text) {
        CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem())
                .ifPresent(state -> state.setTexture(Yakumoblade.prefix(text)));
    }

    public static SlashArts getsa(Player playerIn) {
        return CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem())
                .map(ISlashBladeState::getSlashArts).get();
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

        @NotNull var capability = CapabilitySlashBlade.BLADESTATE.maybeGet(mainHandItem);
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
        return CapabilitySlashBlade.BLADESTATE.maybeGet(playerIn.getMainHandItem())
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
}
