package com.yakumosakura.yakumoblade.item;

import com.yakumosakura.yakumoblade.utils.LocalUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class StarSoulCrystal extends Item {
    public StarSoulCrystal() {

        super(new Properties().stacksTo(64).rarity(Rarity.EPIC));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, TooltipContext context, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, context, list, flag);
        list.add(LocalUtil.returnner("starsoulcrystal"));
        list.add(LocalUtil.returnner("starsoulcrystal_1"));
    }
}
