package cn.mmf.energyblade.energy;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.minecraft.nbt.CompoundTag;
import team.reborn.energy.api.EnergyStorage;

public class FEBladeStorage extends SnapshotParticipant<Long> implements EnergyStorage {
    // 当前存储的能量
    protected long energy;
    // 最大可存储的能量
    protected long capacity;

    // 每tick最大可接收的能量
    protected long maxReceive = 20000;

    // 每tick最大可提取的能量
    protected long maxExtract = 20000;

    // 充能消耗的能量
    protected long powerupExtract;

    // 待机时消耗的能量
    protected long standbyExtract;

    // 能量替换耐久显示(即无耐久设定)
    protected boolean energyDurability;

    // 是否正在启用
    protected boolean isPowered = false;


    public FEBladeStorage(long energy, long capacity, long powerupExtract, long standbyExtract, boolean energyDurability) {
        this.energy = energy;
        this.capacity = capacity;
        this.powerupExtract = powerupExtract;
        this.standbyExtract = standbyExtract;
        this.energyDurability = energyDurability;
    }

    @Override
    public long insert(long maxReceive, TransactionContext context) {
        if (!supportsInsertion())
            return 0;

        long energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        if (energyReceived > 0) {
            updateSnapshots(context);
            energy += energyReceived;
            return energyReceived;
        }
        return 0;
    }

    @Override
    public long extract(long maxExtract, TransactionContext context) {
        if (!supportsExtraction())
            return 0;

        long energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

        if (energyExtracted > 0) {
            updateSnapshots(context);
            energy -= energyExtracted;
            if (energy <= 0) {
                this.setPowered(false);
            }
            return energyExtracted;
        }
        return 0;
    }


    @Override
    public long getAmount() {
        return energy;
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean supportsInsertion() {
        return maxReceive > 0;
    }

    @Override
    public boolean supportsExtraction() {
        return maxExtract > 0;
    }

    public boolean isEnergyDurability() {
        return energyDurability;
    }

    public void setEnergyDurability(boolean energyDurability) {
        this.energyDurability = energyDurability;
    }

    public long getPowerupExtract() {
        return powerupExtract;
    }

    public void setPowerupExtract(long powerupExtract) {
        this.powerupExtract = powerupExtract;
    }

    public long getStandbyExtract() {
        return standbyExtract;
    }

    public void setStandbyExtract(long standbyExtract) {
        this.standbyExtract = standbyExtract;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putLong("Energy", energy);
        tag.putLong("Capacity", capacity);
        tag.putLong("MaxReceive", maxReceive);
        tag.putLong("MaxExtract", maxExtract);
        tag.putLong("PowerupExtract", powerupExtract);
        tag.putLong("StandbyExtract", standbyExtract);
        tag.putBoolean("EnergyDurability", energyDurability);
        tag.putBoolean("isPowered", isPowered);
        return tag;
    }

    public void setMaxReceive(long maxReceive) {
        this.maxReceive = maxReceive;
    }

    public void setMaxExtract(long maxExtract) {
        this.maxExtract = maxExtract;
    }

    public void deserializeNBT(CompoundTag nbt) {
        if (nbt != null) {
            energy = nbt.getLong("Energy");
            capacity = nbt.getLong("Capacity");
            maxReceive = nbt.getLong("MaxReceive");
            maxExtract = nbt.getLong("MaxExtract");
            powerupExtract = nbt.getLong("PowerupExtract");
            standbyExtract = nbt.getLong("StandbyExtract");
            energyDurability = nbt.getBoolean("EnergyDurability");
            isPowered = nbt.getBoolean("isPowered");
        }
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
    }

    @Override
    protected Long createSnapshot() {
        return getAmount();
    }

    @Override
    protected void readSnapshot(Long snapshot) {
        this.energy = snapshot;
    }
}
