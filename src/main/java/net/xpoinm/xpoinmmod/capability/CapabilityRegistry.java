package net.xpoinm.xpoinmmod.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xpoinm.xpoinmmod.Xpoinmmod;

@Mod.EventBusSubscriber(modid = Xpoinmmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ThirstHandler.class);
        event.register(DiseaseHandler.class);
        event.register(FatigueHandler.class);
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(Xpoinmmod.MOD_ID, "thirst"), new ThirstProvider());
            event.addCapability(new ResourceLocation(Xpoinmmod.MOD_ID, "disease"), new DiseaseProvider());
            event.addCapability(new ResourceLocation(Xpoinmmod.MOD_ID, "fatigue"), new FatigueProvider());
        }
    }
}