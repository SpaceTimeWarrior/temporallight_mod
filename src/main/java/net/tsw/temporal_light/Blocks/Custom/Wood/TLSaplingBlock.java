package net.tsw.temporal_light.Blocks.Custom.Wood;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class TLSaplingBlock extends SaplingBlock {
    private Supplier<Block> alt1;
    private Supplier<Block> alt2;
    private Supplier<Block> alt3;
    public TLSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
        alt1 = null;
        alt2 = null;
        alt3 = null;
    }
    public TLSaplingBlock(TreeGrower treeGrower, Properties properties,Supplier<Block> a1) {
        super(treeGrower, properties);
        alt1 = a1;
        alt2 = a1;
        alt3 = a1;
    }
    public TLSaplingBlock(TreeGrower treeGrower, Properties properties,Supplier<Block> a1,Supplier<Block> a2) {
        super(treeGrower, properties);
        alt1 = a1;
        alt2 = a2;
        alt3 = a2;
    }
    public TLSaplingBlock(TreeGrower treeGrower, Properties properties,Supplier<Block> a1,Supplier<Block> a2,Supplier<Block> a3) {
        super(treeGrower, properties);
        alt1 = a1;
        alt2 = a2;
        alt3 = a3;
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if(alt1!=null){
            return super.mayPlaceOn(pState, pLevel, pPos)||pState.is(alt1.get())||pState.is(alt2.get())||pState.is(alt3.get());
        }else {
            return super.mayPlaceOn(pState, pLevel, pPos);
        }
    }
}
