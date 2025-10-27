package net.tsw.Temporal_Light.entity.phoenixF.AI;

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
import net.tsw.Temporal_Light.entity.phoenixF.phoenix_FEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class phoenix_fFireballGoal extends Goal {
    private static final Logger LOGGER = LogManager.getLogger();
    private final phoenix_FEntity phoenix;
    public int chargeTime;

    public phoenix_fFireballGoal(phoenix_FEntity pkitsune) {
        this.phoenix = pkitsune;
    }

    @Override
    public boolean canUse() {

        if (this.phoenix.getTarget() != null && this.phoenix.getTarget().isAlive()) {
            LivingEntity livingentity = phoenix.getTarget();
            //if(!livingentity.isSpectator() && !((Player)livingentity).isCreative()){
            //  return false;  //this was added to not have the player be hit with fireballs when not in survival
            //}
            return true;
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
        this.phoenix.setCharging(false);
        this.chargeTime = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity livingentity = this.phoenix.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            double d0 = 64.0;
            if ( this.phoenix.hasLineOfSight(livingentity)) {
                Level level = this.phoenix.level();
                if (chargeTime == 0) {
                    phoenix.setAttacking(true);
                    if (phoenix.level().isClientSide() && Minecraft.getInstance().player != null) {
                        SimpleSoundInstance sound = new SimpleSoundInstance(
                                SoundEvents.EVOKER_PREPARE_ATTACK, phoenix.getSoundSource(),
                                5.0F, 1.0F, RandomSource.create(), phoenix.getX(), phoenix.getY(), phoenix.getZ()
                        );
                        Minecraft.getInstance().getSoundManager().play(sound);
                        LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), phoenix.position());
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
                    Vec3 vec3 = this.phoenix.getViewVector(1.0F);
                    double d2 = livingentity.getX() - (this.phoenix.getX() + vec3.x * 4.0);
                    double d3 = livingentity.getY(0.5) - (0.5 + this.phoenix.getY(0.5));
                    double d4 = livingentity.getZ() - (this.phoenix.getZ() + vec3.z * 4.0);
                    if (!this.phoenix.isSilent()) {
                        level.levelEvent(null, 1016, this.phoenix.blockPosition(), 0);
                        LargeFireball largefireball = new LargeFireball(level, this.phoenix, d2, d3, d4, this.phoenix.getExplosionPower());
                        largefireball.setPos(
                                this.phoenix.getX() + vec3.x * 4.0, this.phoenix.getY(0.5) + 0.5, largefireball.getZ() + vec3.z * 4.0
                        );
                        level.addFreshEntity(largefireball);
                        if (phoenix.level().isClientSide() && Minecraft.getInstance().player != null) {
                            SimpleSoundInstance sound = new SimpleSoundInstance(
                                    SoundEvents.EVOKER_CAST_SPELL, phoenix.getSoundSource(),
                                    5.0F, 1.0F, RandomSource.create(), phoenix.getX(), phoenix.getY(), phoenix.getZ()
                            );
                            Minecraft.getInstance().getSoundManager().play(sound);
                            LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), phoenix.position());
                        }
                        /*
                        if (kitsune.level().isClientSide()) {
                            kitsune.level().playSound(kitsune,kitsune.getOnPos(),SoundEvents.EVOKER_CAST_SPELL,kitsune.getSoundSource(),5,1);
                            //kitsune.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH, 5.0F, 1.0F);
                            LOGGER.debug("Playing fireball launch sound for Kitsune at {}", kitsune.position());
                        }*/
                    }
                    this.chargeTime = -40;
                    phoenix.setAttacking(false);
                }
            } else if (this.chargeTime > 0) {
                this.chargeTime--;
                if (this.chargeTime == 0) {
                    phoenix.setAttacking(false);
                }
            }
            this.phoenix.setCharging(this.chargeTime > 10);
        } else {
            phoenix.setAttacking(false);
            this.chargeTime = 0;
        }
    }
}