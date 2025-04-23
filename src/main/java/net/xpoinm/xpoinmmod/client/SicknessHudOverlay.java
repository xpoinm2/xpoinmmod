package net.xpoinm.xpoinmmod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;
import net.xpoinm.xpoinmmod.xpoinmmod;

@Mod.EventBusSubscriber(modid = xpoinmmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SicknessHudOverlay {
    private static final int BAR_WIDTH = 81;
    private static final int BAR_HEIGHT = 9;
    private static final int MARGIN_RIGHT = 10;
    private static final int FOOD_BAR_HEIGHT = 39;

    public static final IGuiOverlay HUD_SICKNESS = (gui, poseStack, partialTick, width, height) -> {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;

        mc.player.getCapability(ModCapabilities.SICKNESS).ifPresent(sickness -> {
            int x = width - BAR_WIDTH - MARGIN_RIGHT;
            int y = height - FOOD_BAR_HEIGHT;

            GuiComponent.fill(poseStack, x, y, x + BAR_WIDTH, y + BAR_HEIGHT, 0x80000000);
            int fillWidth = (int)(BAR_WIDTH * (sickness.getSickness() / 100f));
            GuiComponent.fill(poseStack, x, y, x + fillWidth, y + BAR_HEIGHT, 0xFFAA0000);

            String text = String.format("%.0f%%", sickness.getSickness());
            mc.font.draw(poseStack, text, x + BAR_WIDTH + 5, y + 1, 0xFFFFFFFF);
        });
    };

    @SubscribeEvent
    public static void registerOverlays(net.minecraftforge.client.event.RegisterGuiOverlaysEvent event) {
        event.registerAbove(VanillaGuiOverlay.FOOD_LEVEL.id(), "sickness", HUD_SICKNESS);
    }
}