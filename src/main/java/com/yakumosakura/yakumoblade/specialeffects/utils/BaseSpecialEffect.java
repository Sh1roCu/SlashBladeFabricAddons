package com.yakumosakura.yakumoblade.specialeffects.utils;

import com.yakumosakura.yakumoblade.utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public abstract class BaseSpecialEffect extends SpecialEffect {

    public BaseSpecialEffect(int questlevel) {
        super(questlevel);
    }

    /**
     * 标准化的SlashBlade更新方法
     *
     * @param event 事件对象
     */
    public void onSlashBladeUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!checkSpecialEffect(player)) return;

        handleUpdate(event, player);
    }

    /**
     * 标准化的斩击处理方法
     *
     * @param event 事件对象
     * @param roll  随机值
     */
    public void onDoingSlash(SlashBladeEvent.DoSlashEvent event, float roll) {
        if (!(event.getUser() instanceof Player player)) return;
        if (!checkSpecialEffect(player)) return;

        handleSlash(event, player, roll);
    }

    /**
     * 检查玩家是否具有该特殊效果
     *
     * @param player 玩家
     * @return 是否有此特殊效果
     */
    protected boolean checkSpecialEffect(Player player) {
        ISlashBladeState state = SlashBladeUtil.getState(player.getMainHandItem()).orElse(null);
        if (state == null) return false;

        return state.hasSpecialEffect(getEffectId());
    }

    /**
     * 获取特殊效果ID
     *
     * @return 特殊效果的ResourceLocation
     */
    protected abstract ResourceLocation getEffectId();

    /**
     * 处理更新事件的具体逻辑
     *
     * @param event  事件对象
     * @param player 玩家
     */
    protected abstract void handleUpdate(SlashBladeEvent.UpdateEvent event, Player player);

    /**
     * 处理斩击事件的具体逻辑
     *
     * @param event  事件对象
     * @param player 玩家
     * @param roll   随机值
     */
    protected void handleSlash(SlashBladeEvent.DoSlashEvent event, Player player, float roll) {
        // 默认不处理斩击事件，子类可以重写
    }
}