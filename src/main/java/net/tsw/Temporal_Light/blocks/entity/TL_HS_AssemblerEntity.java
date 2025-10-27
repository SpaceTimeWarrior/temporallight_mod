package net.tsw.Temporal_Light.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tsw.Temporal_Light.Recipe.TL_HS_AssemblerRecipes;
import net.tsw.Temporal_Light.menu.TL_HS_Assembler_Menu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TL_HS_AssemblerEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(11);

    private static final int FUEL_SLOT = 0;
    private static final int INPUTSLOT_1 = 1;
    private static final int INPUTSLOT_2 = 2;
    private static final int INPUTSLOT_3 = 3;
    private static final int INPUTSLOT_4 = 4;
    private static final int INPUTSLOT_5 = 5;
    private static final int INPUTSLOT_6 = 6;
    private static final int INPUTSLOT_7 = 7;
    private static final int INPUTSLOT_8 = 8;
    private static final int INPUTSLOT_9 = 9;
    private static final int OUTPUTSLOT = 10;
    public int counter = 0;
    private LazyOptional<IItemHandler> lazyitemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int PowerIn = 0;
    public TL_HS_AssemblerEntity( BlockPos pPos, BlockState pBlockState) {
        super(TLBlockEntityRegistry.TLASSEMBLER.get(), pPos, pBlockState);
        this.data = new ContainerData(){
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 ->TL_HS_AssemblerEntity.this.PowerIn;
                    default->0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0->{TL_HS_AssemblerEntity.this.PowerIn = pValue;/*System.out.println("updating signal to:"+pValue);*/}
                    default-> pValue=0;
                };
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyitemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyitemHandler = LazyOptional.of(()->itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyitemHandler.invalidate();
    }
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i=0;i<itemHandler.getSlots();i++){
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.temporal_light.hypersteelassembler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new TL_HS_Assembler_Menu(pContainerId,pPlayerInventory,this,this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory",itemHandler.serializeNBT());
        pTag.putInt("powin",PowerIn);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        PowerIn = pTag.getInt("powin");
    }
    public void Lightning_Craft(BlockPos pos){
        if(hasRecipe()){
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
            if (lightning != null) {
                lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                level.addFreshEntity(lightning);
            }
            setChanged(this.level,pos,this.getBlockState());
            craft_item();
        }
    }

    private void craft_item() {
        Optional<TL_HS_AssemblerRecipes> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);
        //ItemStack result = new ItemStack(Items.DIAMOND,1);
        this.itemHandler.extractItem(FUEL_SLOT,1,false);
        this.itemHandler.extractItem(INPUTSLOT_1,1,false);
        this.itemHandler.extractItem(INPUTSLOT_2,1,false);
        this.itemHandler.extractItem(INPUTSLOT_3,1,false);
        this.itemHandler.extractItem(INPUTSLOT_4,1,false);
        this.itemHandler.extractItem(INPUTSLOT_5,1,false);
        this.itemHandler.extractItem(INPUTSLOT_6,1,false);
        this.itemHandler.extractItem(INPUTSLOT_7,1,false);
        this.itemHandler.extractItem(INPUTSLOT_8,1,false);
        this.itemHandler.extractItem(INPUTSLOT_9,1,false);
        this.itemHandler.setStackInSlot(OUTPUTSLOT, new ItemStack(result.getItem(),this.itemHandler.getStackInSlot(OUTPUTSLOT).getCount()+result.getCount()));
    }

    private boolean hasRecipe() {
        /*
        boolean hasFuel = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.COAL;
        boolean hasinput1 = this.itemHandler.getStackInSlot(INPUTSLOT_1).isEmpty();
        boolean hasinput2 = this.itemHandler.getStackInSlot(INPUTSLOT_2).getItem() == Items.COAL;
        boolean hasinput3 = this.itemHandler.getStackInSlot(INPUTSLOT_3).isEmpty();
        boolean hasinput4 = this.itemHandler.getStackInSlot(INPUTSLOT_4).getItem() == Items.COAL;
        boolean hasinput5 = this.itemHandler.getStackInSlot(INPUTSLOT_5).getItem() == Blocks.COAL_BLOCK.asItem();
        boolean hasinput6 = this.itemHandler.getStackInSlot(INPUTSLOT_6).getItem() == Items.COAL;
        boolean hasinput7 = this.itemHandler.getStackInSlot(INPUTSLOT_7).isEmpty();
        boolean hasinput8 = this.itemHandler.getStackInSlot(INPUTSLOT_8).getItem() == Items.COAL;
        boolean hasinput9 = this.itemHandler.getStackInSlot(INPUTSLOT_9).isEmpty();
        boolean hasInventory = hasFuel&&hasinput1&&hasinput2&&hasinput3&&hasinput4&&hasinput5&&hasinput6&&hasinput7&&hasinput8&&hasinput9;
        boolean hasRedstone = hasRedstone();
        ItemStack result = new ItemStack(Items.DIAMOND);
        return hasInventory&&hasRedstone&&canInsetAmountIntoOutputSlot(result.getCount())&& canInsertItemIntoOutputSlot(result.getItem());
        */
        Optional<TL_HS_AssemblerRecipes> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());
        boolean hasRedstones = (this.level.getBestNeighborSignal(worldPosition)>=recipe.get().getminRedstone())&&(this.level.getBestNeighborSignal(worldPosition)<=recipe.get().getmaxRedstone());

        return hasRedstones&&canInsetAmountIntoOutputSlot(result.getCount())&& canInsertItemIntoOutputSlot(result.getItem());

    }

    private Optional<TL_HS_AssemblerRecipes> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i<itemHandler.getSlots();i++){
            inventory.setItem(i,this.itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(TL_HS_AssemblerRecipes.Type.INSTANCE,inventory,level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUTSLOT).isEmpty()||this.itemHandler.getStackInSlot(OUTPUTSLOT).is(item);
    }

    private boolean canInsetAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUTSLOT).getCount() + count <=this.itemHandler.getStackInSlot(OUTPUTSLOT).getMaxStackSize();
    }

    public boolean hasRedstone(){
        return PowerIn >=0;
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        counter++;
        counter%=360;
        if(counter == 0){
            //System.out.println("power level"+PowerIn);
        }
        setPower(pLevel.getBestNeighborSignal(pPos));
    }

    public int get_power() {
        if (PowerIn !=this.level.getBestNeighborSignal(worldPosition)){
            setPower(this.level.getBestNeighborSignal(worldPosition));
        }
        return this.level.getBestNeighborSignal(worldPosition);
    }

    public void setPower(int directSignalTo) {
        PowerIn = directSignalTo;
        //System.out.println("updating signal to:"+directSignalTo);
        data.set(0,directSignalTo);
    }
}