package cn.mmf.slashblade_addon.data;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import cn.mmf.slashblade_addon.compat.botania.SBABotaniaCompat;
import cn.mmf.slashblade_addon.registry.SBASlashArtsRegistry;
import cn.mmf.slashblade_addon.registry.SBASpecialEffectsRegistry;
import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashBladeAddonBuiltInRegistry {

    //Nihil
    public static final ResourceKey<SlashBladeDefinition> NIHIL = register("nihil");
    public static final ResourceKey<SlashBladeDefinition> NIHILEX = register("nihilex");
    public static final ResourceKey<SlashBladeDefinition> NIHILBX = register("nihilbx");
    public static final ResourceKey<SlashBladeDefinition> NIHILUL = register("nihilul");
    public static final ResourceKey<SlashBladeDefinition> CRIMSONCHERRY = register("crimsoncherry");

    //WA
    public static final ResourceKey<SlashBladeDefinition> KATANA = register("katana");
    public static final ResourceKey<SlashBladeDefinition> TACHI = register("tachi");
    public static final ResourceKey<SlashBladeDefinition> BLUE = register("blue");

    //BladeMaster
    public static final ResourceKey<SlashBladeDefinition> GREEN_MIST = register("green_mist");
    public static final ResourceKey<SlashBladeDefinition> AQUABLAZE = register("aquablaze");
    public static final ResourceKey<SlashBladeDefinition> MOONLIGHT_CHERRY = register("moonlight_cherry");

    //Dark Raven
    public static final ResourceKey<SlashBladeDefinition> DARK_RAVEN = register("dark_raven");
    public static final ResourceKey<SlashBladeDefinition> SNOW_CROW = register("snow_crow");

    //Fluorescent Bar
    public static final ResourceKey<SlashBladeDefinition> FLUORESCENT_BAR = register("fluorescent_bar");

    //Frost Wolf
    public static final ResourceKey<SlashBladeDefinition> FROSTY_CHERRY = register("frosty_cherry");
    public static final ResourceKey<SlashBladeDefinition> FROST_WOLF = register("frost_wolf");

    //Toyako
    public static final ResourceKey<SlashBladeDefinition> TOYAKO = register("toyako");

    //Yukari
    public static final ResourceKey<SlashBladeDefinition> YUKARI = register("yukari");

    //Laemmle
    public static final ResourceKey<SlashBladeDefinition> LAEMMLE = register("laemmle");

    //Tboen
    public static final ResourceKey<SlashBladeDefinition> TBOEN = register("tboen");

    public static final ResourceKey<SlashBladeDefinition> KIRISAYA = register("kirisaya");

    public static final ResourceKey<SlashBladeDefinition> TERRA_BLADE = register("terra_blade");

    //Laemmle
    public static final ResourceKey<SlashBladeDefinition> KAMUY_NONE = register("kamuy_none");

    public static final ResourceKey<SlashBladeDefinition> KAMUY_WATER = register("kamuy_water");

    public static final ResourceKey<SlashBladeDefinition> KAMUY_FIRE = register("kamuy_fire");

    public static final ResourceKey<SlashBladeDefinition> KAMUY_LIGHTING = register("kamuy_lightning");

    public static final ResourceKey<SlashBladeDefinition> HF_MURASAMA = register("hf_murasama");

    //wanderer
    public static final ResourceKey<SlashBladeDefinition> WANDERER = register("wanderer");
    public static final ResourceKey<SlashBladeDefinition> WANDERER_HF = register("wanderer_hf");

    public static final ResourceKey<SlashBladeDefinition> MURAKUMO = register("murakumo");


    private static final Map<ResourceKey<SlashBladeDefinition>, List<ConditionJsonProvider>> CONDITIONS = new HashMap<>();

    public static Map<ResourceKey<SlashBladeDefinition>, List<ConditionJsonProvider>> getConditions() {
        return CONDITIONS;
    }

    public static void addConditions(ResourceKey<SlashBladeDefinition> key, ConditionJsonProvider... conditions) {
        CONDITIONS.computeIfAbsent(key, k -> List.of(conditions));
    }

    public static class SJAPDefinition extends SlashBladeDefinition {
        public SJAPDefinition(ResourceLocation name, RenderDefinition renderDefinition, PropertiesDefinition stateDefinition, List<EnchantmentDefinition> enchantments) {
            super(name, renderDefinition, stateDefinition, enchantments, BuiltInRegistries.CREATIVE_MODE_TAB.getKey(SlashBladeAddon.SJAP_TAB));
        }
    }

    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {

        //MURAKUMO
        bootstrap.register(MURAKUMO,
                new SJAPDefinition(SlashBladeAddon.prefix("murakumo"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/murakumo/texture.png"))
                                .modelName(SlashBladeAddon.prefix("model/murakumo/model.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(9.0F)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.SAKURA_END))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .maxDamage(80).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 1))));

        // TODO: EnergyBlade(HF Blade) Fabric移植
//		bootstrap.register(HF_MURASAMA, new SJAPDefinition(Energyblade.FORGE_ENERGY_BLADE,
//				SlashBladeAddon.prefix("hf_murasama"),
//				RenderDefinition.Builder.newInstance()
//						.textureName(SlashBladeAddon.prefix("model/murasama/murasama.png"))
//						.modelName(SlashBladeAddon.prefix("model/murasama/murasama.obj"))
//						.effectColor(0xFFFF2600)
//						.standbyRenderType(CarryType.PSO2).build(),
//				PropertiesDefinition.Builder.newInstance().baseAttackModifier(8.0F).maxDamage(250)
//						.defaultSwordType(List.of(SwordType.BEWITCHED))
//						.slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.SPIRAL_EDGE)).build(),
//				List.of()));
//        bootstrap.register(WANDERER_HF, new SJAPDefinition(Energyblade.FORGE_ENERGY_BLADE,
//                SlashBladeAddon.prefix("wanderer_hf"),
//                RenderDefinition.Builder.newInstance()
//                        .textureName(SlashBladeAddon.prefix("model/wanderer/wanderer.png"))
//                        .modelName(SlashBladeAddon.prefix("model/wanderer/wanderer.obj"))
//                        .standbyRenderType(CarryType.NINJA).build(),
//                PropertiesDefinition.Builder.newInstance().baseAttackModifier(7.0F).maxDamage(70)
//                        .defaultSwordType(List.of(SwordType.BEWITCHED))
//                        .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.RAPID_BLISTERING_SWORDS)).build(),
//                List.of()));
//        addConditions(WANDERER_HF, DefaultResourceConditions.itemsRegistered(Energyblade.FORGE_ENERGY_BLADE));
//        addConditions(HF_MURASAMA, DefaultResourceConditions.itemsRegistered(Energyblade.FORGE_ENERGY_BLADE));

        bootstrap.register(WANDERER, new SJAPDefinition(
                SlashBladeAddon.prefix("wanderer"),
                RenderDefinition.Builder.newInstance()
                        .textureName(SlashBladeAddon.prefix("model/wanderer/wanderer.png"))
                        .modelName(SlashBladeAddon.prefix("model/wanderer/wanderer.obj"))
                        .standbyRenderType(CarryType.NINJA).build(),
                PropertiesDefinition.Builder.newInstance().baseAttackModifier(7.0F).maxDamage(60)
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.RAPID_BLISTERING_SWORDS)).build(),
                List.of()));

        bootstrap.register(KAMUY_NONE,
                new SJAPDefinition(SlashBladeAddon.prefix("kamuy_none"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/kamuy/kamuy.png"))
                                .modelName(SlashBladeAddon.prefix("model/kamuy/kamuy.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7.0F)
                                .maxDamage(45)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.CIRCLE_SLASH))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 1)))
        );

        bootstrap.register(KAMUY_WATER,
                new SJAPDefinition(SlashBladeAddon.prefix("kamuy_water"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/kamuy/water.png"))
                                .modelName(SlashBladeAddon.prefix("model/kamuy/kamuy.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7.0F)
                                .maxDamage(50)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.WATER_DRIVE))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_PROTECTION), 1)))
        );

        bootstrap.register(KAMUY_LIGHTING,
                new SJAPDefinition(SlashBladeAddon.prefix("kamuy_lightning"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/kamuy/lightning.png"))
                                .modelName(SlashBladeAddon.prefix("model/kamuy/kamuy.obj"))
                                .effectColor(0xFFFAE900)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7.0F)
                                .maxDamage(50)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.LIGHTING_SWORDS))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 1)))
        );

        bootstrap.register(KAMUY_FIRE,
                new SJAPDefinition(SlashBladeAddon.prefix("kamuy_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/kamuy/fire.png"))
                                .modelName(SlashBladeAddon.prefix("model/kamuy/kamuy.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .effectColor(0xFFEE2600)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7.0F)
                                .maxDamage(40)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.FIRE_SPIRAL))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_PROTECTION), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1)))
        );

        bootstrap.register(TERRA_BLADE,
                new SJAPDefinition(SlashBladeAddon.prefix("terra_blade"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/terra/terra.png"))
                                .modelName(SlashBladeAddon.prefix("model/terra/terra.obj"))
                                .standbyRenderType(CarryType.RNINJA)
                                .effectColor(3524113)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F)
                                .maxDamage(60)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBABotaniaCompat.BLISTERING_TERRA_SWORDS_SA))
                                .addSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(SBABotaniaCompat.MANA_BURST))
                                .addSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(SBABotaniaCompat.MANA_REPAIR))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 1))));

        addConditions(TERRA_BLADE, DefaultResourceConditions.allModsLoaded("botania"));

        //Kirisaya
        bootstrap.register(KIRISAYA,
                new SJAPDefinition(SlashBladeAddon.prefix("kirisaya"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/kirisaya/kirisaya.png"))
                                .modelName(SlashBladeAddon.prefix("model/kirisaya/kirisaya.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(3.0F)
                                .maxDamage(11)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.DRIVE_HORIZONTAL))
                                .addSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(SBASpecialEffectsRegistry.BURST_DRIVE))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5)
                        )));

        //Laemmle
        bootstrap.register(LAEMMLE,
                new SJAPDefinition(SlashBladeAddon.prefix("laemmle"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/laemmle/lem.png"))
                                .modelName(SlashBladeAddon.prefix("model/laemmle/blade.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7.0F)
                                .maxDamage(80).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3))));

        //Yukari
        bootstrap.register(YUKARI,
                new SJAPDefinition(SlashBladeAddon.prefix("yukari"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/yukari/texture.png"))
                                .modelName(SlashBladeAddon.prefix("model/yukari/model.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8.0F)
                                .maxDamage(50)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.DRIVE_HORIZONTAL))
                                .defaultSwordType(List.of(SwordType.NONE)).build(), List.of()));

        //Tboen
        bootstrap.register(TBOEN,
                new SJAPDefinition(SlashBladeAddon.prefix("tboen"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/tboen/texture.png"))
                                .modelName(SlashBladeAddon.prefix("model/tboen/model.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.SAKURA_END))
                                .maxDamage(70)
                                .defaultSwordType(List.of(SwordType.NONE)).build(), List.of()));

        //Toyako
        bootstrap.register(TOYAKO,
                new SJAPDefinition(SlashBladeAddon.prefix("toyako"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/toyako/toyako.png"))
                                .modelName(SlashBladeAddon.prefix("model/toyako/toyako.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(6.0F)
                                .maxDamage(70)
                                .defaultSwordType(List.of(SwordType.NONE)).build(), List.of()));

        //nihil
        bootstrap.register(NIHIL,
                new SJAPDefinition(SlashBladeAddon.prefix("nihil"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/nihil/nihil.png"))
                                .modelName(SlashBladeAddon.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(8.0F)
                                .maxDamage(45)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.DRIVE_HORIZONTAL))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1))));

        bootstrap.register(NIHILEX,
                new SJAPDefinition(SlashBladeAddon.prefix("nihilex"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/nihil/nihilex.png"))
                                .modelName(SlashBladeAddon.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10.0F)
                                .maxDamage(60)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.WAVE_EDGE))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 1))));

        bootstrap.register(NIHILUL,
                new SJAPDefinition(SlashBladeAddon.prefix("nihilul"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/nihil/nihilul.png"))
                                .modelName(SlashBladeAddon.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12.0F)
                                .maxDamage(70)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.WAVE_EDGE))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3))));

        bootstrap.register(CRIMSONCHERRY,
                new SJAPDefinition(SlashBladeAddon.prefix("crimsoncherry"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/nihil/crimsoncherry.png"))
                                .modelName(SlashBladeAddon.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(11.0F)
                                .maxDamage(65)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.SAKURA_END))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2))));

        bootstrap.register(NIHILBX,
                new SJAPDefinition(SlashBladeAddon.prefix("nihilbx"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/nihil/nihil_bx.png"))
                                .modelName(SlashBladeAddon.prefix("model/nihil/nihil.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(13.0F)
                                .maxDamage(240)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.SAKURA_END))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2))));

        //WA
        bootstrap.register(KATANA,
                new SJAPDefinition(SlashBladeAddon.prefix("katana"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/wa/katana.png"))
                                .modelName(SlashBladeAddon.prefix("model/wa/wa.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(3.0F)
                                .maxDamage(457)
                                .defaultSwordType(List.of(SwordType.NONE)).build(), List.of()));

        bootstrap.register(TACHI,
                new SJAPDefinition(SlashBladeAddon.prefix("tachi"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/wa/tachi.png"))
                                .modelName(SlashBladeAddon.prefix("model/wa/wa.obj"))
                                .standbyRenderType(CarryType.DEFAULT)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(3.0F)
                                .maxDamage(457)
                                .defaultSwordType(List.of(SwordType.NONE)).build(), List.of()));

        bootstrap.register(BLUE,
                new SJAPDefinition(SlashBladeAddon.prefix("blue"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/wa/blue.png"))
                                .modelName(SlashBladeAddon.prefix("model/wa/wa.obj"))
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7.0F)
                                .maxDamage(75)
                                .defaultSwordType(List.of(SwordType.NONE)).build(), List.of()));

        //BladeMaster
        bootstrap.register(GREEN_MIST,
                new SJAPDefinition(SlashBladeAddon.prefix("green_mist"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(SlashBladeAddon.prefix("model/blademaster/green_mist.png"))
                                .modelName(SlashBladeAddon.prefix("model/blademaster/blademaster.obj"))
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(4.0F + Tiers.DIAMOND.getAttackDamageBonus())
                                .maxDamage(60)
                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.RAPID_BLISTERING_SWORDS))
                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 3))));

        bootstrap.register
                (
                        AQUABLAZE,
                        new SJAPDefinition
                                (
                                        SlashBladeAddon.prefix("aquablaze"),
                                        RenderDefinition.Builder.newInstance()
                                                .textureName(SlashBladeAddon.prefix("model/blademaster/aquablaze.png"))
                                                .modelName(SlashBladeAddon.prefix("model/blademaster/blademaster.obj"))
                                                .standbyRenderType(CarryType.PSO2)
                                                .build(),
                                        PropertiesDefinition.Builder.newInstance()
                                                .baseAttackModifier(4.0F + Tiers.DIAMOND.getAttackDamageBonus())
                                                .maxDamage(60)
                                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.SPIRAL_EDGE))
                                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                                        List.of
                                                (
                                                        new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2),
                                                        new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_PROTECTION), 1)
                                                )
                                )
                );

        bootstrap.register
                (
                        MOONLIGHT_CHERRY,
                        new SJAPDefinition
                                (
                                        SlashBladeAddon.prefix("moonlight_cherry"),
                                        RenderDefinition.Builder.newInstance()
                                                .textureName(SlashBladeAddon.prefix("model/blademaster/moonlightcherry.png"))
                                                .modelName(SlashBladeAddon.prefix("model/blademaster/blademaster.obj"))
                                                .standbyRenderType(CarryType.PSO2)
                                                .build(),
                                        PropertiesDefinition.Builder.newInstance()
                                                .baseAttackModifier(4.0F + Tiers.DIAMOND.getAttackDamageBonus())
                                                .maxDamage(60)
                                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.GALE_SWORDS))
                                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                                        List.of
                                                (
                                                        new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 1),
                                                        new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5)
                                                )
                                )
                );

        //Dark Raven
        bootstrap.register
                (
                        DARK_RAVEN,
                        new SJAPDefinition
                                (
                                        SlashBladeAddon.prefix("dark_raven"),
                                        RenderDefinition.Builder.newInstance()
                                                .textureName(SlashBladeAddon.prefix("model/darkraven/dark_raven.png"))
                                                .modelName(SlashBladeAddon.prefix("model/darkraven/dark_raven.obj"))
                                                .standbyRenderType(CarryType.PSO2)
                                                .build(),
                                        PropertiesDefinition.Builder.newInstance()
                                                .baseAttackModifier(4.0F + Tiers.DIAMOND.getAttackDamageBonus())
                                                .maxDamage(80)
                                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SlashArtsRegistry.WAVE_EDGE))
                                                .defaultSwordType(List.of(SwordType.NONE)).build(),
                                        List.of()
                                )
                );

        bootstrap.register
                (
                        SNOW_CROW,
                        new SJAPDefinition
                                (
                                        SlashBladeAddon.prefix("snow_crow"),
                                        RenderDefinition.Builder.newInstance()
                                                .textureName(SlashBladeAddon.prefix("model/darkraven/snow_crow.png"))
                                                .modelName(SlashBladeAddon.prefix("model/darkraven/snow_crow.obj"))
                                                .standbyRenderType(CarryType.PSO2)
                                                .build(),
                                        PropertiesDefinition.Builder.newInstance()
                                                .baseAttackModifier(4.0F + Tiers.IRON.getAttackDamageBonus())
                                                .maxDamage(60)
                                                .defaultSwordType(List.of(SwordType.NONE)).build(),
                                        List.of()
                                )
                );

        //Fluorescent Bar
        bootstrap.register
                (
                        FLUORESCENT_BAR,
                        new SJAPDefinition
                                (
                                        SlashBladeAddon.prefix("fluorescent_bar"),
                                        RenderDefinition.Builder.newInstance()
                                                .textureName(SlashBladeAddon.prefix("model/fluorescentbar/fluorescent_bar.png"))
                                                .modelName(SlashBladeAddon.prefix("model/fluorescentbar/fluorescent_bar.obj"))
                                                .standbyRenderType(CarryType.PSO2)
                                                .build(),
                                        PropertiesDefinition.Builder.newInstance()
                                                .baseAttackModifier(2.0F)
                                                .maxDamage(Tiers.DIAMOND.getUses())
                                                .defaultSwordType(List.of(SwordType.NONE)).build(),
                                        List.of
                                                (
                                                        new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                                                )
                                )
                );

        //Frost Wolf
        bootstrap.register
                (
                        FROSTY_CHERRY,
                        new SJAPDefinition
                                (
                                        SlashBladeAddon.prefix("frosty_cherry"),
                                        RenderDefinition.Builder.newInstance()
                                                .textureName(SlashBladeAddon.prefix("model/frostwolf/frosty_cherry.png"))
                                                .modelName(SlashBladeAddon.prefix("model/frostwolf/frosty_cherry.obj"))
                                                .standbyRenderType(CarryType.PSO2)
                                                .build(),
                                        PropertiesDefinition.Builder.newInstance()
                                                .baseAttackModifier(4.0F + Tiers.DIAMOND.getAttackDamageBonus())
                                                .maxDamage(150)
                                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.RAPID_BLISTERING_SWORDS))
                                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                                        List.of()
                                )
                );

        bootstrap.register
                (
                        FROST_WOLF,
                        new SJAPDefinition
                                (
                                        SlashBladeAddon.prefix("frost_wolf"),
                                        RenderDefinition.Builder.newInstance()
                                                .textureName(SlashBladeAddon.prefix("model/frostwolf/frost_wolf.png"))
                                                .modelName(SlashBladeAddon.prefix("model/frostwolf/frost_wolf.obj"))
                                                .standbyRenderType(CarryType.PSO2)
                                                .build(),
                                        PropertiesDefinition.Builder.newInstance()
                                                .baseAttackModifier(4.0F + Tiers.DIAMOND.getAttackDamageBonus())
                                                .maxDamage(150)
                                                .slashArtsType(SlashArtsRegistry.SLASH_ARTS.getKey(SBASlashArtsRegistry.GALE_SWORDS))
                                                .defaultSwordType(List.of(SwordType.BEWITCHED)).build(),
                                        List.of()
                                )
                );

    }

    private static ResourceKey<SlashBladeDefinition> register(String id) {

        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, SlashBladeAddon.prefix(id));
    }

    private static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
    }
}
