package net.tsw.Temporal_Light.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.LightningProjectileEntity;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.items.TLItemToolTiersRegistry;

public class TLLightning_weapon extends Item {
    private Ingredient ing;
    public TLLightning_weapon(Properties pProperties, int durability, Ingredient ingredient) {
        super(pProperties.defaultDurability(durability));
        ing = ingredient;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return TLItemToolTiersRegistry.MAGIWOOD.getEnchantmentValue();
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            LightningProjectileEntity lightningProjectileEntity = new LightningProjectileEntity(pLevel, pPlayer);
            //lightningProjectileEntity.setItem(itemstack);
            lightningProjectileEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 20F, 1F);
            pLevel.addFreshEntity(lightningProjectileEntity);
        }
        if((pPlayer.getItemInHand(pHand).getDamageValue()+1)>=pPlayer.getItemInHand(pHand).getMaxDamage()) {
            ItemStack stack = new ItemStack(TLItemRegistry.MAGIC_ESSENCE_NULL.get());
            SimpleContainer inventory = new SimpleContainer(1);
            inventory.setItem(0, stack);
            Containers.dropContents(pLevel, pPlayer.getOnPos(), inventory);
        }
        pPlayer.getItemInHand(pHand).hurtAndBreak(1,pPlayer,player -> player.broadcastBreakEvent(pHand));
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        item.hurtAndBreak(999,player,splayer ->splayer.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        BlockPos pos = player.getOnPos();
        Level level = player.level();
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
        if (lightning != null) {
            lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            level.addFreshEntity(lightning);
        }
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        Level level =pContext.getLevel();
        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
        if (lightning != null) {
            lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            level.addFreshEntity(lightning);
        }
        if((pContext.getItemInHand().getDamageValue()+1)>=pContext.getItemInHand().getMaxDamage()) {
            ItemStack stack = new ItemStack(TLItemRegistry.MAGIC_ESSENCE_NULL.get());
            SimpleContainer inventory = new SimpleContainer(1);
            inventory.setItem(0,stack);
            Containers.dropContents(pContext.getLevel(),pContext.getPlayer().getOnPos(),inventory);
        }
        pContext.getItemInHand().hurtAndBreak(1,pContext.getPlayer(),player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        return super.useOn(pContext);
    }
    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return this.ing.test(pRepairCandidate)||super.isValidRepairItem(pStack,pRepairCandidate);
    }
}
