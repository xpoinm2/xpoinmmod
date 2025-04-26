package net.xpoinm.xpoinmmod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiseaseProvider implements ICapabilitySerializable<CompoundTag> {
    private final DiseaseHandler diseaseHandler = new DiseaseHandler();
    private final LazyOptional<DiseaseHandler> optional = LazyOptional.of(() -> diseaseHandler);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CapabilityList.DISEASE_CAPABILITY.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        return diseaseHandler.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        diseaseHandler.deserializeNBT(nbt);
    }
}