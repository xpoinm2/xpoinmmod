package net.xpoinm.xpoinmmod;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.xpoinm.xpoinmmod.init.Registration;

@Mod("xpoinmmod")
public class xpoinmmod {
    public static final String MOD_ID = "xpoinmmod";
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public xpoinmmod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        Registration.init();
        MinecraftForge.EVENT_BUS.register(this);
    }
}