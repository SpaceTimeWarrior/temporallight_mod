package com.TimeSpaceWarrior.TemporalLightMod.Compatability.baubles;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import cpw.mods.fml.common.Mod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class KitsuneRing extends Item implements IBauble {
    public KitsuneRing(){
        super();
        MinecraftForge.EVENT_BUS.register(new RingDamageHandler());
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }
}
class RingDamageHandler {

    @Mod.EventHandler
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (event.source.isProjectile()||event.source.isMagicDamage()) { // Fireball damage
                IInventory baubles = BaublesApi.getBaubles(player);
                // Check both ring slots (0 and 1)
                for (int i = 0; i < 2; i++) {
                    ItemStack stack = baubles.getStackInSlot(i);
                    if (stack != null && stack.getItem() instanceof KitsuneRing) {
                        event.ammount *= 0.5f; // 50% reduction
                        player.addPotionEffect(new PotionEffect(Potion.resistance.id, 60, 1));
                        return; // Only trigger once
                    }
                }
            }
        }
    }
}
