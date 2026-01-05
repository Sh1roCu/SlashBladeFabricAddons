package com.yakumosakura.yakumoblade.utils;

import net.minecraft.network.chat.Component;

public class LocalUtil {

    public static Component returnner(String z) {
        return Component.literal(Component.translatable("Tooltip.yakumoblade." + z).getString());
    }


}
