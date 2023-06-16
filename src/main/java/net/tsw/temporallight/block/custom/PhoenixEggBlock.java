package net.tsw.temporallight.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tsw.temporallight.Entity.Mob.MobRegistry;
import net.tsw.temporallight.Entity.Mob.PheonixEntity;
import net.tsw.temporallight.block.blockRegistry;

import javax.annotation.Nullable;
import java.util.Random;

public class PhoenixEggBlock extends Block {
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final IntegerProperty HATCH = IntegerProperty.create("hatch", 0, 1);
    public float timetillhatch = 2;


    public PhoenixEggBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HATCH, Integer.valueOf(0)));

    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        this.tryTrample(worldIn, pos, entityIn, 100);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (!(entityIn instanceof ZombieEntity)) {
            this.tryTrample(worldIn, pos, entityIn, 3);
        }

        super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
    }

    private void tryTrample(World worldIn, BlockPos pos, Entity trampler, int chances) {
        if (this.canTrample(worldIn, trampler)) {
            if (!worldIn.isRemote && worldIn.rand.nextInt(chances) == 0) {
                BlockState blockstate = worldIn.getBlockState(pos);
                if (blockstate.matchesBlock(blockRegistry.PHOENIXEGG.get())) {
                    this.removeOneEgg(worldIn, pos, blockstate);
                }
            }

        }
    }

    @Override
    public void onBlockExploded(BlockState state, World worldIn, BlockPos pos, Explosion explosion) {

        Random random = new Random(worldIn.getWorldInfo().getGameTime());

        create_mob(random,worldIn,pos);
        super.onBlockExploded(state,worldIn,pos,explosion);
    }

    private void removeOneEgg(World worldIn, BlockPos pos, BlockState state) {

    }


    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        create_mob(rand,worldIn,pos);
        super.animateTick(stateIn, worldIn, pos, rand);
    }

    private void create_mob(Random random, World worldIn, BlockPos pos){
        Entity phoenixentity;
        if(random.nextBoolean()) {
            phoenixentity = MobRegistry.PHEONIX.get().create(worldIn);
        }else{
            phoenixentity = MobRegistry.MALEPHEONIX.get().create(worldIn);
        }
        phoenixentity.setLocationAndAngles((double)pos.getX() + 0.3D + (double)1 * 0.2D, (double)pos.getY(), (double)pos.getZ() + 0.3D, 0.0F, 0.0F);
        worldIn.addEntity(phoenixentity);
    }
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if(timetillhatch<=0) {
            worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            Random rand = new Random(worldIn.getWorldInfo().getGameTime());
            create_mob(random,worldIn,pos);
            replaceBlock(state,Blocks.AIR.getDefaultState(),worldIn,pos,0);
        }else{
            timetillhatch-=(float)random.nextFloat();
        }
        super.randomTick(state, worldIn, pos, random);
    }

    public static boolean hasProperHabitat(IBlockReader reader, BlockPos blockReader) {
        return isProperHabitat(reader, blockReader.down());
    }

    public static boolean isProperHabitat(IBlockReader reader, BlockPos pos) {
        boolean ret = reader.getBlockState(pos).isIn(BlockTags.INFINIBURN_OVERWORLD);
        ret = ret|| reader.getBlockState(pos.down()).isIn(BlockTags.INFINIBURN_OVERWORLD);
        ret = ret|| reader.getBlockState(pos.up()).isIn(BlockTags.INFINIBURN_OVERWORLD);
        ret = ret|| reader.getBlockState(pos.east()).isIn(BlockTags.INFINIBURN_OVERWORLD);
        ret = ret|| reader.getBlockState(pos.west()).isIn(BlockTags.INFINIBURN_OVERWORLD);
        ret = ret|| reader.getBlockState(pos.north()).isIn(BlockTags.INFINIBURN_OVERWORLD);
        ret = ret|| reader.getBlockState(pos.south()).isIn(BlockTags.INFINIBURN_OVERWORLD);
        return ret;
    }
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (hasProperHabitat(worldIn, pos) && !worldIn.isRemote) {
            worldIn.playEvent(2005, pos, 0);
        }

    }

    /**
     * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
     * Block.removedByPlayer
     */
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }


    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos());
        return super.getStateForPlacement(context);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HATCH);
    }

    private boolean canTrample(World worldIn, Entity trampler) {
        if (!(trampler instanceof PheonixEntity) && !(trampler instanceof BatEntity)) {
            if (!(trampler instanceof LivingEntity)) {
                return false;
            } else {
                return trampler instanceof PlayerEntity || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(worldIn, trampler);
            }
        } else {
            return false;
        }
    }
}
