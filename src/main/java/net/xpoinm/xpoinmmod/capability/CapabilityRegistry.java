package net.xpoinm.xpoinmmod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.world.entity.player.Player;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {
    public static Capability<ThirstHandler> THIRST_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static Capability<DiseaseHandler> DISEASE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static Capability<FatigueHandler> FATIGUE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(IEventBus bus) {
        bus.addListener(CapabilityRegistry::commonSetup);
        bus.addListener(CapabilityRegistry::loadComplete);
        MinecraftForge.EVENT_BUS.register(CapabilityRegistry.class);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(ThirstHandler.class, new Capability.IStorage<ThirstHandler>() {
                @Override
                public Tag writeNBT(Capability<ThirstHandler> capability, ThirstHandler instance, Direction side) {
                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(Capability<ThirstHandler> capability, ThirstHandler instance, Direction side, Tag nbt) {
                    instance.deserializeNBT((FloatTag) nbt);
                }
            }, ThirstHandler::new);

            CapabilityManager.INSTANCE.register(DiseaseHandler.class, new Capability.IStorage<DiseaseHandler>() {
                @Override
                public Tag writeNBT(Capability<DiseaseHandler> capability, DiseaseHandler instance, Direction side) {
                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(Capability<DiseaseHandler> capability, DiseaseHandler instance, Direction side, Tag nbt) {
                    instance.deserializeNBT((FloatTag) nbt);
                }
            }, DiseaseHandler::new);

            CapabilityManager.INSTANCE.register(FatigueHandler.class, new Capability.IStorage<FatigueHandler>() {
                @Override
                public Tag writeNBT(Capability<FatigueHandler> capability, FatigueHandler instance, Direction side) {
                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(Capability<FatigueHandler> capability, FatigueHandler instance, Direction side, Tag nbt) {
                    instance.deserializeNBT((FloatTag) nbt);
                }
            }, FatigueHandler::new);
        });
    }

    @SubscribeEvent
    public static void loadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.injectCapabilities();
        });
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<?> event) {
        if (event.getObject() instanceof Player) {
            Player player = (Player) event.getObject();
            event.addCapability(new ResourceLocation(Xpoinmmod.MOD_ID, "thirst"), new ThirstProvider(player));
            event.addCapability(new ResourceLocation(Xpoinmmod.MOD_ID, "disease"), new DiseaseProvider(player));
            event.addCapability(new ResourceLocation(Xpoinmmod.MOD_ID, "fatigue"), new FatigueProvider(player));
        }
    }
}