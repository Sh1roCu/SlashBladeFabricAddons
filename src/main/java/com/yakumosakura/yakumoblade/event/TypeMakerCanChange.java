package com.yakumosakura.yakumoblade.event;

import cn.sh1rocu.slashblade.api.event.PlayerEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;

public class TypeMakerCanChange extends PlayerEvent {
    /**
     * 构造一个新的TypeMaker按键按下事件。
     *
     * @param player 触发事件的玩家
     */
    public TypeMakerCanChange(Player player) {
        super(player);
    }

    public static final Event<Callback> EVENT = EventFactory.createArrayBacked(Callback.class, callbacks -> event -> {
        for (Callback callback : callbacks) {
            callback.post(event);
        }
    });

    public interface Callback {
        void post(TypeMakerCanChange event);
    }
}