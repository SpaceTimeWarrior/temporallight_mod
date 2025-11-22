package com.TimeSpaceWarrior.TemporalLightMod.Compatability.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class KitsuneBelt extends Item implements IBauble {
    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.BELT;
    }
    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        entityLivingBase.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 10, 0));
        //entityLivingBase.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 10, 0));
    }
    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}
    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {}
    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }
}
