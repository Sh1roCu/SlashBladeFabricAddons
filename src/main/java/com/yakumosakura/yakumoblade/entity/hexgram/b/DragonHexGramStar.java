package com.yakumosakura.yakumoblade.entity.hexgram.b;

import cn.sh1rocu.sfaddons.api.extension.IEntityListener;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DragonHexGramStar extends Projectile implements IEntityListener {
    public DragonHexGramStar(EntityType<? extends net.minecraft.world.entity.projectile.Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    private boolean isAddedToWorld;

    private static final EntityDataAccessor<Float> LIFE_TIME = SynchedEntityData.defineId(DragonHexGramStar.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(DragonHexGramStar.class, EntityDataSerializers.INT);

    @Override
    public void tick() {
        super.tick();

        // 修正XRot角度为90度（正确面朝正下方）并保持YRot为-90度
        this.setYRot(-90);  // 新增Y轴朝向固定
        this.setXRot(90);    // 修正俯仰角为90度（正确朝正下方）
        this.yRotO = -90;    // 同步记录旧的Y轴旋转
        this.xRotO = 90;     // 同步记录旧的X轴旋转

        this.setDeltaMovement(Vec3.ZERO);
        if (super.getOwner() instanceof LivingEntity le) {
            if (this.tickCount > 5 && this.tickCount % 5 == 0 && !this.level().isClientSide()) {
                doSlash(this, getColor());
            }
        }

        if (this.tickCount > getLifeTime()) {
            this.discard();
        }
    }

    @Override
    public boolean isAddedToWorld() {
        return this.isAddedToWorld;
    }

    // 恢复初始化方法设置初始朝下方向
    @Override
    public void onAddedToWorld() {
        this.isAddedToWorld = true;
        this.setYRot(-90f); // 初始面朝正下方
        this.setXRot(90f);  // 新增初始俯仰角设置
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(COLOR, 16722600);
        builder.define(LIFE_TIME, 600.0f);
    }

    @Override
    public void onRemovedFromWorld() {
        this.isAddedToWorld = false;
    }

    public int getColor() {
        return this.getEntityData().get(COLOR);
    }

    public void setColor(int value) {
        this.getEntityData().set(COLOR, value);
    }

    public float getLifeTime() {
        return this.getEntityData().get(LIFE_TIME);
    }

    public void setLifeTime(float value) {
        this.getEntityData().set(LIFE_TIME, value);
    }


    public void doSlash(Projectile playerIn, int colorCode) {
        if (playerIn.level().isClientSide()) return;

        Level worldIn = playerIn.level();
        for (int i = 0; i < 5; i++) {
            Level level = level();
            SwordRainEntityDragon ss = new SwordRainEntityDragon(YAEntitiesRegistry.swordRainEntityDragon, level);

            // 生成半径2圆内的随机坐标
            Vec3 pos = this.position();
            RandomSource random = level.getRandom();
            double angle = random.nextDouble() * Math.PI * 2; // 随机角度
            double radius = Math.sqrt(random.nextDouble()) * 2; // 平方根分布保证均匀性
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;
            ss.setPos(pos.x + xOffset, pos.y, pos.z + zOffset);

            ss.setIsCritical(false);
            ss.setOwner(this.getOwner());
            // 修改2：使用垂直向下方向
            ss.shoot(0, -1, 0, 3.0f, 0.0f);  // 直接设置向下射击方向


            ss.setColor(getColor());
            ss.setRoll(0);

            ss.setDamage(((LivingEntity) this.getOwner()).getAttributeValue(Attributes.ATTACK_DAMAGE) / 50);

            this.getOwner().playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            level.addFreshEntity(ss);
        }

    }

}
