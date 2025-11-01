package com.TimeSpaceWarrior.TemporalLightMod.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EarthProjectileEntity extends EntityThrowable {
    public Block block;
    public EarthProjectileEntity(World world) {
        super(world);
        block = Blocks.air;
    }

    public EarthProjectileEntity(World world, EntityLivingBase entity) {
        super(world, entity);
        block = Blocks.air;
    }

    public EarthProjectileEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
        block = Blocks.air;
    }
    public EarthProjectileEntity(World world, EntityLivingBase entity,Block block) {
        super(world, entity);
        this.block = block;
    }

    public EarthProjectileEntity(World world, double x, double y, double z,Block block) {
        super(world, x, y, z);
        this.block = block;
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
        if(!block.equals(Blocks.air)) {
            this.worldObj.setBlock(position.blockX, position.blockY, position.blockZ, Blocks.air);
            this.worldObj.setBlock(position.blockX, position.blockY, position.blockZ, block);
            this.setDead();
        }
    }

}
