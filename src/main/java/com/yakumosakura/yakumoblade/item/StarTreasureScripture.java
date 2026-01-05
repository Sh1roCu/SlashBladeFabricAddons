package com.yakumosakura.yakumoblade.item;

import com.yakumosakura.yakumoblade.utils.LocalUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class StarTreasureScripture extends Item {
    public StarTreasureScripture() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(LocalUtil.returnner("star_treasure_scripture"));
        list.add(LocalUtil.returnner("star_treasure_scripture_1"));
    }
}
