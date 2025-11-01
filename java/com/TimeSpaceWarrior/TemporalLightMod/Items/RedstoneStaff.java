package com.TimeSpaceWarrior.TemporalLightMod.Items;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class RedstoneStaff extends EarthStaff {
    public RedstoneStaff(ToolMaterial material) {
        super(material);
        BL.clear();
        BL.add(Blocks.redstone_block);
        BL.add(Blocks.redstone_wire);
        BL.add(Blocks.lever);
        BL.add(Blocks.dispenser);
        BL.add(Blocks.dropper);
        BL.add(Blocks.unpowered_repeater);
        BL.add(Blocks.unpowered_comparator);
        BL.add(Blocks.piston);
        BL.add(Blocks.hopper);
        BL.add(Blocks.tripwire_hook);
        BL.add(Blocks.trapped_chest);
        BL.add(Blocks.tnt);
    }
}
