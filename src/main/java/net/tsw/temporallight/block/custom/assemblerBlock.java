package net.tsw.temporallight.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.container.assemblerContainer;
import net.tsw.temporallight.tileentity.TileEntityRegistry;
import net.tsw.temporallight.tileentity.assemblerTileEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.minecraft.block.ShulkerBoxBlock.CONTENTS;

public class assemblerBlock extends ContainerBlock {
    public assemblerBlock(Properties properties) {
        super(properties);
    }
    @SuppressWarnings("deprecated")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(!player.isCrouching()) {
                if (tileEntity instanceof assemblerTileEntity) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }else{
                if(tileEntity instanceof assemblerTileEntity) {
                    if(worldIn.getRedstonePower(tileEntity.getPos(),null)>0) {
                        EntityType.LIGHTNING_BOLT.spawn(((ServerWorld) worldIn), null, player,
                                pos, SpawnReason.TRIGGERED, true, true);

                        ((assemblerTileEntity)tileEntity).onLightningStrike();
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.temporallight.assembler");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerinventory, PlayerEntity playerentity) {
                return new assemblerContainer(i,worldIn,pos,playerinventory,playerentity);
            }
        };
    }
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        TileEntity tileentity = builder.get(LootParameters.BLOCK_ENTITY);
        if (tileentity instanceof assemblerTileEntity) {
            assemblerTileEntity assemblertileentity = (assemblerTileEntity) tileentity;
            builder = builder.withDynamicDrop(CONTENTS, (context, stackConsumer) -> {
                for(int i = 0; i < 11; ++i) {
                    stackConsumer.accept(assemblertileentity.getStackInSlot(i));
                }

            });
        }

        return super.getDrops(state, builder);
    }
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        ItemStack itemstack = super.getItem(worldIn, pos, state);
        assemblerTileEntity assemblertileentity = (assemblerTileEntity) worldIn.getTileEntity(pos);
        CompoundNBT compoundnbt = assemblertileentity.write(new CompoundNBT());
        if (!compoundnbt.isEmpty()) {
            itemstack.setTagInfo("BlockEntityTag", compoundnbt);
        }

        return itemstack;
    }

    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.matchesBlock(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof assemblerTileEntity) {
                worldIn.updateComparatorOutputLevel(pos, state.getBlock());
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CompoundNBT compoundnbt = stack.getChildTag("BlockEntityTag");
        if (compoundnbt != null) {
            if (compoundnbt.contains("LootTable", 8)) {
                tooltip.add(new StringTextComponent("???????"));
            }

            if (compoundnbt.contains("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(compoundnbt, nonnulllist);
                int i = 0;
                int j = 0;

                for(ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;
                        if (i <= 4) {
                            ++i;
                            IFormattableTextComponent iformattabletextcomponent = itemstack.getDisplayName().deepCopy();
                            iformattabletextcomponent.appendString(" x").appendString(String.valueOf(itemstack.getCount()));
                            tooltip.add(iformattabletextcomponent);
                        }
                    }
                }

                if (j - i > 0) {
                    tooltip.add((new TranslationTextComponent("container.shulkerBox.more", j - i)).mergeStyle(TextFormatting.ITALIC));
                }
            }
        }

    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world){
        return TileEntityRegistry.assemblertileentity.get().create();
    }
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return createTileEntity(this.getDefaultState(),worldIn);
    }
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if(tileentity instanceof assemblerTileEntity){
            ((assemblerTileEntity)tileentity).fillWithLoot(player);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {

    }

        @Override
    public boolean hasTileEntity(BlockState state){
        return true;
    }
}
