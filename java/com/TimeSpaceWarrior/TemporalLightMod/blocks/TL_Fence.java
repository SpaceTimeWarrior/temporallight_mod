package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class TL_Fence extends BlockFence {
    public TL_Fence(String texturepath, Material material) {
        super(texturepath, material);
    }
    public TL_Fence(String texturepath, Material material,int hvl,int hvt) {
        super(texturepath, material);
        if(hvl<0){
            hvl = 0;
        }
        switch(hvt){
            default:
            case 0:
                this.setHarvestLevel("pickaxe",hvl);
                break;
            case 1:
                this.setHarvestLevel("axe",hvl);
                break;
            case 2:
                this.setHarvestLevel("spade",hvl);

        }
    }
    public static boolean func_149825_a(Block p_149825_0_)
    {
        return p_149825_0_ == Blocks.fence || p_149825_0_ == Blocks.nether_brick_fence|| p_149825_0_ == BlockRegistry.HYPERSTEELFENCE||p_149825_0_==BlockRegistry.LIGHTSTEELFENCE||p_149825_0_==BlockRegistry.MAGIWOODFENCE;
    }
}
