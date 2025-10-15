package cn.mmf.energyblade.energy;

import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import net.minecraft.world.item.ItemStack;
import team.reborn.energy.api.EnergyStorage;

public class FECapabilityProvider extends SlashBladeState {
    private final EnergyStorage energyStorage;

    public FECapabilityProvider(ItemStack blade, EnergyStorage energyStorage) {
        super(blade);
        this.energyStorage = energyStorage;
    }

    public EnergyStorage getEnergyStorage() {
        return energyStorage;
    }
}