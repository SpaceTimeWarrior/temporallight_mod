package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFuels extends Item {
    public Item ReplaceItem;
    public ItemFuels(Item replace){
        super();
        ReplaceItem = replace;
    }

    @Override
    public ItemStack getContainerItem(ItemStack fuel) {
        return new ItemStack(ReplaceItem);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
