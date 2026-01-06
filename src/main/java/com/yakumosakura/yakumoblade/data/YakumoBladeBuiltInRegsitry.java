package com.yakumosakura.yakumoblade.data;

import com.yakumosakura.yakumoblade.Yakumoblade;
import com.yakumosakura.yakumoblade.compat.YATouHouMaidItem;
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
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class YakumoBladeBuiltInRegsitry {

    //真yamato
    public static final ResourceKey<SlashBladeDefinition> VERGIL = register("vergil");
    //双重大和
    public static final ResourceKey<SlashBladeDefinition> DOUBLE_YAMATO = register("double_yamato");
    //绿染大和
    public static final ResourceKey<SlashBladeDefinition> GREEN_IN = register("green_inflammation");
    //大和
    public static final ResourceKey<SlashBladeDefinition> GREEN_YAMATO = register("green_yamato");
    //百泪
    public static final ResourceKey<SlashBladeDefinition> HUNDRED = register("hundred_curses");
    //如丧
    public static final ResourceKey<SlashBladeDefinition> RU_SANG = register("ru_sang");
    //不死斩
    public static final ResourceKey<SlashBladeDefinition> UNDEAD_SLASH = register("undead_slash");
    //魅影刃
    public static final ResourceKey<SlashBladeDefinition> mirageedge = register("mirageedge");

    //名刀「酒狐」
    public static final ResourceKey<SlashBladeDefinition> yellowfox = register("yellow_fox");

    public static void registerAll(BootstrapContext<SlashBladeDefinition> bootstrap) {

        bootstrap.register(VERGIL,
                register(Yakumoblade.prefix("vergil"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/custom/vergil/texture.png"))
                                .modelName(Yakumoblade.prefix("model/named/custom/vergil/model.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.randomjudgementCut))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SwordArtOnDMC))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.LostHeart))
                                .baseAttackModifier(50)
                                .maxDamage(120)
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FEATHER_FALLING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10)
                        )));


        bootstrap.register(DOUBLE_YAMATO,
                register(Yakumoblade.prefix("double_yamato"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofpaseone/yamato.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/double.obj"))
                                .effectColor(3729097)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(22)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.VERY_GREEN))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.LOOTING), 3)
                        )));

        bootstrap.register(GREEN_IN,
                register(Yakumoblade.prefix("green_inflammation"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofpaseone/green_inflammation.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/green_inflammation.obj"))
                                .effectColor(2424576)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(22)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.VERY_GREEN))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.LOOTING), 10)
                        )));
        bootstrap.register(GREEN_YAMATO,
                register(Yakumoblade.prefix("green_yamato"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofpaseone/yamato.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/dahe.obj"))
                                .effectColor(3729097)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(10)
                                .maxDamage(180)
                                .build(),
                        List.of()));

        bootstrap.register(HUNDRED,
                register(Yakumoblade.prefix("hundred_curses"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofpaseone/hundred_curses.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/hundred_curses.obj"))
                                .effectColor(8126719)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(SlashArtsRegistry.WAVE_EDGE))
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .addSpecialEffect(getId(SpecialEffectsRegistry.WITHER_EDGE))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 6)
                        )));

        bootstrap.register(RU_SANG,
                register(Yakumoblade.prefix("ru_sang"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/relicofpaseone/ru_sang.png"))
                                .modelName(Yakumoblade.prefix("model/relicofpaseone/ru_sang.obj"))
                                .effectColor(12013391)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(13)
                                .maxDamage(180)

                                .build(),
                        List.of()));

        bootstrap.register(UNDEAD_SLASH,
                register(Yakumoblade.prefix("undead_slash"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/awolf/undead_slash.jpg"))
                                .modelName(Yakumoblade.prefix("model/awolf/undead_slash.obj"))
                                .effectColor(13504014)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.WASC))
                                .baseAttackModifier(25)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.LostHeart))
                                .addSpecialEffect(getId(SpecialEffectsRegistry.WITHER_EDGE))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.PUNCH), 3)
                        )));


        bootstrap.register(mirageedge,
                register(Yakumoblade.prefix("mirageedge"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/custom/vergil/mirageedge.png"))
                                .modelName(Yakumoblade.prefix("model/named/custom/vergil/mirageedge.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.THO2))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ProudSoulDrive))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.LostHeart))
                                .addSpecialEffect(Yakumoblade.lbprefix("eternal"))
                                .baseAttackModifier(10)
                                .maxDamage(120)
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 10)
                        )));


        bootstrap.register(yellowfox, new SlashBladeDefinition(
                YATouHouMaidItem.YELLOW_FOX,
                Yakumoblade.prefix("yellowfox"),
                RenderDefinition.Builder.newInstance()
                        .effectColor(16754944)
                        .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                        .textureName(Yakumoblade.prefix("model/named/sange/yellow_fox.png"))
                        .build(),
                PropertiesDefinition.Builder.newInstance()
                        .baseAttackModifier(25F)
                        .addSpecialEffect(getId(YASpecialEffectsRegistry.FoxBlade))
                        .maxDamage(180)
                        .defaultSwordType(List.of(SwordType.BEWITCHED))
                        .build(),
                List.of(
                        new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 6),
                        new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                        new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                        new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 3)
                ), BuiltInRegistries.CREATIVE_MODE_TAB.getKey(ItemTab.YASlashblade))
        );
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
                BuiltInRegistries.CREATIVE_MODE_TAB.getKey(ItemTab.YASlashblade));
    }

    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, Yakumoblade.prefix(id));
    }

    private static ResourceLocation getEnchantmentID(ResourceKey<Enchantment> key) {
        return key.location();
    }

    private static ResourceLocation getId(SpecialEffect effect) {
        return SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(effect);
    }

    private static ResourceLocation getId(SlashArts slashArts) {
        return SlashArtsRegistry.SLASH_ARTS.getKey(slashArts);
    }
}
