package net.tsw.Temporal_Light.items.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TLNull_Magic_crystal extends Item {
    public TLNull_Magic_crystal(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, Player player) {
        return false;
    }
}
