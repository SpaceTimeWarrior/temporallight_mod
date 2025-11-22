package com.TimeSpaceWarrior.TemporalLightMod.Compatability.cofh;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.Items.ItemCrystal;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrystalGeneratorTileEntity extends TileEntity implements IEnergyProvider, ISidedInventory {
    public EnergyStorage storage = new EnergyStorage(1000000,0,4096);
    public ItemStack fuelSlot = null;
    public ItemStack OutputSlot = null;
    public int burnTime = 0;
    public int maxBurnTime = 0;
    public int lastFuelRF=0;


    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        //LOG.fatal("extracting energy");
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        //LOG.fatal("energy stored");
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        //LOG.fatal("max energy stored");
        return storage.getMaxEnergyStored();
    }

    public static boolean isElectricCrystal(Item item){
        for(int i=0;i<ItemRegistry.ItemCrystalRegistry.size();i++){
            if(ItemRegistry.ItemCrystalRegistry.get(i).getItem().equals(item)){
                return true;
            }
        }
        return false;
    }
    public static int getElectricCrystalRF(Item item){
        if(isElectricCrystal(item)){
            for(int i=0;i<ItemRegistry.ItemCrystalRegistry.size();i++){
                if(ItemRegistry.ItemCrystalRegistry.get(i).getItem().equals(item)){
                    return ItemRegistry.ItemCrystalRegistry.get(i).getNum();
                }
            }
            return 0;
        }else{
            return 0;
        }
    }
    @Override
    public void updateEntity() {
        super.updateEntity();
        //LOG.fatal("tick");
        if(worldObj.isRemote){return;}
        //LOG.fatal("world is not remote");
        //LOG.fatal("bt:"+burnTime+" FS:"+fuelSlot+" OS:"+OutputSlot);
        boolean wasBurning = burnTime > 0;
        if (burnTime <= 0 && fuelSlot != null && fuelSlot.getItem() instanceof ItemCrystal) {
            ItemCrystal crystalItem = (ItemCrystal) fuelSlot.getItem();
            int rfValue = getElectricCrystalRF(crystalItem);
            //LOG.fatal("rf of item:"+rfValue);
            if(rfValue>0){
                lastFuelRF = rfValue;
                //LOG.fatal("output");
                burnTime = rfValue/2;
                maxBurnTime = rfValue/2;
                fuelSlot.stackSize--;
                if (OutputSlot == null) {
                    OutputSlot = new ItemStack(ItemRegistry.NULL_CRYSTAL_ESSENCE);
                } else if (OutputSlot.stackSize < OutputSlot.getMaxStackSize()) {
                    OutputSlot.stackSize++;
                }

                if (fuelSlot.stackSize <= 0) fuelSlot = null;
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                markDirty();
            }else{
                //LOG.fatal("RF is 0");
                ejectCrystal();
            }
        }
        if (burnTime > 0) {
            int generate = Math.min(lastFuelRF, storage.getMaxEnergyStored() - storage.getEnergyStored());
            //LOG.fatal("generating"+generate);
            storage.setEnergyStored(storage.getEnergyStored()+generate);
            burnTime--;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            markDirty();
        }
        if(storage.getEnergyStored()>0) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity te = worldObj.getTileEntity(
                        xCoord + dir.offsetX,
                        yCoord + dir.offsetY,
                        zCoord + dir.offsetZ
                );

                if (te instanceof cofh.api.energy.IEnergyReceiver && fuelSlot != null) {
                    cofh.api.energy.IEnergyReceiver receiver = (cofh.api.energy.IEnergyReceiver) te;
                    if (receiver.canConnectEnergy(dir.getOpposite())) {
                        ItemCrystal crystalItem = (ItemCrystal) fuelSlot.getItem();
                        int rfValue = 0;
                        if(storage.getEnergyStored()<256){
                            rfValue=0;
                        }else if(storage.getEnergyStored()<1024){
                            rfValue=256;
                        }else if(storage.getEnergyStored()<4096){
                            rfValue=1024;
                        }else{
                            rfValue=4096;
                        }
                        IEnergyReceiver ier = (IEnergyReceiver) te;
                        rfValue = ier.receiveEnergy(dir.getOpposite(), rfValue, false);
                        if(rfValue>0){
                            storage.setEnergyStored(storage.getEnergyStored()-rfValue);
                        }
                    }
                }
            }

        }
        if (wasBurning != (burnTime > 0)) {
            CrystalGeneratorBlock.updateBlockState(burnTime > 0, worldObj, xCoord, yCoord, zCoord);
        }else{
            CrystalGeneratorBlock.updateBlockState(storage.getEnergyStored()>0, worldObj, xCoord, yCoord, zCoord);
        }
    }

    private void ejectCrystal() {
        if (fuelSlot != null) {
            EntityItem entity = new EntityItem(worldObj,
                    xCoord + 0.5, yCoord + 1.2, zCoord + 0.5,
                    fuelSlot.copy());
            entity.motionY = 0.1;
            worldObj.spawnEntityInWorld(entity);
            fuelSlot = null;
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            markDirty();
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
        nbt.setInteger("BurnTime", burnTime);
        nbt.setInteger("MaxBurnTime", maxBurnTime);
        nbt.setInteger("LastRF",lastFuelRF);
        if (fuelSlot != null) {
            NBTTagCompound fuelTag = new NBTTagCompound();
            fuelSlot.writeToNBT(fuelTag);
            nbt.setTag("Fuel", fuelTag);
        }
        if (OutputSlot != null) {
            NBTTagCompound outTag = new NBTTagCompound();
            OutputSlot.writeToNBT(outTag);
            nbt.setTag("Output", outTag);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
        burnTime = nbt.getInteger("BurnTime");
        maxBurnTime = nbt.getInteger("MaxBurnTime");
        lastFuelRF = nbt.getInteger("LastRF");
        if (nbt.hasKey("Fuel")) {
            fuelSlot = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Fuel"));
        }
        if (nbt.hasKey("Output")) {
            OutputSlot = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("Output"));
        }
    }
    @Override
    public int getSizeInventory() {
        return 2; // fuel + output
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slot == 0 ? fuelSlot : OutputSlot;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = slot == 0 ? fuelSlot : OutputSlot;
        if (stack != null) {
            if (stack.stackSize <= amount) {
                ItemStack result = stack;
                if (slot == 0) fuelSlot = null;
                else OutputSlot = null;
                markDirty();
                return result;
            } else {
                ItemStack split = stack.splitStack(amount);
                if (stack.stackSize == 0) {
                    if (slot == 0) fuelSlot = null;
                    else OutputSlot = null;
                }
                markDirty();
                return split;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (slot == 0) fuelSlot = null;
        else OutputSlot = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot == 0) {
            fuelSlot = stack;
        } else {
            OutputSlot = stack;
        }
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "Crystal Generator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(net.minecraft.entity.player.EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 0 && stack != null && stack.getItem() instanceof ItemCrystal;
    }

    // Hopper directions
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? new int[]{1} : new int[]{0};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return slot == 0 && side != 0 && isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == 1 && side == 0;
    }
    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return true;
    }
}
