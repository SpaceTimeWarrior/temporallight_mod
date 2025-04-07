package net.tsw.temporal_light.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class TL_HS_AssemblerRecipes implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final int minRedstoneStrength;
    private final Integer maxRedstoneStrength;

    // Updated constructor to match Codec fields directly
    public TL_HS_AssemblerRecipes(NonNullList<Ingredient> ingredients, ItemStack output,
                                  int minRedstoneStrength, Optional<Integer> maxRedstoneStrength) {
        this.inputItems = ingredients;
        this.output = output;
        this.minRedstoneStrength = minRedstoneStrength;
        this.maxRedstoneStrength = maxRedstoneStrength.orElse(null);
    }
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        if (pContainer.getContainerSize() < 10) {
            return false; // Need at least 10 slots for fuel + grid
        }
        for (int i = 0; i < 10; i++) {
            if (!inputItems.get(i).test(pContainer.getItem(i))) {
                return false; // All 10 slots must match
            }
        }
        return true; // Power check handled in block entity
    }

    @Override
    public ItemStack assemble(SimpleContainer pCraftingContainer, HolderLookup.Provider pRegistries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 10; // 1 fuel + 9 grid slots
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output.copy();
    }



    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TLRecipeTypeRegistry.HYPERSTEEL_ASSEMBLE_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return TLRecipeTypeRegistry.TL_HS_ASSEMBLER.get();
    }

    public int getMinRedstoneStrength() {
        return minRedstoneStrength;
    }

    public Integer getMaxRedstoneStrength() {
        return maxRedstoneStrength;
    }
    //    private final NonNullList<Ingredient> inputItems;
//    private final ItemStack output;
//    private final int minPower;
//
//
//    public TL_HS_AssemblerRecipes(NonNullList<Ingredient> inputItems, ItemStack output, int power) {
//        this.inputItems = inputItems;
//        this.output = output;
//        this.minPower = power;
//    }
//
//    @Override
//    public boolean matches(SimpleContainer pContainer, Level pLevel) {
//        if(pLevel.isClientSide()){
//            return false;
//        }
//        boolean correctfuel = inputItems.get(0).test(pContainer.getItem(0));
//        boolean correctinput1 = inputItems.get(1).test(pContainer.getItem(1));
//        boolean correctinput2 = inputItems.get(2).test(pContainer.getItem(2));
//        boolean correctinput3 = inputItems.get(3).test(pContainer.getItem(3));
//        boolean correctinput4 = inputItems.get(4).test(pContainer.getItem(4));
//        boolean correctinput5 = inputItems.get(5).test(pContainer.getItem(5));
//        boolean correctinput6 = inputItems.get(6).test(pContainer.getItem(6));
//        boolean correctinput7 = inputItems.get(7).test(pContainer.getItem(7));
//        boolean correctinput8 = inputItems.get(8).test(pContainer.getItem(8));
//        boolean correctinput9 = inputItems.get(9).test(pContainer.getItem(9));
//
//        return correctfuel&&correctinput1&&correctinput2&&correctinput3&&correctinput4&&correctinput5&&correctinput6&&correctinput7&&correctinput8&&correctinput9;
//    }
//
//    @Override
//    public ItemStack assemble(SimpleContainer pCraftingContainer, HolderLookup.Provider pRegistries) {
//        return output.copy();
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int pWidth, int pHeight) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
//        return output.copy();
//    }
//
//    @Override
//    public NonNullList<Ingredient> getIngredients() {
//        return inputItems;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return null;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return TLRecipeTypeRegistry.TL_HS_ASSEMBLER.get();
//    }
//    public int getMinPower() {
//        return minPower;
//    }
//

}
