package net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.util.TLTagRegistry;

public class TLEarthProjectileEntity extends ThrowableItemProjectile{
    private Block blox = Blocks.GRASS_BLOCK;
    public TLEarthProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return blox.asItem();
    }

    public TLEarthProjectileEntity(Level pLevel) {
        super(TLEntityRegistry.EARTHBALL.get(), pLevel);
    }
    public TLEarthProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(TLEntityRegistry.EARTHBALL.get(), livingEntity,pLevel);
    }
    public TLEarthProjectileEntity(Level pLevel,Block block) {
        super(TLEntityRegistry.EARTHBALL.get(), pLevel);
        blox = block;
    }
    public TLEarthProjectileEntity(Level pLevel, LivingEntity livingEntity,Block block) {
        super(TLEntityRegistry.EARTHBALL.get(), livingEntity,pLevel);
        blox = block;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().setBlock(blockPosition(), this.blox.defaultBlockState(), 3);
        }
        this.discard();
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if(!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().setBlock(blockPosition(), this.blox.defaultBlockState(), 3);
        }
        this.discard();
        super.onHitBlock(pResult);
    }
}
