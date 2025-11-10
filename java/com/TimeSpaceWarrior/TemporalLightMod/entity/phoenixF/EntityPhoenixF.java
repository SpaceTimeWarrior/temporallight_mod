package com.TimeSpaceWarrior.TemporalLightMod.entity.phoenixF;

import cpw.mods.fml.common.Loader;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPhoenixF extends EntityVillager {
    public EntityPhoenixF(World world) {
        super(world);addTasks();
    }

    public EntityPhoenixF(World world, int p_i1748_2_) {
        super(world, p_i1748_2_);addTasks();
    }

    public void addTasks(){
        this.isImmuneToFire = true;

    }

    public double GetVertVelocity(){
        return this.motionY;
    }
    public boolean isJumping(){
        return this.isJumping;
    }

    public boolean isFalling() {
        return this.motionY<-0.5;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(!worldObj.isRemote){
            if (this.isWet())
            {
                this.attackEntityFrom(DamageSource.drown, 1.0F);
            }
        }
    }
    public EntityVillager createChild(EntityAgeable entityAgeable)
    {
        /*if(Loader.isModLoaded("chococraft")){

        }*/
        return null;
    }
}
