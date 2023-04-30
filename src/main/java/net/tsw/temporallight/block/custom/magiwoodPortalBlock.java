package net.tsw.temporallight.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.world.dimension.TLDimensionRegistry;
import net.tsw.temporallight.world.dimension.TLMagiwoodTeleporter;

import javax.annotation.Nullable;
public class magiwoodPortalBlock extends PaneBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public magiwoodPortalBlock(Properties properties) {
        super(properties);
    }
    public static void createPortal(BlockPos thisPos, BlockPos destinationPos, ServerWorld destinationWorld) {
        double xxx = destinationPos.getX();
        double yyy = destinationPos.getY();
        double zzz = destinationPos.getZ()-1;
        setblock(xxx-1,yyy-1,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx,       yyy-1,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx+1,yyy-1,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx+2,yyy-1,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx-1,yyy,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx      ,yyy,zzz,blockRegistry.MAGIWOODPORTAL.get(),destinationWorld);
        setblock(xxx+1,yyy,zzz,blockRegistry.MAGIWOODPORTAL.get(),destinationWorld);
        setblock(xxx+2,yyy,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx-1,yyy+1,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx,       yyy+1,zzz,blockRegistry.MAGIWOODPORTAL.get(),destinationWorld);
        setblock(xxx+1,yyy+1,zzz,blockRegistry.MAGIWOODPORTAL.get(),destinationWorld);
        setblock(xxx+2,yyy+1,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx-1,yyy+2,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx,yyy+2,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx+1,yyy+2,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
        setblock(xxx+2,yyy+2,zzz,blockRegistry.MAGIWOOD.get(),destinationWorld);
    }
    private static void setblock(double xxx,double yyy,double zzz,Block block,ServerWorld destinationWorld){
        BlockPos Pos0 = new BlockPos(xxx,yyy,zzz);
        destinationWorld.setBlockState(Pos0, block.getDefaultState());
        Pos0 = new BlockPos(xxx,yyy,zzz-1);
        destinationWorld.setBlockState(Pos0,Blocks.AIR.getDefaultState());
        Pos0 = new BlockPos(xxx,yyy,zzz-2);
        destinationWorld.setBlockState(Pos0,Blocks.AIR.getDefaultState());
        Pos0 = new BlockPos(xxx,yyy,zzz+1);
        destinationWorld.setBlockState(Pos0,Blocks.AIR.getDefaultState());
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            if (!player.isCrouching()) {
                MinecraftServer server = worldIn.getServer();

                if (server != null) {
                    if (worldIn.getDimensionKey() == TLDimensionRegistry.MAGIWOOD_DIM) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            player.changeDimension(overWorld, new TLMagiwoodTeleporter(pos, false));
                        }
                    } else {
                        ServerWorld MAGIWOOD_DIM = server.getWorld(TLDimensionRegistry.MAGIWOOD_DIM);
                        if (MAGIWOOD_DIM != null) {
                            player.changeDimension(MAGIWOOD_DIM, new TLMagiwoodTeleporter(pos, true));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    /*
    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote()) {
            if (!entityIn.isCrouching()) {
                MinecraftServer server = worldIn.getServer();

                if (server != null) {
                    if (worldIn.getDimensionKey() == TLDimensionRegistry.MAGIWOOD_DIM) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            entityIn.changeDimension(overWorld, new TLMagiwoodTeleporter(pos, false));
                        }
                    } else {
                        ServerWorld MAGIWOOD_DIM = server.getWorld(TLDimensionRegistry.MAGIWOOD_DIM);
                        if (MAGIWOOD_DIM != null) {
                            entityIn.changeDimension(MAGIWOOD_DIM, new TLMagiwoodTeleporter(pos, true));
                        }
                    }
                }
            }
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }*/
}

