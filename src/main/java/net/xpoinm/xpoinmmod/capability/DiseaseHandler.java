package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class DiseaseHandler implements INBTSerializable<FloatTag> {
    private float disease = 0.0F;

    public float getDisease() {
        return disease;
    }

    public void setDisease(float disease) {
        this.disease = disease;
    }

    public void addDisease(float amount) {
        this.disease = Math.min(20.0F, this.disease + amount);
    }

    public void removeDisease(float amount) {
        this.disease = Math.max(0.0F, this.disease - amount);
    }

    @Override
    public FloatTag serializeNBT() {
        return FloatTag.valueOf(disease);
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        this.disease = nbt.getAsFloat();
    }
}