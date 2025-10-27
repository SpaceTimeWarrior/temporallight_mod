package net.tsw.Temporal_Light.entity.kitsune.AI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KitsuneFireballGoal extends Goal {
    private static final Logger LOGGER = LogManager.getLogger();
    private final KitsuneEntity kitsune;
    public int chargeTime;

    public KitsuneFireballGoal(KitsuneEntity pkitsune) {
        this.kitsune = pkitsune;
    }

    @Override
    public boolean canUse() {

        if (this.kitsune.getTarget() != null && this.kitsune.getTarget().isAlive()) {
            LivingEntity livingentity = kitsune.getTarget();
            //if(!livingentity.isSpectator() && !((Player)livingentity).isCreative()){
            //  return false;  //this was added to not have the player be hit with fireballs when not in survival
            //}
            return this.kitsune.getTarget().distanceTo(this.kitsune) > 20;
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {//this was added to not have the player be hit with fireballs when not in survival
        if(! canUse()){
            return super.canContinueToUse();
        }else{
            return canUse();
        }
    }

    @Override
    public void start() {
        this.chargeTime = 0;
    }

    @Override
    public void stop() {
        this.kitsune.setCharging(false);
        this.chargeTime = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity livingentity = this.kitsune.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            double d0 = 64.0;
            if (livingentity.distanceToSqr(this.kitsune) < 4096.0 && this.kitsune.hasLineOfSight(livingentity)) {
                Level level = this.kitsune.level();
                if (chargeTime == 0) {
                    kitsune.setAttacking(true);
                    if (kitsune.level().isClientSide() && Minecraft.getInstance().player != null) {
                        SimpleSoundInstance sound = new SimpleSoundInstance(
                                SoundEvents.EVOKER_PREPARE_ATTACK, kitsune.getSoundSource(),
                                5.0F, 1.0F, RandomSource.create(), kitsune.getX(), kitsune.getY(), kitsune.getZ()
                        );
                        Minecraft.getInstance().getSoundManager().play(sound);
                        LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), kitsune.position());
                    }
                    /*
                    if (kitsune.level().isClientSide()) {
                        kitsune.level().playSound(kitsune,kitsune.getOnPos(),SoundEvents.EVOKER_PREPARE_ATTACK,kitsune.getSoundSource(),5,1);
                        //kitsune.playSound(SoundEvents.EVOKER_CAST_SPELL, 5.0F, 1.0F);
                        LOGGER.debug("Playing fireball charge sound for Kitsune at {}", kitsune.position());
                    }*/
                }
                this.chargeTime++;

                if (this.chargeTime == 20) {
                    double d1 = 4.0;
                    Vec3 vec3 = this.kitsune.getViewVector(1.0F);
                    double d2 = livingentity.getX() - (this.kitsune.getX() + vec3.x * 4.0);
                    double d3 = livingentity.getY(0.5) - (0.5 + this.kitsune.getY(0.5));
                    double d4 = livingentity.getZ() - (this.kitsune.getZ() + vec3.z * 4.0);
                    if (!this.kitsune.isSilent()) {
                        level.levelEvent(null, 1016, this.kitsune.blockPosition(), 0);
                        LargeFireball largefireball = new LargeFireball(level, this.kitsune, d2, d3, d4, this.kitsune.getExplosionPower());
                        largefireball.setPos(
                                this.kitsune.getX() + vec3.x * 4.0, this.kitsune.getY(0.5) + 0.5, largefireball.getZ() + vec3.z * 4.0
                        );
                        level.addFreshEntity(largefireball);
                        if (kitsune.level().isClientSide() && Minecraft.getInstance().player != null) {
                            SimpleSoundInstance sound = new SimpleSoundInstance(
                                    SoundEvents.EVOKER_CAST_SPELL, kitsune.getSoundSource(),
                                    5.0F, 1.0F, RandomSource.create(), kitsune.getX(), kitsune.getY(), kitsune.getZ()
                            );
                            Minecraft.getInstance().getSoundManager().play(sound);
                            LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), kitsune.position());
                        }
                        /*
                        if (kitsune.level().isClientSide()) {
                            kitsune.level().playSound(kitsune,kitsune.getOnPos(),SoundEvents.EVOKER_CAST_SPELL,kitsune.getSoundSource(),5,1);
                            //kitsune.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH, 5.0F, 1.0F);
                            LOGGER.debug("Playing fireball launch sound for Kitsune at {}", kitsune.position());
                        }*/
                    }
                    this.chargeTime = -40;
                    kitsune.setAttacking(false);
                }
            } else if (this.chargeTime > 0) {
                this.chargeTime--;
                if (this.chargeTime == 0) {
                    kitsune.setAttacking(false);
                }
            }
            this.kitsune.setCharging(this.chargeTime > 10);
        } else {
            kitsune.setAttacking(false);
            this.chargeTime = 0;
        }
    }
}