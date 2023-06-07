package net.tsw.temporallight.ui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.ui.container.assemblerContainer;

public class assemblerScreen extends ContainerScreen<assemblerContainer> {
    private final ResourceLocation GUI = new ResourceLocation(TemporalLight.MOD_ID,"textures/gui/assembler_gui.png");
    public assemblerScreen(assemblerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);

        if(container.isPowered()) {
            this.blit(matrixStack, i + 112, j + 16, 176, 0, 32, 32);
            int redstone = container.getPower();
            TranslationTextComponent redstonetext = new TranslationTextComponent("screen.temporallight.assembler.redstone", redstone);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            fontRenderer.drawText(matrixStack, redstonetext, 200, 108, 0xFF0000);
        }
    }
}
