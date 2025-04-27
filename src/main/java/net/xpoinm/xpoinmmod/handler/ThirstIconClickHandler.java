package net.xpoinm.xpoinmmod.handler;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.xpoinm.xpoinmmod.Xpoinmmod;
import net.xpoinm.xpoinmmod.screen.ThirstOverlayScreen;

@Mod.EventBusSubscriber(
        modid = Xpoinmmod.MOD_ID,
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class ThirstIconClickHandler {
    private static final int ICON_X = 10;
    private static final int ICON_Y = 49;

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseButton.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (event.getButton() == 0) { // Левая кнопка мыши
            int scaledWidth = mc.getWindow().getGuiScaledWidth();
            int scaledHeight = mc.getWindow().getGuiScaledHeight();

            // Получение координат мыши через MouseHandler
            double mouseX = mc.mouseHandler.xpos() * (double) scaledWidth / mc.getWindow().getWidth();
            double mouseY = mc.mouseHandler.ypos() * (double) scaledHeight / mc.getWindow().getHeight();

            // Проверка клика по иконке
            if (mouseX >= scaledWidth / 2.0 + ICON_X &&
                    mouseX <= scaledWidth / 2.0 + ICON_X + 9 &&
                    mouseY >= scaledHeight - ICON_Y &&
                    mouseY <= scaledHeight - ICON_Y + 9) {
                mc.setScreen(new ThirstOverlayScreen());
                event.setCanceled(true);
            }
        }
    }
}