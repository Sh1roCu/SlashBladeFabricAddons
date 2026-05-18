package com.yakumosakura.yakumoblade.data;

import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.registry.ItemRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YAItem;
import mods.flammpfeil.slashblade.data.builtin.SlashBladeBuiltInRegistry;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class YakumoBladeRecipeProvioder extends FabricRecipeProvider {
    public YakumoBladeRecipeProvioder(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.DOUBLE_YAMATO.location())
                .pattern("ZVB")
                .pattern("VPV")
                .pattern("BSZ")
                .define('Z', SBItems.PROUDSOUL_SPHERE)
                .define('V', Items.EMERALD_BLOCK)
                .define('S', Items.NETHER_STAR)
                .define('P', Items.SCULK_CATALYST)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitry.GREEN_YAMATO.location())
                                .killCount(10)
                                .refineCount(20)
                                .build()
                ).toVanilla()).unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryHexGram.hexagram_six.location())
                .pattern("WTA")
                .pattern("DXT")
                .pattern("NRB")
                .define('A', Items.AMETHYST_SHARD)
                .define('D', Items.DIAMOND_BLOCK)
                .define('R', Items.REDSTONE_BLOCK)
                .define('N', Items.NETHER_STAR)
                .define('T', SBItems.PROUDSOUL_SPHERE)
                .define('B', SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance()
                                        .name(SlashBladeBuiltInRegistry.RODAI_IRON.location())
                                        .killCount(10)
                                        .build()
                        ).toVanilla()
                )
                .define('W', SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance()
                                        .name(SlashBladeBuiltInRegistry.RODAI_IRON.location())
                                        .killCount(10)
                                        .build()
                        ).toVanilla()
                )
                .define('X', SlashBladeIngredient.of(
                                YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                                RequestDefinition.Builder.newInstance()
                                        .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                        .killCount(1000)
                                        .refineCount(50)
                                        .build()
                        ).toVanilla()
                )
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.GREEN_IN.location())
                .pattern("VVZ")
                .pattern("VPV")
                .pattern("BSV")
                .define('Z', SBItems.PROUDSOUL_SPHERE)
                .define('V', Items.EMERALD_BLOCK)
                .define('S', Items.NETHER_STAR)
                .define('P', Items.SCULK_CATALYST)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitry.GREEN_YAMATO.location())
                                .killCount(500)
                                .refineCount(30)
                                .build()
                ).toVanilla()).unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.GREEN_YAMATO.location())
                .pattern("CZA")
                .pattern("VBV")
                .pattern("AVC")
                .define('Z', ConventionalItemTags.ARMORS)
                .define('V', Items.EMERALD_BLOCK)
                .define('C', Items.ENDER_EYE)
                .define('A', ConventionalItemTags.GREEN_DYES)
                .define('B', SBItems.SLASHBLADE)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.HUNDRED.location())
                .pattern("VAZ")
                .pattern("DBD")
                .pattern("ZCV")
                .define('Z', SBItems.PROUDSOUL_SPHERE)
                .define('V', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('D', ItemRegistry.UMLE)
                .define('C', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.KOSEKI.location())
                                .killCount(1000)
                                .build()
                ).toVanilla())
                .define('B', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.SANGE.location())
                                .killCount(1000)
                                .refineCount(20)
                                .build()
                ).toVanilla())
                .define('A', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitry.GREEN_YAMATO.location())
                                .killCount(1000)
                                .refineCount(20)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.RU_SANG.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FDA")
                .define('A', Items.BLAZE_POWDER)
                .define('B', Items.NETHER_QUARTZ_ORE)
                .define('C', ItemRegistry.UMLE)
                .define('D', Items.NETHER_BRICK)
                .define('E', Items.BLAZE_ROD)
                .define('F', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.TUKUMO.location())
                                .refineCount(20)
                                .build()
                ).toVanilla()).unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.STAR.location())
                .pattern("ZSZ")
                .pattern("ZBZ")
                .pattern("ZZZ")
                .define('Z', SBItems.PROUDSOUL_SPHERE)
                .define('S', Items.NETHER_STAR)
                .define('B', SBItems.SLASHBLADE)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.BLACK_SLASH.location())
                .pattern("BTA")
                .pattern("DXT")
                .pattern("NWR")
                .define('A', ItemRegistry.UMLE)
                .define('B', SBItems.SLASHBLADE)
                .define('D', ItemRegistry.Star_Soul_Crystal)
                .define('N', Items.NETHER_STAR)
                .define('R', ConventionalItemTags.STORAGE_BLOCKS_NETHERITE)
                .define('T', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('W', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.KOSEKI.location())
                                .build()
                ).toVanilla())
                .define('X', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.DINZEER.location())
                .pattern("WTA")
                .pattern("DXT")
                .pattern("NRB")
                .define('A', ItemRegistry.UMLE)
                .define('B', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KIRISAYA.location())
                                .build()
                ).toVanilla())
                .define('D', ConventionalItemTags.STORAGE_BLOCKS_NETHERITE)
                .define('N', Items.NETHER_STAR)
                .define('R', Items.ENDER_EYE)
                .define('T', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('W', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.DARK_RAVEN.location())
                                .build()
                ).toVanilla())
                .define('X', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .killCount(1000)
                                .refineCount(20)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.RED_SUN.location())
                .pattern("VVZ")
                .pattern("VPV")
                .pattern("BSV")
                .define('P', ItemRegistry.Star_Soul_Crystal)
                .define('V', ConventionalItemTags.STORAGE_BLOCKS_REDSTONE)
                .define('S', Items.NETHER_STAR)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .killCount(1000)
                                .refineCount(20)
                                .build()
                ).toVanilla())
                .define('Z', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitry.RU_SANG.location())

                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.UNDEAD_SLASH.location())
                .pattern("DSD")
                .pattern("ZBZ")
                .pattern("DZD")
                .define('Z', SBItems.PROUDSOUL_SPHERE)
                .define('S', Items.NETHER_STAR)
                .define('D', ItemRegistry.UMLE)
                .define('B', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location())

                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.VERGIL.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FDA")
                .define('A', ItemRegistry.UMLE)
                .define('B', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('C', Items.NETHER_STAR)
                .define('D', Items.NETHERITE_INGOT)
                .define('E', Items.DRAGON_BREATH)
                .define('F', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.YAMATO.location())
                                .killCount(1000)
                                .refineCount(200)
                                .proudSoul(1000000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.YE_SOUL.location())
                .pattern("WZS")
                .pattern("VBX")
                .pattern("DYW")
                .define('W', ConventionalItemTags.LAPIS_GEMS)
                .define('V', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('Y', SBItems.PROUDSOUL_CRYSTAL)
                .define('X', SBItems.PROUDSOUL_INGOT)
                .define('Z', SBItems.PROUDSOUL_SPHERE)
                .define('D', ItemRegistry.UMLE)
                .define('S', ItemRegistry.Star_Soul_Crystal)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .killCount(500)
                                .proudSoul(1000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.YAKUMOSAKURA.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FDA")
                .define('A', Items.NETHER_STAR)
                .define('B', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('C', Items.AMETHYST_SHARD)
                .define('D', Items.ENDER_EYE)
                .define('E', Items.END_CRYSTAL)
                .define('F', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.nieblade_yukari.location())
                                .killCount(1300)
                                .refineCount(40)
                                .proudSoul(20000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.YUYUKO.location())
                .pattern("SEG")
                .pattern("GDE")
                .pattern("IGS")
                // .define('D', TFItems.PEACOCK_FEATHER_FAN.get())
                .define('D', Items.DRAGON_EGG)
                .define('E', SBItems.PROUDSOUL_SPHERE)
                // .define('G', TFItems.CARMINITE.get())
                .define('G', Items.DRAGON_HEAD)
                .define('S', Items.END_CRYSTAL)
                .define('I', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.TAGAYASAN.location())
                                .killCount(1000)
                                .proudSoul(3000)
                                .build()
                ).toVanilla())
                // .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(withConditions(consumer, ResourceConditions.allModsLoaded(TwilightForestMod.ID)));
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.YUYUKO_3.location())
                .pattern("SEF")
                .pattern("GDE")
                .pattern("IGS")
                .define('D', Items.NETHER_STAR)
                .define('E', Items.END_CRYSTAL)
                .define('G', Items.RESPAWN_ANCHOR)
                .define('S', Items.ECHO_SHARD)
                .define('F', Items.MUSIC_DISC_11)

                .define('I', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.YUYUKO_2.location())
                                .killCount(1000)
                                .proudSoul(6000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.YUYUKOFINAL.location())
                .pattern("SEF")
                .pattern("GDE")
                .pattern("IGS")
                .define('D', Items.RECOVERY_COMPASS)
                .define('S', Items.END_CRYSTAL)
                .define('G', Items.TOTEM_OF_UNDYING)
                .define('E', Items.PINK_PETALS)
                .define('F', Items.CHERRY_SAPLING)

                .define('I', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.YUYUKO_3.location())
                                .killCount(1000)
                                .proudSoul(10000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.YAKUMOSAKURA2.location())
                .pattern("SCZ")
                .pattern("GDC")
                .pattern("IGS")
                .define('S', Items.NETHER_STAR)
                .define('C', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('Z', Items.AMETHYST_CLUSTER)
                .define('D', Items.END_CRYSTAL)
                .define('G', SBItems.PROUDSOUL_CRYSTAL)
                .define('I', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.YAKUMOSAKURA.location())
                                .killCount(1000)
                                .proudSoul(10000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.YUYUKO_2.location())
                .pattern("ABC")
                .pattern("DCB")
                .pattern("EDA")
                .define('A', Items.END_CRYSTAL)
                .define('B', Items.WITHER_ROSE)
                .define('C', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('D', Items.NETHER_STAR)
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.YUYUKO.location())
                                .killCount(1000)
                                .proudSoul(10000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.YAKUMOSAKURA3.location())
                .pattern("SEF")
                .pattern("GDE")
                .pattern("IAS")
                .define('D', getOptionalItem("touhou_little_maid", "wireless_io", Items.REDSTONE))
                .define('E', Items.NETHER_STAR)
                .define('G', Items.MUSIC_DISC_MALL)
                .define('A', Items.PURPLE_BED)
                .define('S', Items.END_CRYSTAL)
                .define('F', Items.SPYGLASS)

                .define('I', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.YAKUMOSAKURA2.location())
                                .killCount(1000)
                                .proudSoul(10000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(withConditions(consumer, ResourceConditions.allModsLoaded(Yakumoblade.TLM)));


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.SP_YAKUMOSAKURA.location())
                .pattern("SED")
                .pattern("GDE")
                .pattern("IGS")
                // TODO: 灾变物品
//                .define('D', ModItems.VOID_CORE.get())
//                .define('E', ModItems.VOID_EYE.get())
//                .define('G', ModItems.ABYSS_EYE.get())
                // 用原版物品替换
                .define('D', Items.DRAGON_EGG)
                .define('E', Items.ENDER_PEARL)
                .define('G', Items.ENDER_EYE)

                .define('S', Items.END_CRYSTAL)
                .define('I', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.YAKUMOSAKURA3.location())
                                .killCount(1000)
                                .refineCount(88)
                                .proudSoul(17000)
                                .build()
                ).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.SANGE_FLOWER.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("FLG")
                .define('A', Items.ALLIUM)
                .define('B', Items.HORN_CORAL)//去当海底小纵队吧少年！
                .define('C', Items.BLUE_ORCHID)
                .define('D', SBItems.PROUDSOUL_TRAPEZOHEDRON)//YES
                .define('E', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.SANGE.location())
                                .proudSoul(1700)
                                .build()
                ).toVanilla())
                .define('F', Items.SHEARS)
                .define('L', Items.BLAZE_ROD)//去当地狱潜水员吧少年
                .define('G', Items.TRIDENT)//模型和贴图


                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitry.mirageedge.location())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', SBItems.PROUDSOUL_CRYSTAL)
                .define('B', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('C', SlashBladeIngredient.of(
                        SBItems.SLASHBLADE,
                        RequestDefinition.Builder.newInstance()
                                .refineCount(100)
                                .killCount(500)
                                .proudSoul(97540)
                                .build()
                ).toVanilla())


                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.hundred_end.location())
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitry.HUNDRED.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())
                .define('C', ItemRegistry.StarTreasurescripture)
                .define('F', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.KOSEKI.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())
                .define('E', ItemRegistry.Star_Soul_Crystal)
                .define('D', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.BLACK_SLASH.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())

                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.BLUE_FOX.location())
                .pattern("CAC")
                .pattern("CBC")
                .pattern("CCC")
                .define('A', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .proudSoul(1700)
                                .build()).toVanilla())
                .define('B', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.FOX_WHITE.location())
                                .proudSoul(1700)
                                .build()).toVanilla())
                .define('C', SBItems.PROUDSOUL_CRYSTAL)


                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.STAR_RED.location())
                .pattern(" AB")
                .pattern("ACD")
                .pattern("FE ")
                .define('A', Items.REDSTONE_BLOCK)
                .define('B', Items.IRON_INGOT)
                .define('C', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .proudSoul(1700)
                                .killCount(100)
                                .build()).toVanilla())
                .define('D', Items.GUNPOWDER)
                .define('F', SBItems.PROUDSOUL_SPHERE)
                .define('E', SBItems.PROUDSOUL_TINY)

                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.STAR_M.location())
                .pattern(" AB")
                .pattern("ACD")
                .pattern("FE ")
                .define('A', Items.LAPIS_BLOCK)
                .define('B', Items.IRON_INGOT)
                .define('C', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .proudSoul(1700)
                                .killCount(100)
                                .build()).toVanilla())
                .define('D', Items.GUNPOWDER)
                .define('F', SBItems.PROUDSOUL_SPHERE)
                .define('E', SBItems.PROUDSOUL_TINY)

                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryHexGram.HEXAGRAM.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GDH")
                .define('A', Items.BLAZE_POWDER)
                .define('B', Items.ENDER_PEARL)
                .define('C', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('D', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
                                .killCount(1000)
                                .build()).toVanilla())
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryHexGram.hexagram_six.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())
                .define('F', Items.ENDER_PEARL)
                .define('G', ItemRegistry.powercore)
                .define('H', Items.SEA_LANTERN)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryHexGram.foxhexagram.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FDG")
                .define('A', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.FOX_WHITE.location())
                                .build()).toVanilla())
                .define('B', Items.SEA_LANTERN)
                .define('C', Items.NETHERITE_BLOCK)
                .define('D', Items.NETHER_STAR)
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryHexGram.HEXAGRAM.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())
                .define('F', ItemRegistry.powercoreex)
                .define('G', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.FOX_BLACK.location())
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.blackeslashex.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FDA")
                .define('A', Items.CHORUS_FRUIT)
                .define('B', Items.PHANTOM_MEMBRANE)
                .define('C', Items.NETHER_STAR)
                .define('D', Items.AMETHYST_SHARD)
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.BLACK_SLASH.location())
                                .build()).toVanilla())
                .define('F', ItemRegistry.Star_Soul_Crystal)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.RED_FOX_STAR.location())
                .pattern("ABA")
                .pattern("ECE")
                .pattern("ADA")
                .define('A', SBItems.PROUDSOUL_CRYSTAL)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .killCount(1000)
                                .build()).toVanilla())
                .define('C', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.FOX_BLACK.location())
                                .killCount(1000)
                                .build()).toVanilla())
                .define('D', ItemRegistry.Star_Soul_Crystal)
                .define('E', Items.REDSTONE_BLOCK)

                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.RED_FOX_STAR_SOUL.location())
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.RED_SUN.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())
                .define('C', ItemRegistry.StarTreasurescripture)
                .define('D', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.FOX_BLACK.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())
                .define('E', ItemRegistry.Star_Soul_Crystal)
                .define('F', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.RED_FOX_STAR.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())

                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.sky_ruine.location())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EFE")
                .define('A', ItemRegistry.Star_Soul_Crystal)
                .define('B', Items.NETHER_STAR)
                .define('C', Items.FEATHER)
                .define('D', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .build()).toVanilla())
                .define('E', Items.LIGHTNING_ROD)
                .define('F', Items.ELYTRA)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.GREEN_INex.location())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EFE")
                .define('A', ItemRegistry.Star_Soul_Crystal)
                .define('B', Items.TURTLE_HELMET)
                .define('C', Items.ENDER_EYE)
                .define('D', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitry.GREEN_IN.location())
                                .build()).toVanilla())
                .define('E', ConventionalItemTags.STORAGE_BLOCKS_EMERALD)
                .define('F', ItemRegistry.StarTreasurescripture)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.sun_rise.location())
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('B', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.RED_SUN.location())
                                .build()).toVanilla())
                .define('C', ItemRegistry.StarTreasurescripture)
                .define('D', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.TUKUMO.location())
                                .build()).toVanilla())
                .define('E', ItemRegistry.Star_Soul_Crystal)
                .define('F', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.RED_SUN.location())
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.BLUE_FOX_STAR_SOUL.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHA")
                .define('A', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('B', ItemRegistry.Star_Soul_Crystal)
                .define('C', ItemRegistry.StarTreasurescripture)
                .define('D', Items.LAPIS_BLOCK)
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.BLUE_FOX.location())
                                .build()).toVanilla())
                .define('F', ItemRegistry.Star_Soul_Crystal)
                .define('G', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.FOX_BLACK.location())
                                .build()).toVanilla())
                .define('H', Items.ICE)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.PURPLE_FOX_STAR_SOUL.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("CGH")
                .define('A', Items.ICE)
                .define('B', Items.BEACON)
                .define('C', Items.CRYING_OBSIDIAN)
                .define('D', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.BLUE_FOX_STAR_SOUL.location())
                                .build()).toVanilla())
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.STAR.location())
                                .build()).toVanilla())
                .define('F', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.RED_FOX_STAR_SOUL.location())
                                .build()).toVanilla())
                .define('G', ItemRegistry.StarTreasurescripture)
                .define('H', Items.MAGMA_BLOCK)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.starstr.location())
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A', ItemRegistry.Star_Soul_Crystal)
                .define('B', ItemRegistry.StarTreasurescripture)
                .define('C', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.GREEN_INex.location())
                                .build()).toVanilla())
                .define('D', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.YE_SOUL.location())
                                .build()
                ).toVanilla())
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.sun_rise.location())
                                .build()
                ).toVanilla())
                .define('F', ItemRegistry.UMLE)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryStar.FINAL_STAR_SOUL.location())
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A', ItemRegistry.UMLE)
                .define('B', Items.DRAGON_EGG)
                .define('C', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.blackeslashex.location())
                                .build()).toVanilla())
                .define('D', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.sky_ruine.location())
                                .build()
                ).toVanilla())
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryStar.hundred_end.location())
                                .build()
                ).toVanilla())
                .define('F', Items.NETHER_STAR)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.blademaster_yukari.location())
                .pattern(" AB")
                .pattern("CDE")
                .pattern("FA ")
                .define('A', SBItems.PROUDSOUL_INGOT)
                .define('B', Items.ENDER_EYE)
                .define('C', Items.RED_DYE)
                .define('D', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .refineCount(10)
                                .killCount(10)
                                .proudSoul(1000)
                                .build()
                ).toVanilla())
                .define('E', Items.PURPLE_DYE)
                .define('F', Items.DIAMOND)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.nieblade_yukari.location())
                .pattern(" AB")
                .pattern("CDE")
                .pattern("FG ")
                .define('A', Items.ENDER_PEARL)
                .define('B', Items.RED_DYE)
                .define('C', Items.BLAZE_POWDER)
                .define('D', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryTouHou.blademaster_yukari.location())
                                .refineCount(50)
                                .killCount(150)
                                .proudSoul(1000)
                                .build()
                ).toVanilla())
                .define('E', Items.ENDER_EYE)
                .define('F', Items.IRON_INGOT)
                .define('G', Items.BLACK_DYE)
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryTouHou.yukari_blade.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FDA")
                .define('A', Items.PINK_PETALS)
                .define('B', Items.CHERRY_SAPLING)
                .define('C', Items.CHERRY_LOG)

                .define('D', SBItems.PROUDSOUL_TRAPEZOHEDRON)

                .define('E', Items.CHERRY_LOG)

                .define('F', SlashBladeIngredient.of(
                        SBItems.SLASHBLADE_WOOD,
                        RequestDefinition.Builder.newInstance()
                                .refineCount(50)
                                .killCount(100)
                                .proudSoul(2000)
                                .build()
                ).toVanilla())

                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryHexGram.FIRE_DRAGON.location())
                .pattern(" EF")
                .pattern("BCS")
                .pattern("WQ ")
                .define('W', ConventionalItemTags.WHEAT_CROPS)
                .define('Q', Items.ICE)
                .define('B', Items.BLAZE_POWDER)
                .define('S', SBItems.PROUDSOUL_CRYSTAL)
                .define('E', Items.MAGMA_BLOCK)
                .define('F', ConventionalItemTags.FEATHERS)
                .define('C',
                        SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance().name(SlashBladeBuiltInRegistry.RUBY.location())
                                        .build()).toVanilla())

                .unlockedBy(getHasName(SBItems.SLASHBLADE_SILVERBAMBOO), has(SBItems.SLASHBLADE_SILVERBAMBOO))
                .save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryHexGram.ICE_DRAGON.location())
                .pattern(" EF")
                .pattern("BCS")
                .pattern("WQ ")
                .define('W', ConventionalItemTags.WHEAT_CROPS)
                .define('Q', Items.MAGMA_BLOCK)
                .define('B', Items.BLAZE_POWDER)
                .define('S', SBItems.PROUDSOUL_CRYSTAL)
                .define('E', Items.ICE)
                .define('F', ConventionalItemTags.FEATHERS)
                .define('C',
                        SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance().name(SlashBladeBuiltInRegistry.RUBY.location())
                                        .build()).toVanilla())

                .unlockedBy(getHasName(SBItems.SLASHBLADE_SILVERBAMBOO), has(SBItems.SLASHBLADE_SILVERBAMBOO))
                .save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(YakumoBladeBuiltInRegsitryHexGram.dragonhexagram.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FDG")
                .define('A', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryHexGram.ICE_DRAGON.location())
                                .build()).toVanilla())
                .define('B', Items.SEA_LANTERN)
                .define('C', Items.NETHERITE_BLOCK)
                .define('D', Items.NETHER_STAR)
                .define('E', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryHexGram.HEXAGRAM.location())
                                .proudSoul(17000)
                                .killCount(1000)
                                .build()).toVanilla())
                .define('F', ItemRegistry.powercoreex)
                .define('G', SlashBladeIngredient.of(
                        YAItem.getItem(YAItem.SlashBladeOfYakumoBlade),
                        RequestDefinition.Builder.newInstance()
                                .name(YakumoBladeBuiltInRegsitryHexGram.FIRE_DRAGON.location())
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);


    }

        private static Item getOptionalItem(String namespace, String path, Item fallback) {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
                return BuiltInRegistries.ITEM.containsKey(id) ? BuiltInRegistries.ITEM.get(id) : fallback;
        }

    public Item getItem(ResourceLocation item) {
        return BuiltInRegistries.ITEM.get(item);
    }
}

