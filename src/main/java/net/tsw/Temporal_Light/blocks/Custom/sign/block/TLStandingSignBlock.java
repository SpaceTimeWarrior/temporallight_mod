package net.tsw.Temporal_Light.blocks.Custom.sign.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.tsw.Temporal_Light.blocks.Custom.sign.entities.TLSignBlockEntity;

public class TLStandingSignBlock extends StandingSignBlock {
    public TLStandingSignBlock( Properties properties,WoodType woodType) {
        super( properties,woodType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TLSignBlockEntity(pPos,pState);
    }
}
