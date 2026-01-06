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
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class YakumoBladeBuiltInRegsitryTouHou {


    //彼岸「幽幽子」
    public static final ResourceKey<SlashBladeDefinition> YUYUKO_BEYOND = register("yuyuko_beyond");
    //中奖剑
    public static final ResourceKey<SlashBladeDefinition> LUCKY_SWORD = register("lucky_sword");
    //琪露诺的圣代
    public static final ResourceKey<SlashBladeDefinition> cirno = register("cirno");
    //名刀「博麗先代」
    public static final ResourceKey<SlashBladeDefinition> dream = register("dream");
    //禁忌「莱瓦汀」
    public static final ResourceKey<SlashBladeDefinition> LWT = register("lwt");
    //绯想之剑
    public static final ResourceKey<SlashBladeDefinition> hisonotsurugi = register("hisonotsurugi");
    //利刃「承影」
    public static final ResourceKey<SlashBladeDefinition> blademaster_yukari = register("blademaster_yukari");
    //名刀「承影」
    public static final ResourceKey<SlashBladeDefinition> nieblade_yukari = register("nieblade_yukari");
    //樱木刀
    public static final ResourceKey<SlashBladeDefinition> yukari_blade = register("yukari_blade");
    //七星剑
    public static final ResourceKey<SlashBladeDefinition> seven_star = register("seven_star");
    //八云樱
    public static final ResourceKey<SlashBladeDefinition> YAKUMOSAKURA = register("yakumosakura");
    //八云樱2
    public static final ResourceKey<SlashBladeDefinition> YAKUMOSAKURA2 = register("yakumosakura2");
    //八云樱3
    public static final ResourceKey<SlashBladeDefinition> YAKUMOSAKURA3 = register("yakumosakura3");
    //sp八云樱
    public static final ResourceKey<SlashBladeDefinition> SP_YAKUMOSAKURA = register("yakumosakurafinal");

    //幽幽子~
    public static final ResourceKey<SlashBladeDefinition> YUYUKO = register("yuyuko");
    //幽幽子~1
    public static final ResourceKey<SlashBladeDefinition> YUYUKO_2 = register("yuyuko2");
    //幽幽子~2
    public static final ResourceKey<SlashBladeDefinition> YUYUKO_3 = register("yuyuko3");
    //幽幽子~3
    public static final ResourceKey<SlashBladeDefinition> YUYUKOFINAL = register("yuyukofinal");

    //散花
    public static final ResourceKey<SlashBladeDefinition> SANGE_FLOWER = register("san");

    //楼观剑
    public static final ResourceKey<SlashBladeDefinition> red = register("red");
    //白楼剑
    public static final ResourceKey<SlashBladeDefinition> white = register("white");


    public static void registerAll(BootstrapContext<SlashBladeDefinition> bootstrap) {


        bootstrap.register(YUYUKO_BEYOND,
                register(Yakumoblade.prefix("yuyuko_beyond"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/yuyuko/yuyuko_beyond.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/yuyuko/yuyuko_beyond.obj"))
                                .effectColor(10492369)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(35)
                                .slashArtsType(getId(SlashArtsRegistry.WAVE_EDGE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.BeyondYuyuko))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.GhostBlade))
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.KNOCKBACK), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(LUCKY_SWORD,
                register(Yakumoblade.prefix("lucky_sword"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/cirno/lucky_sword.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/cirno/lucky_sword.obj"))
                                .effectColor(12829635)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(1)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                        )
                ));
        bootstrap.register(cirno,
                register(Yakumoblade.prefix("cirno"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/cirno/cirno.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/cirno/cirno.obj"))
                                .effectColor(45055)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(1)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                        )
                ));

        bootstrap.register(dream,
                register(Yakumoblade.prefix("dream"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/dream/dream.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/dream/dream.obj"))
                                .effectColor(16749312)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12)
                                .slashArtsType(getId(SBASlashArtsRegistry.GALE_SWORDS))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.Dream))
                                .maxDamage(180)
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.LOOTING), 3)
                        )
                ));
        bootstrap.register(LWT,
                register(Yakumoblade.prefix("lwt"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/lwt/lwt.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/lwt/lwt.obj"))
                                .effectColor(13924864)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(40)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.HotDrives))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.Lwt))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(hisonotsurugi,
                register(Yakumoblade.prefix("hisonotsurugi"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/hisonotsurugi/hisonotsurugi.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/hisonotsurugi/hisonotsurugi.obj"))
                                .effectColor(13915392)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(12)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.CrisonSwords))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                ));
        bootstrap.register(blademaster_yukari,
                register(Yakumoblade.prefix("blademaster_yukari"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/yukari/blademaster_yukari.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/yukari/blademaster_yukari.obj"))
                                .effectColor(16737792)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(SlashArtsRegistry.DRIVE_VERTICAL))
                                .baseAttackModifier(7)
                                .maxDamage(180)
                                .build(),
                        List.of(
                        )
                ));
        bootstrap.register(nieblade_yukari,
                register(Yakumoblade.prefix("nieblade_yukari"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/yukari/nieblade_yukari.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/dream/dream.obj"))
                                .effectColor(4260050)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.RetreatFlyingknife))
                                .baseAttackModifier(12)
                                .maxDamage(180)
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 3)
                        )
                ));
        bootstrap.register(yukari_blade,
                register(Yakumoblade.prefix("yukari_blade"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/yukari/blade.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/yukari/blade.obj"))
                                .effectColor(4260050)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(4)
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.YouKai))
                                .maxDamage(180)
                                .build(),
                        List.of(
                        )));
        bootstrap.register(seven_star,
                register(Yakumoblade.prefix("seven_star"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/touhou/seven_star/seven_star.png"))
                                .modelName(Yakumoblade.prefix("model/touhou/seven_star/seven_star.obj"))
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(4)
                                .maxDamage(180)
                                .build(),
                        List.of(
                        )));
        bootstrap.register(YAKUMOSAKURA,
                register(Yakumoblade.prefix("yakumosakura"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yakumosakura.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(16722600)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(SlashArtsRegistry.DRIVE_VERTICAL))
                                .baseAttackModifier(16)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(

                        )));
        bootstrap.register(YAKUMOSAKURA2,
                register(Yakumoblade.prefix("yakumosakura2"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yakumosakura1.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(16722600)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(SlashArtsRegistry.SAKURA_END))
                                .baseAttackModifier(15)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FEATHER_FALLING), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(YAKUMOSAKURA3,
                register(Yakumoblade.prefix("yakumosakura3"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yakumosakura2.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(16722600)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(SBASlashArtsRegistry.RAPID_BLISTERING_SWORDS))
                                .baseAttackModifier(21)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FEATHER_FALLING), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 6)
                        )));
        bootstrap.register(SP_YAKUMOSAKURA,
                register(Yakumoblade.prefix("yakumosakurafinal"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yakumosakurafinal.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/yakumosakurafinal.obj"))
                                .effectColor(16722600)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.DeadOrLifes))
                                .baseAttackModifier(39)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.MakeGaps))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SwordArtOnGap))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.YouKai))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FEATHER_FALLING), 8),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.LOOTING), 3)
                        )));
        bootstrap.register(YUYUKO,
                register(Yakumoblade.prefix("yuyuko"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yuyuko.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(16722600)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(16)
                                .maxDamage(180)
                                .slashArtsType(getId(SlashArtsRegistry.VOID_SLASH))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.GhostBlade))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                        )));


        bootstrap.register(SANGE_FLOWER,
                register(Yakumoblade.prefix("san"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/san.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(16769536)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(SlashArtsRegistry.CIRCLE_SLASH))
                                .baseAttackModifier(21)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.YouKai))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 3)
                        )));
        bootstrap.register(YUYUKO_3,
                register(Yakumoblade.prefix("yuyuko3"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yuyukofinal.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(16722600)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(21)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.SoulGaleSwords))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.GhostBlade))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FEATHER_FALLING), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 6)
                        )));
        bootstrap.register(YUYUKO_2,
                register(Yakumoblade.prefix("yuyuko2"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yuyuko1.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/sange.obj"))
                                .effectColor(16722600)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(21)
                                .maxDamage(180)
                                .slashArtsType(getId(SBASlashArtsRegistry.SPIRAL_EDGE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.GhostBlade))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FEATHER_FALLING), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(YUYUKOFINAL,
                register(Yakumoblade.prefix("yuyukofinal"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/named/sange/yuyukofinal.png"))
                                .modelName(Yakumoblade.prefix("model/named/sange/yakumosakurafinal.obj"))
                                .effectColor(16722600)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(40)
                                .maxDamage(180)
                                .slashArtsType(getId(YASlashArtRegistry.CSOULEADGE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.YuyukoSe))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SwordArtOnDead))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.GhostBlade))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SOUL_SPEED), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FEATHER_FALLING), 8),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(white,
                register(Yakumoblade.prefix("white"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/blade/white.png"))
                                .modelName(Yakumoblade.prefix("model/blade/white.obj"))
                                .effectColor(16777215)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(SlashArtsRegistry.PIERCING))
                                .baseAttackModifier(24)
                                .maxDamage(180)
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 6),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.THORNS), 3)
                        ))
        );

        bootstrap.register(red,
                register(Yakumoblade.prefix("red"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Yakumoblade.prefix("model/blade/red.png"))
                                .modelName(Yakumoblade.prefix("model/blade/red.obj"))
                                .effectColor(16711680)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .slashArtsType(getId(YASlashArtRegistry.TEN_DRIVE))
                                .addSpecialEffect(getId(YASpecialEffectsRegistry.SharpnessBlade))
                                .baseAttackModifier(25)
                                .maxDamage(180)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .build(),
                        List.of(
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1)
                        ))
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
                BuiltInRegistries.CREATIVE_MODE_TAB.getKey(ItemTab.YASlashbladeTouHou));
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
