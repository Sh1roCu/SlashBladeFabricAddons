package com.yakumosakura.yakumoblade.registry;

import com.yakumosakura.yakumoblade.registry.creativetab.ItemTab;
import com.yakumosakura.yakumoblade.registry.slashblade.YASlashArtRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.combostate.YAComboRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.combostate.YAComboRegistry2;

public class RegistryEventBus {
    public static void Registry() {
        ItemRegistry.init();//物品注册
        ItemTab.init();//创造物品栏注册
        YASpecialEffectsRegistry.init();//se注册
        YAComboRegistry.init();//combo注册
        YAComboRegistry2.init();
        YASlashArtRegistry.init();//sa注册
    }
}
