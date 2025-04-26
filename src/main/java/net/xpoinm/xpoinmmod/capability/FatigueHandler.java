package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class FatigueHandler implements INBTSerializable<FloatTag> {
    private float fatigue = 0.0F;

    public float getFatigue() {
        return fatigue;
    }

    public void setFatigue(float fatigue) {
        this.fatigue = fatigue;
    }

    public void addFatigue(float amount) {
        this.fatigue = Math.min(20.0F, this.fatigue + amount);
    }

    public void removeFatigue(float amount) {
        this.fatigue = Math.max(0.0F, this.fatigue - amount);
    }

    @Override
    public FloatTag serializeNBT() {
        return FloatTag.valueOf(fatigue);
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        this.fatigue = nbt.getAsFloat();
    }
}