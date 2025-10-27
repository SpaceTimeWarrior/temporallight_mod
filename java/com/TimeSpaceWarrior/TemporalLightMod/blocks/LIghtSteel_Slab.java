package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class LIghtSteel_Slab extends BlockSlab {
    public static final String[] field_150005_b = new String[] {"lightsteel"};
    public LIghtSteel_Slab(Material oremat, boolean b) {
        super(b,oremat);
        this.setHarvestLevel("pickaxe", TLConfig.harvest_lv_floor+1);
        this.setHardness(4.0f);
        this.setResistance(5000f);
        if(TLConfig.harvest_lv_floor<0) {
            this.setHarvestLevel("pickaxe", TLConfig.harvest_lv_floor +1);
        }else{
            this.setHarvestLevel("pickaxe", 0);
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
