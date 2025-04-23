package net.xpoinm.xpoinmmod.init;

import net.minecraftforge.common.MinecraftForge;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;
import net.xpoinm.xpoinmmod.client.SicknessHudOverlay;
import net.xpoinm.xpoinmmod.network.NetworkHandler;

public class Registration {
    public static void init() {
        ModItems.register();
        ModCapabilities.register();
        NetworkHandler.register();
    }
}