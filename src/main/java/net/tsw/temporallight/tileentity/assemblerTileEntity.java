package net.tsw.temporallight.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.data.recipes.RecipieTypeRegistry;
import net.tsw.temporallight.data.recipes.assemblerRecipes;
import net.tsw.temporallight.item.ItemRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class assemblerTileEntity extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler itemstackhandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(()->itemstackhandler);
    public assemblerTileEntity(){
        this(TileEntityRegistry.assemblertileentity.get());
    }

    public assemblerTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }
    @Override
    public void read(BlockState state, CompoundNBT nbt){
        itemstackhandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state,nbt);
    }
    @Override
    public CompoundNBT write(CompoundNBT compound){
        compound.put("inv",itemstackhandler.serializeNBT());
        return super.write(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(11) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack){
                return true;
            }
            @Override
            public int getSlotLimit(int slot){
                return 64;
            }
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate){
                if(!isItemValid(slot,stack)){
                    return stack;
                }
                return super.insertItem(slot,stack,simulate);
            }
        };
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side){
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }
        return super.getCapability(cap,side);
    }

    public void onLightningStrike(){
        Inventory inv = new Inventory(itemstackhandler.getSlots());
        for (int i = 0; i < itemstackhandler.getSlots(); i++) {
            inv.setInventorySlotContents(i, itemstackhandler.getStackInSlot(i));
        }

        Optional<assemblerRecipes> recipe = world.getRecipeManager()
                .getRecipe(RecipieTypeRegistry.ASSEMBLER_RECIPE, inv, world);

        recipe.ifPresent(iRecipe -> {
            ItemStack output = iRecipe.getRecipeOutput();

            if(iRecipe.getMinRedstone()<=world.getRedstonePower(pos,null)){
                craftTheItem(output);
            }

            markDirty();
        });
    }

    private void craftTheItem(ItemStack output) {
        itemstackhandler.extractItem(0, 1, false);
        itemstackhandler.extractItem(1, 1, false);
        itemstackhandler.extractItem(2, 1, false);
        itemstackhandler.extractItem(3, 1, false);
        itemstackhandler.extractItem(4, 1, false);
        itemstackhandler.extractItem(5, 1, false);
        itemstackhandler.extractItem(6, 1, false);
        itemstackhandler.extractItem(7, 1, false);
        itemstackhandler.extractItem(8, 1, false);
        itemstackhandler.extractItem(9, 1, false);
        itemstackhandler.insertItem(10, output, false);
    }

    @Override
    public void tick() {
        if(world.isRemote){return;}

    }
}
