package com.yakumosakura.yakumoblade.registry.creativetab;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.registry.ItemRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import mods.flammpfeil.slashblade.init.SBItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.yakumosakura.yakumoblade.Yakumoblade.MODID;

public class ItemTab {

    public static final CreativeModeTab YAKUMOTAB = register("yakumobladeitem",
            FabricItemGroup.builder().title(Component.translatable("item_group.yakumoblade.yakumobladeitem")).icon(() -> new ItemStack(ItemRegistry.UMLE)).displayItems((parameters, tabData) -> {
                        tabData.accept(ItemRegistry.UMLE);
                        tabData.accept(ItemRegistry.Star_Soul_Crystal);
                        tabData.accept(ItemRegistry.StarTreasurescripture);
                        tabData.accept(ItemRegistry.powercore);
                        tabData.accept(ItemRegistry.powercoreex);
//                        tabData.accept(ItemRegistry.ghost_ingot);
//                        tabData.accept(ItemRegistry.red_eyes);
//                        tabData.accept(ItemRegistry.legend_od_the_star);
//                        tabData.accept(ItemRegistry.ungod_s_crown);
//                        tabData.accept(ItemRegistry.beyond_flower);
//                        tabData.accept(ItemRegistry.red_iron_ingot);
//                        tabData.accept(ItemRegistry.ark_metal_ingot);
//                        tabData.accept(ItemRegistry.blaze_umle);
//                        tabData.accept(ItemRegistry.the_god_s_soul);
//                        tabData.accept(ItemRegistry.blood_sakura);
//                        tabData.accept(ItemRegistry.westward_youkai_wood);
//                        tabData.accept(ItemRegistry.metal_ingot);
                    })

                    .build());


    public static final CreativeModeTab YASlashbladeTouHou = register(MODID + "_slashblade_touhou",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group." + MODID + "." + MODID + "_slashblade_touhou")).icon(() -> {
                        ItemStack stack = new ItemStack(SBItems.SLASHBLADE);
                        CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent(s -> {
                            s.setModel(Yakumoblade.prefix("model/named/sange/sange.obj"));
                            s.setTexture(Yakumoblade.prefix("model/named/sange/yakumosakurafinal.png"));
                        });
                        return stack;
                    })
                    .displayItems((parameters, tabData) -> {

                    })
                    .build());
    public static final CreativeModeTab YASlashbladeStar = register(MODID + "_slashblade_star",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group." + MODID + "." + MODID + "_slashblade_star")).icon(() -> {
                        ItemStack stack = new ItemStack(SBItems.SLASHBLADE);
                        CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent(s -> {
                            s.setModel(Yakumoblade.prefix("model/maker_sword/dark_raven.obj"));
                            s.setTexture(Yakumoblade.prefix("model/maker_sword/star_of_dinzeer.png"));
                        });
                        return stack;
                    })
                    .displayItems((parameters, tabData) -> {

                    })
                    .build());
    public static final CreativeModeTab YASlashbladeHexGram = register(MODID + "_slashblade_hexgram",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group." + MODID + "." + MODID + "_slashblade_hexgram")).icon(() -> {
                        ItemStack stack = new ItemStack(SBItems.SLASHBLADE);
                        CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent(s -> {
                            s.setModel(Yakumoblade.prefix("model/hexgram/five/five.obj"));
                            s.setTexture(Yakumoblade.prefix("model/hexgram/five/five.png"));
                        });
                        return stack;
                    })
                    .displayItems((parameters, tabData) -> {

                    })
                    .build());

    public static final CreativeModeTab YASlashblade = register(MODID + "_slashblade",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group." + MODID + "." + MODID + "_slashblade")).icon(() -> {
                        ItemStack stack = new ItemStack(SBItems.SLASHBLADE);
                        CapabilitySlashBlade.BLADESTATE.maybeGet(stack).ifPresent(s -> {
                            s.setModel(Yakumoblade.prefix("model/named/custom/vergil/model.obj"));
                            s.setTexture(Yakumoblade.prefix("model/named/custom/vergil/texture.png"));
                        });
                        return stack;
                    })
                    .displayItems((parameters, tabData) -> {

                    })
                    .build());


    private static CreativeModeTab register(String name, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Yakumoblade.prefix(name), tab);
    }

    public static void init() {

    }
}
