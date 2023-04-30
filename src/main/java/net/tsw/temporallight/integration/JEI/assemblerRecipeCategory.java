package net.tsw.temporallight.integration.JEI;

import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.data.recipes.assemblerRecipes;

public class assemblerRecipeCategory implements IRecipeCategory<assemblerRecipes>{
    public final static ResourceLocation UID = new ResourceLocation(TemporalLight.MOD_ID,"assembler");
    public final static ResourceLocation TEXTURE = new ResourceLocation(TemporalLight.MOD_ID, "textures/gui/assembler_gui.png");
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable poweredicon;


    public assemblerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0,0,176,85);
        this.icon  = helper.createDrawableIngredient(new ItemStack(blockRegistry.HYPERSTEEL_ASSEMBLER.get()));
        this.poweredicon = helper.createDrawable(TEXTURE,176,0,32,32);

    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends assemblerRecipes> getRecipeClass() {
        return assemblerRecipes.class;
    }

    @Override
    public String getTitle() {
        return blockRegistry.HYPERSTEEL_ASSEMBLER.get().getTranslatedName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(assemblerRecipes recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM,recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, assemblerRecipes recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0,true,79,30);
        recipeLayout.getItemStacks().init(1,true,8,20);
        recipeLayout.getItemStacks().init(2,true,26,20);
        recipeLayout.getItemStacks().init(3,true,44,20);
        recipeLayout.getItemStacks().init(4,true,8,38);
        recipeLayout.getItemStacks().init(5,true,26,38);
        recipeLayout.getItemStacks().init(6,true,44,38);
        recipeLayout.getItemStacks().init(7,true,8,56);
        recipeLayout.getItemStacks().init(8,true,26,56);
        recipeLayout.getItemStacks().init(9,true,44,56);
        recipeLayout.getItemStacks().init(10,false,120,53);
        recipeLayout.getItemStacks().set(ingredients);
    }
    @Override
    public void draw(assemblerRecipes recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        float redstone = recipe.getMinRedstone();
        if(redstone >0) {
            this.poweredicon.draw(matrixStack, 112, 16);
            TranslationTextComponent redstonetext = new TranslationTextComponent("jei.temporallight.assembler.redstone", redstone);
            Minecraft minecraft = Minecraft.getInstance();
            FontRenderer fontRenderer = minecraft.fontRenderer;
            fontRenderer.drawText(matrixStack, redstonetext, 8, 77, 0xFF0000);
        }
    }
}
