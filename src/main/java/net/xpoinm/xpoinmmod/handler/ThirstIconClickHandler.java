package net.xpoinm.xpoinmmod.handler;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xpoinm.xpoinmmod.screen.ThirstOverlayScreen;

@Mod.EventBusSubscriber(modid = Xpoinmmod.MOD_ID, value = Dist.CLIENT)
public class ThirstIconClickHandler {
    @SubscribeEvent
    public static void onMouseClicked(InputEvent.MouseButtonEvent event) { // Исправлено на MouseButtonEvent
        if (event.getButton() == 0 && event.getAction() == 0) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.screen == null) {
                int x = minecraft.getWindow().getGuiScaledWidth() / 2;
                int y = minecraft.getWindow().getGuiScaledHeight();

                // Проверяем, нажата ли иконка жажды
                if (event.getMouseX() >= x - 91 && event.getMouseX() <= x - 71 && event.getMouseY() >= y - 39 && event.getMouseY() <= y - 29) {
                    minecraft.setScreen(new ThirstOverlayScreen());
                }
            }
        }
    }
}