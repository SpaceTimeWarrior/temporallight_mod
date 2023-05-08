package net.tsw.temporallight.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.container.assemblerContainer;
import net.tsw.temporallight.data.recipes.RecipieTypeRegistry;
import net.tsw.temporallight.data.recipes.assemblerRecipes;
import net.tsw.temporallight.item.ItemRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static net.minecraft.block.ShulkerBoxBlock.CONTENTS;

public class assemblerTileEntity extends LockableLootTileEntity implements ITickableTileEntity {
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

    @Override
    protected ITextComponent getDefaultName() {
        return null;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new assemblerContainer(id,player.player.getEntityWorld(), this.pos,player, player.player);
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
                return 128;
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


    public ItemStack getStackInSlot(int i) {
       return itemstackhandler.getStackInSlot(i);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        NonNullList<ItemStack> items = NonNullList.withSize(11, ItemStack.EMPTY);
        for(int i=0;i<11;i++) {
            items.add(getStackInSlot(i));
        }
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {

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

    public void destroy(World world, PlayerEntity player) {

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



    @Override
    public int getSizeInventory() {
        return 11;
    }

    public boolean isEmpty() {
        boolean ret =(getStackInSlot(0).getCount()==0);
        ret =ret&&(getStackInSlot(1).getCount()==0);
        ret =ret&&(getStackInSlot(2).getCount()==0);
        ret =ret&&(getStackInSlot(3).getCount()==0);
        ret =ret&&(getStackInSlot(4).getCount()==0);
        ret =ret&&(getStackInSlot(5).getCount()==0);
        ret =ret&&(getStackInSlot(6).getCount()==0);
        ret =ret&&(getStackInSlot(7).getCount()==0);
        ret =ret&&(getStackInSlot(8).getCount()==0);
        ret =ret&&(getStackInSlot(9).getCount()==0);
        ret =ret&&(getStackInSlot(10).getCount()==0);
        return  ret;
    }

    public void fillWithLoot(PlayerEntity player) {
       ItemStackHandler pinv = new ItemStackHandler(player.inventory.getSizeInventory()) {
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
       int ii = player.inventory.getSizeInventory();
       for(int i=0;i<ii;i++){
           pinv.insertItem(i,player.inventory.getStackInSlot(i),false);
        }
        player.inventory.clear();
        player.addItemStackToInventory(itemstackhandler.extractItem(0,getStackInSlot(0).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(1,getStackInSlot(1).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(2,getStackInSlot(2).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(3,getStackInSlot(3).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(4,getStackInSlot(4).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(5,getStackInSlot(5).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(6,getStackInSlot(6).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(7,getStackInSlot(7).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(8,getStackInSlot(8).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(9,getStackInSlot(9).getCount(),false));
        player.addItemStackToInventory(itemstackhandler.extractItem(10,getStackInSlot(10).getCount(),false));
        player.inventory.dropAllItems();
        for(int i=0;i<ii;i++){
            player.addItemStackToInventory(pinv.extractItem(i,pinv.getStackInSlot(i).getCount(),false));
        }
        super.fillWithLoot(player);
    }
}
