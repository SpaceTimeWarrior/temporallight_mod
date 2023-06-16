package net.tsw.temporallight.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.world.dimension.TLDimensionRegistry;
import net.tsw.temporallight.world.dimension.TLMagiwoodTeleporter;

import java.util.Random;

public class earth44portalBlock extends customBlock {
    public RegistryKey<World> dim;
    public RegistryKey<World> dim2;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    //public TileEntity createNewTileEntity(IBlockReader worldIn) {
      //  return new PortalTileEntity();
   // }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    public earth44portalBlock(Properties properties) {
        super(properties);
        properties.doesNotBlockMovement();
        this.setDefaultState(this.stateContainer.getBaseState());
        dim = TLDimensionRegistry.EARTH_44;
        dim2 = World.OVERWORLD;
    }
    public earth44portalBlock(Properties properties, RegistryKey<World> dim){
        super(properties);
        properties.doesNotBlockMovement();
        this.setDefaultState(this.stateContainer.getBaseState());
        this.dim = dim;
        dim2 = World.OVERWORLD;
    }
    public earth44portalBlock(Properties properties, RegistryKey<World> dim, RegistryKey<World> dim2){
        super(properties);
        properties.notSolid();
        this.setDefaultState(this.stateContainer.getBaseState());
        this.dim = dim;
        this.dim2 = dim2;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if(TemporalLight.portalcooldown>0){
            TemporalLight.portalcooldown--;
        }
        this.properties.notSolid();
        super.tick(state, worldIn, pos, rand);
    }

    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        tick(state,worldIn,pos,random);
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
    private boolean hascooldown(PlayerEntity player){
        if (TemporalLight.portalcooldown>0){
            player.setPortalCooldown();
        }
        return TemporalLight.portalcooldown>0;
    }
    private boolean hascooldown(Entity player){
        if (TemporalLight.portalcooldown>0){
            player.setPortalCooldown();
        }
        return TemporalLight.portalcooldown>0;
    }
    private boolean hascooldown(){
        return TemporalLight.portalcooldown>0;
    }
    private void tock(){
        if(TemporalLight.portalcooldown>0){
            TemporalLight.portalcooldown--;
        }
        this.properties.notSolid();
    }
    public Entity changeDimension(Entity player,ServerWorld world,BlockPos pos,boolean insideDim,int teleporternumber){
        player.setPortalCooldown();
        if(teleporternumber==0){
            return player.changeDimension(world);
        }else if(teleporternumber == 1){
           return player.changeDimension(world);
        }else if(teleporternumber ==2) {
            return player.changeDimension(world, new TLMagiwoodTeleporter(pos, insideDim, world));
        }
        return player.changeDimension(world, new TLMagiwoodTeleporter(pos, insideDim, world));
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        onEntityCollision(state,worldIn,pos,player);
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (worldIn instanceof ServerWorld && !entityIn.isPassenger() && !entityIn.isBeingRidden()&&!entityIn.hasPortalCooldown() && entityIn.canChangeDimension() && VoxelShapes.compare(VoxelShapes.create(entityIn.getBoundingBox().offset((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getShape(worldIn, pos), IBooleanFunction.AND)) {
            RegistryKey<World> registrykey = worldIn.getDimensionKey() == dim ? dim2 : dim;
            ServerWorld serverworld = ((ServerWorld)worldIn).getServer().getWorld(registrykey);
            if (serverworld == null) {
                return;
            }
            boolean inside = false;
            if(worldIn.getDimensionKey()==dim){
                inside = true;
            }
            entityIn.setPortalCooldown();
            changeDimension(entityIn,serverworld,pos,inside,2);
        }

    }

}