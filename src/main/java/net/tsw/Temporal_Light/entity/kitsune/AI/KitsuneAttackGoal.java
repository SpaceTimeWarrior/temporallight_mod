package net.tsw.Temporal_Light.entity.kitsune.AI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KitsuneAttackGoal extends MeleeAttackGoal {
    private static final Logger LOGGER = LogManager.getLogger();
    private final KitsuneEntity entity;
    private int attackDelay = 10;
    private int ticksUntilNextAttack = 10;
    private int attackDuration = 0;
    private int outOfRangeTicks = 0;
    private boolean shouldCountTillNextAttack = false;
    private double speedModifier;

    public KitsuneAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = (KitsuneEntity) pMob;
        speedModifier = pSpeedModifier;
    }

    @Override
    public boolean canUse() {

        if (entity.getTarget() != null) {

            if (entity.getTarget().distanceTo(entity) <= 20) {
                return super.canUse();
            } else {
                return false;
            }
        } else return false;
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 10;
        ticksUntilNextAttack = 10;
        attackDuration = 0;
        outOfRangeTicks = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = this.entity.getTarget();
        //System.out.println("KitsuneAttackGoal tick, Target: " + (target != null ? target.getName().getString() : "null"));
        if (target != null) {
            this.entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
            double d0 = this.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());

            if (this.entity.isAttacking()) {
                attackDuration++;
                if (attackDuration == 5 && d0 <= this.getAttackReachSqr(target)) {
                    this.entity.doHurtTarget(target);
                    //System.out.println("KitsuneAttackGoal: Attack performed, distance: " + Math.sqrt(d0));
                }
                if (attackDuration >= 10) {
                    this.entity.setAttacking(false);
                    attackDuration = 0;
                    ticksUntilNextAttack = attackDelay;
                    //System.out.println("KitsuneAttackGoal: Attack completed, setting attacking false");
                }
            } else {
                if (d0 <= this.getAttackReachSqr(target)) {
                    outOfRangeTicks = 0;
                    if (--this.ticksUntilNextAttack <= 0) {
                        this.entity.setAttacking(true);
                        ticksUntilNextAttack = attackDelay;
                        attackDuration = 0;
                        //System.out.println("KitsuneAttackGoal: Setting attacking true, distance: " + Math.sqrt(d0));
                    }
                } else {
                    outOfRangeTicks++;
                    if (outOfRangeTicks > 5) {
                        ticksUntilNextAttack = attackDelay;
                        //System.out.println("KitsuneAttackGoal: Target out of range, resetting cooldown");
                    }
                }
            }

            this.entity.getNavigation().moveTo(target, this.speedModifier);
            //System.out.println("Navigating to target at " + target.getX() + ", " + target.getY() + ", " + target.getZ());
        } else {
            this.entity.setAttacking(false);
            attackDuration = 0;
            ticksUntilNextAttack = attackDelay;
            //System.out.println("KitsuneAttackGoal: Setting attacking false, no target");
        }
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        attackDuration = 0;
        ticksUntilNextAttack = attackDelay;
        super.stop();
    }

    protected void checkAndPerformAttack(LivingEntity pTarget) {
        double pDistToEnemySqr = entity.distanceToSqr(pTarget);
        if (isEnemyWithinAttackDistance(pTarget, pDistToEnemySqr)) {
            shouldCountTillNextAttack = true;
            if (isTimeToAttackAnimation()) {
                entity.setAttacking(true);
                if (entity.level().isClientSide() && Minecraft.getInstance().player != null) {
                    SimpleSoundInstance sound = new SimpleSoundInstance(
                            SoundEvents.AXOLOTL_ATTACK, entity.getSoundSource(),
                            5.0F, 1.0F, RandomSource.create(), entity.getX(), entity.getY(), entity.getZ()
                    );
                    Minecraft.getInstance().getSoundManager().play(sound);
                    LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.AXOLOTL_ATTACK.getLocation(), entity.position());
                }
            }
            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(pTarget);
                performAttack(pTarget);
                if (entity.level().isClientSide() && Minecraft.getInstance().player != null) {
                    SimpleSoundInstance sound = new SimpleSoundInstance(
                            SoundEvents.AXOLOTL_ATTACK, entity.getSoundSource(),
                            5.0F, 1.0F, RandomSource.create(), entity.getX(), entity.getY(), entity.getZ()
                    );
                    Minecraft.getInstance().getSoundManager().play(sound);
                    LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.AXOLOTL_ATTACK.getLocation(), entity.position());
                }
                /*if (entity.level().isClientSide()) {
                    entity.level().playSound(entity,entity.getOnPos(),SoundEvents.WARDEN_ATTACK_IMPACT,entity.getSoundSource(),5f,1f);
                    //entity.playSound(SoundEvents.AXOLOTL_ATTACK, 5.0F, 1.0F);
                    LOGGER.debug("Playing attack sound for Kitsune at {}", entity.position());
                }*/
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.AttackAnimationTimeout = 0;
        }
        if (this.canPerformAttack(pTarget)) {
            entity.setAttacking(true);
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(pTarget);
            if (entity.level().isClientSide()) {
                entity.playSound(SoundEvents.AXOLOTL_ATTACK, 5.0F, 1.0F);
                LOGGER.debug("Playing melee attack sound for Kitsune at {}", entity.position());
            }
        }
    }

    private void performAttack(LivingEntity pTarget) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pTarget);
    }

    private boolean isTimeToAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity pTarget, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getAttackReachSqr(pTarget);
    }

    protected double getAttackReachSqr(LivingEntity target) {
        float reach = this.entity.getBbWidth() * 3.0F;
        return (double) (reach * reach + target.getBbWidth());
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean canPerformAttack(LivingEntity pEntity) {
        return this.isTimeToAttack() && this.mob.isWithinMeleeAttackRange(pEntity) && this.mob.getSensing().hasLineOfSight(pEntity);
    }
}