package com.yakumosakura.yakumoblade.entity.hexgram.neo;

import com.yakumosakura.yakumoblade.entity.MagicCircleEntity;
import com.yakumosakura.yakumoblade.entity.hexgram.b.SwordRainEntityDragon;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FoxHexGramExEntity extends MagicCircleEntity {
    public FoxHexGramExEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void doEffect() {
        doSlash(this);
    }

    public void doSlash(Projectile playerIn) {
        if (playerIn.level().isClientSide()) return;

        Level worldIn = playerIn.level();
        for (int i = 0; i < 10; i++) {
            Level level = level();
            SwordRainEntityDragon ss = new SwordRainEntityDragon(YAEntitiesRegistry.swordRainEntityDragon, level);

            // 生成半径2圆内的随机坐标
            Vec3 pos = this.position();
            RandomSource random = level.getRandom();
            double angle = random.nextDouble() * Math.PI * 5; // 随机角度
            double radius = Math.sqrt(random.nextDouble()) * 45; // 平方根分布保证均匀性
            double xOffset = Math.cos(angle) * radius;
            double zOffset = Math.sin(angle) * radius;
            ss.setPos(pos.x + xOffset, pos.y, pos.z + zOffset);

            ss.setIsCritical(false);
            ss.setOwner(this.getOwner());
            // 修改2：使用垂直向下方向
            ss.shoot(0, -1, 0, 3.0f, 0.0f);  // 直接设置向下射击方向

            ss.setColor(16722600);
            ss.setRoll(0);

            ss.setDamage(((LivingEntity) this.getOwner()).getAttributeValue(Attributes.ATTACK_DAMAGE) / 50);

            this.getOwner().playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
            level.addFreshEntity(ss);
        }
    }
}