package net.xpoinm.xpoinmmod.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xpoinm.xpoinmmod.Xpoinmmod;

@Mod.EventBusSubscriber(modid = Xpoinmmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
    // Объявление возможностей через CapabilityToken
    public static final Capability<ThirstHandler> THIRST_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<FatigueHandler> FATIGUE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<DiseaseHandler> DISEASE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    // Регистрация возможностей
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ThirstHandler.class);
        event.register(FatigueHandler.class);
        event.register(DiseaseHandler.class);
    }

    // Прикрепление возможностей к игроку
    @SubscribeEvent
    public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation(Xpoinmmod.MOD_ID, "thirst_data"),
                    new ThirstProvider()
            );
            event.addCapability(
                    new ResourceLocation(Xpoinmmod.MOD_ID, "fatigue_data"),
                    new FatigueProvider()
            );
            event.addCapability(
                    new ResourceLocation(Xpoinmmod.MOD_ID, "disease_data"),
                    new DiseaseProvider()
            );
        }
    }
}