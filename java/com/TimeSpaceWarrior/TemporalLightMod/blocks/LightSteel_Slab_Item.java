package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;

public class LightSteel_Slab_Item extends ItemSlab {
    public LightSteel_Slab_Item(Block block) {
        super(block,(BlockSlab) BlockRegistry.LIGHTSTEEL_SLAB,(BlockSlab) BlockRegistry.LIGHTSTEEL_SLAB_D,false);
        this.setHasSubtypes(true);
    }
}
