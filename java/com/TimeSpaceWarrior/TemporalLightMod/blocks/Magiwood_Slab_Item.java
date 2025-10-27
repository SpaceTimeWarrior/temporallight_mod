package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

public class Magiwood_Slab_Item extends ItemSlab {
    public Magiwood_Slab_Item(Block block) {
        super(block,(BlockSlab) BlockRegistry.MAGIWOODSLAB,(BlockSlab) BlockRegistry.MAGIWOODSLAB_D,false);
        this.setHasSubtypes(true);
    }
}
