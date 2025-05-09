package net.tsw.Temporal_Light.items.custom;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class TLShieldItem extends ShieldItem {
    Ingredient ing;
    public TLShieldItem(Properties pProperties) {
        super(pProperties);
        ing = Ingredient.of(ItemTags.PLANKS);
    }
    public TLShieldItem(Properties pProperties,Ingredient ingredient) {
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
