package com.TimeSpaceWarrior.TemporalLightMod.entity.AI;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIRangedAttack extends EntityAIBase {
    private EntityKitsune entity;
    private double entityMoveSpeed;
    private int attackCooldown;
    private int maxAttackRange = 20; // Max range for ranged attack
    private int minAttackRange = 10; // Min range to switch to ranged
    private int attackTime = -1;

    public EntityAIRangedAttack(EntityKitsune entity, double moveSpeed) {
        this.entity = entity;
        this.entityMoveSpeed = moveSpeed;
        this.setMutexBits(3); // Mutually exclusive with melee attack
    }

    @Override
    public boolean shouldExecute() {
        if (entity.getAttackTarget() == null || !entity.getAttackTarget().isEntityAlive()) {
            return false;
        }
        double distance = entity.getDistanceToEntity(entity.getAttackTarget());
        return distance >= minAttackRange && distance <= maxAttackRange;
    }

    @Override
    public boolean continueExecuting() {
        return shouldExecute() && attackTime >= 0;
    }

    @Override
    public void startExecuting() {
        attackTime = 0;
    }

    @Override
    public void resetTask() {
        attackTime = -1;
    }

    @Override
    public void updateTask() {
        EntityLivingBase target = entity.getAttackTarget();
        double distance = entity.getDistanceToEntity(target);

        entity.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
        if (--attackTime <= 0) {
            attackTime = 20; // Attack every 1 second
            if (distance >= minAttackRange && entity.canEntityBeSeen(target)) {
                entity.attackEntityWithRangedAttack(target, (float)distance);
            }
        }
        entity.getNavigator().tryMoveToEntityLiving(target, entityMoveSpeed);
    }
}