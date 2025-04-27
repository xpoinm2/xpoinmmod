package net.xpoinm.xpoinmmod.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xpoinm.xpoinmmod.Xpoinmmod;

@Mod.EventBusSubscriber(modid = Xpoinmmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomHudOverlay {
    // Объявляем экземпляр IGuiOverlay
    private static final IGuiOverlay CUSTOM_HUD_OVERLAY = (gui, poseStack, partialTick, width, height) -> {
        render(gui, poseStack, partialTick, width, height);
    };

    public static void render(ForgeGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Minecraft minecraft = Minecraft.getInstance();
        int screenWidth = width;
        int screenHeight = height;

        // Настройки масштаба
        int guiScale = Math.max(1, (int) minecraft.options.guiScale().get());
        float scale = 1.0f / guiScale;
        poseStack.pushPose();
        poseStack.scale(scale, scale, 1.0f);

        // Размер текстуры
        int textureWidth = 64;
        int textureHeight = 64;

        // Позиция в правом верхнем углу (после масштабирования)
        int posX = (int) ((screenWidth / scale) - textureWidth - 10);
        int posY = 10;

        // Отрисовка
        RenderSystem.setShaderTexture(0, new ResourceLocation("xpoinmmod", "textures/hud_overlay.png"));
        GuiComponent.blit(poseStack, posX, posY, 0, 0, textureWidth, textureHeight, textureWidth, textureHeight);

        poseStack.popPose();
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("custom_hud", CUSTOM_HUD_OVERLAY);
    }
}