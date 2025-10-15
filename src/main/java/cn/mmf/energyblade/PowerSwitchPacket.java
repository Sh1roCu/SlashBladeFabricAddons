package cn.mmf.energyblade;

import cn.mmf.energyblade.energy.FEBladeStorage;
import cn.sh1rocu.sfaddons.util.ItemEnergyStorageHelper;
import mods.flammpfeil.slashblade.capability.slashblade.CapabilitySlashBlade;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public record PowerSwitchPacket(String message) implements CustomPacketPayload {
    private static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Energyblade.MODID, "power_switch");
    public static final Type<PowerSwitchPacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, PowerSwitchPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            PowerSwitchPacket::message,
            PowerSwitchPacket::new
    );

    public static void toBytes(FriendlyByteBuf buf, String message) {
        buf.writeUtf(message);
    }

    public static void handle(PowerSwitchPacket packet, ServerPlayNetworking.Context context) {
        var message = packet.message();
        var player = context.player();
        context.server().execute(() -> {
            if (player.isSpectator())
                return;

            ItemStack mainHandItem = player.getMainHandItem();
            if (CapabilitySlashBlade.getBladeState(mainHandItem).isEmpty())
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
                            player.playNotifySound(SoundEvents.TRIDENT_THUNDER.value(), SoundSource.PLAYERS, 2.5F, 1F);
                            tx.commit();
                        } else {
                            bladeFE.setPowered(false);
                            CustomData.update(CapabilitySlashBlade.BLADESTATE_COMPONENT, mainHandItem, tag -> {
                                tag.getCompound("Energy").putBoolean("isPowered", false);
                            });
                            player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1F, 1F);
                        }
                    }
                }
            });
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
