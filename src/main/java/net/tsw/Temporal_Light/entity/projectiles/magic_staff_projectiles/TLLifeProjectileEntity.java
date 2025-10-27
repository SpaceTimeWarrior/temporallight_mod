package net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;

import java.util.Random;

public class TLLifeProjectileEntity extends ThrowableItemProjectile{

    public TLLifeProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.AZALEA_LEAVES.asItem();
    }

    public TLLifeProjectileEntity(Level pLevel) {
        super(TLEntityRegistry.LIFEBALL.get(), pLevel);
    }
    public TLLifeProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(TLEntityRegistry.LIFEBALL.get(), livingEntity,pLevel);
    }
    public TLLifeProjectileEntity(Level pLevel, Block block) {
        super(TLEntityRegistry.LIFEBALL.get(), pLevel);
    }
    public TLLifeProjectileEntity(Level pLevel, LivingEntity livingEntity, Block block) {
        super(TLEntityRegistry.LIFEBALL.get(), livingEntity,pLevel);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            Entity entity = pResult.getEntity();
            if (entity instanceof LivingEntity){
                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.HEAL,10));
            }
            //this.level().setBlock(blockPosition(), this.blox.defaultBlockState(), 3);
        }
        this.discard();
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            Random rand = new Random();
            rand.setSeed(pResult.getBlockPos().getX()-pResult.getBlockPos().getZ()*pResult.getBlockPos().getY());
            switch(rand.nextInt(5)){
                case 1:
                    this.level().setBlock(blockPosition(), Blocks.AZALEA.defaultBlockState(), 3);
                    break;
                case 2:
                    this.level().setBlock(blockPosition(), Blocks.BAMBOO_SAPLING.defaultBlockState(), 3);
                    break;
                case 3:
                    this.level().setBlock(blockPosition(), Blocks.BEE_NEST.defaultBlockState(), 3);
                    break;
                case 4:
                    this.level().setBlock(blockPosition(), Blocks.CACTUS.defaultBlockState(), 3);
                    break;
                default:
                    this.level().setBlock(blockPosition(), Blocks.GRASS.defaultBlockState(), 3);
            }

        }
        this.discard();
        super.onHitBlock(pResult);
    }
}
