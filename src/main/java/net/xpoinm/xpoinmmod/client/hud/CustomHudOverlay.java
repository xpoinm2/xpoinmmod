package net.xpoinm.xpoinmmod.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xpoinm.xpoinmmod.Xpoinmmod;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;

@Mod.EventBusSubscriber(modid = Xpoinmmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomHudOverlay {
    private static final ResourceLocation HUD_TEXTURE = new ResourceLocation(Xpoinmmod.MOD_ID, "textures/gui/hud_overlay.png");

    public static final IGuiOverlay CUSTOM_HUD_OVERLAY = (gui, poseStack, partialTick, width, height) -> {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null || minecraft.level == null) return;

        RenderSystem.setShaderTexture(0, HUD_TEXTURE);
        int x = width / 2;

        // Отрисовка фона HUD
        gui.blit(poseStack, x - 91, height - 39, 0, 0, 182, 54, 256, 256);

        // Получение данных возможностей
        int thirstLevel = (int) (minecraft.player.getCapability(ModCapabilities.THIRST_CAPABILITY)
                .orElseThrow(() -> new RuntimeException("Thirst capability missing")).getThirst() * 10);
        int diseaseLevel = (int) (minecraft.player.getCapability(ModCapabilities.DISEASE_CAPABILITY)
                .orElseThrow(() -> new RuntimeException("Disease capability missing")).getDisease() * 10);
        int fatigueLevel = (int) (minecraft.player.getCapability(ModCapabilities.FATIGUE_CAPABILITY)
                .orElseThrow(() -> new RuntimeException("Fatigue capability missing")).getFatigue() * 10);

        // Отрисовка шкал
        gui.blit(poseStack, x - 89, height - 37, 0, 54, thirstLevel * 2, 5);
        gui.blit(poseStack, x - 89, height - 31, 0, 59, diseaseLevel * 2, 5);
        gui.blit(poseStack, x - 89, height - 25, 0, 64, fatigueLevel * 2, 5);
    };

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("custom_hud", CUSTOM_HUD_OVERLAY);
    }
}