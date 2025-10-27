package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;

public class HyperSteel_Slab_Item extends ItemSlab {
    public HyperSteel_Slab_Item(Block block) {
        super(block,(BlockSlab) BlockRegistry.HYPERSTEEL_SLAB,(BlockSlab) BlockRegistry.HYPERSTEEL_SLAB_D,false);
        this.setHasSubtypes(true);
    }
}
