package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.network.PacketSyncInventory;
import com.TimeSpaceWarrior.TemporalLightMod.tile_entity.HyperSteel_Assembler_TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class HyperSteel_Assembler extends BlockContainer{
    boolean ispowered;
    private static boolean keepInventory = false;
    HyperSteel_Assembler_TileEntity entity;
    public HyperSteel_Assembler(Material steelmat, boolean is_powered) {
        super(steelmat);
        ispowered=is_powered;

        this.setHardness(3.105F);

        if(TLConfig.harvest_lv_floor-1<0) {
            this.setHarvestLevel("pickaxe", TLConfig.harvest_lv_floor - 1);
        }else{
            this.setHarvestLevel("pickaxe", 0);
        }

        this.setResistance(90.0F);
    }

    public static int UpdateBlockState(World world, int x, int y, int z) {
        int i = world.getBlockMetadata(x,y,z);
        TileEntity tileEntity = world.getTileEntity(x,y,z);
        int red = getRedstonePower(world,x,y,z);
        keepInventory = true;
        if(tileEntity instanceof HyperSteel_Assembler_TileEntity ){
            if(((HyperSteel_Assembler_TileEntity) tileEntity).lRed!=red) {
                ((HyperSteel_Assembler_TileEntity) tileEntity).lRed = red;
                if (red > 0) {
                    world.setBlock(x, y, z, BlockRegistry.HYPERSTEEL_ASSEMBLER_powered);
                } else {
                    world.setBlock(x, y, z, BlockRegistry.HYPERSTEEL_ASSEMBLER_unpowered);
                }
            }
        }
        keepInventory = false;
        world.setBlockMetadataWithNotify(x,y,z,i,2);
        if(tileEntity!=null){
            tileEntity.validate();
            world.setTileEntity(x,y,z,tileEntity);
        }
        return red;
    }
    public void SetisPowered(boolean pow){
        this.ispowered = pow;
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(BlockRegistry.HYPERSTEEL_ASSEMBLER_unpowered);
    }
    public void onBlockAdded(World world, int x, int y, int z){
        super.onBlockAdded(world,x,y,z);
    }
    public static int getRedstonePower(World world, int x, int y, int z){
        int red = 0;
        for(int i=0;i<6;i++) {
            int signal = world.getIndirectPowerLevelTo(x, y, z, i);
            if(signal>red){
                red = signal;
            }
        }

        return red;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, net.minecraft.block.Block block, int meta) {
        if (!keepInventory && !world.isRemote) {
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof HyperSteel_Assembler_TileEntity) {
                HyperSteel_Assembler_TileEntity assembler = (HyperSteel_Assembler_TileEntity) tile;
                // Drop all items in the inventory
                for (int i = 0; i < assembler.getSizeInventory(); i++) {
                    ItemStack stack = assembler.getStackInSlot(i);
                    if (stack != null) {
                        float offsetX = world.rand.nextFloat() * 0.8F + 0.1F;
                        float offsetY = world.rand.nextFloat() * 0.8F + 0.1F;
                        float offsetZ = world.rand.nextFloat() * 0.8F + 0.1F;
                        EntityItem entityItem = new EntityItem(
                                world,
                                x + offsetX,
                                y + offsetY,
                                z + offsetZ,
                                stack.copy()
                        );
                        entityItem.motionX = world.rand.nextGaussian() * 0.05F;
                        entityItem.motionY = world.rand.nextGaussian() * 0.05F + 0.2F;
                        entityItem.motionZ = world.rand.nextGaussian() * 0.05F;
                        world.spawnEntityInWorld(entityItem);
                        assembler.setInventorySlotContents(i, null); // Clear the slot
                    }
                }
                assembler.markDirty();
                world.markBlockForUpdate(x, y, z);
                TemporalLightMod.network.sendToAllAround(
                        new PacketSyncInventory(assembler),
                        new NetworkRegistry.TargetPoint(world.provider.dimensionId, x + 0.5, y + 0.5, z + 0.5, 64)
                );
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true; // Early return on client to allow packet send to server
        }

        // Now on server only
        entity = (HyperSteel_Assembler_TileEntity) world.getTileEntity(x, y, z); // Cache entity here if needed
        if (player.isSneaking()) {
            System.out.println("playerIsSneaking");
            if (entity.canCraft(getRedstonePower(world,x,y,z))) {
                entity.CraftItem(); // Removed unnecessary if—CraftItem returns boolean if needed
            }
        } else {
            System.out.println("playerIsInteracting");
            FMLNetworkHandler.openGui(player, TemporalLightMod.instance, TemporalLightMod.HYPERSTEEL_ASSEMBLER_GUIID, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        entity = new HyperSteel_Assembler_TileEntity();
    return entity;
    }
}
