package net.xpoinm.xpoinmmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.xpoinm.xpoinmmod.Xpoinmmod;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Xpoinmmod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        INSTANCE.registerMessage(id++, SicknessSyncPacket.class,
                SicknessSyncPacket::encode,
                SicknessSyncPacket::decode,
                SicknessSyncPacket::handle);

        INSTANCE.registerMessage(id++, ThirstOverlayPacket.class,
                ThirstOverlayPacket::encode,
                SicknessSyncPacket::decode,
                ThirstOverlayPacket::handle);
    }
}