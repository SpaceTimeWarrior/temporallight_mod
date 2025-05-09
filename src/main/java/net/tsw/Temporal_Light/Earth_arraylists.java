package net.tsw.Temporal_Light;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;

public class Earth_arraylists {
    public ArrayList<Block> redb= new ArrayList<>();
    public ArrayList<Block> earthdb = new ArrayList<>();
    public Earth_arraylists(){
        redb.add(Blocks.REDSTONE_BLOCK);
        redb.add(Blocks.REDSTONE_WIRE);
        redb.add(Blocks.LEVER);
        redb.add(Blocks.DISPENSER);
        redb.add(Blocks.DROPPER);
        redb.add(Blocks.OBSERVER);
        redb.add(Blocks.REPEATER);
        redb.add(Blocks.COMPARATOR);
        redb.add(Blocks.PISTON);
        redb.add(Blocks.STICKY_PISTON);
        redb.add(Blocks.OBSERVER);
        redb.add(Blocks.HOPPER);
        redb.add(Blocks.TRIPWIRE_HOOK);
        redb.add(Blocks.TNT);
        earthdb.add(Blocks.DIRT);
        earthdb.add(Blocks.GRASS_BLOCK);
        earthdb.add(Blocks.SAND);
        earthdb.add(Blocks.STONE);
        earthdb.add(Blocks.NETHERRACK);
        earthdb.add(Blocks.END_STONE);
        earthdb.add(Blocks.REDSTONE_WIRE);
    }
}
