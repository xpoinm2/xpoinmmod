package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public class DiseaseHandler implements INBTSerializable<CompoundTag> {
    private float disease = 0.0F;
    public static final float MAX_DISEASE = 20.0F;
    public static final float MIN_DISEASE = 0.0F;

    public float getDisease() {
        return disease;
    }

    public void setDisease(float disease) {
        this.disease = Math.min(MAX_DISEASE, Math.max(MIN_DISEASE, disease));
    }

    public void addDisease(float amount) {
        setDisease(this.disease + amount);
    }

    public void removeDisease(float amount) {
        setDisease(this.disease - amount);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("disease", disease);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.disease = nbt.getFloat("disease");
    }
}