package cn.mmf.energyblade;

import cn.mmf.energyblade.energy.FEBladeStorage;
import cn.sh1rocu.sfaddons.util.ItemEnergyStorageHelper;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;

public class PowerSwitchPacket {
    public static final ResourceLocation ID = new ResourceLocation(Energyblade.MODID, "power_switch");

    public static void toBytes(FriendlyByteBuf buf, String message) {
        buf.writeUtf(message);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl context, FriendlyByteBuf buf, PacketSender packetSender) {
        String message = buf.readUtf(Short.MAX_VALUE);
        server.execute(() -> {
            if (player.isSpectator())
                return;

            ItemStack mainHandItem = player.getMainHandItem();
            if (CapabilitySlashBlade.BLADESTATE.maybeGet(mainHandItem).isEmpty())
                return;

            ItemEnergyStorageHelper.fromStack(mainHandItem).ifPresent(energy -> {
                if (energy instanceof FEBladeStorage bladeFE) {
                    try (Transaction tx = Transaction.openOuter()) {
                        if (!bladeFE.isPowered() && bladeFE.extract(bladeFE.getPowerupExtract(), tx) == bladeFE.getPowerupExtract()) {
                            bladeFE.setPowered(true);
                            ServerLevel serverLevel = player.serverLevel();
                            var random = serverLevel.random;
                            for (int i = 0; i < 32; ++i) {
                                double xDist = (random.nextFloat() * 2.0F - 1.0F);
                                double yDist = (random.nextFloat() * 2.0F - 1.0F);
                                double zDist = (random.nextFloat() * 2.0F - 1.0F);
                                if (!(xDist * xDist + yDist * yDist + zDist * zDist > 1.0D)) {
                                    double x = player.getX(xDist / 4.0D);
                                    double y = player.getY(0.5D + yDist / 4.0D);
                                    double z = player.getZ(zDist / 4.0D);
                                    serverLevel.sendParticles(ParticleTypes.PORTAL, x, y, z, 0, xDist, yDist + 0.2D, zDist,
                                            1);
                                }
                            }
                            player.playNotifySound(SoundEvents.TRIDENT_THUNDER, SoundSource.PLAYERS, 2.5F, 1F);
                            tx.commit();
                        } else {
                            bladeFE.setPowered(false);
                            mainHandItem.getOrCreateTagElement("bladeState").getCompound("Energy").putBoolean("isPowered", false);
                            player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1F, 1F);
                        }
                    }
                }
            });
        });
    }

}
