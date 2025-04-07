package net.tsw.temporal_light.Blocks.Custom.sign.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tsw.temporal_light.Blocks.entity.TLBlockEntityRegistry;

public class TLSignHangingBlockEntity extends SignBlockEntity {
    public TLSignHangingBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(TLBlockEntityRegistry.TLHANGINGSIGN.get(),pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return TLBlockEntityRegistry.TLHANGINGSIGN.get();
    }
}
