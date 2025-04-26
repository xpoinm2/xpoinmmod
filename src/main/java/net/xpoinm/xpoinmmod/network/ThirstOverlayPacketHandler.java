package net.xpoinm.xpoinmmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;

public class ThirstOverlayPacketHandler {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Xpoinmmod.MOD_ID, "main"),
            () -> "1",
            (v) -> true,
            (v) -> true
    );

    public static void registerPackets() {
        ThirstOverlayPacket.register(INSTANCE);
    }
}