package com.yakumosakura.yakumoblade.data;

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

public class YakumoBladeBuiltInRegsitryHexGram {

    //火龙
    public static final ResourceKey<SlashBladeDefinition> FIRE_DRAGON = register("fire_dragon");
    //冰龙
    public static final ResourceKey<SlashBladeDefinition> ICE_DRAGON = register("ice_dragon");
    //五芒星
    public static final ResourceKey<SlashBladeDefinition> FIVE = register("five_hexgram");
    //七芒星
    public static final ResourceKey<SlashBladeDefinition> SEVEN = register("seven_hexgram");
    //六芒星
    public static final ResourceKey<SlashBladeDefinition> SIX = register("six_hexgram");
    //十芒星
    public static final ResourceKey<SlashBladeDefinition> TEN = register("ten_hexgram");
    //妖刀六芒星
    public static final ResourceKey<SlashBladeDefinition> hexagram_six = register("godbladehexagram");
    //神六芒星
    public static final ResourceKey<SlashBladeDefinition> HEXAGRAM = register("hexagram");
    //仙狐之刃「六芒星」
    public static final ResourceKey<SlashBladeDefinition> foxhexagram = register("foxhexagram");
    //神龙之刃「六芒星」
    public static final ResourceKey<SlashBladeDefinition> dragonhexagram = register("dragonhexagram");

    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {

        bootstrap.register(FIRE_DRAGON,
                register(Yakumoblade.prefix("fire_dragon"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/dragon/red.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/dragon/sange.obj"))
                                .effectColor(16711680)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(ICE_DRAGON,
                register(Yakumoblade.prefix("ice_dragon"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/dragon/blue.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/dragon/sange.obj"))
                                .effectColor(2047)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(7)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));

        bootstrap.register(FIVE,
                register(Yakumoblade.prefix("five_hexgram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/five/five.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/five/five.obj"))
                                .effectColor(16776704)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.HexGramSummonSwordyellow))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(SEVEN,
                register(Yakumoblade.prefix("seven_hexgram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/seven/seven.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/seven/blade_seven.obj"))
                                .effectColor(16711680)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.HexGramSummonSwordBlue))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(SIX,
                register(Yakumoblade.prefix("six_hexgram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/six/blade_hexagram.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/six/blade_hexagram.obj"))
                                .effectColor(2047)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.HexGramSummonSwordred))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ExMode))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(TEN,
                register(Yakumoblade.prefix("ten_hexgram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/ten/texture.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/ten/model.obj"))
                                .effectColor(16776704)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.SpiralSwordex))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.BANE_OF_ARTHROPODS), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(HEXAGRAM,
                register(Yakumoblade.prefix("hexagram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/custom/hexagram/hexagram.png"))
                                .modelName(Yakumoblade.prefix("model/named/custom/hexagram/hexagram.obj"))
                                .effectColor(8126719)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(25)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 12),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10)
                        )));
        bootstrap.register(hexagram_six,
                register(Yakumoblade.prefix("godbladehexagram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/custom/hexagram/godbladehexagram.png"))
                                .modelName(Yakumoblade.prefix("model/named/custom/hexagram/godbladehexagram.obj"))
                                .effectColor(8126719)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.TEN_DRIVE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.STAR_SOUL))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1)
                        )));
        bootstrap.register(dragonhexagram,
                register(Yakumoblade.prefix("dragonhexagram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/dragon_six/dragonhexagram.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/dragon_six/dragonhexagram.obj"))
                                .effectColor(16722600)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(39)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.DragonJustices))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SwordArtOnDRAGON))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.HexGamDragon))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ExMode))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)
                        )));
        bootstrap.register(foxhexagram,
                register(Yakumoblade.prefix("foxhexagram"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/hexgram/fox_six/foxhexagram.png"))
                                .modelName(Yakumoblade.prefix("model/hexgram/fox_six/foxhexagram.obj"))
                                .effectColor(16722600)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(39)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(getId(YASlashArtRegistry.FoxJustices))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SwordArtOnFOX))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.HexGamFox))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.ExMode))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)
                        )));
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
                BuiltInRegistries.CREATIVE_MODE_TAB.getKey(ItemTab.YASlashbladeHexGram));
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
