package net.tsw.temporal_light.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tsw.temporal_light.Temporal_Light;

public class TL_HS_Assembler_screen extends AbstractContainerScreen<TL_HS_Assembler_Menu> {
    @SuppressWarnings("depricated")
    private static final ResourceLocation TEXTURE =ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/gui/assembler_gui.png");
    public TL_HS_Assembler_screen(TL_HS_Assembler_Menu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width - imageWidth)/2;
        int y = (height - imageHeight)/2;
        pGuiGraphics.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);
        renderRedstonePower(pGuiGraphics,x,y);
    }

    private void renderRedstonePower(GuiGraphics pGuiGraphics, int x, int y) {
        if(menu.isPowered()){
            switch(menu.get_power()){
                case 0:
                    break;
                case 1:
                case 2:
                case 3:
                    pGuiGraphics.blit(TEXTURE,x + 112, y + 16, 176, 128, 32, 32);
                    break;
                case 4:
                case 5:
                case 6:
                    pGuiGraphics.blit(TEXTURE,x + 112, y + 16, 176, 96, 32, 32);
                    break;
                case 7:
                case 8:
                case 9:
                    pGuiGraphics.blit(TEXTURE,x + 112, y + 16, 176, 64, 32, 32);
                    break;
                case 10:
                case 11:
                case 12:
                    pGuiGraphics.blit(TEXTURE,x + 112, y + 16, 176, 32, 32, 32);
                    break;
                case 13:
                case 14:
                case 15:
                    pGuiGraphics.blit(TEXTURE,x + 112, y + 16, 176, 0, 32, 32);
                    break;
            }
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics,pMouseX,pMouseY,pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics,pMouseX,pMouseY);
    }


}
