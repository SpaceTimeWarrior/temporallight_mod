package net.tsw.Temporal_Light.entity.phoenixF;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.entity.kitsune.AI.KitsuneAttackGoal;
import net.tsw.Temporal_Light.entity.kitsune.AI.KitsuneFireballGoal;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneEntity;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneVarients;
import net.tsw.Temporal_Light.entity.phoenixF.AI.phoenix_fFireballGoal;
import net.tsw.Temporal_Light.villager.TLVillagerRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class phoenix_FEntity extends Villager implements ReputationEventHandler, VillagerDataHolder, NeutralMob {
    private static final EntityDataAccessor<Optional<UUID>> TARGET_UUID = SynchedEntityData.defineId(phoenix_FEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(KitsuneEntity.class, EntityDataSerializers.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;
    private int remainingPersistentAngerTime;
    public final AnimationState Attack01AnimationState = new AnimationState();
    public int AttackAnimationTimeout = 0;
    private int foodLevel;
    private static final EntityDataAccessor<Integer> VARIENT = SynchedEntityData.defineId(phoenix_FEntity.class,EntityDataSerializers.INT);
    private static final EntityDataAccessor<VillagerData> DATA_VILLAGER_DATA = SynchedEntityData.defineId(phoenix_FEntity.class, EntityDataSerializers.VILLAGER_DATA);

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    private static final int IdleTime = 80;
    private static final int AttackTime = 10;

    public phoenix_FEntity(EntityType<? extends phoenix_FEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;
    }
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PARROT_HURT;
    }
    public void setVillagerData(VillagerData villagerData) {
        VillagerData villagerdata = this.getVillagerData();
        if (VillagerProfession.ARMORER.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.armorer);
        }else if (VillagerProfession.BUTCHER.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.butcher);
        }else if (VillagerProfession.CARTOGRAPHER.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.cartographer);
        }else if (VillagerProfession.CLERIC.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.cleric);
        }else if (VillagerProfession.FARMER.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.farmer);
        }else if (VillagerProfession.FLETCHER.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.fletcher);
        }else if (VillagerProfession.FISHERMAN.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.fisherman);
        }else if (VillagerProfession.LEATHERWORKER.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.leatherworker);
        }else if (VillagerProfession.LIBRARIAN.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.librarian);
        }else if (VillagerProfession.MASON.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.mason);
        }else if (VillagerProfession.SHEPHERD.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.sheperd);
        }else if (VillagerProfession.TOOLSMITH.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.toolsmith);
        }else if (VillagerProfession.WEAPONSMITH.equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.weaponsmith);
        }else if (TLVillagerRegistry.END_TRADER.get().equals(villagerData.getProfession())){
            this.setVarient(phoenix_fVarients.end_trader);
        }else if (VillagerProfession.NONE.equals(villagerData.getProfession())||VillagerProfession.NITWIT.equals(villagerData.getProfession())) {
            this.setVarient(phoenix_fVarients.nitwit);
        } else {
            this.setVarient(phoenix_fVarients.other_trader);
        }
        super.setVillagerData(villagerData);
    }
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PARROT_DEATH;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.75, Ingredient.of(Items.SWEET_BERRIES), false));
        //this.goalSelector.addGoal(1, new KitsuneAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(1, new phoenix_fFireballGoal(this));
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
    public void setVarient(phoenix_fVarients varient){
        this.entityData.set(VARIENT,varient.getId() & 255);
    }

    @Override
    protected SoundEvent getDrinkingSound(ItemStack pStack) {
        return SoundEvents.PARROT_EAT;
    }

    public void aiStep() {
        super.aiStep();
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < 0.0D) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
        }
    }
    @Override
    public SoundEvent getEatingSound(ItemStack pStack) {
        return SoundEvents.PARROT_EAT;
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

    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        this.entityData.set(TARGET_UUID, target != null ? Optional.of(target.getUUID()) : Optional.empty());
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

    public phoenix_fVarients getVarient() {
        return phoenix_fVarients.byID(this.getTypeVarient()&255);
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(DATA_VILLAGER_DATA, new VillagerData(VillagerType.PLAINS, VillagerProfession.NONE, 1));
        this.entityData.define(TARGET_UUID, Optional.empty());
        this.entityData.define(VARIENT,0);
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addPersistentAngerSaveData(pCompound);
        pCompound.putInt("varient",this.getTypeVarient());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readPersistentAngerSaveData(this.level(), pCompound);

        this.entityData.set(VARIENT,pCompound.getInt("varient"));
    }
    private int getTypeVarient(){
        return this.entityData.get(VARIENT);
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
    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = IdleTime;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
        LivingEntity target = this.getTarget();
        //System.out.println("Client tick, isAttacking: " + isAttacking() + ", NightForm: " + isNightForm() + ", Target: " + (target != null ? target.getName().getString() : "null"));
        if (this.isAttacking() && target != null) {
            //  System.out.println("IsAttacking: true, Timeout: " + AttackAnimationTimeout + ", Target: " + target.getName().getString());
            if (AttackAnimationTimeout <= 0) {
                float distance = this.distanceTo(target);
                //    System.out.println("Target distance: " + distance);
                if (!Attack01AnimationState.isStarted()) {
                    Attack01AnimationState.start(this.tickCount);
                    playSound(SoundEvents.AXOLOTL_ATTACK);
                    //        System.out.println("Attack01 animation started");
                }
                AttackAnimationTimeout = AttackTime;

            }
            --AttackAnimationTimeout;
        } else {
            AttackAnimationTimeout = 0;
            Attack01AnimationState.stop();
            //System.out.println("Attack animations stopped");
        }
    }

    @Nullable
    @Override
    public Villager getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        phoenix_FEntity villager = new phoenix_FEntity(TLEntityRegistry.PHOENIX_F.get(), pLevel);
        return villager;
    }
    public boolean canBreed() {
        return this.foodLevel + this.countFoodPointsInInventory() >= 12 && !this.isSleeping() && this.getAge() == 0;
    }
    private void digestFood(int pQty) {
        this.foodLevel -= pQty;
    }

    public void eatAndDigestFood() {
        this.eatUntilFull();
        this.digestFood(12);
    }
    private int countFoodPointsInInventory() {
        SimpleContainer simplecontainer = this.getInventory();
        return FOOD_POINTS.entrySet().stream().mapToInt((p_186300_) -> {
            return simplecontainer.countItem(p_186300_.getKey()) * p_186300_.getValue();
        }).sum();
    }
    private boolean hungry() {
        return this.foodLevel < 12;
    }

    private void eatUntilFull() {
        if (this.hungry() && this.countFoodPointsInInventory() != 0) {
            for(int i = 0; i < this.getInventory().getContainerSize(); ++i) {
                ItemStack itemstack = this.getInventory().getItem(i);
                if (!itemstack.isEmpty()) {
                    Integer integer = FOOD_POINTS.get(itemstack.getItem());
                    if (integer != null) {
                        int j = itemstack.getCount();

                        for(int k = j; k > 0; --k) {
                            this.foodLevel += integer;
                            this.getInventory().removeItem(i, 1);
                            if (!this.hungry()) {
                                return;
                            }
                        }
                    }
                }
            }

        }
    }


    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData, @javax.annotation.Nullable CompoundTag pDataTag) {
        phoenix_fVarients varient = phoenix_fVarients.nitwit;
        this.setVarient(varient);
        return super.finalizeSpawn(pLevel, pDifficulty,MobSpawnType.SPAWN_EGG, pSpawnGroupData,pDataTag);
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
        //Temporal_Light.LOGGER.info("phoenix Data"+entityData.toString());
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
}
