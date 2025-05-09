package net.tsw.Temporal_Light.items.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.TLFireball;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.items.TLItemToolTiersRegistry;

public class TLFireStaff extends Item {
    private Ingredient ing;
    public TLFireStaff(Properties pProperties, int durability, Ingredient ingredient) {
        super(pProperties.defaultDurability(durability));
        ing = ingredient;
    }
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
            BlockPos blockpos1 = blockpos.relative(pContext.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, pContext.getHorizontalDirection())) {
                level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockstate1, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
                ItemStack itemstack = pContext.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos1, itemstack);
                    if((pContext.getItemInHand().getDamageValue()+1)>=pContext.getItemInHand().getMaxDamage()) {
                        ItemStack stack = new ItemStack(TLItemRegistry.MAGIC_ESSENCE_NULL.get());
                        SimpleContainer inventory = new SimpleContainer(1);
                        inventory.setItem(0,stack);
                        Containers.dropContents(pContext.getLevel(),pContext.getPlayer().getOnPos(),inventory);
                    }
                    itemstack.hurtAndBreak(1, player, (p_41300_) -> {
                        p_41300_.broadcastBreakEvent(pContext.getHand());
                    });
                }

                return InteractionResult.sidedSuccess(level.isClientSide());
            } else {
                return InteractionResult.FAIL;
            }
        } else {
            level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
            if (player != null) {
                if((pContext.getItemInHand().getDamageValue()+1)>=pContext.getItemInHand().getMaxDamage()) {
                    ItemStack stack = new ItemStack(TLItemRegistry.MAGIC_ESSENCE_NULL.get());
                    SimpleContainer inventory = new SimpleContainer(1);
                    inventory.setItem(0,stack);
                    Containers.dropContents(pContext.getLevel(),pContext.getPlayer().getOnPos(),inventory);
                }
                pContext.getItemInHand().hurtAndBreak(1, player, (p_41303_) -> {
                    p_41303_.broadcastBreakEvent(pContext.getHand());
                });
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            TLFireball fireball = new TLFireball(pLevel,pPlayer);
            //lightningProjectileEntity.setItem(itemstack);
            fireball.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 3F, 1F);
            pLevel.addFreshEntity(fireball);
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
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return TLItemToolTiersRegistry.MAGIWOOD.getEnchantmentValue();
    }


    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        //item.setDamageValue(999);
        item.hurtAndBreak(999,player,splayer ->splayer.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        BlockPos pos = player.getOnPos();
        Level level = player.level();
        DragonFireball fireball = EntityType.DRAGON_FIREBALL.create(level);
        if (fireball != null) {
            fireball.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3F, 1F);
            level.addFreshEntity(fireball);
        }

        return super.onDroppedByPlayer(item, player);
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
