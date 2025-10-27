package com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class InventoryKitsune implements IInventory {
    public EntityKitsune kitsune;
    public boolean inventoryChanged;
    public boolean isArmor;

    public InventoryKitsune(EntityKitsune kit,boolean armor){
        kitsune = kit;
        isArmor = armor;
    }

    @Override
    public int getSizeInventory() {
        if(isArmor){
            return 5;
        }else{
            return 27;
        }
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if(isArmor){
            return kitsune.storedEquipment[slot];
        }else{
            return kitsune.tamedInventory[slot];
        }
    }
    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        if (isArmor) {
            if (kitsune.storedEquipment[slot] != null) {
                if (quantity >= kitsune.storedEquipment[slot].stackSize) {
                    ItemStack itemStack = kitsune.storedEquipment[slot];
                    kitsune.storedEquipment[slot] = null;
                    kitsune.setCurrentItemOrArmor(slot,null);
                    markDirty();
                    return itemStack;
                } else {
                    ItemStack ret = kitsune.storedEquipment[slot].splitStack(quantity);
                    kitsune.setCurrentItemOrArmor(slot,ret);
                    return ret;
                }
            }
        } else {
            if (kitsune.tamedInventory[slot] != null) {
                if (quantity >= kitsune.tamedInventory[slot].stackSize) {
                    ItemStack itemStack = kitsune.tamedInventory[slot];
                    kitsune.tamedInventory[slot] = null;

                    markDirty();
                    return itemStack;
                } else {
                    return kitsune.tamedInventory[slot].splitStack(quantity);
                }
            }
        }
        return null;
    }


    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if(isArmor){
            return kitsune.storedEquipment[slot];
        }else{
            return kitsune.tamedInventory[slot];
        }
    }
    @Override
    public void setInventorySlotContents(int slot, ItemStack newContents) {
        if (isArmor) {
            if (newContents != null && newContents.stackSize > getInventoryStackLimit()) {
                newContents.stackSize = getInventoryStackLimit();
            }
            kitsune.storedEquipment[slot] = newContents;
            kitsune.setCurrentItemOrArmor(slot,newContents);
        } else {
            if (newContents != null && newContents.stackSize > getInventoryStackLimit()) {
                newContents.stackSize = getInventoryStackLimit();
            }
            kitsune.tamedInventory[slot] = newContents;
        }
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return kitsune.getCustomNameTag();
    }

    @Override
    public boolean hasCustomInventoryName() {
        return kitsune.hasCustomNameTag();
    }

    @Override
    public int getInventoryStackLimit() {
        return 999;
    }

    public void markDirty()
    {
        this.inventoryChanged = true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(int slot, ItemStack itemtocheck)
    {
        if(isArmor){
            switch (slot){
                case 1:
                    if(itemtocheck.getItem()instanceof ItemArmor){
                        ItemArmor itm = (ItemArmor) itemtocheck.getItem();
                        if(itm.armorType==3){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                case 2:
                    if(itemtocheck.getItem()instanceof ItemArmor){
                        ItemArmor itm = (ItemArmor) itemtocheck.getItem();
                        if(itm.armorType==2){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                case 3:
                    if(itemtocheck.getItem()instanceof ItemArmor){
                        ItemArmor itm = (ItemArmor) itemtocheck.getItem();
                        if(itm.armorType==1){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                case 4:
                    if(itemtocheck.getItem()instanceof ItemArmor){
                        ItemArmor itm = (ItemArmor) itemtocheck.getItem();
                        if(itm.armorType==0){
                            return true;
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                default:
                    return true;
            }
        }
        return true;
    }
    //0 weapon
    //1 boots
    //2 leggings
    //3 chestplate
    //4 helmet
}
