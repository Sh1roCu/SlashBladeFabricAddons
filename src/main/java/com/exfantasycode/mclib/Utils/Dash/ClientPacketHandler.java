package com.exfantasycode.mclib.Utils.Dash;


import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

import static com.exfantasycode.mclib.Utils.Dash.DashMessage.vmove;


public class ClientPacketHandler {
    public static void handledash(DashMessage msg) {

        LivingEntity entity = Minecraft.getInstance().player;
        vmove(entity, msg.dy, msg.dashDistance);
    }

}
