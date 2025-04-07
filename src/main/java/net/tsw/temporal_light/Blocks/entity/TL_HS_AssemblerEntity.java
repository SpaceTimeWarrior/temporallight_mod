package net.tsw.temporal_light.Blocks.entity;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.tsw.temporal_light.Blocks.Custom.TL_HS_Assembler_Block;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Items.TLItemRegistry;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.recipes.TLRecipeTypeRegistry;
import net.tsw.temporal_light.recipes.TL_HS_AssemblerRecipes;
import net.tsw.temporal_light.screen.TL_HS_Assembler_Menu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraftforge.items.wrapper.SidedInvWrapper; // For reference, but we’ll customize
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Optional;

public class TL_HS_AssemblerEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(11);
    private static final int FUEL_SLOT = 0;
    private static final int ITEM_SLOT1 = 1;
    private static final int ITEM_SLOT2 = 2;
    private static final int ITEM_SLOT3 = 3;
    private static final int ITEM_SLOT4 = 4;
    private static final int ITEM_SLOT5 = 5;
    private static final int ITEM_SLOT6 = 6;
    private static final int ITEM_SLOT7 = 7;
    private static final int ITEM_SLOT8 = 8;
    private static final int ITEM_SLOT9 = 9;
    private static final int OUTPUT_SLOT = 10;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;
    private int PowerIn;
    private BlockPos pos;
    public TL_HS_AssemblerEntity( BlockPos pPos, BlockState pBlockState) {
        super(TLBlockEntityRegistry.TLASSEMBLER.get(), pPos, pBlockState);
        this.pos = pPos;
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex){
                    case 0 -> TL_HS_AssemblerEntity.this.PowerIn;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0 -> TL_HS_AssemblerEntity.this.PowerIn=pValue;
                    default -> TL_HS_AssemblerEntity.this.PowerIn=0;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0;i<itemHandler.getSlots();i++){
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }
    @Override
    public Component getDisplayName() {
        return Component.translatable("block.hypersteel_assembler.name");
    }
/*
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, net.minecraft.core.@Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return  lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }*/
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) {
                // GUI access: return full inventory
                return lazyItemHandler.cast();
            } else {
                // Hopper access: return a wrapped handler based on direction
                return LazyOptional.of(() -> new SidedItemHandler(this, side)).cast();
            }
        }
        return super.getCapability(cap, side);
    }

    public ItemStack getItem(int i) {
        return itemHandler.getStackInSlot(i);
    }

    private static class SidedItemHandler implements IItemHandlerModifiable {
        private final TL_HS_AssemblerEntity entity;
        private final Direction side;

        public SidedItemHandler(TL_HS_AssemblerEntity entity, Direction side) {
            this.entity = entity;
            this.side = side;
        }

        @Override
        public int getSlots() {
            return entity.itemHandler.getSlots(); // 11 slots total
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            return isSlotAccessible(slot) ? entity.itemHandler.getStackInSlot(slot) : ItemStack.EMPTY;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (!isSlotAccessible(slot) || !isInputSlot(slot)) {
                return stack; // Reject if slot isn’t accessible or isn’t an input
            }
            ItemStack result = entity.itemHandler.insertItem(slot, stack, simulate);
            if (!simulate && !result.equals(stack)) {
                entity.setChanged(); // Sync changes
            }
            return result;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (!isSlotAccessible(slot) || !isOutputSlot(slot)) {
                return ItemStack.EMPTY; // Only allow extraction from output
            }
            ItemStack result = entity.itemHandler.extractItem(slot, amount, simulate);
            if (!simulate && !result.isEmpty()) {
                entity.setChanged(); // Sync changes
            }
            return result;
        }

        @Override
        public int getSlotLimit(int slot) {
            return entity.itemHandler.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return isSlotAccessible(slot) && isInputSlot(slot) && entity.itemHandler.isItemValid(slot, stack);
        }

        @Override
        public void setStackInSlot(int slot, @NotNull ItemStack stack) {
            if (isSlotAccessible(slot)) {
                entity.itemHandler.setStackInSlot(slot, stack);
                entity.setChanged();
            }
        }

        private boolean isSlotAccessible(int slot) {
            if (side == Direction.UP) {
                return slot == FUEL_SLOT; // Slot 0 only from above
            } else if (side == Direction.DOWN) {
                return slot == OUTPUT_SLOT; // Slot 10 only from below
            } else {
                return slot >= ITEM_SLOT1 && slot <= ITEM_SLOT9; // Slots 1-9 from sides
            }
        }

        private boolean isInputSlot(int slot) {
            return slot == FUEL_SLOT || (slot >= ITEM_SLOT1 && slot <= ITEM_SLOT9);
        }

        private boolean isOutputSlot(int slot) {
            return slot == OUTPUT_SLOT;
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()->itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    public InteractionResult use(Player player) {
        if (!player.level().isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, playerInv, p) -> new TL_HS_Assembler_Menu(containerId, playerInv, this.pos),
                    Component.literal("HS Assembler")
            ), buf -> buf.writeBlockPos(this.pos)); // This sends the BlockPos
            craft_item();
        }
        return InteractionResult.SUCCESS;
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        if (pPlayerInventory.player.level().isClientSide) {
            System.out.println("Client: BlockEntity data access attempted.");
        } else {
            System.out.println("Server: BlockEntity data access attempted.");
        }
        return new TL_HS_Assembler_Menu(pContainerId,pPlayerInventory, this.pos);
    }
    public int get_power(){
        return PowerIn;
    }
    @Override
    public void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        CompoundTag inventoryTag = itemHandler.serializeNBT(pRegistries);
        pTag.put("inventory", inventoryTag);
        pTag.putInt("tl_hs_assembler_power", PowerIn);
        super.saveAdditional(pTag, pRegistries);
        System.out.println("Saving inventory: " + inventoryTag.toString());
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        if (pTag.contains("inventory")) {
            CompoundTag inventoryTag = pTag.getCompound("inventory");
            itemHandler.deserializeNBT(pRegistries, inventoryTag); // Updated for 1.20.6
            System.out.println("Loading inventory: " + inventoryTag.toString());
        } else {
            System.out.println("No inventory tag found in NBT: " + pTag.toString());
        }
        PowerIn = pTag.getInt("tl_hs_assembler_power");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    private static <T> Tag encode(Codec<T> pCodec, T pValue, HolderLookup.Provider pLevelRegistry) {
        return pCodec.encodeStart(pLevelRegistry.createSerializationContext(NbtOps.INSTANCE), pValue).getOrThrow();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider holders) {
        super.handleUpdateTag(tag, holders);

        if (this.data != null) {
            CompoundTag inventoryTag = itemHandler.serializeNBT(holders);
            tag.put("inventory", inventoryTag);
            tag.putInt("tl_hs_assembler_power", PowerIn);
            super.handleUpdateTag(tag, holders);
        }
        System.out.println("handleUpdateTag:"+tag.toString());
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag pTag = new CompoundTag();
        CompoundTag inventoryTag = itemHandler.serializeNBT(pRegistries);
        pTag.put("inventory", inventoryTag);
        pTag.putInt("tl_hs_assembler_power", PowerIn);
        super.getUpdateTag(pRegistries);
        return pTag;
    }


    /*public boolean craft_item(){
        if(hasRecipe()){
            ItemStack result = new ItemStack(TLItemRegistry.LIGHTSTEELINGOT.get(),1);
            this.itemHandler.extractItem(FUEL_SLOT,1,false);
            this.itemHandler.extractItem(ITEM_SLOT1,1,false);
            this.itemHandler.extractItem(ITEM_SLOT2,1,false);
            this.itemHandler.extractItem(ITEM_SLOT3,1,false);
            this.itemHandler.extractItem(ITEM_SLOT4,1,false);
            this.itemHandler.extractItem(ITEM_SLOT5,1,false);
            this.itemHandler.extractItem(ITEM_SLOT6,1,false);
            this.itemHandler.extractItem(ITEM_SLOT7,1,false);
            this.itemHandler.extractItem(ITEM_SLOT8,1,false);
            this.itemHandler.extractItem(ITEM_SLOT9,1,false);
            this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+result.getCount()));
            return true;
        }else{
            return false;
        }
    }*/
    public void LightningWeldingCraft(){
        if(craft_item()){
            if (true/*&&(level != null && !level.isClientSide)*/) {
                // Summon lightning
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                if (lightning != null) {
                    lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                    level.addFreshEntity(lightning);
                }
                setChanged(); // Sync changes
            }
        }
    }
    /*public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        int oldPower = PowerIn; // Track old value
        PowerIn = pLevel.getDirectSignalTo(pPos); // Update power
        this.data.set(0, PowerIn);
        if (oldPower != PowerIn || hasRecipe()) { // Check for change
            setChanged(pLevel, pPos, pState); // Trigger sync
        }
    }*/

    /*private boolean hasRecipe() {
        boolean hasFuelItem = this.itemHandler.getStackInSlot(FUEL_SLOT).getItem() == Items.NETHERITE_INGOT;
        boolean hasSlot1Item = this.itemHandler.getStackInSlot(ITEM_SLOT1).getItem() == Items.AIR;
        boolean hasSlot2Item = this.itemHandler.getStackInSlot(ITEM_SLOT2).getItem() == TLItemRegistry.HYPERSTEELINGOT.get();
        boolean hasSlot3Item = this.itemHandler.getStackInSlot(ITEM_SLOT3).getItem() == Items.AIR;
        boolean hasSlot4Item = this.itemHandler.getStackInSlot(ITEM_SLOT4).getItem() == TLItemRegistry.HYPERSTEELINGOT.get();
        boolean hasSlot5Item = this.itemHandler.getStackInSlot(ITEM_SLOT5).getItem() == TLBlocksRegistry.HYPERSTEEL_BLOCK.get().asItem();
        boolean hasSlot6Item = this.itemHandler.getStackInSlot(ITEM_SLOT6).getItem() == TLItemRegistry.HYPERSTEELINGOT.get();
        boolean hasSlot7Item = this.itemHandler.getStackInSlot(ITEM_SLOT7).getItem() == Items.AIR;
        boolean hasSlot8Item = this.itemHandler.getStackInSlot(ITEM_SLOT8).getItem() == TLItemRegistry.HYPERSTEELINGOT.get();
        boolean hasSlot9Item = this.itemHandler.getStackInSlot(ITEM_SLOT9).getItem() == Items.AIR;
        boolean hasSignal = PowerIn>=1;
        boolean hasCraftingRecipe = hasSignal&&hasFuelItem&&hasSlot1Item&&hasSlot2Item&&hasSlot3Item&&hasSlot4Item&&hasSlot5Item&&hasSlot6Item&&hasSlot7Item&&hasSlot8Item&&hasSlot9Item;
        ItemStack result = new ItemStack(TLItemRegistry.LIGHTSTEELINGOT.get());
        return hasCraftingRecipe&&canInsertAmountIntoOutputSlot(result.getCount())&&canInsertItemIntoOutputSlot(result.getItem());
    }*/
    private SimpleContainer createRecipeContainer() {
        SimpleContainer container = new SimpleContainer(10); // Fuel + 3x3 grid
        for (int i = 0; i < 10; i++) { // Slots 0-9
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        return container;
    }

    private Optional<TL_HS_AssemblerRecipes> getCurrentRecipe() {
        if (level == null) return Optional.empty();
        SimpleContainer container = createRecipeContainer();
        return level.getRecipeManager()
                .getRecipeFor(TLRecipeTypeRegistry.TL_HS_ASSEMBLER.get(), container, level)
                .map(RecipeHolder::value)
                .filter(recipe -> PowerIn >= recipe.getMinRedstoneStrength() &&
                        (recipe.getMaxRedstoneStrength() == null || PowerIn <= recipe.getMaxRedstoneStrength()));
    }

    public boolean craft_item() {
        Optional<TL_HS_AssemblerRecipes> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            ItemStack result = recipe.get().assemble(createRecipeContainer(), level.registryAccess());
            itemHandler.extractItem(FUEL_SLOT, 1, false);
            for (int i = ITEM_SLOT1; i <= ITEM_SLOT9; i++) {
                itemHandler.extractItem(i, 1, false);
            }
            ItemStack currentOutput = itemHandler.getStackInSlot(OUTPUT_SLOT);
            itemHandler.setStackInSlot(OUTPUT_SLOT,
                    new ItemStack(result.getItem(), currentOutput.getCount() + result.getCount()));
            setChanged();
            return true;
        }
        return false;
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        int oldPower = PowerIn;
        PowerIn = pLevel.getDirectSignalTo(pPos);
        if (oldPower != PowerIn || getCurrentRecipe().isPresent()) {
            setChanged(pLevel, pPos, pState);
        }
    }

    private boolean hasRecipe() {
        return getCurrentRecipe().isPresent();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()||this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount()+count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }
}
