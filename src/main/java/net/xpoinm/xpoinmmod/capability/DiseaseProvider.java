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

public class DiseaseProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<DiseaseHandler> DISEASE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private DiseaseHandler diseaseHandler = null;
    private LazyOptional<DiseaseHandler> optionalDiseaseHandler = null;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == DISEASE_CAPABILITY) {
            return optionalDiseaseHandler.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createDiseaseHandler();
        nbt.put("disease", DISEASE_CAPABILITY.writeNBT(diseaseHandler, null));
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createDiseaseHandler();
        DISEASE_CAPABILITY.readNBT(diseaseHandler, null, nbt.get("disease"));
    }

    private void createDiseaseHandler() {
        if (this.diseaseHandler == null) {
            this.diseaseHandler = new DiseaseHandler();
            this.optionalDiseaseHandler = LazyOptional.of(() -> this.diseaseHandler);
        }
    }
}