package net.xpoinm.xpoinmmod.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CapabilityList {
    public static final Capability<ThirstHandler> THIRST_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<DiseaseHandler> DISEASE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<FatigueHandler> FATIGUE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
}