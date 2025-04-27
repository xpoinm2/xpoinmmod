package net.xpoinm.xpoinmmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;

import java.util.Optional;
import java.util.function.Supplier;

public class ThirstOverlayPacket {
    private final float thirst;
    private final float disease;
    private final float fatigue;

    // Конструктор для отправки данных
    public ThirstOverlayPacket(float thirst, float disease, float fatigue) {
        this.thirst = thirst;
        this.disease = disease;
        this.fatigue = fatigue;
    }

    // Конструктор для десериализации из буфера
    public ThirstOverlayPacket(FriendlyByteBuf buf) {
        this.thirst = buf.readFloat();
        this.disease = buf.readFloat();
        this.fatigue = buf.readFloat();
    }

    // Сериализация данных в буфер
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFloat(thirst);
        buf.writeFloat(disease);
        buf.writeFloat(fatigue);
    }

    // Десериализация данных из буфера
    public static ThirstOverlayPacket decode(FriendlyByteBuf buffer) {
        return new ThirstOverlayPacket(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
    }

    // Обработка пакета на сервере
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                // Используем ModCapabilities вместо Provider'ов
                player.getCapability(ModCapabilities.THIRST_CAPABILITY).ifPresent(cap -> cap.setThirst(thirst));
                player.getCapability(ModCapabilities.DISEASE_CAPABILITY).ifPresent(cap -> cap.setDisease(disease));
                player.getCapability(ModCapabilities.FATIGUE_CAPABILITY).ifPresent(cap -> cap.setFatigue(fatigue));
            }
        });
        ctx.get().setPacketHandled(true);
    }

    // Регистрация пакета
    public static void register(SimpleChannel channel) {
        channel.registerMessage(
                0,
                ThirstOverlayPacket.class,
                ThirstOverlayPacket::toBytes,
                ThirstOverlayPacket::new,
                ThirstOverlayPacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
    }
}