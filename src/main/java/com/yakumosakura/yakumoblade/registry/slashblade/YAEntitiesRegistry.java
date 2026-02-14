package com.yakumosakura.yakumoblade.registry.slashblade;

import com.google.common.base.CaseFormat;
import com.yakumosakura.yakumoblade.entity.*;
import com.yakumosakura.yakumoblade.entity.drive.BigDriveEntity;
import com.yakumosakura.yakumoblade.entity.drive.StarDriveEntity;
import com.yakumosakura.yakumoblade.entity.exer.EntityNRBlisteringSword;
import com.yakumosakura.yakumoblade.entity.exer.EntitySpiralSwords;
import com.yakumosakura.yakumoblade.entity.exer.SwordRainEntityFire;
import com.yakumosakura.yakumoblade.entity.hexgram.a.HexGramSumonSwordEntity;
import com.yakumosakura.yakumoblade.entity.hexgram.b.DragonHexGramStar;
import com.yakumosakura.yakumoblade.entity.hexgram.b.SwordRainEntityDragon;
import com.yakumosakura.yakumoblade.entity.hexgram.neo.DragonHexGramEntity;
import com.yakumosakura.yakumoblade.entity.hexgram.neo.FoxHexGramExEntity;
import com.yakumosakura.yakumoblade.entity.hexgram.neo.LaserCircleEntity;
import com.yakumosakura.yakumoblade.entity.hexgram.old.FoxSumonSwordEntity;
import com.yakumosakura.yakumoblade.entity.hexgram.old.StarEntityFox;
import com.yakumosakura.yakumoblade.entity.hexgram.old.SwordRainEntityFox;
import com.yakumosakura.yakumoblade.entity.star.StarRiderEntity;
import com.yakumosakura.yakumoblade.entity.touhou.YuYuKoSpiralSwordsEntity;
import com.yakumosakura.yakumoblade.entity.touhou.YukariEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static com.yakumosakura.yakumoblade.Yakumoblade.MODID;

public class YAEntitiesRegistry {
    //实体注册表
    public static final ResourceLocation WitherAttackLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(WitherAttackEntity.class));
    public static final ResourceLocation SoulEdgeLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(SoulEdgeEntity.class));
    public static final ResourceLocation WitherSummonSwordLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(WitherSummonSwordEntity.class));
    public static final ResourceLocation SoulEdgeBLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(SoulEdgeEntityButerfly.class));
    public static final ResourceLocation BlackSlashLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(BlackSlashEntity.class));
    public static final ResourceLocation GigantjudgementCutLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(GigantjudgementCut.class));
    public static final ResourceLocation SumonSwordEntityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(FoxSumonSwordEntity.class));
    public static final ResourceLocation StarEntityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(StarEntityFox.class));
    public static final ResourceLocation SwordRainEntityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(SwordRainEntityFox.class));
    public static final ResourceLocation SwordRainEntityDragonLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(SwordRainEntityDragon.class));
    public static final ResourceLocation StarEntityDragonLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(DragonHexGramStar.class));
    public static final ResourceLocation getSummonSwordLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(SummonSwordEntity.class));
    public static final ResourceLocation BigDriveEnityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(BigDriveEntity.class));
    public static final ResourceLocation SwordRainEntityFireLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(SwordRainEntityFire.class));
    public static final ResourceLocation BlueFoxLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(EntitySpiralSwords.class));
    public static final ResourceLocation HexGramSumonSwordLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(HexGramSumonSwordEntity.class));
    public static final ResourceLocation StarDriveLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(StarDriveEntity.class));
    public static final ResourceLocation EntityNRBlisteringSwordLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(EntityNRBlisteringSword.class));
    public static final ResourceLocation YuYuKoSpiralSwordsEntityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(YuYuKoSpiralSwordsEntity.class));
    public static final ResourceLocation FoxHexGramExEntityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(FoxHexGramExEntity.class));
    public static final ResourceLocation YukariEntityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(YukariEntity.class));
    public static final ResourceLocation DragonHexGramEntityLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(DragonHexGramEntity.class));
    public static final ResourceLocation LaserCircleLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(LaserCircleEntity.class));
    public static final ResourceLocation StarRiderLoc = ResourceLocation.fromNamespaceAndPath(MODID, classToString(StarRiderEntity.class));


    public static EntityType<WitherAttackEntity> wither_attack = register(WitherAttackLoc, EntityType.Builder
            .of(WitherAttackEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(WitherAttackLoc.toString()));
    public static EntityType<SoulEdgeEntity> soul_edge = register(SoulEdgeLoc, EntityType.Builder
            .of(SoulEdgeEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(SoulEdgeLoc.toString()));
    public static EntityType<WitherSummonSwordEntity> wither_summon_sword = register(WitherSummonSwordLoc, EntityType.Builder
            .of(WitherSummonSwordEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(WitherSummonSwordLoc.toString()));
    public static EntityType<SoulEdgeEntityButerfly> SoulEdgeB = register(SoulEdgeBLoc, EntityType.Builder
            .of(SoulEdgeEntityButerfly::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(SoulEdgeBLoc.toString()));//反魂蝶
    public static EntityType<BlackSlashEntity> BlackSlash = register(BlackSlashLoc, EntityType.Builder
            .of(BlackSlashEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(BlackSlashLoc.toString()));//黑斩
    public static EntityType<GigantjudgementCut> GigantjudgementCuts = register(GigantjudgementCutLoc, EntityType.Builder
            .of(GigantjudgementCut::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(GigantjudgementCutLoc.toString()));
    public static EntityType<FoxSumonSwordEntity> SumonSwordentityFox = register(SumonSwordEntityLoc, EntityType.Builder
            .of(FoxSumonSwordEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(SumonSwordEntityLoc.toString()));
    public static EntityType<StarEntityFox> starEntity = register(StarEntityLoc, EntityType.Builder
            .of(StarEntityFox::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(StarEntityLoc.toString()));
    public static EntityType<SwordRainEntityFox> swordRainEntity = register(SwordRainEntityLoc, EntityType.Builder
            .of(SwordRainEntityFox::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(SwordRainEntityLoc.toString()));
    public static EntityType<SwordRainEntityDragon> swordRainEntityDragon = register(SwordRainEntityDragonLoc, EntityType.Builder
            .of(SwordRainEntityDragon::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(SwordRainEntityDragonLoc.toString()));
    public static EntityType<DragonHexGramStar> starEntityDragon = register(StarEntityDragonLoc, EntityType.Builder
            .of(DragonHexGramStar::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(StarEntityDragonLoc.toString()));
    public static EntityType<SummonSwordEntity> SummonSword = register(getSummonSwordLoc, EntityType.Builder
            .of(SummonSwordEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(getSummonSwordLoc.toString()));
    public static EntityType<BigDriveEntity> BigDrive = register(BigDriveEnityLoc, EntityType.Builder
            .of(BigDriveEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(BigDriveEnityLoc.toString()));
    public static EntityType<SwordRainEntityFire> swordRainFire = register(SwordRainEntityFireLoc, EntityType.Builder
            .of(SwordRainEntityFire::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(SwordRainEntityFireLoc.toString()));
    public static EntityType<EntitySpiralSwords> BlueFox = register(BlueFoxLoc, EntityType.Builder
            .of(EntitySpiralSwords::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(BlueFoxLoc.toString()));
    public static EntityType<HexGramSumonSwordEntity> HexGramSumonSword = register(HexGramSumonSwordLoc, EntityType.Builder
            .of(HexGramSumonSwordEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(HexGramSumonSwordLoc.toString()));
    public static EntityType<StarDriveEntity> StarDrive = register(StarDriveLoc, EntityType.Builder
            .of(StarDriveEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(StarDriveLoc.toString()));
    public static EntityType<EntityNRBlisteringSword> EntityNRBlisteringsword = register(EntityNRBlisteringSwordLoc, EntityType.Builder
            .of(EntityNRBlisteringSword::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(EntityNRBlisteringSwordLoc.toString()));
    public static EntityType<YuYuKoSpiralSwordsEntity> YuYuKoSpiralSwords = register(YuYuKoSpiralSwordsEntityLoc, EntityType.Builder
            .of(YuYuKoSpiralSwordsEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(YuYuKoSpiralSwordsEntityLoc.toString()));
    public static EntityType<FoxHexGramExEntity> FoxHexGramEx = register(FoxHexGramExEntityLoc, EntityType.Builder
            .of(FoxHexGramExEntity::new, MobCategory.MISC)
            .sized(0.9F, -100F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(FoxHexGramExEntityLoc.toString()));
    public static EntityType<YukariEntity> Yukari = register(YukariEntityLoc, EntityType.Builder
            .of(YukariEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(YukariEntityLoc.toString()));
    public static EntityType<DragonHexGramEntity> DragonHexGramEntitys = register(DragonHexGramEntityLoc, EntityType.Builder
            .of(DragonHexGramEntity::new, MobCategory.MISC)
            .sized(0.9F, -100F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(DragonHexGramEntityLoc.toString()));
    public static EntityType<LaserCircleEntity> LaserCircle = register(LaserCircleLoc, EntityType.Builder
            .of(LaserCircleEntity::new, MobCategory.MISC)
            .sized(0.9F, -100F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(LaserCircleLoc.toString()));
    public static EntityType<StarRiderEntity> StarRider = register(StarRiderLoc, EntityType.Builder
            .of(StarRiderEntity::new, MobCategory.MISC)
            .sized(0.9F, 0.9F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(StarRiderLoc.toString()));

    private static <T extends Entity> EntityType<T> register(ResourceLocation loc, EntityType<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, loc, type);

    }

    private static String classToString(Class<? extends Entity> entityClass) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName())
                .replace("entity_", "");
    }

    public static void init() {
    }
}
