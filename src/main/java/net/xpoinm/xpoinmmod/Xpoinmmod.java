package net.xpoinm.xpoinmmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.xpoinm.xpoinmmod.init.Registration;
import net.xpoinm.xpoinmmod.network.NetworkHandler;

@Mod(Xpoinmmod.MOD_ID)
public class Xpoinmmod {
    public static final String MOD_ID = "xpoinmmod";

    public Xpoinmmod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        // Регистрация всех компонентов
        Registration.init(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            NetworkHandler.register(); // Регистрация пакетов
        });
    }
}