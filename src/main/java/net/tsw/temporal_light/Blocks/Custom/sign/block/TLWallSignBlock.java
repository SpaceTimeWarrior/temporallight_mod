package net.tsw.temporal_light.Blocks.Custom.sign.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.tsw.temporal_light.Blocks.Custom.sign.entities.TLSignBlockEntity;

public class TLWallSignBlock extends WallSignBlock {
    public TLWallSignBlock( Properties properties,WoodType woodType) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TLSignBlockEntity(pPos,pState);
    }
}
