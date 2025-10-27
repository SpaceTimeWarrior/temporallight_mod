package com.TimeSpaceWarrior.TemporalLightMod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LightningProjectileEntity extends EntityThrowable {
    public LightningProjectileEntity(World world) {
        super(world);
    }
    public LightningProjectileEntity(World world, EntityLivingBase livingBase){
        super(world,livingBase);
    }
    public LightningProjectileEntity(World world,double x, double y,double z){
        super(world,x,y,z);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.07F;
    }

    @Override
    protected float func_70182_d() {//velocity multiplier
        return 10.0F;
    }

    @Override
    protected float func_70183_g() {//arc decrement per tick
        return super.func_70183_g();
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {
        if(!this.worldObj.isRemote){
            if (position.entityHit != null){
                if(!(position.entityHit instanceof EntityCreeper)) {
                    position.entityHit.setFire(10);
                    position.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), 3f);
                }else{
                    EntityCreeper creep = (EntityCreeper) position.entityHit;
                    creep.onStruckByLightning(new EntityLightningBolt(worldObj, position.blockX + 0.5, position.blockY + 1.0, position.blockZ + 0.5));
                }
            }
            EntityLightningBolt lightning = new EntityLightningBolt(worldObj, position.blockX + 0.5, position.blockY + 1.0, position.blockZ + 0.5);
            worldObj.spawnEntityInWorld(lightning);
            worldObj.playSoundEffect(position.blockX + 0.5, position.blockY + 1.0, position.blockZ + 0.5, "ambient.weather.thunder", 10000.0F, 0.8F + worldObj.rand.nextFloat() * 0.2F);
            this.setDead();
        }
    }

    @Override
    public void applyEntityCollision(Entity entity) {
        onImpact(new MovingObjectPosition(this));
        super.applyEntityCollision(entity);
    }

}
