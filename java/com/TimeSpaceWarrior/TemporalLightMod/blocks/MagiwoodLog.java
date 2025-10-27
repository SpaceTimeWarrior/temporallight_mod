package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class MagiwoodLog extends BlockLog {
    @SideOnly(Side.CLIENT)
    protected IIcon iconSide;
    @SideOnly(Side.CLIENT)
    protected IIcon iconTop;

    public MagiwoodLog(Material woodmat) {
        super();
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister IconRegister) {
        this.iconSide = IconRegister.registerIcon(TemporalLightMod.MODID+":magiwooodlog");
        this.iconTop = IconRegister.registerIcon(TemporalLightMod.MODID+ ":magiwooodlog_top");
    }
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_)
    {
        return this.iconSide;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return this.iconTop;
    }
}
