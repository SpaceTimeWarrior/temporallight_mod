package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class MagiwoodPlanks extends BlockWood {
    public static final String[] field_150096_a = new String[] {"magiwood"};
    @SideOnly(Side.CLIENT)
    private IIcon[] field_150095_b;
    public MagiwoodPlanks(Material material) {
        this.setHarvestLevel("axe", TLConfig.harvest_lv_floor);
        this.setHardness(2.0f);
        this.setResistance(50f);
        if(TLConfig.harvest_lv_floor-1<0) {
            this.setHarvestLevel("axe", TLConfig.harvest_lv_floor - 1);
        }else{
            this.setHarvestLevel("axe", 0);
        }
    }

    @Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
        p_149666_3_.add(new ItemStack(BlockRegistry.MAGIWOODPLANK,1));
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int index)
    {
        if (index < 0 || index >= this.field_150095_b.length)
        {
            index = 0;
        }

        return this.field_150095_b[index];
    }
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_150095_b = new IIcon[field_150096_a.length];

        for (int i = 0; i < this.field_150095_b.length; ++i)
        {
            this.field_150095_b[i] = p_149651_1_.registerIcon(TemporalLightMod.MODID +":"+ field_150096_a[i]+"planks");
        }
    }
}
