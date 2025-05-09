package net.tsw.Temporal_Light.items.custom;

import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class TLBowItem extends BowItem {
    Ingredient ing;
    public TLBowItem(Properties pProperties) {
        super(pProperties);
        ing = Ingredient.of(Items.STICK);
    }
    public TLBowItem(Properties pProperties,Ingredient ingredient) {
        super(pProperties);
        ing = ingredient;
    }
    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return this.ing.test(pRepairCandidate)||super.isValidRepairItem(pStack,pRepairCandidate);
    }
}
