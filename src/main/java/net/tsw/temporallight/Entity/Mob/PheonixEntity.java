package net.tsw.temporallight.Entity.Mob;

import net.minecraft.block.Blocks;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.IReputationTracking;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.villager.IVillagerDataHolder;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.block.custom.PhoenixEggBlock;
import net.tsw.temporallight.util.CustomSoundEvents;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;

public class PheonixEntity extends VillagerEntity implements IAngerable, IReputationTracking, IVillagerDataHolder {
    private UUID angertarget;
    private int angertime;
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;
    public final float breedlength = 6000.0F;
    public float breed;
    private static final RangedInteger ANGER_TIME_RANGE = TickRangeConverter.convertRange(20, 39);
    public PheonixEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveController = new pheonixflyingmovementcontroller(this,10,false);
        this.wingRotation = 0;
        this.breed = 0.0F;
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 1D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 13.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 10.0D)
                .createMutableAttribute(Attributes.FLYING_SPEED, 10.0D);


    }
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }
    public void livingTick() {
        breed++;
        super.livingTick();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
        if (!this.onGround && this.wingRotDelta < 1.0F) {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);
        Vector3d vector3d = this.getMotion();
        if (!this.onGround && vector3d.y < 0.0D) {
            this.setMotion(vector3d.mul(1.0D, 0.6D, 1.0D));
        }

        this.wingRotation += this.wingRotDelta * 2.0F;

    }

    public SoundEvent getAmbientSound() {return CustomSoundEvents.PHOENIXIDLE.get();}

    @Override
    public SoundEvent getYesSound() {
        return CustomSoundEvents.PHOENIXACCEPT.get();
    }

    public SoundEvent getVillagerYesNoSound(boolean getYesSound) {
        return getYesSound ? CustomSoundEvents.PHOENIXACCEPT.get() : CustomSoundEvents.PHOENIXDecline.get();
    }

    public SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new PheonixEntity.LayEggGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new PheonixEntity.FireballAttackGoal(this));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
    }
    static class LayEggGoal extends MoveToBlockGoal {
        private final PheonixEntity turtle;

        LayEggGoal(PheonixEntity turtle, double speedIn) {
            super(turtle, speedIn, 16);
            this.turtle = turtle;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return super.shouldExecute();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return super.shouldContinueExecuting();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */

        public void tick() {
            super.tick();
            float breed = this.turtle.breed;
            float minbreed = this.turtle.breed;
            BlockPos blockpos = this.turtle.getPosition();
            World worldIn = this.turtle.getWorld();
            if (!this.turtle.isInWater() && this.getIsAboveDestination()&&breed>=minbreed && PhoenixEggBlock.isProperHabitat(worldIn, blockpos)) {
                    World world = this.turtle.world;
                    this.turtle.breed= -1000000.0F;
                    world.playSound((PlayerEntity)null, blockpos, SoundEvents.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.rand.nextFloat() * 0.2F);
                    world.setBlockState(this.destinationBlock.up(), blockRegistry.PHOENIXEGG.get().getDefaultState(), 3);
            }
        }
        /**
         * Return true to set given position as destination
         */
        protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
            return !worldIn.isAirBlock(pos.up()) ? false : PhoenixEggBlock.isProperHabitat(worldIn, pos);
        }
    }
    static class FireballAttackGoal extends Goal {
        private final PheonixEntity blaze;
        private int attackStep;
        private int attackTime;
        private int firedRecentlyTimer;
        public FireballAttackGoal(PheonixEntity blazeIn) {
            this.blaze = blazeIn;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }
        @Override
        public boolean shouldExecute() {
            LivingEntity livingentity = this.blaze.getAttackTarget();
            return livingentity != null && livingentity.isAlive() && this.blaze.canAttack(livingentity);
        }
        public void startExecuting() {
            this.attackStep = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.firedRecentlyTimer = 0;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            --this.attackTime;
            LivingEntity livingentity = this.blaze.getAttackTarget();
            if (livingentity != null) {
                boolean flag = this.blaze.getEntitySenses().canSee(livingentity);
                if (flag) {
                    this.firedRecentlyTimer = 0;
                } else {
                    ++this.firedRecentlyTimer;
                }

                double d0 = this.blaze.getDistanceSq(livingentity);
                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.blaze.attackEntityAsMob(livingentity);
                    }

                    this.blaze.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getPosX() - this.blaze.getPosX();
                    double d2 = livingentity.getPosYHeight(0.5D) - this.blaze.getPosYHeight(0.5D);
                    double d3 = livingentity.getPosZ() - this.blaze.getPosZ();
                    if (this.attackTime <= 0) {
                        ++this.attackStep;
                        if (this.attackStep == 1) {
                            this.attackTime = 60;
                        } else if (this.attackStep <= 4) {
                            this.attackTime = 6;
                        } else {
                            this.attackTime = 100;
                            this.attackStep = 0;

                        }

                        if (this.attackStep > 1) {
                            float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                            if (!this.blaze.isSilent()) {
                                this.blaze.world.playEvent((PlayerEntity)null, 1018, this.blaze.getPosition(), 0);
                            }

                            for(int i = 0; i < 1; ++i) {
                                SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.blaze.world, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * (double)f, d2, d3 + this.blaze.getRNG().nextGaussian() * (double)f);
                                smallfireballentity.setPosition(smallfireballentity.getPosX(), this.blaze.getPosYHeight(0.5D) + 0.5D, smallfireballentity.getPosZ());
                                this.blaze.world.addEntity(smallfireballentity);
                            }
                        }
                    }

                    this.blaze.getLookController().setLookPositionWithEntity(livingentity, 10.0F, 10.0F);
                } else if (this.firedRecentlyTimer < 5) {
                    this.blaze.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
                }

                super.tick();
            }
        }

        private double getFollowDistance() {
            return this.blaze.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();
            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof AbstractArrowEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }
    public void tick() {
        super.tick();
        if(angertime>0){
            angertime--;
        }
    }
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.applyEnchantments(this, entityIn);
        }
        return flag;
    }
    @Override
    public int getAngerTime() {return angertime;}
    @Override
    public void setAngerTime(int time) {angertime = time;}
    @Nullable
    @Override
    public UUID getAngerTarget() {return this.angertarget;}
    @Override
    public void setAngerTarget(@Nullable UUID target) {this.angertarget = target;}
    public boolean canBeLeashedTo(PlayerEntity player) {
        return !this.isAngry() && true;
    }
    @Override
    public void func_230258_H__() {this.setAngerTime(ANGER_TIME_RANGE.getRandomWithinRange(this.rand));}
}
