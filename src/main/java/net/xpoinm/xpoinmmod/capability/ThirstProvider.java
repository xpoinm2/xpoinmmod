package net.xpoinm.xpoinmmod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ThirstProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<ThirstHandler> THIRST_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private ThirstHandler thirstHandler = null;
    private LazyOptional<ThirstHandler> optionalThirstHandler = null;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == THIRST_CAPABILITY) {
            return optionalThirstHandler.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createThirstHandler();
        nbt.put("thirst", THIRST_CAPABILITY.writeNBT(thirstHandler, null));
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createThirstHandler();
        THIRST_CAPABILITY.readNBT(thirstHandler, null, nbt.get("thirst"));
    }

    private void createThirstHandler() {
        if (this.thirstHandler == null) {
            this.thirstHandler = new ThirstHandler();
            this.optionalThirstHandler = LazyOptional.of(() -> this.thirstHandler);
        }
    }
}