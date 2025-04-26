package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class ThirstHandler implements INBTSerializable<CompoundTag> {
    private float thirst = 20.0F;
    public static final float MAX_THIRST = 20.0F;
    public static final float MIN_THIRST = 0.0F;

    public float getThirst() {
        return thirst;
    }

    public void setThirst(float thirst) {
        this.thirst = Math.min(MAX_THIRST, Math.max(MIN_THIRST, thirst));
    }

    public void addThirst(float amount) {
        setThirst(this.thirst + amount);
    }

    public void removeThirst(float amount) {
        setThirst(this.thirst - amount);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("thirst", thirst);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.thirst = nbt.getFloat("thirst");
    }
}