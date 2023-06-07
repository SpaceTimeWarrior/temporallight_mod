package net.tsw.temporallight.ui.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.tsw.temporallight.block.blockRegistry;

public class assemblerContainer extends Container {
    private final TileEntity tileentity;
    private final PlayerEntity playerentity;
    private final IItemHandler playerinventory;
    public assemblerContainer(int windowid, World world, BlockPos pos, PlayerInventory playerinventory,PlayerEntity player) {
        super(ContainerRegistry.ASSEMBLER_CONTAINER.get(), windowid);
        this.tileentity = world.getTileEntity(pos);
        playerentity = player;
        this.playerinventory = new InvWrapper(playerinventory);

        layoutPlayerInventorySlots(8, 86);
        if(tileentity != null) {
            tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 80, 31));
                addSlotBox(h,1,8,20,3,18,3,18);
                addSlot(new SlotItemHandler(h, 10, 120, 53));
            });
        }
    }
    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return  playerIn.inventory.getStackInSlot(index);
    }
    public boolean isPowered(){
        return (tileentity.getWorld().getRedstonePower(tileentity.getPos(),null)>0);
    }
    public int getPower(){
        return (tileentity.getWorld().getRedstonePower(tileentity.getPos(),null));
    }
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileentity.getWorld(),tileentity.getPos()),playerIn, blockRegistry.HYPERSTEEL_ASSEMBLER.get());
    }
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }

        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        addSlotBox(playerinventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(playerinventory, 0, leftCol, topRow, 9, 18);
    }

}
