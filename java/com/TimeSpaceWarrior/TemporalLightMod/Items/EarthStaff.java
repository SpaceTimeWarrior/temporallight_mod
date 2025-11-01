package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.entity.EarthProjectileEntity;
import com.TimeSpaceWarrior.TemporalLightMod.entity.FireballProjectileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class EarthStaff extends ItemPickaxe {
    public int index = 0;
    public int ticks = 0;
    public ArrayList<Block> BL = new ArrayList<Block>();
    public EarthStaff(ToolMaterial material) {
        super(material);
        BL.add(Blocks.dirt);
        //BL.add(Blocks.redstone_block);
        BL.add(Blocks.grass);
        BL.add(Blocks.sand);
        BL.add(Blocks.stone);
        BL.add(Blocks.netherrack);
        BL.add(Blocks.end_stone);
        BL.add(Blocks.redstone_ore);
        //BL.add(Blocks.tnt);
        BL.add(Blocks.glowstone);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        //System.out.println(ticks);
        if(ticks%20==0){
            ticks = 1;
            if(entity instanceof EntityPlayer){
               EntityPlayer play = (EntityPlayer) entity;
               if(play.isSneaking()){
                   index ++;
                   if(index>= BL.size()){
                       index = 0;
                   }
               }
            }
        }else{
            ticks++;
        }
        super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack cstack, World world, EntityPlayer player) {
            cstack.damageItem(15, player);
            world.spawnEntityInWorld(new EarthProjectileEntity(world,player,BL.get(index)));
            return cstack;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        item.damageItem(9999999,player);
        World world = player.getEntityWorld();
        if(!world.isRemote){
            int x = (int)player.posX;
            int y = (int)player.posY;
            int z = (int)player.posZ;
            world.setBlock(x,y,z,BL.get(BL.size()-1));
            world.setBlock(x,y+1,z,BL.get(BL.size()-1));
            world.setBlock(x,y-1,z,BL.get(0));
            world.setBlock(x,y+2,z,BL.get(0));
            world.setBlock(x+1,y,z,BL.get(0));
            world.setBlock(x-1,y,z,BL.get(0));
            world.setBlock(x,y,z+1,BL.get(0));
            world.setBlock(x,y,z-1,BL.get(0));
            world.setBlock(x+1,y+1,z,BL.get(0));
            world.setBlock(x-1,y+1,z,BL.get(0));
            world.setBlock(x,y+1,z+1,BL.get(0));
            world.setBlock(x,y+1,z-1,BL.get(0));

            player.setPositionAndUpdate(x + 0.5D, y + 0.5D, z + 0.5D);
        }
        return super.onDroppedByPlayer(item, player);
    }
}
