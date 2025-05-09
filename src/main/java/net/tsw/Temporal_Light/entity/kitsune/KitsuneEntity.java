package net.tsw.Temporal_Light.entity.kitsune;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.tsw.Temporal_Light.entity.kitsune.AI.KitsuneAttackGoal;
import net.tsw.Temporal_Light.entity.kitsune.AI.KitsuneFireballGoal;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class KitsuneEntity extends Animal implements NeutralMob {
    private static final Logger LOGGER = LogManager.getLogger();
//comented out debug output for less lag
    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;

    private static final EntityDataAccessor<Integer> VARIENT = SynchedEntityData.defineId(KitsuneEntity.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(KitsuneEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> NIGHT_FORM = SynchedEntityData.defineId(KitsuneEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> TRANSFORMING = SynchedEntityData.defineId(KitsuneEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Optional<UUID>> TARGET_UUID = SynchedEntityData.defineId(KitsuneEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    public final AnimationState Attack01AnimationState = new AnimationState();
    public final AnimationState Attack02AnimationState = new AnimationState();
    public final AnimationState TransformToNightAnimationState = new AnimationState();
    public final AnimationState TransformToDayAnimationState = new AnimationState();
    public int AttackAnimationTimeout = 0;
    public int TransformAnimationTimeout = 0;

    private static final int IdleTime = 80;
    private static final int AttackTime = 10;
    private static final int TransformTime = 20; // ~1 second for transformation
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private static final UniformInt TRANSFORM_COOLDOWN = TimeUtil.rangeOfSeconds(25, 50); // half to 1 minecraft hour
    private int remainingPersistentAngerTime;
    private int transformCooldown = 0;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        this.entityData.set(TARGET_UUID, target != null ? Optional.of(target.getUUID()) : Optional.empty());
    }

    @Nullable
    @Override
    public LivingEntity getTarget() {
        Optional<UUID> targetUUID = this.entityData.get(TARGET_UUID);
        if (targetUUID.isPresent()) {
            return this.level().getPlayerByUUID(targetUUID.get());
        }
        return super.getTarget();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addPersistentAngerSaveData(pCompound);
        pCompound.putBoolean("NightForm", this.isNightForm());
        pCompound.putInt("TransformCooldown", this.transformCooldown);
        pCompound.putInt("varient",this.getTypeVarient());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(NIGHT_FORM, false);
        this.entityData.define(TRANSFORMING, false);
        this.entityData.define(TARGET_UUID, Optional.empty());
        this.entityData.define(VARIENT,0);
    }
    private int getTypeVarient(){
        return this.entityData.get(VARIENT);
    }
    public KitsuneVarients getVarient(){
        return KitsuneVarients.byID(this.getTypeVarient()&255);
    }
    public void setVarient(KitsuneVarients varient){
        this.entityData.set(VARIENT,varient.getId() & 255);
    }
    public void setAttacking(boolean attacking) {
        boolean wasAttacking = this.entityData.get(ATTACKING);
        this.entityData.set(ATTACKING, attacking);
        if (wasAttacking != attacking && !this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) (attacking ? 4 : 5));
        }
        //System.out.println("setAttacking: " + attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setNightForm(boolean nightForm) {
        if (this.isNightForm() != nightForm) {
            this.entityData.set(NIGHT_FORM, nightForm);
            this.entityData.set(TRANSFORMING, true);
            this.updateDimensions();
            if (!this.level().isClientSide()) {
                this.level().broadcastEntityEvent(this, (byte) (nightForm ? 6 : 7));
                //System.out.println("setNightForm: " + nightForm + ", broadcasting event: " + (nightForm ? 6 : 7));
            }
        } else {
            //System.out.println("setNightForm skipped: current=" + this.isNightForm() + ", requested=" + nightForm);
        }
    }

    public boolean isNightForm() {
        return this.entityData.get(NIGHT_FORM);
    }

    public void setTransforming(boolean transforming) {
        this.entityData.set(TRANSFORMING, transforming);
        //.out.println("setTransforming: " + transforming);
    }

    public boolean isTransforming() {
        return this.entityData.get(TRANSFORMING);
    }

    private void updateDimensions() {
        float scale = this.isNightForm() ? 1.5F : 0.8F;
        EntityDimensions base = EntityType.FOX.getDimensions();
        this.setBoundingBox(base.scale(scale).makeBoundingBox(this.position()));
        this.refreshDimensions();
    }

    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @Nullable AgeableMob pBaby) {
        KitsuneVarients varient = Util.getRandom(KitsuneVarients.values(),this.random);
        ((KitsuneEntity) pBaby).setVarient(varient);
        super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData,@javax.annotation.Nullable CompoundTag pDataTag) {
        KitsuneVarients varient = Util.getRandom(KitsuneVarients.values(),this.random);
        this.setVarient(varient);
        return super.finalizeSpawn(pLevel, pDifficulty,MobSpawnType.SPAWN_EGG, pSpawnGroupData,pDataTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level(), pCompound);
        this.setNightForm(pCompound.getBoolean("NightForm"));
        this.transformCooldown = pCompound.getInt("TransformCooldown");
        this.entityData.set(VARIENT,pCompound.getInt("varient"));
    }

    public KitsuneEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 2));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.75, Ingredient.of(Items.SWEET_BERRIES), false));
        this.goalSelector.addGoal(1, new KitsuneAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(1, new KitsuneFireballGoal(this));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.75));
        this.goalSelector.addGoal(6, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngry));
    }

    private boolean isAngry(LivingEntity livingEntity) {
        return this.isAngry();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            if (source.getEntity() instanceof LivingEntity attacker && !(attacker instanceof Player && ((Player) attacker).isCreative())) {
                this.startPersistentAngerTimer();
                this.setPersistentAngerTarget(attacker.getUUID());
            }
            return super.hurt(source, amount);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.ATTACK_SPEED, 1)
                .add(Attributes.FOLLOW_RANGE, 240);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.SWEET_BERRIES);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return TLEntityRegistry.KITSUNE.get().create(pLevel);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = IdleTime;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isTransforming()) {
            if (TransformAnimationTimeout <= 0) {
                if (this.isNightForm()) {
                    if (!TransformToNightAnimationState.isStarted()) {
                        TransformToDayAnimationState.stop();
                        TransformToNightAnimationState.start(this.tickCount);
                        if (this.level().isClientSide()) {
                            this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 5.0F, 1.0F);
                            LOGGER.debug("Playing transformation sound (night form) for Kitsune at {}", this.position());
                        }
                    }
                } else {
                    if (!TransformToDayAnimationState.isStarted()) {
                        TransformToNightAnimationState.stop();
                        TransformToDayAnimationState.start(this.tickCount);
                        if (this.level().isClientSide()) {
                            this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 5.0F, 1.0F);
                            LOGGER.debug("Playing transformation sound (day form) for Kitsune at {}", this.position());
                        }
                    }
                }
                TransformAnimationTimeout = TransformTime;
            }
            if (--TransformAnimationTimeout <= 0) {
                this.setTransforming(false);
                TransformToNightAnimationState.stop();
                TransformToDayAnimationState.stop();
            }
            return;
        }

        LivingEntity target = this.getTarget();
        //System.out.println("Client tick, isAttacking: " + isAttacking() + ", NightForm: " + isNightForm() + ", Target: " + (target != null ? target.getName().getString() : "null"));
        if (this.isAttacking() && target != null) {
          //  System.out.println("IsAttacking: true, Timeout: " + AttackAnimationTimeout + ", Target: " + target.getName().getString());
            if (AttackAnimationTimeout <= 0) {
                float distance = this.distanceTo(target);
            //    System.out.println("Target distance: " + distance);
                if (distance > 20.0f) {
                    if (!Attack02AnimationState.isStarted()) {
                        Attack01AnimationState.stop();
                        Attack02AnimationState.start(this.tickCount);
                        playSound(SoundEvents.EVOKER_CAST_SPELL);
              //          System.out.println("Attack02 animation started");
                    }
                } else {
                    if (!Attack01AnimationState.isStarted()) {
                        Attack02AnimationState.stop();
                        Attack01AnimationState.start(this.tickCount);
                        playSound(SoundEvents.AXOLOTL_ATTACK);
                //        System.out.println("Attack01 animation started");
                    }
                }
                AttackAnimationTimeout = AttackTime;
            }
            --AttackAnimationTimeout;
        } else {
            AttackAnimationTimeout = 0;
            Attack01AnimationState.stop();
            Attack02AnimationState.stop();
            //System.out.println("Attack animations stopped");
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        } else {
            int lightLevel = this.level().getMaxLocalRawBrightness(this.blockPosition());
            boolean shouldBeNightForm = lightLevel <= 7;
            //System.out.println("Server tick, Light level: " + lightLevel + ", shouldBeNightForm: " + shouldBeNightForm + ", currentNightForm: " + isNightForm() + ", transformCooldown: " + transformCooldown);

            if (transformCooldown > 0) {
                transformCooldown--;
            } else {
                if (shouldBeNightForm != this.isNightForm()) {
                    this.setNightForm(shouldBeNightForm);
                    this.transformCooldown = TRANSFORM_COOLDOWN.sample(this.random);
              //      System.out.println("Transformed to " + (shouldBeNightForm ? "night" : "day") + " form, cooldown set to: " + transformCooldown);
                }
            }
        }
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.NEUTRAL;
    }
    @Override
    public boolean isSilent() {
        //LOGGER.debug("Kitsune isSilent: false (forced)");
        return super.isSilent();
    }
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.setAttacking(true);
            LivingEntity target = this.getTarget();
            if (this.level().isClientSide() && target != null) {
                float distance = this.distanceTo(target);
                if (distance > 20.0f) {
                    Attack01AnimationState.stop();
                    Attack02AnimationState.start(this.tickCount);
                    if (level().isClientSide() && Minecraft.getInstance().player != null) {
                        SimpleSoundInstance sound = new SimpleSoundInstance(
                                SoundEvents.EVOKER_PREPARE_ATTACK, getSoundSource(),
                                5.0F, 1.0F, RandomSource.create(), getX(), getY(), getZ()
                        );
                        Minecraft.getInstance().getSoundManager().play(sound);
                        //LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), position());
                    }

                    //this.playSound(SoundEvents.FIREWORK_ROCKET_LAUNCH, 5.0F, 1.0F);
                    //LOGGER.debug("Client received event 4: Playing fireball attack sound at {}", this.position());
                } else {
                    Attack02AnimationState.stop();
                    Attack01AnimationState.start(this.tickCount);
                    if (level().isClientSide() && Minecraft.getInstance().player != null) {
                        SimpleSoundInstance sound = new SimpleSoundInstance(
                                SoundEvents.RABBIT_ATTACK, getSoundSource(),
                                5.0F, 1.0F, RandomSource.create(), getX(), getY(), getZ()
                        );
                        Minecraft.getInstance().getSoundManager().play(sound);
                        //LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), position());
                    }
                    //this.playSound(SoundEvents.AXOLOTL_ATTACK, 5.0F, 1.0F);
                    //LOGGER.debug("Client received event 4: Playing melee attack sound at {}", this.position());
                }
            }
        } else if (id == 5) {
            this.setAttacking(false);
            Attack01AnimationState.stop();
            Attack02AnimationState.stop();
        } else if (id == 6) {
            this.entityData.set(NIGHT_FORM, true);
            this.entityData.set(TRANSFORMING, true);
            this.updateDimensions();
            if (this.level().isClientSide()) {
                if (level().isClientSide() && Minecraft.getInstance().player != null) {
                    SimpleSoundInstance sound = new SimpleSoundInstance(
                            SoundEvents.ZOMBIE_VILLAGER_CONVERTED, getSoundSource(),
                            5.0F, 1.0F, RandomSource.create(), getX(), getY(), getZ()
                    );
                    Minecraft.getInstance().getSoundManager().play(sound);
                    //LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), position());
                }
            }
        } else if (id == 7) {
            this.entityData.set(NIGHT_FORM, false);
            this.entityData.set(TRANSFORMING, true);
            this.updateDimensions();
            if (level().isClientSide() && Minecraft.getInstance().player != null) {
                SimpleSoundInstance sound = new SimpleSoundInstance(
                        SoundEvents.ZOMBIE_VILLAGER_CONVERTED, getSoundSource(),
                        5.0F, 1.0F, RandomSource.create(), getX(), getY(), getZ()
                );
                Minecraft.getInstance().getSoundManager().play(sound);
                LOGGER.debug("Playing attack animation sound (ghast_scream, SoundManager, ID: {}) for Kitsune at {}", SoundEvents.EVOKER_CAST_SPELL.getLocation(), position());
            }
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()) {
            return SoundEvents.WOLF_GROWL;
        } else if (this.random.nextInt(3) == 0) {
            return this.getHealth() < 20.0F ? SoundEvents.FOX_SCREECH : SoundEvents.FOX_SNIFF;
        } else {
            return SoundEvents.FOX_AMBIENT;
        }
    }

    /*@Override
    public void makeSound(@Nullable SoundEvent pSound) {
        super.makeSound(pSound);
    }*/

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.FOX_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }

    @Override
    protected SoundEvent getDrinkingSound(ItemStack pStack) {
        return SoundEvents.FOX_EAT;
    }

    @Override
    public SoundEvent getEatingSound(ItemStack pStack) {
        return SoundEvents.FOX_EAT;
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public boolean isAngry() {
        return this.getRemainingPersistentAngerTime() > 0;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setCharging(boolean b) {
    }

    public int getExplosionPower() {
        return 1;
    }
}