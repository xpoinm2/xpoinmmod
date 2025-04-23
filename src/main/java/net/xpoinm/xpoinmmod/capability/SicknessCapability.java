package net.xpoinm.xpoinmmod.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class SicknessCapability {
    private float sicknessLevel;
    private final float MAX_SICKNESS = 100.0f;
    private final float MIN_SICKNESS = 0.0f;

    public float getSickness() {
        return sicknessLevel;
    }

    public void setSickness(float value) {
        this.sicknessLevel = Math.min(Math.max(value, MIN_SICKNESS), MAX_SICKNESS);
    }

    public void addSickness(Player player, float value) {
        setSickness(this.sicknessLevel + value);
        ModCapabilities.syncSickness(player, this.sicknessLevel);
    }

    public void copyFrom(SicknessCapability source) {
        this.sicknessLevel = source.sicknessLevel;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("sickness", sicknessLevel);
    }

    public void loadNBTData(CompoundTag nbt) {
        sicknessLevel = nbt.getFloat("sickness");
    }
}