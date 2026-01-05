package com.exfantasycode.mclib.Utils.local;

import net.minecraft.network.chat.Component;

public class ItemtipUtil {
    public static Component Tooltiplocal(String modid, String id) {
        return Component.translatable("tooltip." + modid + "." + id);
    }


}
