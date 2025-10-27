package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.entity.LightningProjectileEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LightningStaff extends ItemSword {
    public LightningStaff(Item.ToolMaterial material) {
        super(material);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack cstack, World world, EntityPlayer player) {
        if(player.isSneaking()){
            cstack.damageItem(15, player);
            world.spawnEntityInWorld(new LightningProjectileEntity(world,player));
            return cstack;
        }else {
            return super.onItemRightClick(cstack, world, player);
        }
    }
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
        World world = user.worldObj;
        MovingObjectPosition position = new MovingObjectPosition(target);
        if(!world.isRemote){
                EntityLightningBolt lightning = new EntityLightningBolt(world, position.blockX + 0.5, position.blockY + 1.0, position.blockZ + 0.5);
                world.spawnEntityInWorld(lightning);
                world.playSoundEffect(position.blockX + 0.5, position.blockY + 1.0, position.blockZ + 0.5, "ambient.weather.thunder", 10000.0F, 0.8F + world.rand.nextFloat() * 0.2F);
        }
        return super.hitEntity(stack, target, user);
    }
    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        item.damageItem(9999999,player);
        World world = player.getEntityWorld();
        MovingObjectPosition position = new MovingObjectPosition(player);
        if(!world.isRemote){
            for(int i=0;i<10;i++) {
                EntityLightningBolt lightning = new EntityLightningBolt(world, position.blockX + 0.5, position.blockY + 1.0, position.blockZ + 0.5);
                world.spawnEntityInWorld(lightning);
                world.playSoundEffect(position.blockX + 0.5, position.blockY + 1.0, position.blockZ + 0.5, "ambient.weather.thunder", 10000.0F, 0.8F + world.rand.nextFloat() * 0.2F);
                player.setFire(10);
                player.attackEntityFrom(DamageSource.causeIndirectMagicDamage(lightning,player),4);
            }
        }
        return true;
    }
}
