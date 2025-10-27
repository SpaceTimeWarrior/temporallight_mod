package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class HyperSteel_Block extends Block {
    public HyperSteel_Block(Material mat) {
        super(mat);
        if(TLConfig.harvest_lv_floor-1<0) {
            this.setHarvestLevel("pickaxe", TLConfig.harvest_lv_floor - 1);
        }else{
            this.setHarvestLevel("pickaxe", 0);
        }
    }
}
