package com.yakumosakura.yakumoblade.data;

import cn.mmf.slashblade_addon.registry.SBASlashArtsRegistry;
import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.registry.creativetab.ItemTab;
import com.yakumosakura.yakumoblade.registry.slashblade.YAItem;
import com.yakumosakura.yakumoblade.registry.slashblade.YASlashArtRegistry;
import com.yakumosakura.yakumoblade.registry.slashblade.YASpecialEffectsRegistry;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;


public class YakumoBladeBuiltInRegsitryStar {
    //炽狐星
    public static final ResourceKey<SlashBladeDefinition> RED_FOX_STAR = register("red_fox_star");
    //炽狐神录「天烈剑誓」
    public static final ResourceKey<SlashBladeDefinition> RED_FOX_STAR_SOUL = register("red_fox_star_soul");
    //天墟星
    public static final ResourceKey<SlashBladeDefinition> sky_ruine = register("sky_ruine");
    //寒狐神录「本质宣判」
    public static final ResourceKey<SlashBladeDefinition> BLUE_FOX_STAR_SOUL = register("blue_fox_star_soul");
    //黑耀神录「极致誓约」
    public static final ResourceKey<SlashBladeDefinition> PURPLE_FOX_STAR_SOUL = register("purple_fox_star_soul");
    //终焉神录「永劫轮回」
    public static final ResourceKey<SlashBladeDefinition> FINAL_STAR_SOUL = register("final_star_soul");
    //赤耀神录「红之阳」
    public static final ResourceKey<SlashBladeDefinition> sun_rise = register("sun_rise");
    //丁泽尔
    public static final ResourceKey<SlashBladeDefinition> DINZEER = register("star_of_dinzeer");
    //黑斩星
    public static final ResourceKey<SlashBladeDefinition> BLACK_SLASH = register("black_slash");
    //蓝狐
    public static final ResourceKey<SlashBladeDefinition> BLUE_FOX = register("blue_fox");
    //极日刀
    public static final ResourceKey<SlashBladeDefinition> RED_SUN = register("red_sun");
    //天元刀
    public static final ResourceKey<SlashBladeDefinition> STAR_M = register("star_muasum");
    //天红星
    public static final ResourceKey<SlashBladeDefinition> STAR_RED = register("starsky_red");
    //夜魂星
    public static final ResourceKey<SlashBladeDefinition> YE_SOUL = register("ye_soul");

    //绿染神录「星魂大和」
    public static final ResourceKey<SlashBladeDefinition> GREEN_INex = register("green_inflammation_star");
    //暗星神录「生灵死寂」
    public static final ResourceKey<SlashBladeDefinition> hundred_end = register("hundred_end");
    //极星神录「曙光黑斩」
    public static final ResourceKey<SlashBladeDefinition> blackeslashex = register("blackeslashex");
    //三魂神录「史达之名」
    public static final ResourceKey<SlashBladeDefinition> starstr = register("starstr");
    //三色星辰
    public static final ResourceKey<SlashBladeDefinition> STAR = register("star");

    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {


        bootstrap.register(RED_FOX_STAR,
                register(Yakumoblade.prefix("red_fox_star"),
                        RenderDefinition.Builder.newInstance()
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .textureName(Yakumoblade.prefix("model/star/red_fox_star.png"))
                                .effectColor(16711680)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.FIRE_BOOST))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.Foxsoul))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .baseAttackModifier(20F)
                                .maxDamage(200)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5)
                        )));

        bootstrap.register(RED_FOX_STAR_SOUL,
                register(
                        Yakumoblade.prefix("red_fox_star_soul"),
                        RenderDefinition.Builder.newInstance()
                                .modelName(Yakumoblade.prefix("model/named/sange/yakumosakurafinal.obj"))
                                .textureName(Yakumoblade.prefix("model/star/red_fox_star.png"))
                                .effectColor(16711680)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.FIRE_BOOST2))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.FoxsoulEX))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .baseAttackModifier(30F)
                                .maxDamage(200)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10)
                        )
                )
        );

        bootstrap.register(sky_ruine,
                register(Yakumoblade.prefix("sky_ruine"),
                        RenderDefinition.Builder.newInstance()
                                .modelName(Yakumoblade.prefix("model/star/model/sky_ruine.obj"))
                                .textureName(Yakumoblade.prefix("model/star/sky_ruine.png"))
                                .effectColor(3071)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.SwordRainlLightning))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ThunderSoul))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.LostHeart))

                                .baseAttackModifier(20F)
                                .maxDamage(200)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5)
                        )));
        bootstrap.register(BLUE_FOX_STAR_SOUL,
                register(Yakumoblade.prefix("blue_fox_star_soul"),
                        RenderDefinition.Builder.newInstance()
                                .modelName(Yakumoblade.prefix("model/named/sange/yakumosakurafinal.obj"))
                                .textureName(Yakumoblade.prefix("model/star/blue_fox_star_soul.png"))
                                .effectColor(16895)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.SelfNoall))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.FoxSoulBlue))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .baseAttackModifier(30F)
                                .maxDamage(200)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10)
                        )
                )
        );
        bootstrap.register(PURPLE_FOX_STAR_SOUL,
                register(Yakumoblade.prefix("purple_fox_star_soul"),
                        RenderDefinition.Builder.newInstance()
                                .modelName(Yakumoblade.prefix("model/named/sange/yakumosakurafinal.obj"))
                                .textureName(Yakumoblade.prefix("model/star/purple_fox_star_soul.png"))
                                .effectColor(6881535)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.SelfNoall_FIRE_BOOST2))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ObsdianFoxSoul))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .baseAttackModifier(40F)
                                .maxDamage(200)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10)
                        )
                )
        );
        bootstrap.register(FINAL_STAR_SOUL,
                register(Yakumoblade.prefix("final_star_soul"),
                        RenderDefinition.Builder.newInstance()
                                .modelName(Yakumoblade.prefix("model/star/model/dark_all_slash.obj"))
                                .textureName(Yakumoblade.prefix("model/star/dark_all_slash.png"))
                                .effectColor(6881535)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.FinalAllMake))
                                .slashArtsType(getId(YASlashArtRegistry.Reincarnationheaven))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .baseAttackModifier(40F)
                                .maxDamage(200)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10)
                        )
                )
        );

        bootstrap.register(sun_rise,
                register(Yakumoblade.prefix("sun_rise"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofpaseone/red_sun.png"))
                                .modelName(Yakumoblade.prefix("model/relicofstar/sun/sun_god.obj"))
                                .effectColor(12013391)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.TEN_DRIVE))
                                .baseAttackModifier(32)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.TEN_DRIVEEX))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.GreatSunSoul))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ExMode))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10)
                        )));
        bootstrap.register(DINZEER,
                register(Yakumoblade.prefix("star_of_dinzeer"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/maker_sword/star_of_dinzeer.png"))
                                .modelName(Yakumoblade.prefix("model/maker_sword/dark_raven.obj"))
                                .effectColor(6837744)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.thoo))
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.StarDriveEdges))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SwordArtOnCrow))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 6)
                        )));
        bootstrap.register(STAR_M,
                register(Yakumoblade.prefix("star_muasum"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/murasama/z.png"))
                                .modelName(Yakumoblade.prefix("model/named/murasama/murasama.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .slashArtsType(getId(SBASlashArtsRegistry.RAPID_BLISTERING_SWORDS))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ExMode))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .slashArtsType(getId(YASlashArtRegistry.HIGHSLASH))

                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));

        bootstrap.register(STAR_RED,
                register(Yakumoblade.prefix("starsky_red"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/murasama/redsky_star.png"))
                                .modelName(Yakumoblade.prefix("model/named/murasama/murasama.obj"))
                                .effectColor(12013391)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.highfrequency))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 6))));

        bootstrap.register(YE_SOUL,
                register(Yakumoblade.prefix("ye_soul"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/yasha/ye_soul.png"))
                                .modelName(Yakumoblade.prefix("model/named/yasha/yasha_true.obj"))
                                .effectColor(3091266)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(17)
                                .maxDamage(180)
                                .slashArtsType(getId(SlashArtsRegistry.CIRCLE_SLASH))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.VERY_BLUE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));


        bootstrap.register(GREEN_INex,
                register(Yakumoblade.prefix("green_inflammation_star"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofstar/green_inflammation_star.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/green_inflammation.obj"))
                                .effectColor(3729097)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(30)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.GigantjudgementCut))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.Greenstarsoul))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 15)
                        )));
        bootstrap.register(hundred_end,
                register(Yakumoblade.prefix("hundred_end"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/dios/black_slashs.png"))
                                .modelName(Yakumoblade.prefix("model/relicofstar/diosex.obj"))
                                .effectColor(16722600)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(39)
                                .maxDamage(180)
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.blackslashbreak))
                                .addSpecialEffect(getId(SpecialEffectsRegistry.WITHER_EDGE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .slashArtsType(getId(YASlashArtRegistry.thoo))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)
                        )));
        bootstrap.register(RED_SUN,
                register(Yakumoblade.prefix("red_sun"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofpaseone/red_sun.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/ru_sang.obj"))
                                .effectColor(12013391)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.TEN_DRIVE))
                                .baseAttackModifier(17)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SUN_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(BLUE_FOX,
                register(Yakumoblade.prefix("blue_fox"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/blue.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(24)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.judgementCut))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(blackeslashex,
                register(Yakumoblade.prefix("blackeslashex"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/dios/black_slashs.png"))
                                .modelName(Yakumoblade.prefix("model/relicofstar/diosexstar.obj"))
                                .effectColor(10748159)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.thoo))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.StarDriveEdges))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .addSpecialEffect(getId(SpecialEffectsRegistry.WITHER_EDGE))
                                .baseAttackModifier(25)
                                .maxDamage(120)
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10)
                        )));

        bootstrap.register(starstr,
                register(Yakumoblade.prefix("starstr"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofstar/starstr.png"))
                                .modelName(Yakumoblade.prefix("model/relicofstar/starstr.obj"))
                                .effectColor(7935)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()

                                .baseAttackModifier(50)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.StarSname))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ETERNAL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .slashArtsType(getId(YASlashArtRegistry.HIGHSLASH))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 10)
                        )));
        bootstrap.register(BLACK_SLASH,
                register(Yakumoblade.prefix("black_slash"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/dios/black_slashs.png"))
                                .modelName(Yakumoblade.prefix("model/named/dios/dios.obj"))
                                .effectColor(1)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.WSS))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(SpecialEffectsRegistry.WITHER_EDGE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .maxDamage(120)
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )));
        bootstrap.register(STAR,
                register(Yakumoblade.prefix("star"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/other/color_cs.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/dahe.obj"))
                                .effectColor(10492369)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.judgementCutLow))
                                .maxDamage(80)
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5))));

    }

    private static SlashBladeDefinition register(ResourceLocation id,
                                                 RenderDefinition renderDefinition,
                                                 PropertiesDefinition stateDefinition,
                                                 List<EnchantmentDefinition> enchantments) {
        return new SlashBladeDefinition(
                YAItem.SlashBladeOfYakumoBlade,
                id,
                renderDefinition,
                stateDefinition,
                enchantments,
                BuiltInRegistries.CREATIVE_MODE_TAB.getKey(ItemTab.YASlashbladeStar));
    }


    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, Yakumoblade.prefix(id));
    }

    private static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
    }

    private static ResourceLocation getId(SpecialEffect effect) {
        return SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(effect);
    }

    private static ResourceLocation getId(SlashArts slashArts) {
        return SlashArtsRegistry.SLASH_ARTS.getKey(slashArts);
    }
}
