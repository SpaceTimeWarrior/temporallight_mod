package net.tsw.Temporal_Light.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TLEatherian_portal extends TheEndPortalBlockEntity {
    public TLEatherian_portal(BlockPos pos, BlockState state) {
        super(TLBlockEntityRegistry.EATHERIAN_PORTAL.get(), pos, state);
    }
    @Override
    public boolean shouldRenderFace(Direction direction) {
        return this.getLevel() == null || Block.shouldRenderFace(this.getBlockState(), this.getLevel(), this.getBlockPos(), direction, this.getBlockPos().relative(direction));
    }
}
