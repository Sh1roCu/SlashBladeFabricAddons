package cn.mmf.slashblade_addon.registry;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import cn.mmf.slashblade_addon.entity.BlisteringSwordsEntity;
import cn.mmf.slashblade_addon.entity.EntityWaterDrive;
import cn.mmf.slashblade_addon.entity.GaleSwordsEntity;
import cn.mmf.slashblade_addon.entity.SpiralEdgeSwordsEntity;
import com.google.common.base.CaseFormat;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SBAEntitiesRegistry {
    public static final ResourceLocation BlisteringSwordsLoc = ResourceLocation.fromNamespaceAndPath(SlashBladeAddon.MODID,
            classToString(BlisteringSwordsEntity.class));
    public static final ResourceLocation SpiralEdgeSwordsLoc = ResourceLocation.fromNamespaceAndPath(SlashBladeAddon.MODID,
            classToString(SpiralEdgeSwordsEntity.class));
    public static final ResourceLocation GaleSwordsLoc = ResourceLocation.fromNamespaceAndPath(SlashBladeAddon.MODID,
            classToString(GaleSwordsEntity.class));

    public static EntityType<BlisteringSwordsEntity> BlisteringSwords;
    public static EntityType<SpiralEdgeSwordsEntity> SpiralEdgeSwords;
    public static EntityType<SpiralEdgeSwordsEntity> GaleSwords;
    public static EntityType<BlisteringSwordsEntity> LightingSwords;
    public static EntityType<EntityWaterDrive> WaterDrive;

    public static void init() {
        WaterDrive = register(ResourceLocation.fromNamespaceAndPath(SlashBladeAddon.MODID, "water_drive"), EntityType.Builder
                .of(EntityWaterDrive::new, MobCategory.MISC).sized(0.9F, 0.9F).clientTrackingRange(4)
                .updateInterval(20)
                .build("water_drive"));

        LightingSwords = register(ResourceLocation.fromNamespaceAndPath(SlashBladeAddon.MODID, "lighting_swords"), EntityType.Builder
                .of(BlisteringSwordsEntity::new, MobCategory.MISC).sized(0.9F, 0.9F).clientTrackingRange(4)
                .updateInterval(20)
                .build("lighting_swords"));

        BlisteringSwords = register(BlisteringSwordsLoc, EntityType.Builder
                .of(BlisteringSwordsEntity::new, MobCategory.MISC).sized(0.9F, 0.9F).clientTrackingRange(4)
                .updateInterval(20)
                .build(BlisteringSwordsLoc.toString()));

        SpiralEdgeSwords = register(SpiralEdgeSwordsLoc, EntityType.Builder
                .of(SpiralEdgeSwordsEntity::new, MobCategory.MISC).sized(0.9F, 0.9F).clientTrackingRange(4)
                .updateInterval(20)
                .build(SpiralEdgeSwordsLoc.toString()));

        GaleSwords = register(GaleSwordsLoc, EntityType.Builder
                .of(SpiralEdgeSwordsEntity::new, MobCategory.MISC).sized(0.9F, 0.9F).clientTrackingRange(4)
                .updateInterval(20)
                .build(GaleSwordsLoc.toString()));
    }

    private static String classToString(Class<? extends Entity> entityClass) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entityClass.getSimpleName()).replace("entity_",
                "");
    }

    private static <T extends Entity> EntityType<T> register(ResourceLocation loc, EntityType<T> type) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, loc, type);
    }
}
