package net.tsw.temporal_light.Blocks.Custom.sign.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.tsw.temporal_light.Blocks.Custom.sign.entities.TLSignHangingBlockEntity;

public class TLWallHangingSignBlock extends WallHangingSignBlock {
    public TLWallHangingSignBlock( Properties properties,WoodType woodType) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TLSignHangingBlockEntity(pPos,pState);
    }
}
