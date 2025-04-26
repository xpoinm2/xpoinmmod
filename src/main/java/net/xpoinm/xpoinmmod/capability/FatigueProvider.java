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

public class FatigueProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<FatigueHandler> FATIGUE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private FatigueHandler fatigueHandler = null;
    private LazyOptional<FatigueHandler> optionalFatigueHandler = null;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == FATIGUE_CAPABILITY) {
            return optionalFatigueHandler.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createFatigueHandler();
        nbt.put("fatigue", FATIGUE_CAPABILITY.writeNBT(fatigueHandler, null));
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createFatigueHandler();
        FATIGUE_CAPABILITY.readNBT(fatigueHandler, null, nbt.get("fatigue"));
    }

    private void createFatigueHandler() {
        if (this.fatigueHandler == null) {
            this.fatigueHandler = new FatigueHandler();
            this.optionalFatigueHandler = LazyOptional.of(() -> this.fatigueHandler);
        }
    }
}