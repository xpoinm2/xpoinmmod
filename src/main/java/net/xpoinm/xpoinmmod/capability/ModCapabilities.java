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
import net.xpoinm.xpoinmmod.Xpoinmmod;

@Mod.EventBusSubscriber(modid = Xpoinmmod.MOD_ID)
public class ModCapabilities {
    public static final Capability<SicknessCapability> SICKNESS = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation(Xpoinmmod.MOD_ID, "sickness"),
                    new SicknessProvider()
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        event.getOriginal().getCapability(SICKNESS).ifPresent(oldStore -> {
            event.getPlayer().getCapability(SICKNESS).ifPresent(newStore -> {
                newStore.copyFrom(oldStore);
                syncSickness(event.getPlayer(), newStore.getSickness()); // Добавлена синхронизация
            });
        });
    }

    public static void syncSickness(Player player, float sickness) {
        if (!player.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHandler.INSTANCE.send(
                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                    new SicknessSyncPacket(sickness)
            );
        }
    }
}