package net.tsw.temporallight.integration.JEI;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.data.recipes.RecipieTypeRegistry;
import net.tsw.temporallight.data.recipes.assemblerRecipes;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class TemporalLightJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TemporalLight.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new assemblerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager Rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(Rm.getRecipesForType(RecipieTypeRegistry.ASSEMBLER_RECIPE).stream().filter(r->r instanceof assemblerRecipes).collect(Collectors.toList()),assemblerRecipeCategory.UID );
    }
}
