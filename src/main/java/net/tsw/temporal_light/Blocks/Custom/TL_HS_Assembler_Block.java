package net.tsw.temporal_light.Blocks.Custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.tsw.temporal_light.Blocks.entity.TLBlockEntityRegistry;
import net.tsw.temporal_light.Blocks.entity.TL_HS_AssemblerEntity;
import org.jetbrains.annotations.Nullable;

public class TL_HS_Assembler_Block extends BaseEntityBlock {
    public static final MapCodec<TL_HS_Assembler_Block> CODEC = simpleCodec(TL_HS_Assembler_Block::new);
    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public TL_HS_Assembler_Block(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if(pState.getBlock()!=pNewState.getBlock()){
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if(blockEntity instanceof  TL_HS_AssemblerEntity tl_hs_assemblerEntity){
                tl_hs_assemblerEntity.drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        useItemOn(new ItemStack(Blocks.AIR),pState,pLevel,pPos,pPlayer,InteractionHand.MAIN_HAND,pHitResult);
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if(!pLevel.isClientSide()){
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof  TL_HS_AssemblerEntity assembler){
                if(pPlayer.isCrouching()){
                    assembler.LightningWeldingCraft();
                }else {
                    assembler.use(pPlayer);
                }
            }else{
                throw new IllegalStateException("!Our ContainerProvider is missing! ASSEMBLER WILL NOW KILL EVERYTHING");
            }
        }
        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, TLBlockEntityRegistry.TLASSEMBLER.get(),(level,blockPos,blockState,TL_HS_AssemblerEntity)->TL_HS_AssemblerEntity.tick(level,blockPos,blockState));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TL_HS_AssemblerEntity(pPos,pState);
    }
}
