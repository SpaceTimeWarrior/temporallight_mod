package com.TimeSpaceWarrior.TemporalLightMod.gui;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.tile_entity.HyperSteel_Assembler_TileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIHyperSteelAssembler extends GuiContainer {
    public HyperSteel_Assembler_TileEntity HypersteelassemblerEntity;
    public static final ResourceLocation background = new ResourceLocation(TemporalLightMod.MODID+":"+"textures/gui/assembler_gui.png");
    public GUIHyperSteelAssembler(InventoryPlayer inventory, HyperSteel_Assembler_TileEntity entity) {
        super(new ContainerHyperSteelAssembler(inventory,entity));
        HypersteelassemblerEntity = entity;
        this.xSize = 176;
        this.ySize = 168;
        //177 icon 32x width and height
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String name = this.HypersteelassemblerEntity.hasCustomInventoryName() ? this.HypersteelassemblerEntity.getInventoryName() : I18n.format(this.HypersteelassemblerEntity.getInventoryName(),new Object[0]);
        this.fontRendererObj.drawString(name,this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2,6,4450072);
        this.fontRendererObj.drawString(I18n.format("container.inventory"),6,this.ySize-96+2,4458872);
        super.drawGuiContainerForegroundLayer(par1, par2);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1f,1f,1f,1f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
        switch (HypersteelassemblerEntity.RedstonePower){
            case 1:
            case 2:
            case 3:
                drawTexturedModalRect( guiLeft+112, guiTop+16, 176, 128, 32, 32);
            break;
            case 4:
            case 5:
            case 6:
                drawTexturedModalRect(guiLeft+112, guiTop+16, 176, 96, 32, 32);
                break;
            case 7:
            case 8:
            case 9:
                drawTexturedModalRect(guiLeft+112, guiTop+16, 176, 64, 32, 32);
                break;
            case 10:
            case 11:
            case 12:
                drawTexturedModalRect(guiLeft+112, guiTop+16, 176, 32, 32, 32);
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                drawTexturedModalRect(guiLeft+112, guiTop+16, 176, 0, 32, 32);
                break;
            default:
                break;
        }
    }
    @Override
    public void updateScreen() {
        super.updateScreen();
    }

}
