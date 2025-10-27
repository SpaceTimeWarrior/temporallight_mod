package com.TimeSpaceWarrior.TemporalLightMod.gui;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.InventoryKitsune;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerKitsune extends Container {
    public EntityKitsune kitsune;
    public IInventory tamedInventory;
    public IInventory armorInventory;
    public ContainerKitsune(InventoryPlayer inventory, EntityKitsune entity) {
        kitsune = entity;
        System.out.println(kitsune);
        this.tamedInventory = new InventoryKitsune(kitsune,false);
        System.out.println(tamedInventory);
        this.armorInventory = new InventoryKitsune(kitsune,true);
        System.out.println(armorInventory);
        this.addSlotToContainer(new Slot(armorInventory,0,8+8*18,36-8));//weapon
        this.addSlotToContainer(new Slot(armorInventory,1,8+6*18,36-8));//boots
        this.addSlotToContainer(new Slot(armorInventory,2,8+4*18,36-8));//leggings
        this.addSlotToContainer(new Slot(armorInventory,3,8+2*18,36-8));//chestplate
        this.addSlotToContainer(new Slot(armorInventory,4,8+0*18,36-8));//helmet
        this.add_kitsune_Inventory(8,36+16,kitsune);
        System.out.println("added kitsune container");
        this.add_Inventory(8,96+18+32,inventory);
        System.out.println("added player inventory container");
        this.add_Inventory_Hotbar(8,160+34+32,inventory);
        System.out.println("added player hotbar container");
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemStack = slotStack.copy();

            // Determine the range based on slot index
            if (slotIndex >= 0 && slotIndex < 5) { // Armor slots (0-4)
                if (!this.mergeItemStack(slotStack, 5, 67, true)) { // Move to tamed or player inventory
                    return null;
                }
            } else if (slotIndex >= 5 && slotIndex < 32) { // Tamed inventory slots (5-31)
                if (!this.mergeItemStack(slotStack, 0, 5, false) && !this.mergeItemStack(slotStack, 32, 67, false)) { // Try armor, then player
                    return null;
                }
            } else if (slotIndex >= 32 && slotIndex < 67) { // Player inventory + hotbar (32-66)
                if (!this.mergeItemStack(slotStack, 0, 5, false) && !this.mergeItemStack(slotStack, 5, 32, false)) { // Try armor, then tamed
                    return null;
                }
            }

            if (slotStack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.stackSize == itemStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, slotStack);
        }

        return itemStack;
    }

    private void add_kitsune_Inventory(int x, int y, EntityKitsune kit){
        int x_offset = 18;
        int y_offset = 24;
        int cntr = 0;
        int rows = 3;
        int cols = 9;
        for(int i = 0; i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                this.addSlotToContainer(new Slot(this.tamedInventory,cntr,x+j*x_offset,y+i*y_offset));
                cntr++;
            }
        }
    }
    private void add_Inventory(int x,int y,InventoryPlayer inventory){
        int x_offset = 18;
        int y_offset = 24;
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
}
