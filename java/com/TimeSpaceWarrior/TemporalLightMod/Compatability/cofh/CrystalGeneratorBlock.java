package com.TimeSpaceWarrior.TemporalLightMod.Compatability.cofh;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class CrystalGeneratorBlock extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(Side.CLIENT)
    private IIcon iconActive;
    public static boolean keepInventory = false;

    private static boolean isActive = false;
    protected CrystalGeneratorBlock(Material material) {
        super(material);
        setHardness(3.5F);
        setStepSound(soundTypePiston);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, net.minecraft.block.Block block, int meta) {
        if(!keepInventory&&!world.isRemote){
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof CrystalGeneratorTileEntity){
                CrystalGeneratorTileEntity crystal = (CrystalGeneratorTileEntity) tile;
                ItemStack stack = crystal.fuelSlot;
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
                    crystal.fuelSlot=null;

                }
                stack = crystal.OutputSlot;
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
                    crystal.OutputSlot=null;
                }
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        System.out.println("generator updating");
        return new CrystalGeneratorTileEntity();
    }
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (world.isRemote) return;

        int dir = world.getBlockMetadata(x, y, z);
        if (dir != 0) return;

        // Face player when placed
        EntityPlayer player = world.getClosestPlayer(x + 0.5, y + 0.5, z + 0.5, 5);
        if (player != null) {
            int l = MathHelper.floor_double((player.rotationYaw * 4F / 360F) + 0.5D) & 3;
            world.setBlockMetadataWithNotify(x, y, z, l + 2, 2);
        }
    }
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int direction = MathHelper.floor_double((placer.rotationYaw * 4F / 360F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, direction + 2, 2);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.blockIcon = reg.registerIcon("tmpl_lgt:crystal_generator_side");
        this.iconFront = reg.registerIcon("tmpl_lgt:crystal_generator_front");
        this.iconBottom = reg.registerIcon("tmpl_lgt:crystal_generator_bottom");
        this.iconTop = reg.registerIcon("tmpl_lgt:crystal_generator_top");
        this.iconActive = reg.registerIcon("tmpl_lgt:crystal_generator_front_active");
    }
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) return iconTop;
        if (side == 0) return iconBottom;
        if (side == meta) return isActive ? iconActive : iconFront;
        return blockIcon;
    }

    public static void updateBlockState(boolean active, World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        TileEntity te = world.getTileEntity(x, y, z);

        isActive = active;
        keepInventory = true;
        world.setBlock(x, y, z, BlockRegistryCOFH.CRYSTALGENERATOR);
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        if (te != null) {
            te.validate();
            world.setTileEntity(x, y, z, te);
        }
        keepInventory = false;
    }
}
