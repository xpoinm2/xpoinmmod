package net.xpoinm.xpoinmmod.client.hud;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.xpoinm.xpoinmmod.Xpoinmmod;
import net.xpoinm.xpoinmmod.capability.ThirstProvider;
import net.xpoinm.xpoinmmod.capability.DiseaseProvider;
import net.xpoinm.xpoinmmod.capability.FatigueProvider;

@Mod.EventBusSubscriber(modid = Xpoinmmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomHudOverlay {
    private static final ResourceLocation HUD_TEXTURE = new ResourceLocation(Xpoinmmod.MOD_ID, "textures/gui/hud_overlay.png");

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(CustomHudOverlay.class);
    }

    public static final IGuiOverlay CUSTOM_HUD_OVERLAY = (gui, poseStack, partialTick, width, height) -> {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return;

        int x = width / 2;
        int y = height;

        gui.blit(poseStack, x - 91, y - 39, 0, 0, 182, 54, 256, 256);

        int thirstLevel = (int) (minecraft.player.getCapability(ThirstProvider.THIRST_CAPABILITY).orElseThrow(RuntimeException::new).getThirst() * 10);
        int diseaseLevel = (int) (minecraft.player.getCapability(DiseaseProvider.DISEASE_CAPABILITY).orElseThrow(RuntimeException::new).getDisease() * 10);
        int fatigueLevel = (int) (minecraft.player.getCapability(FatigueProvider.FATIGUE_CAPABILITY).orElseThrow(RuntimeException::new).getFatigue() * 10);

        gui.blit(poseStack, x - 89, y - 37, 0, 54, thirstLevel * 2, 5);
        gui.blit(poseStack, x - 89, y - 31, 0, 59, diseaseLevel * 2, 5);
        gui.blit(poseStack, x - 89, y - 25, 0, 64, fatigueLevel * 2, 5);
    };
}