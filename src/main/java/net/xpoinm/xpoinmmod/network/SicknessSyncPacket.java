package net.xpoinm.xpoinmmod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;
import java.util.function.Supplier;

public class SicknessSyncPacket {
    private final float sickness;

    public SicknessSyncPacket(float sickness) {
        this.sickness = sickness;
    }

    public static void encode(SicknessSyncPacket msg, FriendlyByteBuf buf) {
        buf.writeFloat(msg.sickness);
    }

    public static SicknessSyncPacket decode(FriendlyByteBuf buf) {
        return new SicknessSyncPacket(buf.readFloat());
    }

    public static void handle(SicknessSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(ModCapabilities.SICKNESS)
                        .ifPresent(cap -> cap.setSickness(msg.sickness));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}