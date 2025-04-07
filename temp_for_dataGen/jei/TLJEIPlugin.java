package net.tsw.temporal_light.compatability.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.recipes.TLRecipeTypeRegistry;
import net.tsw.temporal_light.recipes.TL_HS_AssemblerRecipes;
import net.tsw.temporal_light.screen.TL_HS_Assembler_screen;

import java.util.List;

@JeiPlugin
public class TLJEIPlugin implements  IModPlugin{
    @Override
    public ResourceLocation getPluginUid() {
        return  ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new TL_HS_AssemblerCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<TL_HS_AssemblerRecipes> tl_hs_assemblerRecipes = recipeManager
                .getAllRecipesFor(TLRecipeTypeRegistry.TL_HS_ASSEMBLER.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(TL_HS_AssemblerCategory.TL_HS_ASSEMBLER_RECIPE_RECIPE_TYPE,tl_hs_assemblerRecipes);

    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(TL_HS_Assembler_screen.class,112,16,32,32,TL_HS_AssemblerCategory.TL_HS_ASSEMBLER_RECIPE_RECIPE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(TLBlocksRegistry.HYPERSTEEL_ASSEMBLER.get().asItem()),TL_HS_AssemblerCategory.TL_HS_ASSEMBLER_RECIPE_RECIPE_TYPE);
    }
}
