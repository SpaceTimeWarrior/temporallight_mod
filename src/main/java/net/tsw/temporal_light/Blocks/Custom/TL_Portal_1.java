package net.tsw.temporal_light.Blocks.Custom;

import net.minecraft.world.level.block.Block;

public class TL_Portal_1 extends Block {
    public TL_Portal_1(Properties properties) {
        super(properties);
    }
/*
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && !entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            ResourceKey<Level> destination = level.dimension().equals(TLDimensionRegistry.EATHERIAN_SLEEP_DIM_LEVEL_KEY)
                    ? Level.OVERWORLD
                    : TLDimensionRegistry.EATHERIAN_SLEEP_DIM_LEVEL_KEY;

            ServerLevel serverLevel = ((ServerLevel) level).getServer().getLevel(destination);
            if (serverLevel != null) {
                entity.changeDimension(serverLevel);
            }
        }
    }*/
}
