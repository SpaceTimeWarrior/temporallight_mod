package com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune;

import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.AI.EntityAIRangedAttack;
import com.TimeSpaceWarrior.TemporalLightMod.entity.AI.TLEntityAIBeg;
import com.TimeSpaceWarrior.TemporalLightMod.entity.FireballProjectileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

public class EntityKitsune extends EntityTameable implements IRangedAttackMob {
    Random rand = new Random(worldObj.getWorldTime());
    public int Varient;
    boolean has_updated = false;
    private boolean loadedfromNBT;
    public static Item RandomPreferencefood;
    public ArrayList<Item>RandomaltPreferenceFoods = new ArrayList<Item>(0);

    public ItemStack[] storedEquipment = new ItemStack[5];
    public ItemStack[] tamedInventory = new ItemStack[27];



    public EntityKitsune(World world) {
        super(world);
        this.setCanPickUpLoot(true);
        this.tasks.addTask(0,new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(3, new EntityAIRangedAttack(this,5.0D));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new TLEntityAIBeg(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(4, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntityOcelot.class,200,false));
        //loadedfromNBT = false;
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean isTamed() {
        return this.dataWatcher.getWatchableObjectByte(16) == 1;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
    }
    @Override
    public void setTamed(boolean tamed) {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)(tamed ? 1 : 0)));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return null;
    }
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variants",this.getVariant());
        for (int i = 0; i < storedEquipment.length; i++) {
            NBTTagCompound itemTag = new NBTTagCompound();
            if (storedEquipment[i] != null) {
                storedEquipment[i].writeToNBT(itemTag);
            }
            compound.setTag("StoredGear" + i, itemTag);
        }
        for (int i = 0; i < tamedInventory.length; i++) {
            NBTTagCompound itemTag = new NBTTagCompound();
            if (tamedInventory[i] != null) {
                tamedInventory[i].writeToNBT(itemTag);
            }
            compound.setTag("TamedInventory" + i, itemTag);
        }
        NBTTagCompound itemTagRP = new NBTTagCompound();
        new ItemStack(RandomPreferencefood).writeToNBT(itemTagRP);
        compound.setTag("randompreference",itemTagRP);

    }

    @Override
    public void onItemPickup(Entity p_71001_1_, int p_71001_2_) {
        super.onItemPickup(p_71001_1_, p_71001_2_);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        posY++;
        setVariant(compound.getInteger("Variants"));
        for (int i = 0; i < storedEquipment.length; i++) {
            NBTTagCompound itemTag = compound.getCompoundTag("StoredGear" + i);
            storedEquipment[i] = ItemStack.loadItemStackFromNBT(itemTag);

        }
        NBTTagCompound itemTag = compound.getCompoundTag("randompreference");
        for (int i = 0; i < tamedInventory.length; i++) {
            NBTTagCompound itemTags = compound.getCompoundTag("TamedInventory" + i);
            tamedInventory[i] = ItemStack.loadItemStackFromNBT(itemTags);

        }
        RandomPreferencefood = ItemStack.loadItemStackFromNBT(itemTag).getItem();
        if(RandomPreferencefood!=null){
            RandomaltPreferenceFoods=TemporalLightMod.getListOfAltFoods(TemporalLightMod.getKitsuneRandomTamebyItem(RandomPreferencefood));
        }
        if(RandomaltPreferenceFoods == null){
            int tmp = rand.nextInt(TemporalLightMod.KitsuneRandomTame.size());
            RandomPreferencefood = TemporalLightMod.KitsuneRandomTame.get(tmp);
            RandomaltPreferenceFoods=TemporalLightMod.getListOfAltFoods(tmp);
        }
        this.loadedfromNBT = true;

    }
    public boolean isItemFavoriteRPF(ItemStack stack){
        if(stack == null){
            return false;
        }
        if (stack.getItem()==null){
            return false;
        }
        if(stack.getItem().equals(RandomPreferencefood)){
            return true;
        }
        return false;
    }//random raw
    public boolean isItemFavoriteRAPF(ItemStack stack){
        if(stack == null){
            return false;
        }
        if (stack.getItem()==null){
            return false;
        }
        for(int i=0;i<RandomaltPreferenceFoods.size();i++){
            if(stack.getItem().equals(RandomaltPreferenceFoods.get(i))){
                return true;
            }
        }
        return false;
    }//random cooked
    public boolean isItemFavoriteBG(ItemStack stack){
        if(stack == null){
            return false;
        }
        if (stack.getItem()==null){
            return false;
        }
        for(int i=0;i<TemporalLightMod.KitsuneBadGut.size();i++){
            if(stack.getItem().equals(TemporalLightMod.KitsuneBadGut.get(i))){
                return true;
            }
        }
        return false;
    }//guarenteed raw
    public boolean isItemFavoriteG(ItemStack stack){
        if(stack == null){
            return false;
        }
        if (stack.getItem()==null){
            return false;
        }
        for(int i=0;i<TemporalLightMod.KitsuneGut.size();i++){
            if(stack.getItem().equals(TemporalLightMod.KitsuneGut.get(i))){
                return true;
            }
        }
        return false;
    }//guarenteed cooked

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack stacks = player.getHeldItem();
        if(stacks!=null){
            if(stacks.getItem()==Items.milk_bucket){
                this.clearActivePotions();
                player.inventory.setInventorySlotContents(player.inventory.currentItem,new ItemStack(Items.bucket));

            }
        }

        if(this.isTamed()&&!player.isSneaking()){
            //open menu when tamed for future releases
            //System.out.println("this kitsune is already tamed");
            if(stacks!=null) {
                if (stacks.getItem() instanceof ItemFood) {
                    this.heal(3.0f);
                    player.inventory.decrStackSize(player.inventory.currentItem, 1);
                }
            }
            if(!worldObj.isRemote){
                player.openGui(TemporalLightMod.instance,TemporalLightMod.KITSUNE_GUIID,worldObj,(int) posX,(int)posY,(int)posZ);
            }
            return false;
        }else {
            ItemStack stack = player.getHeldItem();
            if (stack == null) {
                return false;
            }else{
                if(isItemFavorite(stack)){
                    this.setTamed(true);
                    this.func_152115_b(player.getUniqueID().toString());
                    this.worldObj.setEntityState(this,(byte)7);
                    if(isItemFavoriteRPF(stack)){
                        Random rand = this.getRNG();
                        if(rand.nextInt(100)<30) {
                            this.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
                            this.generateRandomParticles("slime");
                        }
                    }
                    if(isItemFavoriteBG(stack)){
                        Random rand = this.getRNG();
                        switch (rand.nextInt(3)){
                            case 0:
                                this.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
                                this.addPotionEffect(new PotionEffect(Potion.confusion.id, 100,0));
                                this.addPotionEffect(new PotionEffect(Potion.weakness.id, 100,0));
                                this.generateRandomParticles("slime");
                                break;
                            case 1:
                                this.addPotionEffect(new PotionEffect(Potion.poison.id, 200, 1));
                                this.addPotionEffect(new PotionEffect(Potion.confusion.id, 100,0));
                                this.generateRandomParticles("witchMagic");
                                break;
                            case 2:
                                this.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
                                this.addPotionEffect(new PotionEffect(Potion.confusion.id, 100,0));
                                this.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100,0));
                                this.generateRandomParticles("slime");
                                break;
                        }
                        player.inventory.decrStackSize(player.inventory.currentItem,1);
                        return true;
                    }
                    player.inventory.decrStackSize(player.inventory.currentItem,1);
                }
            }
        }
        return false;
    }

    public boolean isItemFavorite(ItemStack stack){
        if(stack == null){
            return false;
        }
        if (stack.getItem()==null){
            return false;
        }
        if(isItemFavoriteRPF(stack)){return true;}
        if(isItemFavoriteRAPF(stack)){return true;}
        if(isItemFavoriteBG(stack)){return true;}
        if(isItemFavoriteG(stack)){return true;}
        return false;
    }
    @SideOnly(Side.CLIENT)
    private void generateRandomParticles(String p_70942_1_) {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(p_70942_1_, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 1.0D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
        }
    }
    @Override
    public boolean attackEntityAsMob(Entity target) {
        float damage;
        if(getEquipmentInSlot(0)!=null){
            damage = 1+this.rand.nextInt(4)+getEquipmentInSlot(0).getItemDamage();
        }else{
            damage = 1+this.rand.nextInt(4);
        }

        boolean attacked = target.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
        return attacked;
    }
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.isNightForm()) {
            this.setSize(0.4F, 1.0F); // smaller hitbox
        } else {
            this.setSize(0.6F, 1.8F); // full size
        }
        if(this.isTransition()){
            Random random = this.getRNG();
            if(random.nextInt(3)==0) {
                generateRandomParticles("flame");
            }else if(random.nextInt(3)==1){
                generateRandomParticles("mobSpellAmbient");
            }else{
                generateRandomParticles("magicCrit");
            }
        }
        if(this.isDayForm()){
            for (int i = 0; i < storedEquipment.length; i++) {
                if (this.getEquipmentInSlot(i) == null && storedEquipment[i] != null) {
                    this.setCurrentItemOrArmor(i, storedEquipment[i]);
                }else if(this.getEquipmentInSlot(i)!=null){
                    storedEquipment[i]=this.getEquipmentInSlot(i);
                }
            }
            has_updated = false;
        }else{
            if(has_updated==false) {
                for (int i = 0; i < storedEquipment.length; i++) {
                    storedEquipment[i] = this.getEquipmentInSlot(i);
                    this.setCurrentItemOrArmor(i, null);
                }
                has_updated = true;
            }
        }

    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, Integer.valueOf(0));
        if (!loadedfromNBT) {
            rand = new Random(worldObj.getWorldTime());
            Varient = rand.nextInt(100) + 1;
            int tmp = rand.nextInt(TemporalLightMod.KitsuneRandomTame.size());
            RandomPreferencefood = TemporalLightMod.KitsuneRandomTame.get(tmp);
            RandomaltPreferenceFoods=TemporalLightMod.getListOfAltFoods(tmp);
            if (Varient > 60) {
                setVariant(0);
            } else if (Varient > 40) {
                setVariant(1);
            } else if (Varient > 20) {
                setVariant(2);
            } else if (Varient > 3) {
                setVariant(3);
            } else {
                setVariant(4);
            }


        }
    }
    protected boolean canDespawn()
    {
        return !this.isTamed() && this.ticksExisted > 2400;
    }
    @Override
    protected Item getDropItem() {
        int rng = rand.nextInt(10);
        switch (rng){
            case 0:
            case 1:
            case 2:
                return ItemRegistry.KITSUNE_TAIL;
            case 3:
            case 4:
                return Items.book;
            case 5:
            case 6:
                return Items.leather;
            default:
        }
        return super.getDropItem();
    }

    public boolean isNightForm() {return this.worldObj.getWorldTime() % 24000 >= 13000;}
    public boolean isDayForm() {
        return this.worldObj.getWorldTime() % 24000 <= 12980&&this.worldObj.getWorldTime() % 24000 >=20;
    }
    public boolean isTransition(){
        return !(isDayForm()||isNightForm());
    }
    public void setVariant(int variant) {
        this.dataWatcher.updateObject(20, Integer.valueOf(variant));
        this.Varient = variant;
    }
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    public int getVariant() { return this.dataWatcher.getWatchableObjectInt(20);}
    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause); // Handle default drops and equipment

        // Drop tamed inventory items
        InventoryKitsune tamedInv = new InventoryKitsune(this, false); // Get tamed inventory
        for (int i = 0; i < tamedInv.getSizeInventory(); i++) {
            ItemStack itemStack = tamedInv.getStackInSlot(i);
            if (itemStack != null) {
                entityDropItem(itemStack, 0.0F); // Drop at entity position
                tamedInv.setInventorySlotContents(i, null); // Clear the slot
            }
        }
    }
    @SuppressWarnings("EntityConstructor")
    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
        FireballProjectileEntity entityarrow = new FireballProjectileEntity(worldObj,this);


        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
    }
}
