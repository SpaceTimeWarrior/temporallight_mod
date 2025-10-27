package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class MagiwoodLeaves extends BlockLeaves {
    public MagiwoodLeaves() {
        super();
    }
    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.blockIcon;
    }

    @Override
    public String[] func_150125_e() {
        return new String[]{this.getUnlocalizedName()};
    }
}
