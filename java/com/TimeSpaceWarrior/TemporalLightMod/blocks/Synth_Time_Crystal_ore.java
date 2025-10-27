package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class Synth_Time_Crystal_ore extends Block {
    public Synth_Time_Crystal_ore(Material p_i45394_1_) {
        super(p_i45394_1_);
        if(TLConfig.harvest_lv_floor-1<0) {
            this.setHarvestLevel("pickaxe", TLConfig.harvest_lv_floor - 1);
        }else{
            this.setHarvestLevel("pickaxe", 0);
        }

    }

    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return ItemRegistry.SYNTHTIMECRYSTALSHARD;
    }
    public int quantityDropped(Random random)
    {
        return 3 + random.nextInt(5);
    }
}
