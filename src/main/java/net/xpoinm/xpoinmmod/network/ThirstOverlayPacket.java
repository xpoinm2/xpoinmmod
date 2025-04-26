package net.xpoinm.xpoinmmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.xpoinm.xpoinmmod.capability.FatigueProvider;
import net.xpoinm.xpoinmmod.capability.DiseaseProvider;
import net.xpoinm.xpoinmmod.capability.ThirstProvider;

import java.util.function.Supplier;

public class ThirstOverlayPacket {
    private final float thirst;
    private final float disease;
    private final float fatigue;

    public ThirstOverlayPacket(float thirst, float disease, float fatigue) {
        this.thirst = thirst;
        this.disease = disease;
        this.fatigue = fatigue;
    }

    public ThirstOverlayPacket(FriendlyByteBuf buf) {
        this.thirst = buf.readFloat();
        this.disease = buf.readFloat();
        this.fatigue = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(thirst);
        buf.writeFloat(disease);
        buf.writeFloat(fatigue);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                player.getCapability(ThirstProvider.THIRST_CAPABILITY).ifPresent(cap -> cap.setThirst(thirst));
                player.getCapability(DiseaseProvider.DISEASE_CAPABILITY).ifPresent(cap -> cap.setDisease(disease));
                player.getCapability(FatigueProvider.FATIGUE_CAPABILITY).ifPresent(cap -> cap.setFatigue(fatigue));
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public static void register(SimpleChannel channel) {
        channel.registerMessage(
                0,
                ThirstOverlayPacket.class,
                ThirstOverlayPacket::toBytes,
                ThirstOverlayPacket::new,
                ThirstOverlayPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }
}