package com.TimeSpaceWarrior.TemporalLightMod.gui;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIKitsune extends GuiContainer {
    public EntityKitsune kitsune;
    public static final ResourceLocation background = new ResourceLocation(TemporalLightMod.MODID+":"+"textures/gui/kitsune_inventory.png");

    public GUIKitsune(InventoryPlayer inventory, EntityKitsune entity) {
        super(new ContainerKitsune(inventory,entity));
        kitsune = entity;
        //System.out.println(kitsune);
        this.xSize = 176;
        this.ySize = 256;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1f,1f,1f,1f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String name = this.kitsune.hasCustomNameTag()? this.kitsune.getCustomNameTag() : I18n.format(this.kitsune.getCustomNameTag(),new Object[0]);
        //this.fontRendererObj.drawString(name,this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2,6,4450072);
        if(this.kitsune.hasCustomNameTag()){
            this.fontRendererObj.drawString(this.kitsune.getCustomNameTag()+" "+I18n.format("pkitsune.inventory"), 6, 8, 4458872);
        }else {
            this.fontRendererObj.drawString(I18n.format("kitsune.inventory"), 6, 8, 4458872);
        }
        //this.fontRendererObj.drawString(name,this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2,6,4450072);
        this.fontRendererObj.drawString(I18n.format("pkitsune.inventory"),6,96+32,4458872);
        super.drawGuiContainerForegroundLayer(par1, par2);

    }
}
