package com.yakumosakura.yakumoblade.client;

import com.yakumosakura.yakumoblade.client.renderer.*;
import com.yakumosakura.yakumoblade.client.renderer.effect.StarRideRender;
import com.yakumosakura.yakumoblade.client.renderer.hexgram.DragonHexGramRender;
import com.yakumosakura.yakumoblade.client.renderer.hexgram.FoxHexGramExRender;
import com.yakumosakura.yakumoblade.client.renderer.hexgram.LaserCircleRender;
import com.yakumosakura.yakumoblade.client.renderer.touhou.YukariRender;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.client.renderer.entity.JudgementCutRenderer;
import mods.flammpfeil.slashblade.client.renderer.entity.SummonedSwordRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class YakumoBladeCilent {
    //客户端渲染
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(YAEntitiesRegistry.wither_attack, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.soul_edge, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.wither_summon_sword, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.SoulEdgeB, FlyRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.BlackSlash, BlackSlashRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.GigantjudgementCuts, JudgementCutRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.BigDrive, BigDriveRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.StarDrive, StarDriveRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.EntityNRBlisteringsword, BlueFoxRenderer::new);

        EntityRendererRegistry.register(YAEntitiesRegistry.SumonSwordentityFox, FoxFlowerSuperRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.swordRainEntity, FoxFlowerRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.swordRainEntityDragon, FoxFlowerRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.SummonSword, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.BlueFox, BlueFoxRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.HexGramSumonSword, SummonedSwordRenderer::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.YuYuKoSpiralSwords, FlyRender::new);

        EntityRendererRegistry.register(YAEntitiesRegistry.FoxHexGramEx, FoxHexGramExRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.Yukari, YukariRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.DragonHexGramEntitys, DragonHexGramRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.LaserCircle, LaserCircleRender::new);
        EntityRendererRegistry.register(YAEntitiesRegistry.StarRider, StarRideRender::new);

        // TODO: 暂时随便注册一个渲染器，防止部分SE调用未注册渲染器的实体导致的崩溃，等原版幻想之刃做完这些实体的渲染器再同步
        EntityRendererRegistry.register(YAEntitiesRegistry.swordRainFire, SummonedSwordRenderer::new);
    }
}