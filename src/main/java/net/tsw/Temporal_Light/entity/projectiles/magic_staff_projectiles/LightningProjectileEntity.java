package net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.items.TLItemRegistry;

public class LightningProjectileEntity extends ThrowableItemProjectile {
    public LightningProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public LightningProjectileEntity(Level pLevel) {
        super(TLEntityRegistry.LIGHTNING_PROJECTILE.get(), pLevel);
    }
    public LightningProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(TLEntityRegistry.LIGHTNING_PROJECTILE.get(), livingEntity,pLevel);
    }


    @Override
    protected Item getDefaultItem() {
        return TLItemRegistry.LIGHTNING_CHARGE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        BlockPos pos = pResult.getEntity().getOnPos();
        Level level =this.level();
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
        if (lightning != null) {
            lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            level.addFreshEntity(lightning);
        }
        this.discard();
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        BlockPos pos = pResult.getBlockPos();
        Level level =this.level();
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
        if (lightning != null) {
            lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            level.addFreshEntity(lightning);
        }
        this.discard();
        super.onHitBlock(pResult);
    }

}
