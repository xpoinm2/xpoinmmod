package net.xpoinm.xpoinmmod.capability;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "xpoinmmod", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityRegistry {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

        event.register(ThirstHandler.class);
        event.register(DiseaseHandler.class);
        event.register(FatigueHandler.class);
    }
}