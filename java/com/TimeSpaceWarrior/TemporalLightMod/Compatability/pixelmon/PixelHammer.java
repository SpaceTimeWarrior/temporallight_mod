package com.TimeSpaceWarrior.TemporalLightMod.Compatability.pixelmon;

import com.pixelmonmod.pixelmon.config.PixelmonBlocks;
import com.pixelmonmod.pixelmon.items.ItemHammer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;

public class PixelHammer extends ItemHammer {
    String modid;
    String texturename;
    ToolMaterial material;
    float speed;
    public PixelHammer(ToolMaterial material,String Modid, String iconString, String itemName) {
        super(material, iconString, itemName);
        modid = Modid;
        texturename = iconString;
        this.material=material;
        speed = 0.0f;
    }
    public PixelHammer(ToolMaterial material,String Modid, String iconString, String itemName,float speed_offset) {
        super(material, iconString, itemName);
        modid = Modid;
        texturename = iconString;
        this.material=material;
        speed = speed_offset;
    }
    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(modid+":" + texturename);
    }
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        if (par2Block == PixelmonBlocks.anvil) {
            return toolMaterial.getHarvestLevel()+1.0f+speed;
        }

        return 1.0F;
    }
}
