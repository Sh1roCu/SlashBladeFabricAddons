package cn.mmf.slashblade_addon.data;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import mods.flammpfeil.slashblade.data.SlashBladeRecipeProvider;
import mods.flammpfeil.slashblade.data.builtin.SlashBladeBuiltInRegistry;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import vazkii.botania.common.item.BotaniaItems;

import java.util.function.Consumer;

public class SlashBladeAddonRecipeProvider extends FabricRecipeProvider {
    public SlashBladeAddonRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        SlashBladeShapedRecipeBuilder
                .shaped(SlashBladeAddonBuiltInRegistry.HF_MURASAMA.location()).pattern(" RI").pattern("RBG")
                .pattern("SL ").define('S', SBItems.PROUDSOUL_TRAPEZOHEDRON).define('I', ConventionalItemTags.IRON_INGOTS)
                .define('G', Items.GUNPOWDER).define('R', SlashBladeRecipeProvider.STORAGE_BLOCKS_REDSTONE)
                .define('L', Items.LEVER)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location()).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE))
                .save(consumer, SlashBladeAddon.prefix("murasama_blade"));

        SlashBladeShapedRecipeBuilder
                .shaped(SlashBladeAddonBuiltInRegistry.WANDERER_HF.location())
                .pattern("  I")
                .pattern("QI ")
                .pattern("BC ")
                .define('C', SBItems.PROUDSOUL_TRAPEZOHEDRON)
                .define('I', ConventionalItemTags.REDSTONE_DUSTS)
                .define('Q', ConventionalItemTags.QUARTZ)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.WANDERER.location()).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE))
                .save(consumer, SlashBladeAddon.prefix("wanderer_hf"));

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.WANDERER.location())
                .pattern("  I")
                .pattern("QI ")
                .pattern("BC ")
                .define('C', Items.CLOCK)
                .define('I', SBItems.PROUDSOUL_INGOT)
                .define('Q', ConventionalItemTags.QUARTZ)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.DOUTANUKI.location()).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        // TODO: TofuCraft Fabric移植
//        SmithingTransformRecipeBuilder
//                .smithing(Ingredient.of(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE),
//                        Ingredient.of(getItem(SBATofuCraftItems.TOFUMETAL_SLASHBLADE)),
//                        Ingredient.of(TofuBlocks.DIAMONDTOFU), RecipeCategory.COMBAT,
//                        getItem(SBATofuCraftItems.TOFUDIAMOND_SLASHBLADE))
//                .unlocks("has_item", has(getItem(SBATofuCraftItems.TOFUMETAL_SLASHBLADE)))
//                .save(withConditions(consumer, DefaultResourceConditions.allModsLoaded("tofucraft")), SlashBladeAddon.prefix("tofu_diamond_blade"));
//        SlashBladeShapedRecipeBuilder.shaped(SBATofuCraftItems.TOFUMETAL_SLASHBLADE).pattern(" ST")
//                .pattern("ST ").pattern("WR ").define('S', TofuItems.TOFUISHI)
//                .define('R',SlashBladeRecipeProvider.STRING).define('W', Items.STICK)
//                .define('T', TofuItems.TOFUMETAL)
//                .unlockedBy(getHasName(SBItems.SLASHBLADE_WOOD), has(SBItems.SLASHBLADE_WOOD))
//                .save(withConditions(consumer, DefaultResourceConditions.allModsLoaded("tofucraft"), SBATofuCraftItems.TOFUMETAL_SLASHBLADE));
//        SlashBladeShapedRecipeBuilder.shaped(SBATofuCraftItems.TOFUISHI_SLASHBLADE).pattern(" SS")
//                .pattern("SS ").pattern("WR ").define('S', TofuItems.TOFUISHI)
//                .define('R', SlashBladeRecipeProvider.STRING).define('W', SlashBladeRecipeProvider.RODS_WOODEN)
//                .unlockedBy(getHasName(SBItems.SLASHBLADE_WOOD), has(SBItems.SLASHBLADE_WOOD))
//                .save(withConditions(consumer, DefaultResourceConditions.allModsLoaded("tofucraft"), SBATofuCraftItems.TOFUISHI_SLASHBLADE));

        SlashBladeShapedRecipeBuilder
                .shaped(SlashBladeAddonBuiltInRegistry.TERRA_BLADE.location()).pattern("ZCO").pattern(" BG")
                .pattern("Q X").define('G', SBItems.PROUDSOUL).define('X', SBItems.PROUDSOUL_SPHERE)
                .define('Q', BotaniaItems.terraSword).define('Z', BotaniaItems.vineBall)
                .define('C', BotaniaItems.thornChakram).define('O', BotaniaItems.gaiaIngot)
                .define('B', SBItems.SLASHBLADE).unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE))
                .save(withConditions(consumer, DefaultResourceConditions.allModsLoaded("botania")), SlashBladeAddonBuiltInRegistry.TERRA_BLADE.location());

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.KAMUY_NONE.location()).pattern("SNS")
                .pattern("IBI").pattern("SDS").define('S', SBItems.PROUDSOUL_SPHERE)
                .define('I', SBItems.PROUDSOUL_INGOT).define('N', ConventionalItemTags.QUARTZ).define('D', Items.BOOK)
                .define('B',
                        SlashBladeIngredient
                                .of(RequestDefinition.Builder.newInstance().killCount(100).refineCount(1).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.KAMUY_WATER.location()).pattern("SNS")
                .pattern("ABQ").pattern("SDS").define('S', SBItems.PROUDSOUL_SPHERE).define('A', Items.PACKED_ICE)
                .define('Q', Items.SNOW_BLOCK).define('N', SlashBladeRecipeProvider.STORAGE_BLOCKS_LAPIS)
                .define('D', Items.WATER_BUCKET)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KAMUY_NONE.location()).killCount(500)
                                .proudSoul(5000).refineCount(20).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.KAMUY_LIGHTING.location()).pattern("SNS")
                .pattern("ABQ").pattern("SDS").define('S', SBItems.PROUDSOUL_SPHERE)
                .define('A', SlashBladeRecipeProvider.STORAGE_BLOCKS_GOlD).define('Q', SlashBladeRecipeProvider.STORAGE_BLOCKS_DIAMOND)
                .define('N', SlashBladeRecipeProvider.STORAGE_BLOCKS_IRON).define('D', SlashBladeRecipeProvider.STORAGE_BLOCKS_EMERALD)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KAMUY_NONE.location()).killCount(500)
                                .proudSoul(5000).refineCount(20).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.KAMUY_FIRE.location()).pattern("SNS")
                .pattern("ABQ").pattern("SDS").define('S', SBItems.PROUDSOUL_SPHERE).define('A', Items.FIRE_CHARGE)
                .define('Q', SlashBladeRecipeProvider.RODS_BLAZE).define('N', SlashBladeRecipeProvider.STORAGE_BLOCKS_REDSTONE)
                .define('D', Items.LAVA_BUCKET)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KAMUY_NONE.location()).killCount(500)
                                .proudSoul(5000).refineCount(20).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        // nihil
        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.NIHIL.location()).pattern("SIS")
                .pattern("IBI").pattern("SIS").define('S', SBItems.PROUDSOUL_SPHERE)
                .define('I', SBItems.PROUDSOUL_INGOT).define('B', SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.NIHILEX.location()).pattern("SNS")
                .pattern("IBI").pattern("SDS").define('S', SBItems.PROUDSOUL_SPHERE)
                .define('I', SBItems.PROUDSOUL_INGOT).define('N', Items.NETHER_STAR).define('D', Items.DIAMOND_BLOCK)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.NIHIL.location()).killCount(1000).proudSoul(100)
                                .refineCount(1).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.NIHILUL.location()).pattern("SNS")
                .pattern("DBD").pattern("SYS").define('S', SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()).toVanilla())
                .define('Y',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.YAMATO.location()).build()).toVanilla())
                .define('N', Items.NETHER_STAR).define('D', Items.DIAMOND_BLOCK)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.NIHILEX.location()).killCount(3000).proudSoul(6500)
                                .refineCount(3).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.CRIMSONCHERRY.location()).pattern("DUD")
                .pattern("DED").pattern("DDD")
                .define('E',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.NIHIL.location()).build()).toVanilla())
                .define('D', Items.DIAMOND_BLOCK)
                .define('U',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.NIHILEX.location()).killCount(3000).proudSoul(6500)
                                .refineCount(3).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.NIHILBX.location()).pattern("DDD")
                .pattern("CSU").pattern("DDD").define('S', SlashBladeIngredient.of(RequestDefinition.Builder.newInstance().build()).toVanilla()).define('D', Items.DIAMOND_BLOCK)
                .define('C',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.CRIMSONCHERRY.location()).killCount(3000)
                                .proudSoul(6500).refineCount(3).build()).toVanilla())
                .define('U',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.NIHILUL.location()).killCount(3000).proudSoul(6500)
                                .refineCount(3).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        // WA
        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.KATANA.location()).pattern("  P")
                .pattern(" B ").pattern("S  ").define('P', SBItems.PROUDSOUL_INGOT).define('S', Items.IRON_SWORD)
                .define('B',
                        SlashBladeIngredient.of(SBItems.SLASHBLADE_SILVERBAMBOO,
                                RequestDefinition.Builder.newInstance().addSwordType(SwordType.BROKEN).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE_SILVERBAMBOO), has(SBItems.SLASHBLADE_SILVERBAMBOO))
                .save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.TACHI.location()).pattern("  P")
                .pattern(" B ").pattern("S  ").define('P', SBItems.PROUDSOUL_SPHERE).define('S', Items.IRON_SWORD)
                .define('B',
                        SlashBladeIngredient.of(SBItems.SLASHBLADE_SILVERBAMBOO,
                                RequestDefinition.Builder.newInstance().addSwordType(SwordType.BROKEN).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE_SILVERBAMBOO), has(SBItems.SLASHBLADE_SILVERBAMBOO))
                .save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.BLUE.location()).pattern("BCI")
                .pattern("CI ").pattern("SL ").define('B', Items.BLUE_DYE).define('C', Items.COAL_BLOCK)
                .define('I', SBItems.PROUDSOUL_INGOT).define('S', Items.STICK).define('L', Items.STRING)
                .unlockedBy(getHasName(SBItems.PROUDSOUL_INGOT), has(SBItems.PROUDSOUL_INGOT)).save(consumer);

        // BladeMaster
        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.GREEN_MIST.location()).pattern("PRE")
                .pattern("RE ").pattern("BGC").define('P', SBItems.PROUDSOUL_SPHERE).define('R', Items.REDSTONE_BLOCK)
                .define('E', Items.EMERALD_BLOCK).define('G', Items.GOLD_BLOCK).define('C', Items.CHERRY_LEAVES)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location()).killCount(1000).proudSoul(10000)
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(
                                        BuiltInRegistries.ENCHANTMENT.getKey(Enchantments.POWER_ARROWS), 3))
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.AQUABLAZE.location()).pattern("PRW")
                .pattern("RL ").pattern("BGC").define('P', SBItems.PROUDSOUL_SPHERE).define('R', Items.REDSTONE_BLOCK)
                .define('W', Items.WATER_BUCKET).define('L', Items.LAVA_BUCKET).define('G', Items.GOLD_BLOCK)
                .define('C', Items.CHERRY_LEAVES)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location()).killCount(1000).proudSoul(10000)
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(
                                        BuiltInRegistries.ENCHANTMENT.getKey(Enchantments.FIRE_PROTECTION), 1))
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.MOONLIGHT_CHERRY.location()).pattern("PRQ")
                .pattern("RL ").pattern("BGC").define('P', SBItems.PROUDSOUL_SPHERE).define('R', Items.REDSTONE_BLOCK)
                .define('Q', Items.QUARTZ_BLOCK).define('L', Items.GLOWSTONE).define('G', Items.GOLD_BLOCK)
                .define('C', Items.CHERRY_LEAVES)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location()).killCount(1000).proudSoul(10000)
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(
                                        BuiltInRegistries.ENCHANTMENT.getKey(Enchantments.THORNS), 1))
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        // Dark Raven:Snow Crow
        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.SNOW_CROW.location()).pattern(" FQ")
                .pattern("SQ ").pattern("B  ").define('F', Items.FEATHER).define('S', Items.SNOWBALL)
                .define('Q', Items.QUARTZ_BLOCK)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.DOUTANUKI.location()).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        // Fluorescent Bar
        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.FLUORESCENT_BAR.location()).pattern(" PS")
                .pattern("PGP").pattern("SP ").define('P', Items.PAPER).define('G', Items.GLASS)
                .define('S', SBItems.PROUDSOUL).unlockedBy(getHasName(SBItems.PROUDSOUL), has(SBItems.PROUDSOUL))
                .save(consumer);

        // Frost Wolf
        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.FROSTY_CHERRY.location()).pattern(" ID")
                .pattern("SP ").pattern("BQ ").define('I', Items.ICE).define('D', Items.BLUE_DYE)
                .define('S', Items.SNOW_BLOCK).define('P', SBItems.PROUDSOUL_SPHERE).define('Q', Items.QUARTZ)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.DOUTANUKI.location()).refineCount(10)
                                .addEnchantment(new EnchantmentDefinition(
                                        BuiltInRegistries.ENCHANTMENT.getKey(Enchantments.FIRE_PROTECTION), 1))
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.FROST_WOLF.location()).pattern(" ID")
                .pattern("SP ").pattern("BQ ").define('I', Items.ICE).define('D', Items.BLUE_DYE)
                .define('S', Items.SNOW_BLOCK).define('P', SBItems.PROUDSOUL_SPHERE).define('Q', Items.QUARTZ)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location()).refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(
                                        BuiltInRegistries.ENCHANTMENT.getKey(Enchantments.FIRE_PROTECTION), 1))
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.YUKARI.location()).pattern("ISI")
                .pattern("SBS").pattern("ISI").define('I', SBItems.PROUDSOUL_INGOT)
                .define('S', SBItems.PROUDSOUL_SPHERE)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.TUKUMO.location()).killCount(1000)
                                .addEnchantment(new EnchantmentDefinition(
                                        BuiltInRegistries.ENCHANTMENT.getKey(Enchantments.FIRE_ASPECT), 1))
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.KIRISAYA.location()).pattern("DID")
                .pattern("SBS").pattern("IDI").define('D', Items.MUSIC_DISC_13).define('I', Items.GOLDEN_APPLE)
                .define('S', SBItems.PROUDSOUL_SPHERE)
                .define('B',
                        SlashBladeIngredient.of(SBItems.SLASHBLADE_SILVERBAMBOO, RequestDefinition.Builder.newInstance()
                                .proudSoul(10000).killCount(1000).refineCount(1).addSwordType(SwordType.BROKEN)
                                .addEnchantment(new EnchantmentDefinition(
                                        BuiltInRegistries.ENCHANTMENT.getKey(Enchantments.SHARPNESS), 3))
                                .build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE_SILVERBAMBOO), has(SBItems.SLASHBLADE_SILVERBAMBOO))
                .save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.LAEMMLE.location()).pattern("XGO")
                .pattern("GBG").pattern("QGX").define('G', ConventionalItemTags.GOLD_INGOTS)
                .define('X', Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.STRONG_STRENGTH)))
                .define('Q', SlashBladeRecipeProvider.STORAGE_BLOCKS_QUARTZ).define('O', SlashBladeRecipeProvider.OBSIDIAN)
                .define('B',
                        SlashBladeIngredient.of(RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location()).build()).toVanilla())
                .unlockedBy(getHasName(SBItems.SLASHBLADE), has(SBItems.SLASHBLADE)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SlashBladeAddonBuiltInRegistry.TBOEN.location()).pattern("SSS")
                .pattern("SBS").pattern("SSS").define('S', SBItems.PROUDSOUL).define('B', SBItems.SLASHBLADE_WHITE)
                .unlockedBy(getHasName(SBItems.SLASHBLADE_WHITE), has(SBItems.SLASHBLADE_WHITE)).save(consumer);

    }

    public Item getItem(ResourceLocation item) {
        return BuiltInRegistries.ITEM.get(item);
    }

}
