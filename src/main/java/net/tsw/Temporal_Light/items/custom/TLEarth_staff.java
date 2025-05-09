package net.tsw.Temporal_Light.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.TLEarthProjectileEntity;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TLEarth_staff extends Item {
    public int Block_counter = 0;

    private boolean block_cutter = false;//fires twice every block break. this is used to prevent odditional calls
    public ArrayList<Block> BL = new ArrayList<Block>();

    private Ingredient ing;
    private boolean clt = false;
    public TLEarth_staff(Properties pProperties, int durability, Ingredient ingredient) {
        super(pProperties.defaultDurability(durability));
        ing = ingredient;
        //BL.add(Blocks.REDSTONE_BLOCK);
        BL.add(Blocks.DIRT);
        BL.add(Blocks.GRASS_BLOCK);
        BL.add(Blocks.SAND);
        BL.add(Blocks.STONE);
        BL.add(Blocks.NETHERRACK);
        BL.add(Blocks.END_STONE);
        BL.add(Blocks.REDSTONE_WIRE);
        BL.add(Blocks.DEEPSLATE);
        //BL.add(Blocks.TNT);
    }
    public TLEarth_staff(Properties pProperties, int durability, Ingredient ingredient,ArrayList<Block> blocklist) {
        super(pProperties.defaultDurability(durability));
        ing = ingredient;
        if(blocklist.size()>=1){
            for(int iii = 0; iii<blocklist.size();iii++){
                BL.add(blocklist.get(iii));
            }
        }else{
            BL.add(Blocks.DIRT);
            BL.add(Blocks.GRASS_BLOCK);
            BL.add(Blocks.SAND);
            BL.add(Blocks.STONE);
            BL.add(Blocks.NETHERRACK);
            BL.add(Blocks.END_STONE);
            BL.add(Blocks.REDSTONE_WIRE);
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.temporal_light.earth_staff.toggle"));
        if(this.Block_counter<BL.size()) {
            pTooltipComponents.add(BL.get(this.Block_counter).getName());
        }
        if(BL.get(0)==Blocks.REDSTONE_BLOCK &&BL.get(BL.size()-1)==Blocks.TNT){
            pTooltipComponents.add(Component.translatable("tooltip.temporal_light.earth_staff.explosion_warning"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if(!clt) {

            pLevel.playSound((Player) null, pPlayer.getX() + 2 * Math.cos(pPlayer.getXRot()), pPlayer.getY() - Math.sin(pPlayer.getYRot()), pPlayer.getZ() + 2 * Math.sin(pPlayer.getXRot()), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!pLevel.isClientSide) {
                TLEarthProjectileEntity earthProjectileEntity = new TLEarthProjectileEntity(pLevel, pPlayer, BL.get(this.Block_counter));
                //lightningProjectileEntity.setItem(itemstack);
                earthProjectileEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 5F, 1F);
                pLevel.addFreshEntity(earthProjectileEntity);
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

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            BlockPos posi = pContext.getClickedPos();
            System.out.println("direction"+pContext.getClickedFace());
            switch (pContext.getClickedFace()){
                case NORTH:
                    posi = posi.north();
                    break;
                case SOUTH:
                    posi = posi.south();
                    break;
                case WEST:
                    posi = posi.west();
                    break;
                case EAST:
                    posi = posi.east();
                    break;
                case UP:
                    posi = posi.above();
                    break;
                case DOWN:
                    posi = posi.below();
                    break;
                default:
            }
            pContext.getLevel().setBlock(posi, BL.get(this.Block_counter).getStateForPlacement(new BlockPlaceContext(pContext)), 3);
        }
        clt = true;
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
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        BlockPos posi = player.getOnPos().above();
        BlockPos posi2 = posi.above();
        player.level().setBlock(posi.north(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi.south(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi.east(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi.west(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi.below(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi2.north(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi2.south(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi2.east(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi2.west(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi2.below(), BL.get(0).defaultBlockState(), 3);
        player.level().setBlock(posi.above(), BL.get(BL.size()-1).defaultBlockState(), 3);
        player.level().setBlock(posi, BL.get(BL.size()-1).defaultBlockState(), 3);
        player.level().setBlock(posi.above(2), BL.get(0).defaultBlockState(), 3);

        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player entity) {
        if(!block_cutter){//fires twice every block break
        this.Block_counter++;
        if (this.Block_counter>=BL.size()){
            this.Block_counter = 0;
        }}
        block_cutter=!block_cutter;
        return super.onBlockStartBreak(stack,pos, entity);
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
