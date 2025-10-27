package com.TimeSpaceWarrior.TemporalLightMod.tile_entity;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.blocks.HyperSteel_Assembler;
import com.TimeSpaceWarrior.TemporalLightMod.network.PacketSyncInventory;
import com.TimeSpaceWarrior.TemporalLightMod.network.PacketSyncSlot;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import org.lwjgl.Sys;

public class HyperSteel_Assembler_TileEntity extends TileEntity implements ISidedInventory {
    public HyperSteel_Assembler block;
    public int RedstonePower = 0;
    public int lRed = 0;
    private String localizedName;
    static int fuelslot = 9;
    static int outputslot = 10;
    private boolean needsSync = false;
    private int syncDelay = 0;

    private static final int[] topSlots = new int[]{fuelslot};
    private static final int[] bottomSlots = new int[]{outputslot};
    private static final int[] sideSlots = new int[]{0,1,2,3,4,5,6,7,8};
    private ItemStack[] slots = new ItemStack[11];

    /*
    input slots 0-8
    fuel = slot 9
    output = slot 10
     */

    public void setGuiDisplayName(String name){
        this.localizedName = name;
    }
    public int getSizeInventory(){
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.slots[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        if(this.slots[slot] != null){
            ItemStack itemstack;

            if(this.slots[slot].stackSize <= quantity ){
                itemstack = this.slots[slot];
                this.slots[slot] = null;
                markDirty();
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                return itemstack;
            }else{
                itemstack = this.slots[slot].splitStack(quantity);
                markDirty();
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                if(this.slots[slot].stackSize == 0) {
                    this.slots[slot] = null;
                }

                return itemstack;
            }
        }else{
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if(this.slots[slot]!= null) {
            ItemStack itemstack = this.slots[slot];
            this.slots[slot] = null;
            return itemstack;
        }
        return null;
    }


    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        slots[slot] = stack;
        if(stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public HyperSteel_Assembler_TileEntity(){
        super();
    }
    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            this.RedstonePower = HyperSteel_Assembler.UpdateBlockState(worldObj,xCoord,yCoord,zCoord);

                    //markDirty();
                    //worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    //needsSync = false;


            //System.out.println("red:"+RedstonePower);
        }
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName()? this.localizedName: "container.hypersteel_assembler";
    }

    public boolean hasCustomInventoryName() {
        return this.localizedName !=null && this.localizedName.length()>0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(xCoord,yCoord,zCoord)!=this?false:player.getDistanceSq((double)xCoord,(double) yCoord,(double) zCoord)<=64.0D;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if(slot == outputslot){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        if(slot == 0){
            return bottomSlots;
        }else if(slot == 1){
            return topSlots;
        }else{
            return sideSlots;
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return this.isItemValidForSlot(slot,stack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack stack, int side) {
        return i == outputslot;
    }
    public void print_slots(){
        System.out.println("slots:"+slots[0]+" "+slots[1]+" "+slots[2]+" "+slots[3]+" "+slots[4]+" "+slots[5]+" "+slots[6]+" "+slots[7]+" "+slots[8]+" "+slots[9]+" "+slots[10]);
    }
    public boolean CraftItem() {
        print_slots();
        ItemStack stack = TemporalLightMod.assem_recipe.RecipeCraft(this.slots);
        if (stack != null) {
            if (slots[10] == null) {
                //System.out.println("inserting into a empty slot");
                slots[outputslot]=stack.copy();
                markDirty();
                if (slots[10] != null) {
                    //System.out.println("Sending PacketSyncSlot with stack: " + slots[10]);
                    TemporalLightMod.network.sendToAllAround(
                            new PacketSyncInventory(xCoord, yCoord, zCoord, 10, slots[10]),
                            new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 64)
                    );
                }


            } else if (slots[10].getItem()==stack.getItem()) {
                //System.out.println("inserting into a non-empty slot");
                slots[10].stackSize += stack.stackSize;
                markDirty();
                TemporalLightMod.network.sendToAllAround(
                        //new PacketSyncSlot(xCoord, yCoord, zCoord, 10, slots[10]),
                        new PacketSyncInventory(xCoord, yCoord, zCoord, 10, slots[10]),
                        new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64)
                );
            } else{
                System.out.println("unknown Error:"+slots+"::"+stack);
            }
            for (int i = 0; i < 10; i++) {
                if(slots[i]!=null) {
                    slots[i].stackSize--;
                    if(slots[i].stackSize<=0){
                        slots[i]=null;
                    }
                    markDirty();
                    /*TemporalLightMod.network.sendToAllAround(
                            //new PacketSyncSlot(xCoord, yCoord, zCoord, 10, slots[10]),
                            new PacketSyncInventory(this),
                            new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64)
                    );*/
                }
            }
            //needsSync = true;
            //syncDelay = 1;
            if (!worldObj.isRemote) {
                //System.out.println("Spawning lightning at: " + xCoord + ", " + yCoord + ", " + zCoord);
                EntityLightningBolt lightning = new EntityLightningBolt(worldObj, xCoord + 0.5, yCoord + 1.0, zCoord + 0.5);
                worldObj.spawnEntityInWorld(lightning);
                worldObj.playSoundEffect(xCoord + 0.5, yCoord + 1.0, zCoord + 0.5, "ambient.weather.thunder", 10000.0F, 0.8F + worldObj.rand.nextFloat() * 0.2F);
            }
            print_slots();

            if (worldObj != null && !worldObj.isRemote) {
                print_slots();

                //System.out.println("calling sendToAllAround");
                TemporalLightMod.network.sendToAllAround(
                        //new PacketSyncSlot(xCoord, yCoord, zCoord, 10, slots[10]),
                        new PacketSyncInventory(this),
                        new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64)
                );
                //System.out.println("marked dirty");
                markDirty();
                print_slots();
               // System.out.println("marking block for update");
                //System.out.println("Sending slot sync packet: " + slots[10]);
                int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                print_slots();
                //worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord), 2);
            }else{
               System.out.println("world is remote");
            }

            return true;
            //worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }else{
            return false;
        }

    }
    public boolean CraftItem(int signal) {
        print_slots();
        ItemStack stack = TemporalLightMod.assem_recipe.RecipeCraft(this.slots,signal);
        if (stack != null) {
            if (slots[10] == null) {
                //System.out.println("inserting into a empty slot");
                slots[outputslot]=stack.copy();
                markDirty();
                if (slots[10] != null) {
                   // System.out.println("Sending PacketSyncSlot with stack: " + slots[10]);
                    TemporalLightMod.network.sendToAllAround(
                            new PacketSyncInventory(xCoord, yCoord, zCoord, 10, slots[10]),
                            new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 64)
                    );
                }


            } else if (slots[10].getItem()==stack.getItem()) {
                //System.out.println("inserting into a non-empty slot");
                slots[10].stackSize += stack.stackSize;
                markDirty();
                TemporalLightMod.network.sendToAllAround(
                        //new PacketSyncSlot(xCoord, yCoord, zCoord, 10, slots[10]),
                        new PacketSyncInventory(xCoord, yCoord, zCoord, 10, slots[10]),
                        new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64)
                );
            } else{
                System.out.println("unknown Error:"+slots+"::"+stack);
            }
            for (int i = 0; i < 10; i++) {
                if(slots[i]!=null) {
                    slots[i].stackSize--;
                    if(slots[i].stackSize<=0){
                        slots[i]=null;
                    }
                    markDirty();
                    /*TemporalLightMod.network.sendToAllAround(
                            //new PacketSyncSlot(xCoord, yCoord, zCoord, 10, slots[10]),
                            new PacketSyncInventory(this),
                            new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64)
                    );*/
                }
            }
            if (!worldObj.isRemote) {
                //System.out.println("Spawning lightning at: " + xCoord + ", " + yCoord + ", " + zCoord);
                EntityLightningBolt lightning = new EntityLightningBolt(worldObj, xCoord + 0.5, yCoord + 1.0, zCoord + 0.5);
                worldObj.spawnEntityInWorld(lightning);
                worldObj.playSoundEffect(xCoord + 0.5, yCoord + 1.0, zCoord + 0.5, "ambient.weather.thunder", 10000.0F, 0.8F + worldObj.rand.nextFloat() * 0.2F);
            }
            //needsSync = true;
            //syncDelay = 1;
            print_slots();

            if (worldObj != null && !worldObj.isRemote) {
                print_slots();

                //System.out.println("calling sendToAllAround");
                TemporalLightMod.network.sendToAllAround(
                        //new PacketSyncSlot(xCoord, yCoord, zCoord, 10, slots[10]),
                        new PacketSyncInventory(this),
                        new NetworkRegistry.TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64)
                );
               // System.out.println("marked dirty");
                markDirty();
                print_slots();
                System.out.println("marking block for update");
                //System.out.println("Sending slot sync packet: " + slots[10]);
                int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta, 2);
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                print_slots();
                //worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord), 2);
            }else{
                System.out.println("world is remote");
            }

            return true;
            //worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }else{
            return false;
        }

    }

    @Override
    public Packet getDescriptionPacket() {
        //System.out.println("Sending description packet");
        //print_slots();
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        //System.out.println("Received data packet");
        readFromNBT(pkt.func_148857_g()); // apply inventory update
        //print_slots();
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                NBTTagCompound slotTag = new NBTTagCompound();
                slotTag.setByte("Slot", (byte) i);
                slots[i].writeToNBT(slotTag);
                tagList.appendTag(slotTag);
            }
        }
        compound.setTag("Items", tagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList tagList = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        slots = new ItemStack[getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound slotTag = tagList.getCompoundTagAt(i);
            int slot = slotTag.getByte("Slot") & 255;
            slots[slot] = ItemStack.loadItemStackFromNBT(slotTag);
        }
    }


    public boolean canCraft() {
        /*
    input slots 0-8
    fuel = slot 9
    output = slot 10
     */
        if(this.slots[0]==null&&this.slots[1]==null&&this.slots[2]==null&&this.slots[3]==null&&this.slots[4]==null&&this.slots[5]==null&&this.slots[6]==null&&this.slots[7]==null&&this.slots[8]==null&&this.slots[9]==null){
            return false;
        }else if(TemporalLightMod.assem_recipe.RecipeMatches(this.slots)){
            return true;
        }
        return false;
    }
    public boolean canCraft(int signal) {
        /*
    input slots 0-8
    fuel = slot 9
    output = slot 10
     */
        if(this.slots[0]==null&&this.slots[1]==null&&this.slots[2]==null&&this.slots[3]==null&&this.slots[4]==null&&this.slots[5]==null&&this.slots[6]==null&&this.slots[7]==null&&this.slots[8]==null&&this.slots[9]==null){
            return false;
        }else if(TemporalLightMod.assem_recipe.RecipeMatches(this.slots,signal)){
            return true;
        }
        return false;
    }
}
