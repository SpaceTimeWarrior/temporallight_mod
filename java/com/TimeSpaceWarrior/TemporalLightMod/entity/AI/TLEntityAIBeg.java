package com.TimeSpaceWarrior.TemporalLightMod.entity.AI;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TLEntityAIBeg extends EntityAIBase
{
    private EntityKitsune theKitsune;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int field_75384_e;
    private static final String __OBFID = "CL_00001576";

    public TLEntityAIBeg(EntityKitsune p_i1617_1_, float p_i1617_2_)
    {
        this.theKitsune = p_i1617_1_;
        this.worldObject = p_i1617_1_.worldObj;
        this.minPlayerDistance = p_i1617_2_;
        this.setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theKitsune, (double)this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerFavoriteItemInHand(this.thePlayer);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.thePlayer.isEntityAlive() ? false : (this.theKitsune.getDistanceSqToEntity(this.thePlayer) > (double)(this.minPlayerDistance * this.minPlayerDistance) ? false : this.field_75384_e > 0 && this.hasPlayerFavoriteItemInHand(this.thePlayer));
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        //this.theKitsune.func_70918_i(true);
        this.field_75384_e = 40 + this.theKitsune.getRNG().nextInt(40);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        //this.theKitsune.func_70918_i(false);
        this.thePlayer = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.theKitsune.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + (double)this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, (float)this.theKitsune.getVerticalFaceSpeed());
        --this.field_75384_e;
    }

    /**
     * Gets if the Player has the tamable Item in the hand.
     */
    private boolean hasPlayerFavoriteItemInHand(EntityPlayer p_75382_1_)
    {
        ItemStack itemstack = p_75382_1_.inventory.getCurrentItem();
        return itemstack == null ? false : (!this.theKitsune.isTamed() && theKitsune.isItemFavorite(itemstack) ? true : this.theKitsune.isBreedingItem(itemstack));
    }
}