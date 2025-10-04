package cn.mmf.slashblade_addon.specialeffect;

import cn.mmf.slashblade_addon.registry.SBASpecialEffectsRegistry;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.Drive;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class BurstDrive extends SpecialEffect {

    public BurstDrive() {
        super(30, false, false);
    }

    public static void onDoingSlash(SlashBladeEvent.DoSlashEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if (state.hasSpecialEffect(SpecialEffectsRegistry.SPECIAL_EFFECT.getKey(SBASpecialEffectsRegistry.BURST_DRIVE))) {
            if (!(event.getUser() instanceof Player)) {
                return;
            }

            Player player = (Player) event.getUser();

            int level = player.experienceLevel;
            int colorCode = event.getSlashBladeState().getColorCode();
            if (SpecialEffect.isEffective(SBASpecialEffectsRegistry.BURST_DRIVE, level)) {
                Drive.doSlash(player, event.getRoll(), event.getYRot(), 10, colorCode, Vec3.ZERO, false, event.getDamage(), null, 1.5f);
            }
        }
    }
}
