package com.TimeSpaceWarrior.TemporalLightMod.entity;

import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class FireballProjectileEntity extends EntityThrowable {
    public FireballProjectileEntity(World world) {
        super(world);
    }
    public boolean canBeCollidedWith()
    {
        return true;
    }

    public FireballProjectileEntity(World world, EntityLivingBase entityLivingBase) {
        super(world, entityLivingBase);
    }

    public FireballProjectileEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
    @Override
    protected float getGravityVelocity() {
        return 0.05F;
    }

    @Override
    protected float func_70182_d() {//velocity multiplier
        return 1.0F;
    }

    @Override
    protected float func_70183_g() {//arc decrement per tick
        return super.func_70183_g();
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {
        if(!this.worldObj.isRemote){
            if (position.entityHit != null){
                position.entityHit.setFire(10);
                position.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this,this.getThrower()),3f);
                worldObj.createExplosion(this,this.posX,this.posY,this.posZ,2.5f,true);
            }
            worldObj.createExplosion(this,this.posX,this.posY,this.posZ,1.0f,true);
            worldObj.setBlock(position.blockX,position.blockY+1,position.blockZ,Blocks.fire);
            this.setDead();
        }
    }
}
