package com.yakumosakura.yakumoblade.specialeffects;

import com.yakumosakura.yakumoblade.specialeffects.colorSE.Eternal;
import com.yakumosakura.yakumoblade.specialeffects.colorSE.VeryBlue;
import com.yakumosakura.yakumoblade.specialeffects.colorSE.VeryGreen;
import com.yakumosakura.yakumoblade.specialeffects.hexgam.ExMode;
import com.yakumosakura.yakumoblade.specialeffects.ses.HexGam;
import com.yakumosakura.yakumoblade.specialeffects.ses.ProudSoulDrive;
import com.yakumosakura.yakumoblade.specialeffects.starSE.Highfrequency;
import com.yakumosakura.yakumoblade.specialeffects.starSE.ex.*;
import com.yakumosakura.yakumoblade.specialeffects.starSE.re.*;
import com.yakumosakura.yakumoblade.specialeffects.swordart.LostHeart;
import com.yakumosakura.yakumoblade.specialeffects.theblades.SharpnessBlade;
import com.yakumosakura.yakumoblade.specialeffects.theblades.YouKai;
import com.yakumosakura.yakumoblade.specialeffects.touhouSE.*;
import com.yakumosakura.yakumoblade.specialeffects.xross.DualSoul;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.entity.player.Player;

public class SEEventBus {
    public static void OnUpdate(SlashBladeEvent.UpdateEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        VeryGreen.onSlashBladeUpdate(event);
        VeryBlue.onSlashBladeUpdate(event);
        Eternal.onSlashBladeUpdate(event);
        FoxSoul.onUpdate(event);
        StarSoul.onSlashBladeUpdate(event);
        BlackSlashBreak.onSlashBladeUpdate(event);
        GreenStarSoul.onSlashBladeUpdate(event);
        StarsName.onSlashBladeUpdate(event);
        SunSoul.onSlashBladeUpdate(event);
        ThunderSoul.onSlashBladeUpdate(event);
        Gapgap.onSlashBladeUpdate(event);
        HexGam.onSlashBladeUpdate(event);
        FinalAllMake.Update(event);
        YouKai.onSlashBladeUpdate(event);
        MakeGap.onUpdate(event);
        YuyukoSe.onLivingUpdate(event);
        GreatSunSoul.UpdateEvent(event);
    }


    public static void DoSlash(SlashBladeEvent.DoSlashEvent event) {
        if (!(event.getUser() instanceof Player player)) return;
        float roll = player.getRandom().nextInt(720) - event.getRoll();
        DualSoul.onDoDualSlash(event, roll);
        BlueFoxSoul.DoSlashEvent(event);
        BlackSlashBreak.onDoingSlash(event);
        StarsName.onDoingSlash(event);
        FoxSoulEX.onDoingSlash(event);
        StarDriveEdge.onDoingSlash(event, roll);
        ThunderSoul.onDoSlash(event);
        Highfrequency.onDoingSlash(event);
        ProudSoulDrive.onDoingSlash(event, roll);
        FinalAllMake.DoSlashRvent(event);
        DreamSE.OnDoingSlash(event);
        ExMode.doSlash(event);
        YouKai.doSlashBladeDoSlash(event);
        ObsdianFoxSoul.onDoingSlash(event);
        ObsdianFoxSoul.DoSlashEvent(event);
    }

    public static void onSlashHit(SlashBladeEvent.HitEvent event) {
        FoxSoul.onSlashHit(event);
        ThunderSoul.onHit(event);
        Yuyuko.onSlashBladeHit(event);
        FinalAllMake.DoHitEvent(event);
        Highfrequency.onslashbladehit(event);
        SharpnessBlade.onSlashBladeHit(event);
    }

    public static void onLivingHurtEvent(LivingHurtEvent event) {
        ThunderSoul.riseUp(event);
        Gapgap.atDeath(event);
        DreamSE.onLivingHurt(event);
        LwtSE.onLivingHurt(event);
        MakeGap.onLivingDamage(event);
        YuyukoSe.onLivingAttack(event);
        GreatSunSoul.LivingHurtEvent(event);
    }

    public static void onLivingDeathEvent() {
        ServerLivingEntityEvents.AFTER_DEATH.register(LostHeart::onLivingDeathEvent);
    }
}
