package com.yakumosakura.yakumoblade.entity.hexgram.neo;

import com.yakumosakura.yakumoblade.entity.MagicCircleEntity;
import com.yakumosakura.yakumoblade.registry.slashblade.YAEntitiesRegistry;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class DragonHexGramEntity extends MagicCircleEntity {
    private int phase = 0; // 0:初始阶段, 1:z1-5显示阶段, 2:z6-8下沉阶段, 3:结束阶段
    private int phaseTimer = 0;

    public DragonHexGramEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setLifeTime(120); // 设置较短的生命周期
    }

    @Override
    protected void doEffect() {
        phaseTimer++;

        // 阶段转换逻辑
        switch (phase) {
            case 0: // 初始阶段
                if (phaseTimer >= 5) { // 5 tick后进入第一阶段
                    phase = 1;
                    phaseTimer = 0;

                    // 生成激光法阵
                    if (!this.level().isClientSide()) {
                        LaserCircleEntity laserCircle = new LaserCircleEntity(YAEntitiesRegistry.LaserCircle, this.level());
                        laserCircle.setOwner(this.getOwner());
                        laserCircle.setPos(
                                this.getX(),
                                this.getY(),
                                this.getZ()
                        );
                        this.level().addFreshEntity(laserCircle);
                    }
                }
                break;
            case 1: // z1-5显示阶段
                if (phaseTimer >= 20) { // 20 tick后进入第二阶段
                    phase = 2;
                    phaseTimer = 0;
                }
                break;
            case 2: // z6-8下沉阶段
                if (phaseTimer >= 40) { // 30 tick后进入结束阶段
                    phase = 3;
                    phaseTimer = 0;
                }
                break;
            case 3: // 结束阶段
                if (phaseTimer >= 60) { // 40 tick后消失
                    this.discard();
                }
                break;
        }
    }

    public int getPhase() {
        return phase;
    }

    public float getPhaseProgress() {
        return (float) phaseTimer / getPhaseDuration(phase);
    }

    private int getPhaseDuration(int phase) {
        switch (phase) {
            case 0:
                return 5;
            case 1:
                return 20;
            case 2:
                return 30;
            case 3:
                return 40;
            default:
                return 1;
        }
    }

    // 获取各个部分的透明度
    public float getPartAlpha(String partName) {
        switch (partName) {
            case "z1":
            case "z2":
            case "z3":
            case "z4":
            case "z5":
                return phase >= 1 ? 1.0f : 0.0f;
            case "z6":
                return phase >= 2 ? Math.min(1.0f, getPhaseProgress() * 3.0f) : 0.0f;
            case "z7":
                return phase >= 2 ? Math.min(1.0f, (getPhaseProgress() - 0.33f) * 3.0f) : 0.0f;
            case "z8":
                return phase >= 2 ? Math.min(1.0f, (getPhaseProgress() - 0.66f) * 3.0f) : 0.0f;
            default:
                return 0.0f;
        }
    }

    // 获取各个部分的垂直偏移
    public float getPartOffset(String partName) {
        if (phase < 2) return 0.0f;

        switch (partName) {
            case "z6":
                return -5.0f * (1.0f - Math.min(1.0f, getPhaseProgress() * 3.0f));
            case "z7":
                return -5.0f * (1.0f - Math.min(1.0f, (getPhaseProgress() - 0.33f) * 3.0f));
            case "z8":
                return -5.0f * (1.0f - Math.min(1.0f, (getPhaseProgress() - 0.66f) * 3.0f));
            default:
                return 0.0f;
        }
    }
}