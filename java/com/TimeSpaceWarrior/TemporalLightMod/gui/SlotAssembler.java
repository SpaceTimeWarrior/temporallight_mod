package com.TimeSpaceWarrior.TemporalLightMod.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class SlotAssembler extends Slot {
    private final TileEntity tile;

    public SlotAssembler(IInventory inventory, int index, int x, int y, TileEntity tile) {
        super(inventory, index, x, y);
        this.tile = tile;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        if (tile != null) {
            tile.markDirty();
        }
    }
}
