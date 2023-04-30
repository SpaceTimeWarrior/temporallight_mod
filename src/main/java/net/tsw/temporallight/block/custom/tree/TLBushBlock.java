package net.tsw.temporallight.block.custom.tree;

import net.minecraft.block.*;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.tsw.temporallight.block.blockRegistry;

import java.lang.reflect.Array;
import java.util.List;


public class TLBushBlock extends Block implements net.minecraftforge.common.IPlantable {
    public TLBushBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        boolean returns = state.matchesBlock(Blocks.GRASS_BLOCK) || state.matchesBlock(Blocks.DIRT) || state.matchesBlock(Blocks.COARSE_DIRT) || state.matchesBlock(Blocks.PODZOL) || state.matchesBlock(Blocks.FARMLAND);
        /*List<Block> bl=Tags.Blocks.DIRT.getAllElements();
        int bls = bl.toArray().length;
        for(int i = 0;i<bls;i++){
            returns = returns&&state.matchesBlock(bl.get(i));
        }*/
        return returns;
    }
    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        if (state.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return worldIn.getBlockState(blockpos).canSustainPlant(worldIn, blockpos, Direction.UP, this);
        return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return type == PathType.AIR && !this.canCollide ? true : super.allowsMovement(state, worldIn, pos, type);
    }
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.offset(facing));
        net.minecraftforge.common.PlantType type = plantable.getPlantType(world, pos.offset(facing));

        if (plant.getBlock() == Blocks.CACTUS)
            return state.matchesBlock(Blocks.CACTUS) || state.matchesBlock(Blocks.SAND) || state.matchesBlock(Blocks.RED_SAND);

        if (plant.getBlock() == Blocks.SUGAR_CANE && this == Blocks.SUGAR_CANE)
            return true;

        if (plantable instanceof TLBushBlock && ((TLBushBlock)plantable).isValidGround(state, world, pos))
            return true;

        if (net.minecraftforge.common.PlantType.DESERT.equals(type)) {
            return this.getBlock() == Blocks.SAND || this.getBlock() == Blocks.TERRACOTTA || this.getBlock() instanceof GlazedTerracottaBlock;
        } else if (net.minecraftforge.common.PlantType.NETHER.equals(type)) {
            return this.getBlock() == Blocks.SOUL_SAND;
        } else if (net.minecraftforge.common.PlantType.CROP.equals(type)) {
            return state.matchesBlock(Blocks.FARMLAND);
        } else if (net.minecraftforge.common.PlantType.CAVE.equals(type)) {
            return state.isSolidSide(world, pos, Direction.UP);
        } else if (net.minecraftforge.common.PlantType.PLAINS.equals(type)) {
            return this.getBlock() == Blocks.GRASS_BLOCK || net.minecraftforge.common.Tags.Blocks.DIRT.contains(this) || this.getBlock() == Blocks.FARMLAND;
        } else if (net.minecraftforge.common.PlantType.WATER.equals(type)) {
            return state.getMaterial() == net.minecraft.block.material.Material.WATER; //&& state.getValue(BlockLiquidWrapper)
        } else if (net.minecraftforge.common.PlantType.BEACH.equals(type)) {
            boolean isBeach = state.matchesBlock(Blocks.GRASS_BLOCK) || net.minecraftforge.common.Tags.Blocks.DIRT.contains(this) || state.matchesBlock(Blocks.SAND) || state.matchesBlock(Blocks.RED_SAND);
            boolean hasWater = false;
            for (Direction face : Direction.Plane.HORIZONTAL) {
                BlockState blockState = world.getBlockState(pos.offset(face));
                net.minecraft.fluid.FluidState fluidState = world.getFluidState(pos.offset(face));
                hasWater |= blockState.matchesBlock(Blocks.FROSTED_ICE);
                hasWater |= fluidState.isTagged(net.minecraft.tags.FluidTags.WATER);
                if (hasWater)
                    break; //No point continuing.
            }
            return isBeach && hasWater;
        }
        return true;
    }
    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }
}

