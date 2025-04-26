package net.xpoinm.xpoinmmod.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import net.xpoinm.xpoinmmod.Xpoinmmod;
import net.xpoinm.xpoinmmod.capability.ThirstProvider;
import net.xpoinm.xpoinmmod.capability.DiseaseProvider;
import net.xpoinm.xpoinmmod.capability.FatigueProvider;

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

        this.addRenderableWidget(new ExtendedButton(x + 10, y + 230, 100, 20, Component.literal("Close"), button -> minecraft.setScreen(null)));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        minecraft.getTextureManager().bind(TEXTURE);
        int x = (this.width - 256) / 2;
        int y = (this.height - 256) / 2;
        this.blit(poseStack, x, y, 0, 0, 256, 256, 256, 256);

        float thirstLevel = minecraft.player.getCapability(ThirstProvider.THIRST_CAPABILITY).orElseThrow(RuntimeException::new).getThirst();
        float diseaseLevel = minecraft.player.getCapability(DiseaseProvider.DISEASE_CAPABILITY).orElseThrow(RuntimeException::new).getDisease();
        float fatigueLevel = minecraft.player.getCapability(FatigueProvider.FATIGUE_CAPABILITY).orElseThrow(RuntimeException::new).getFatigue();

        this.blit(poseStack, x + 10, y + 10, 0, 256, (int) (thirstLevel * 10) * 2, 10);
        this.blit(poseStack, x + 10, y + 30, 0, 266, (int) (diseaseLevel * 10) * 2, 10);
        this.blit(poseStack, x + 10, y + 50, 0, 276, (int) (fatigueLevel * 10) * 2, 10);

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }
}