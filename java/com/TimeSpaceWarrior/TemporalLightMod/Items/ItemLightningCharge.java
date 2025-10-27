package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.entity.LightningProjectileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemLightningCharge extends Item {
    @Override
    public ItemStack onItemRightClick(ItemStack currentItemstack, World world, EntityPlayer player) {
        if(player.capabilities.isCreativeMode){
            currentItemstack.stackSize--;
        }
        world.spawnEntityInWorld(new LightningProjectileEntity(world,player));
        return currentItemstack;
    }
}

