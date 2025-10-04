package cn.mmf.slashblade_addon.entity;

import cn.mmf.slashblade_addon.registry.SBAEntitiesRegistry;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class GaleSwordsEntity extends BlisteringSwordsEntity {
	public GaleSwordsEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

	public static GaleSwordsEntity createInstance(Level worldIn) {
		return new GaleSwordsEntity(SBAEntitiesRegistry.GaleSwords, worldIn);
	}

	@Override
	protected void onHitBlock(BlockHitResult blockraytraceresult) {
		super.onHitBlock(blockraytraceresult);
		this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1, Level.ExplosionInteraction.NONE);
		burst();
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1, Level.ExplosionInteraction.NONE);
		burst();
	}
}
