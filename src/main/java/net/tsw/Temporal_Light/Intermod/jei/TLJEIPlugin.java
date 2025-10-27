package net.tsw.Temporal_Light.Intermod.jei;


import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.crafting.RecipeManager;
import net.tsw.Temporal_Light.Recipe.TLRecipeTypeRegistry;
import net.tsw.Temporal_Light.Recipe.TL_HS_AssemblerRecipes;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.screen.TL_HS_Assembler_screen;


import java.util.List;

@JeiPlugin
public class TLJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TL_HS_AssemblerCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<TL_HS_AssemblerRecipes>asm_rec = recipeManager.getAllRecipesFor(TL_HS_AssemblerRecipes.Type.INSTANCE);
        registration.addRecipes(TL_HS_AssemblerCategory.TL_HS_ASSEMBLER_RECIPE_RECIPE_TYPE,asm_rec);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(TL_HS_Assembler_screen.class,112,16,32,32,TL_HS_AssemblerCategory.TL_HS_ASSEMBLER_RECIPE_RECIPE_TYPE);
    }
}
