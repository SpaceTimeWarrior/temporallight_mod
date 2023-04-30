package net.tsw.temporallight.world.Gen;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;
import net.tsw.temporallight.block.blockRegistry;

public enum TLOreType {
    SYNTHETICTIMECRYSTAL(Lazy.of(blockRegistry.SYNTHETICTIMECRYSTALORE),5,10,120, 10);
    private final Lazy<Block> block;
    private final int maxVeinsize;
    private final int minHeight;
    private final int maxHeight;
    private final int veinsperchunk;

    TLOreType(Lazy<Block> block, int maxVeinsize, int minHeight, int maxHeight, int veinsperchunk) {
        this.block = block;
        this.maxVeinsize = maxVeinsize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsperchunk = veinsperchunk;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinsize() {
        return maxVeinsize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }
    public static TLOreType get(Block block){
        for(TLOreType ore : values()){
            if(block == ore.block){
                return ore;
            }
        }
        return null;
    }
}
