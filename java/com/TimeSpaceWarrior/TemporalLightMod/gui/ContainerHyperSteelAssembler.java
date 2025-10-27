package com.TimeSpaceWarrior.TemporalLightMod.gui;

import com.TimeSpaceWarrior.TemporalLightMod.tile_entity.HyperSteel_Assembler_TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHyperSteelAssembler extends Container {
    private HyperSteel_Assembler_TileEntity HypersteelassemblerEntity;
    public int LastRedstonePower;
    public ContainerHyperSteelAssembler(InventoryPlayer inventory, HyperSteel_Assembler_TileEntity entity) {
        HypersteelassemblerEntity = entity;
        add_grid_slots(0,8,9,20,3,3,entity);
        this.add_grid_slot(9,80,31,entity);
        this.add_grid_slot(10,121,53,entity);
        this.add_Inventory(8,86,inventory);
        this.add_Inventory_Hotbar(8,144,inventory);
    }
    public void forceSync() {
        detectAndSendChanges();
    }

    private void add_grid_slot(int slot,int x,int y,HyperSteel_Assembler_TileEntity entity){
        this.addSlotToContainer(new SlotAssembler(entity,slot,x,y,entity));
    }
    private void add_grid_slots(int first_slot,int last_slot,int x,int y,int rows,int cols,HyperSteel_Assembler_TileEntity entity){
        int x_offset = 18;
        int y_offset = 18;
        int cntr = first_slot;
        if(last_slot+1-first_slot==rows*cols){
            for(int i = 0; i<rows;i++){
                for(int j = 0;j<cols;j++){
                    this.addSlotToContainer(new SlotAssembler(entity,cntr,x+(j*x_offset),y+(i*y_offset),entity));
                    cntr++;
                }
            }
        }
    }
    private void add_Inventory(int x, int y, InventoryPlayer inventory){
        int x_offset = 18;
        int y_offset = 18;
        int cntr = 9;
        int rows = 3;
        int cols = 9;
        for(int i = 0; i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                this.addSlotToContainer(new Slot(inventory,cntr,x+j*x_offset,y+i*y_offset));
                cntr++;
            }
        }
    }
    private void add_Inventory_Hotbar(int x,int y,InventoryPlayer inventory){
        int x_offset = 18;
        int cntr = 0;
        int cols = 9;
            for (int j = 0; j < cols; j++) {
                this.addSlotToContainer(new Slot(inventory,cntr,x+j*x_offset,y));
                cntr++;
            }
    }
    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this,0,HypersteelassemblerEntity.RedstonePower);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            itemstack = stackInSlot.copy();

            // Output slot (slot 10)
            if (index == 10) {
                if (!mergeItemStack(stackInSlot, 11, 47, true)) {
                    return null;
                }
                slot.onSlotChange(stackInSlot, itemstack);
            }
            // Assembler inventory (slots 0–9)
            else if (index < 11) {
                if (!mergeItemStack(stackInSlot, 11, 47, false)) {
                    return null;
                }
            }
            // Player inventory (slots 11–46)
            else {
                // Try to place into assembler input slots (0–8)
                if (!mergeItemStack(stackInSlot, 0, 9, false)) {
                    // Try fuel/signal slot (slot 9)
                    if (!mergeItemStack(stackInSlot, 9, 10, false)) {
                        return null;
                    }
                }
            }

            if (stackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (stackInSlot.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stackInSlot);
        }

        return itemstack;
    }


    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for(int i=0;i<this.crafters.size();i++){
            ICrafting iCrafting = (ICrafting) this.crafters.get(i);
            if(this.LastRedstonePower!=this.HypersteelassemblerEntity.RedstonePower){
                iCrafting.sendProgressBarUpdate(this,0,this.HypersteelassemblerEntity.RedstonePower);
            }
        }
        this.LastRedstonePower=this.HypersteelassemblerEntity.RedstonePower;
    }


    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int newvalue) {
        if(slot == 0){
            this.HypersteelassemblerEntity.RedstonePower=newvalue;
        }
        super.updateProgressBar(slot, newvalue);
    }

}
