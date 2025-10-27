package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.entity.FireballProjectileEntity;
import com.TimeSpaceWarrior.TemporalLightMod.entity.LightningProjectileEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class FireStaff extends ItemSword{
    public FireStaff(Item.ToolMaterial material) {
        super(material);
    }
    @Override
    public ItemStack onItemRightClick(ItemStack cstack, World world, EntityPlayer player) {
        if(player.isSneaking()){
            cstack.damageItem(15, player);
            world.spawnEntityInWorld(new FireballProjectileEntity(world,player));
            return cstack;
        }else {
            return super.onItemRightClick(cstack, world, player);
        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        item.damageItem(9999999,player);
        World world = player.getEntityWorld();
        if(!world.isRemote){
            world.createExplosion(player,player.posX,player.posY,player.posZ,25.0f,true);
        }
        player.attackEntityFrom(DamageSource.causeIndirectMagicDamage(new EntityTNTPrimed(world),player), 3f);
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
        user.worldObj.setBlock((int)target.posX,(int)target.posY,(int)target.posZ, Blocks.flowing_lava);
    return super.hitEntity(stack, target, user);
    }
}
