package net.tsw.temporal_light.screen;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Blocks.entity.TL_HS_AssemblerEntity;



public class TL_HS_Assembler_Menu extends AbstractContainerMenu {
    public final TL_HS_AssemblerEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public TL_HS_Assembler_Menu(int pContainerId, Inventory inv, FriendlyByteBuf data){
        this(pContainerId,inv,inv.player.level().getBlockEntity(data.readBlockPos()),new SimpleContainerData(11));
    }
    public TL_HS_Assembler_Menu(int pContainerId, Inventory inv, BlockPos pos){
        this(pContainerId,inv,inv.player.level().getBlockEntity(pos),new SimpleContainerData(11));
    }

    public TL_HS_Assembler_Menu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(TLMenuTypesRegistry.TL_HS_ASSEMBLER_MENU.get(),pContainerId);
        checkContainerSize(inv,11);
        blockEntity = ((TL_HS_AssemblerEntity) entity);
        this.level = inv.player.level();
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        addEntity_slots(8,20,80,31,120,53);
        addDataSlots(data);
    }
    private void addEntity_slots(int cgridx,int cgridy,int fuelx,int fuely,int outputx,int outputy){
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new SlotItemHandler(iItemHandler,0,fuelx,fuely));
            for(int i = 0;i<3;++i){
                for(int l = 0;l<3;l++){
                    this.addSlot(new SlotItemHandler(iItemHandler,l+1+(i*3),cgridx+l*18,cgridy+i*18));
                }
            }
            this.addSlot(new SlotItemHandler(iItemHandler,10,outputx,outputy));
        });
    }
    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack originalStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);

        if (slot != null && slot.hasItem()) {
            ItemStack stackToMove = slot.getItem();
            originalStack = stackToMove.copy();
            int inventoryStart = 11; // First player inventory slot (after assembler slots)
            int inventoryEnd = inventoryStart + 27; // 3 rows of 9 = 27 slots
            int hotbarStart = inventoryEnd; // Hotbar starts after inventory
            int hotbarEnd = hotbarStart + 9; // 9 hotbar slots

            // Moving from assembler slots (0-10) to player inventory
            if (pIndex < inventoryStart) {
                if (!moveItemStackTo(stackToMove, inventoryStart, hotbarEnd, true)) {
                    return ItemStack.EMPTY; // Failed to move, cancel
                }
                slot.onQuickCraft(stackToMove, originalStack);
            }
            // Moving from player inventory/hotbar to assembler slots
            else {
                // Try fuel slot (0) first
                if (blockEntity.itemHandler.isItemValid(FUEL_SLOT, stackToMove)) {
                    if (!moveItemStackTo(stackToMove, FUEL_SLOT, FUEL_SLOT + 1, false)) {
                        // Fuel slot full or invalid, try crafting grid (1-9)
                        if (!moveItemStackTo(stackToMove, ITEM_SLOT1, ITEM_SLOT9 + 1, false)) {
                            return ItemStack.EMPTY; // Failed to move
                        }
                    }
                } else {
                    // Not valid for fuel, try crafting grid directly
                    if (!moveItemStackTo(stackToMove, ITEM_SLOT1, ITEM_SLOT9 + 1, false)) {
                        return ItemStack.EMPTY; // Failed to move
                    }
                }
            }

            // Update slot if stack size changed
            if (stackToMove.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            // Notify slot of the move
            slot.onTake(pPlayer, stackToMove);
        }

        return originalStack; // Return the original stack for vanilla behavior
    }

    // Constants for slot indices (assuming your setup)
    private static final int FUEL_SLOT = 0;
    private static final int ITEM_SLOT1 = 1;
    private static final int ITEM_SLOT9 = 9;

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level,blockEntity.getBlockPos()),pPlayer, TLBlocksRegistry.HYPERSTEEL_ASSEMBLER.get());
    }
    private void addPlayerInventory(Inventory playerInventory){
        for(int i = 0;i<3;++i){
            for(int l = 0;l<9;l++){
                this.addSlot(new Slot(playerInventory,l+i*9+9,8+l*18,84+i*18));
            }
        }
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges(); // This sends ContainerData updates to the client
    }
    private void addPlayerHotbar(Inventory playerInventory){
        for(int i = 0;i<9;++i){
            this.addSlot(new Slot(playerInventory,i,8+i*18,142));
        }
    }

    public boolean isPowered() {
        //.out.println("Power:"+data.get(0));
        //System.out.println("Power2:"+blockEntity.get_power());
        return blockEntity.get_power()>0;
    }

    public int get_power() {
        //System.out.println("Power:"+data.get(0));
        //System.out.println("Power3:"+blockEntity.get_power());
        return blockEntity.get_power();
    }
}
