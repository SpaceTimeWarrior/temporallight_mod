package net.tsw.Temporal_Light.blocks.Custom.Wood;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraft.core.Direction;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import org.jetbrains.annotations.Nullable;

public class TLRotatedPillarBlock extends RotatedPillarBlock {
    public TLRotatedPillarBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(TLBlocksRegistry.MAGIWOODLOG.get())) {
                return TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(TLBlocksRegistry.MAGIWOOD.get())) {
                return TLBlocksRegistry.MAGIWOODSTRIPPED.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
