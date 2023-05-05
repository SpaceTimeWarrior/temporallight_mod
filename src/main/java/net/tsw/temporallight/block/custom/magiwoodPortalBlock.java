package net.tsw.temporallight.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.world.dimension.TLDimensionRegistry;
import net.tsw.temporallight.world.dimension.TLMagiwoodTeleporter;

import javax.annotation.Nullable;
import java.util.Random;

public class magiwoodPortalBlock extends PaneBlock {



    public magiwoodPortalBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState());
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getDimensionType().isNatural() && worldIn.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && random.nextInt(2000) < worldIn.getDifficulty().getId()) {
            while(worldIn.getBlockState(pos).matchesBlock(this)) {
                pos = pos.down();
            }

            if (worldIn.getBlockState(pos).canEntitySpawn(worldIn, pos, EntityType.ZOMBIFIED_PIGLIN)) {
                Entity entity = EntityType.ZOMBIFIED_PIGLIN.spawn(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos.up(), SpawnReason.STRUCTURE, false, false);
                if (entity != null) {
                    entity.setPortalCooldown();
                }
            }
        }

    }



    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble();
            double d2 = (double)pos.getZ() + rand.nextDouble();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            int j = rand.nextInt(2) * 2 - 1;
            if (!worldIn.getBlockState(pos.west()).matchesBlock(this) && !worldIn.getBlockState(pos.east()).matchesBlock(this)) {
                d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
            } else {
                d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
            }

            worldIn.addParticle(ParticleTypes.MYCELIUM, d0, d1, d2, d3, d4, d5);
        }

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
            if (!player.isCrouching()&&!player.hasPortalCooldown() && player.canChangeDimension()) {
                player.setPortalCooldown();
                MinecraftServer server = worldIn.getServer();

                if (server != null) {
                    if (worldIn.getDimensionKey() == TLDimensionRegistry.MAGIWOOD_DIM) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            player.changeDimension(overWorld, new TLMagiwoodTeleporter(pos, false,overWorld));
                        }
                    } else {
                        ServerWorld MAGIWOOD_DIM = server.getWorld(TLDimensionRegistry.MAGIWOOD_DIM);
                        if (MAGIWOOD_DIM != null) {
                            player.changeDimension(MAGIWOOD_DIM, new TLMagiwoodTeleporter(pos, true,MAGIWOOD_DIM));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote()) {
            if (!entityIn.hasPortalCooldown() && entityIn.canChangeDimension()) {
                entityIn.setPortalCooldown();
                MinecraftServer server = worldIn.getServer();
                if (server != null) {
                    if (worldIn.getDimensionKey() == TLDimensionRegistry.MAGIWOOD_DIM) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                            entityIn.changeDimension(overWorld, new TLMagiwoodTeleporter(pos, false,overWorld));
                        }
                    } else {
                        ServerWorld MAGIWOOD_DIM = server.getWorld(TLDimensionRegistry.MAGIWOOD_DIM);
                        if (MAGIWOOD_DIM != null) {
                            entityIn.changeDimension(MAGIWOOD_DIM, new TLMagiwoodTeleporter(pos, true,MAGIWOOD_DIM));
                        }
                    }
                }
            }
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

}

