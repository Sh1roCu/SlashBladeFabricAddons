package cn.mmf.slashblade_addon;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.init.SBItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class VillagerSlashBladeListing extends BasicItemListing {
    private final ResourceLocation bladeName;

    public VillagerSlashBladeListing(int emeralds, ResourceLocation bladeName, int maxTrades, int xp, float mult) {
        super(emeralds, SBItems.slashblade.getDefaultInstance(), maxTrades, xp, mult);
        this.bladeName = bladeName;
    }

    public ResourceLocation getBladeName() {
        return bladeName;
    }

    @Override
    public @Nullable MerchantOffer getOffer(Entity entity, RandomSource random) {
        Level level = entity.level();
        ItemStack blade = SlashBlade.getSlashBladeDefinitionRegistry(level).get(this.getBladeName()).getBlade();

        return new MerchantOffer(this.price, ItemStack.EMPTY, blade, maxTrades, xp, priceMult);
    }
}

class BasicItemListing implements VillagerTrades.ItemListing {
    protected final ItemStack price;
    protected final ItemStack price2;
    protected final ItemStack forSale;
    protected final int maxTrades;
    protected final int xp;
    protected final float priceMult;

    public BasicItemListing(ItemStack price, ItemStack price2, ItemStack forSale, int maxTrades, int xp, float priceMult) {
        this.price = price;
        this.price2 = price2;
        this.forSale = forSale;
        this.maxTrades = maxTrades;
        this.xp = xp;
        this.priceMult = priceMult;
    }

    public BasicItemListing(ItemStack price, ItemStack forSale, int maxTrades, int xp, float priceMult) {
        this(price, ItemStack.EMPTY, forSale, maxTrades, xp, priceMult);
    }

    public BasicItemListing(int emeralds, ItemStack forSale, int maxTrades, int xp, float mult) {
        this(new ItemStack(Items.EMERALD, emeralds), forSale, maxTrades, xp, mult);
    }

    public BasicItemListing(int emeralds, ItemStack forSale, int maxTrades, int xp) {
        this(new ItemStack(Items.EMERALD, emeralds), forSale, maxTrades, xp, 1.0F);
    }

    public @Nullable MerchantOffer getOffer(Entity p_219693_, RandomSource p_219694_) {
        return new MerchantOffer(this.price, this.price2, this.forSale, this.maxTrades, this.xp, this.priceMult);
    }
}
