package net.tsw.Temporal_Light.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.TLLifeProjectileEntity;
import net.tsw.Temporal_Light.items.TLItemRegistry;

import javax.annotation.Nullable;

public class TLLife_staff extends Item {
    private boolean clt = false;
    private Ingredient ing;
    public TLLife_staff(Properties pProperties, int durability, Ingredient ingredient) {
        super(pProperties.defaultDurability(durability));
        ing = ingredient;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if(!clt) {

            pLevel.playSound((Player) null, pPlayer.getX() + 2 * Math.cos(pPlayer.getXRot()), pPlayer.getY() - Math.sin(pPlayer.getYRot()), pPlayer.getZ() + 2 * Math.sin(pPlayer.getXRot()), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!pLevel.isClientSide) {
                TLLifeProjectileEntity lifeProjectileEntity = new TLLifeProjectileEntity(pLevel, pPlayer);
                //lightningProjectileEntity.setItem(itemstack);
                lifeProjectileEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 20F, 1F);
                pLevel.addFreshEntity(lifeProjectileEntity);
            }
            if((pPlayer.getItemInHand(pHand).getDamageValue()+1)>=pPlayer.getItemInHand(pHand).getMaxDamage()) {
                ItemStack stack = new ItemStack(TLItemRegistry.MAGIC_ESSENCE_NULL.get());
                SimpleContainer inventory = new SimpleContainer(1);
                inventory.setItem(0, stack);
                Containers.dropContents(pLevel, pPlayer.getOnPos(), inventory);
            }
            pPlayer.getItemInHand(pHand).hurtAndBreak(1, pPlayer, player -> player.broadcastBreakEvent(pHand));
        }
        clt = false;
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(pContext.getClickedFace());
        clt = true;
        if (applyBonemeal(pContext.getItemInHand(), level, blockpos, pContext.getPlayer())) {
            if (!level.isClientSide) {
                level.levelEvent(1505, blockpos, 0);
            }
            pContext.getItemInHand().hurtAndBreak(1,pContext.getPlayer(),player -> player.broadcastBreakEvent(player.getUsedItemHand()));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            BlockState blockstate = level.getBlockState(blockpos);
            boolean flag = blockstate.isFaceSturdy(level, blockpos, pContext.getClickedFace());
            if (flag && growWaterPlant(pContext.getItemInHand(), level, blockpos1, pContext.getClickedFace())) {
                if (!level.isClientSide) {
                    level.levelEvent(1505, blockpos1, 0);
                }
                if((pContext.getItemInHand().getDamageValue()+1)>=pContext.getItemInHand().getMaxDamage()) {
                    ItemStack stack = new ItemStack(TLItemRegistry.MAGIC_ESSENCE_NULL.get());
                    SimpleContainer inventory = new SimpleContainer(1);
                    inventory.setItem(0,stack);
                    Containers.dropContents(pContext.getLevel(),pContext.getPlayer().getOnPos(),inventory);
                }
                pContext.getItemInHand().hurtAndBreak(1,pContext.getPlayer(),player -> player.broadcastBreakEvent(player.getUsedItemHand()));
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        }

    }
    public static boolean applyBonemeal(ItemStack pStack, Level pLevel, BlockPos pPos, net.minecraft.world.entity.player.Player player) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, pLevel, pPos, blockstate, pStack);
        if (hook != 0) return hook > 0;
        if (blockstate.getBlock() instanceof BonemealableBlock) {
            BonemealableBlock bonemealableblock = (BonemealableBlock)blockstate.getBlock();
            if (bonemealableblock.isValidBonemealTarget(pLevel, pPos, blockstate, pLevel.isClientSide)) {
                if (pLevel instanceof ServerLevel) {
                    if (bonemealableblock.isBonemealSuccess(pLevel, pLevel.random, pPos, blockstate)) {
                        bonemealableblock.performBonemeal((ServerLevel)pLevel, pLevel.random, pPos, blockstate);
                    }
                    //pStack.shrink(1);
                }

                return true;
            }
        }

        return false;
    }
    public static boolean growWaterPlant(ItemStack pStack, Level pLevel, BlockPos pPos, @Nullable Direction pClickedSide) {
        if (pLevel.getBlockState(pPos).is(Blocks.WATER) && pLevel.getFluidState(pPos).getAmount() == 8) {
            if (!(pLevel instanceof ServerLevel)) {
                return true;
            } else {
                RandomSource randomsource = pLevel.getRandom();

                label78:
                for(int i = 0; i < 128; ++i) {
                    BlockPos blockpos = pPos;
                    BlockState blockstate = Blocks.SEAGRASS.defaultBlockState();

                    for(int j = 0; j < i / 16; ++j) {
                        blockpos = blockpos.offset(randomsource.nextInt(3) - 1, (randomsource.nextInt(3) - 1) * randomsource.nextInt(3) / 2, randomsource.nextInt(3) - 1);
                        if (pLevel.getBlockState(blockpos).isCollisionShapeFullBlock(pLevel, blockpos)) {
                            continue label78;
                        }
                    }

                    Holder<Biome> holder = pLevel.getBiome(blockpos);
                    if (holder.is(BiomeTags.PRODUCES_CORALS_FROM_BONEMEAL)) {
                        if (i == 0 && pClickedSide != null && pClickedSide.getAxis().isHorizontal()) {
                            blockstate = BuiltInRegistries.BLOCK.getTag(BlockTags.WALL_CORALS).flatMap((p_204098_) -> {
                                return p_204098_.getRandomElement(pLevel.random);
                            }).map((p_204100_) -> {
                                return p_204100_.value().defaultBlockState();
                            }).orElse(blockstate);
                            if (blockstate.hasProperty(BaseCoralWallFanBlock.FACING)) {
                                blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, pClickedSide);
                            }
                        } else if (randomsource.nextInt(4) == 0) {
                            blockstate = BuiltInRegistries.BLOCK.getTag(BlockTags.UNDERWATER_BONEMEALS).flatMap((p_204091_) -> {
                                return p_204091_.getRandomElement(pLevel.random);
                            }).map((p_204095_) -> {
                                return p_204095_.value().defaultBlockState();
                            }).orElse(blockstate);
                        }
                    }

                    if (blockstate.is(BlockTags.WALL_CORALS, (p_204093_) -> {
                        return p_204093_.hasProperty(BaseCoralWallFanBlock.FACING);
                    })) {
                        for(int k = 0; !blockstate.canSurvive(pLevel, blockpos) && k < 4; ++k) {
                            blockstate = blockstate.setValue(BaseCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(randomsource));
                        }
                    }

                    if (blockstate.canSurvive(pLevel, blockpos)) {
                        BlockState blockstate1 = pLevel.getBlockState(blockpos);
                        if (blockstate1.is(Blocks.WATER) && pLevel.getFluidState(blockpos).getAmount() == 8) {
                            pLevel.setBlock(blockpos, blockstate, 3);
                        } else if (blockstate1.is(Blocks.SEAGRASS) && randomsource.nextInt(10) == 0) {
                            ((BonemealableBlock)Blocks.SEAGRASS).performBonemeal((ServerLevel)pLevel, randomsource, blockpos, blockstate1);
                        }
                    }
                }
                return true;
            }
        } else {
            return false;
        }
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
