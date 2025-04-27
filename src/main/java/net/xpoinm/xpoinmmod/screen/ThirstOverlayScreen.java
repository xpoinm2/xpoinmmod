package net.xpoinm.xpoinmmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import net.xpoinm.xpoinmmod.Xpoinmmod;
import net.xpoinm.xpoinmmod.capability.ModCapabilities;
import javax.annotation.Nonnull;
import java.util.Objects;

public class ThirstOverlayScreen extends Screen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Xpoinmmod.MOD_ID, "textures/gui/thirst_overlay_screen.png");

    public ThirstOverlayScreen() {
        super(Component.literal("Thirst Overlay"));
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - 256) / 2;
        int y = (this.height - 256) / 2;

        // Проверка на null для minecraft
        if (this.minecraft != null) {
            this.addRenderableWidget(new ExtendedButton(
                    x + 10, y + 230, 100, 20,
                    Component.literal("Close"),
                    button -> this.minecraft.setScreen(null)
            ));
        }
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - 256) / 2;
        int y = (this.height - 256) / 2;
        this.blit(poseStack, x, y, 0, 0, 256, 256);

        // Проверки на null для minecraft и игрока
        if (this.minecraft != null && this.minecraft.player != null) {
            Player player = this.minecraft.player;

            // Использование ModCapabilities вместо Provider'ов
            float thirstLevel = Objects.requireNonNull(
                    player.getCapability(ModCapabilities.THIRST_CAPABILITY).orElse(null)
            ).getThirst();

            float diseaseLevel = Objects.requireNonNull(
                    player.getCapability(ModCapabilities.DISEASE_CAPABILITY).orElse(null)
            ).getDisease();

            float fatigueLevel = Objects.requireNonNull(
                    player.getCapability(ModCapabilities.FATIGUE_CAPABILITY).orElse(null)
            ).getFatigue();

            // Отрисовка шкал
            this.blit(poseStack, x + 10, y + 10, 0, 256, (int) (thirstLevel * 10) * 2, 10);
            this.blit(poseStack, x + 10, y + 30, 0, 266, (int) (diseaseLevel * 10) * 2, 10);
            this.blit(poseStack, x + 10, y + 50, 0, 276, (int) (fatigueLevel * 10) * 2, 10);
        }

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }
}