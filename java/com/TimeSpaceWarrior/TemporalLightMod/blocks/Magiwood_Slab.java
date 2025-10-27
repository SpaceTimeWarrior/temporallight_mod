package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class Magiwood_Slab extends BlockSlab {
    public static final String[] field_150005_b = new String[] {"magiwood"};
    public Magiwood_Slab(Material woodmat, boolean doubled) {
        super(doubled,woodmat);
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
    public String func_150002_b(int p_150002_1_) {
        if (p_150002_1_ < 0 || p_150002_1_ >= field_150005_b.length)
        {
            p_150002_1_ = 0;
        }

        return super.getUnlocalizedName() + "." + field_150005_b[p_150002_1_];
    }
}
