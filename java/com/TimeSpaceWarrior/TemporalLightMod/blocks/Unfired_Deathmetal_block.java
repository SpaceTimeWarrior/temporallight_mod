package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Unfired_Deathmetal_block extends Block {
    public Unfired_Deathmetal_block(Material material) {
        super(material);
        this.setHarvestLevel("pickaxe", 3);
        this.setHardness(6f);
        this.setResistance(6000f);
    }
}
