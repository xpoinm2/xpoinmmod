package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.xpoinm.xpoinmmod.network.NetworkHandler;
import net.xpoinm.xpoinmmod.network.SicknessSyncPacket;
import net.xpoinm.xpoinmmod.xpoinmmod;

@Mod.EventBusSubscriber(modid = xpoinmmod.MOD_ID)
public class ModCapabilities {
    public static final Capability<SicknessCapability> SICKNESS =
            CapabilityManager.get(new CapabilityToken<>() {});

    public static void register() {
        // Современный способ регистрации capability (Forge 1.19+)
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation(xpoinmmod.MOD_ID, "sickness"),
                    new ICapabilityProvider() {
                        final LazyOptional<SicknessCapability> optional =
                                LazyOptional.of(SicknessCapability::new);

                        @Override
                        public <T> LazyOptional<T> getCapability(Capability<T> cap, net.minecraft.core.Direction side) {
                            return SICKNESS.orEmpty(cap, optional);
                        }
                    }
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(SICKNESS).ifPresent(oldStore -> {
                event.getEntity().getCapability(SICKNESS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    public static void syncSickness(Player player, float sickness) {
        if (!player.getCommandSenderWorld().isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHandler.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                    new SicknessSyncPacket(sickness)
            );
        }
    }
}