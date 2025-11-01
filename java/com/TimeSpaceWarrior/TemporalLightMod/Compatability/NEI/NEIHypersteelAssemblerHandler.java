package com.TimeSpaceWarrior.TemporalLightMod.Compatability.NEI;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.TimeSpaceWarrior.TemporalLightMod.Assembler_Recipe;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NEIHypersteelAssemblerHandler extends TemplateRecipeHandler {
    @Override
    public String getGuiTexture() {
        return TemporalLightMod.MODID+":textures/gui/assembler_gui_nei.png";
    }

    @Override
    public String getRecipeName() {
        return "Hypersteel Assembler";
    }
    @Override
    public void loadCraftingRecipes(ItemStack result) {
        Assembler_Recipe assembler = TemporalLightMod.assem_recipe;

        for (int i = 0; i < assembler.getRecipeList().size(); i++) {
            ItemStack[] recipe = assembler.getRecipeList().get(i);
            int min = assembler.getMinPower().get(i);
            int max = assembler.getMaxPower().get(i);

            if (recipe[10] != null && recipe[10].isItemEqual(result)) {
                arecipes.add(new CachedAssemblerRecipe(recipe, min, max));
            }
        }
    }
    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        Assembler_Recipe assembler = TemporalLightMod.assem_recipe;

        for (int i = 0; i < assembler.getRecipeList().size(); i++) {
            ItemStack[] recipe = assembler.getRecipeList().get(i);
            int min = assembler.getMinPower().get(i);
            int max = assembler.getMaxPower().get(i);

            for (int j = 0; j < 10; j++) {
                if (recipe[j] != null && recipe[j].isItemEqual(ingredient)) {
                    arecipes.add(new CachedAssemblerRecipe(recipe, min, max));
                    break;
                }
            }
        }
    }


    public class CachedAssemblerRecipe extends TemplateRecipeHandler.CachedRecipe {
        public List<PositionedStack> inputs = new ArrayList<PositionedStack>();
        public PositionedStack output;
        public PositionedStack minSignal;
        public PositionedStack maxSignal;

        public CachedAssemblerRecipe(ItemStack[] recipe, int minPower, int maxPower) {
            // 3x3 grid
            for (int i = 0; i < 9; i++) {
                if (recipe[i] != null) {
                    inputs.add(new PositionedStack(recipe[i], 4 + (i % 3) * 18, 9 + (i / 3) * 18));
                }
            }

            // Fuel slot
            if (recipe[9] != null) {
                inputs.add(new PositionedStack(recipe[9], 90-(16), 6+14));
            }

            // Output
            output = new PositionedStack(recipe[10], 130-14, 24+18);

            // Redstone signal bounds
            if (minPower > 0) {
                minSignal = new PositionedStack(new ItemStack(Items.redstone, minPower), 142, 6+14);
            }

            if (maxPower < 15) {
                maxSignal = new PositionedStack(new ItemStack(Items.redstone, maxPower), 142, 6+14+18);
            }

        }

        @Override
        public PositionedStack getResult() {
            return output;
        }

        @Override
        public List<PositionedStack> getIngredients() {
            List<PositionedStack> all = new ArrayList<PositionedStack>(inputs);
            if (minSignal != null) all.add(minSignal);
            if (maxSignal != null) all.add(maxSignal);
            return all;
        }
    }


}
