package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCrystalFuel extends ItemCrystal {
    public ItemCrystalFuel(String element) {
        super(element);
        this.setMaxStackSize(1);

    }


    @Override
    public ItemStack getContainerItem(ItemStack fuel) {
        return new ItemStack(ItemRegistry.NULL_CRYSTAL_ESSENCE);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
