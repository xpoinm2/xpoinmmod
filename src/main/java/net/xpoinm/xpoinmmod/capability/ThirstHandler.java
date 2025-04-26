package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class ThirstHandler implements INBTSerializable<FloatTag> {
    private float thirst = 20.0F;

    public float getThirst() {
        return thirst;
    }

    public void setThirst(float thirst) {
        this.thirst = thirst;
    }

    public void addThirst(float amount) {
        this.thirst = Math.min(20.0F, this.thirst + amount);
    }

    public void removeThirst(float amount) {
        this.thirst = Math.max(0.0F, this.thirst - amount);
    }

    @Override
    public FloatTag serializeNBT() {
        return FloatTag.valueOf(thirst);
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        this.thirst = nbt.getAsFloat();
    }
}