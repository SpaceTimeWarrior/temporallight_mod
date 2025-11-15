package com.TimeSpaceWarrior.TemporalLightMod.tile_entity;

import com.TimeSpaceWarrior.TemporalLightMod.blocks.PhoenixEgg;
import com.TimeSpaceWarrior.TemporalLightMod.entity.phoenixF.EntityPhoenixF;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.Sys;

import java.util.Random;

public class PhoenixEgg_TileEntity extends TileEntity {
    public int countdown2hatch;

    public PhoenixEgg_TileEntity(){
        super();
        Random random = new Random();
        countdown2hatch = 10+random.nextInt(10);
        System.out.println("TileEntityCreated");
    }
    public PhoenixEgg_TileEntity(int ticks) {
        super();
        Random random = new Random();
        countdown2hatch = ticks+random.nextInt(ticks);
        System.out.println("TileEntityCreated");
    }

    @Override
    public void updateEntity() {
        System.out.println(countdown2hatch);
        super.updateEntity();
        if(countdown2hatch-1==0){
            Random rand = new Random(worldObj.getWorldTime());
            if(/*rand.nextInt(4)>4*/true){
                //System.out.println("Creating Entity Phoenix Female");
                EntityPhoenixF phoenix = new EntityPhoenixF(worldObj);
                phoenix.setLocationAndAngles(xCoord + 0.5, yCoord + 1.0, zCoord + 0.5, 0, 0);
                worldObj.spawnEntityInWorld(phoenix);
            }else{
                System.out.println("TODO ADD COUNTERPART");
            }
            worldObj.setBlock(this.xCoord,this.yCoord,this.zCoord, Blocks.air);
            worldObj.removeTileEntity(xCoord, yCoord, zCoord);
            this.invalidate();
        }else{
            countdown2hatch--;
        }
    }
}
