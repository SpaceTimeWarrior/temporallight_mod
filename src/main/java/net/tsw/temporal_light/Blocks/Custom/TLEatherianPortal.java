package net.tsw.temporal_light.Blocks.Custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Blocks.entity.TLEatherian_portal;
import net.tsw.temporal_light.util.TLTagRegistry;
import net.tsw.temporal_light.world.Dimension.TLDimensionRegistry;
import net.tsw.temporal_light.world.Features.portal.TLEatherianTeleporter;
import org.jetbrains.annotations.Nullable;

public class TLEatherianPortal extends BaseEntityBlock {
    public TLEatherianPortal(Properties pProperties) {
        super(pProperties);
    }



    public static final MapCodec<TLEatherianPortal> CODEC = simpleCodec(TLEatherianPortal::new);
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {

        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                MinecraftServer server = entity.level().getServer();
                ResourceKey<Level> destination = entity.level().dimension() == TLDimensionRegistry.EATHERIAN_SLEEP_DIM_LEVEL_KEY ? Level.OVERWORLD : TLDimensionRegistry.EATHERIAN_SLEEP_DIM_LEVEL_KEY;
                if (server != null) {
                    ServerLevel dest = server.getLevel(destination);
                    if (dest != null && server.isNetherEnabled() && !entity.isPassenger()) {
                        entity.level().getProfiler().push("eatherian_portal");
                        entity.setPortalCooldown();
                        entity.changeDimension(dest, new TLEatherianTeleporter(dest));
                        entity.setDeltaMovement(Vec3.ZERO);
                        entity.level().getProfiler().pop();
                    }
                }
            }

        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!this.canSurviveAt(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    public boolean canSurviveAt(Level level, BlockPos pos) {
        return (level.getBlockState(pos.above()).is(TLBlocksRegistry.EATHERIAN_SLEEP_PORTAL.get()) || level.getBlockState(pos.above()).is(TLTagRegistry.Blocks.RAW_MAGIWOOD_BLOCKS)) &&
                (level.getBlockState(pos.below()).is(TLBlocksRegistry.EATHERIAN_SLEEP_PORTAL.get()) || level.getBlockState(pos.below()).is(TLTagRegistry.Blocks.RAW_MAGIWOOD_BLOCKS));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
            for (int j = 0; j < 2; ++j) {
                double d0 = (float) pos.getX() + random.nextFloat();
                double d1 = (float) pos.getY() + random.nextFloat();
                double d2 = (float) pos.getZ() + random.nextFloat();
                double d3 = ((double) random.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double) random.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double) random.nextFloat() - 0.5D) * 0.5D;
                level.addParticle(ParticleTypes.END_ROD, d0, d1, d2, d3, d4, d5);
            }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TLEatherian_portal(pPos,pState);
    }

    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType type) {
        return false;
    }
}
