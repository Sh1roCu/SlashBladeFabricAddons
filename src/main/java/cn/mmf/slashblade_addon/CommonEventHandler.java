package cn.mmf.slashblade_addon;

import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

public class CommonEventHandler {
    public static void onVillagerTrades() {
        addVillageTrade(VillagerProfession.WEAPONSMITH, 2,
                new VillagerSlashBladeListing(4, SlashBladeAddonBuiltInRegistry.TOYAKO.location(), 3, 5, 0.05F));
    }

    public static void onWandererTrades() {
        TradeOfferHelper.registerWanderingTraderOffers(1, rareTrades ->
                rareTrades.add(new VillagerSlashBladeListing(4, SlashBladeAddonBuiltInRegistry.TOYAKO.location(), 3, 5, 0.05F)));
    }

    private static void addVillageTrade(VillagerProfession profession, int level,
                                        VillagerTrades.ItemListing listing) {
        TradeOfferHelper.registerVillagerOffers(profession, level, trades ->
                trades.add(listing));
    }
}
