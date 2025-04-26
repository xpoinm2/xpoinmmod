package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class FatigueHandler implements INBTSerializable<CompoundTag> {
    private float fatigue = 0.0F;
    public static final float MAX_FATIGUE = 20.0F;
    public static final float MIN_FATIGUE = 0.0F;

    public float getFatigue() {
        return fatigue;
    }

    public void setFatigue(float fatigue) {
        this.fatigue = Math.min(MAX_FATIGUE, Math.max(MIN_FATIGUE, fatigue));
    }

    public void addFatigue(float amount) {
        setFatigue(this.fatigue + amount);
    }

    public void removeFatigue(float amount) {
        setFatigue(this.fatigue - amount);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("fatigue", fatigue);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.fatigue = nbt.getFloat("fatigue");
    }
}