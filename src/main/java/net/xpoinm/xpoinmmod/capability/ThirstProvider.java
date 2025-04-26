package net.xpoinm.xpoinmmod.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThirstProvider implements ICapabilitySerializable<CompoundTag> {
    private final ThirstHandler thirstHandler = new ThirstHandler();
    private final LazyOptional<ThirstHandler> optional = LazyOptional.of(() -> thirstHandler);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CapabilityList.THIRST_CAPABILITY.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        return thirstHandler.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        thirstHandler.deserializeNBT(nbt);
    }
}