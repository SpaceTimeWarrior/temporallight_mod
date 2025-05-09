package net.tsw.Temporal_Light.items.custom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class TLHorseArmorItem extends HorseArmorItem {
    Ingredient ing;
    public TLHorseArmorItem(int pProtection, String pIdentifier, Properties pProperties) {
        super(pProtection, pIdentifier, pProperties);
        ing = Ingredient.of(Items.IRON_INGOT);
    }

    public TLHorseArmorItem(int pProtection, ResourceLocation pIdentifier, Properties pProperties) {
        super(pProtection, pIdentifier, pProperties);
        ing = Ingredient.of(Items.IRON_INGOT);
    }
    public TLHorseArmorItem(int pProtection, String pIdentifier, Properties pProperties,Ingredient ingredient) {
        super(pProtection, pIdentifier, pProperties);
        ing = ingredient;
    }

    public TLHorseArmorItem(int pProtection, ResourceLocation pIdentifier, Properties pProperties,Ingredient ingredient) {
        super(pProtection, pIdentifier, pProperties);
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
