/*package net.tsw.temporal_light.compatability.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.recipes.TL_HS_AssemblerRecipes;

public class TL_HS_AssemblerCategory implements IRecipeCategory<TL_HS_AssemblerRecipes> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"hypersteel_assemble");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/gui/assembler_gui_jei.png");

    public static final RecipeType<TL_HS_AssemblerRecipes> TL_HS_ASSEMBLER_RECIPE_RECIPE_TYPE = new RecipeType<>(UID,TL_HS_AssemblerRecipes.class);

    private final IDrawable background;
    private final IDrawable icon;

    public TL_HS_AssemblerCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE,0,0,176,152);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(TLBlocksRegistry.HYPERSTEEL_ASSEMBLER.get()));
    }

    @Override
    public RecipeType<TL_HS_AssemblerRecipes> getRecipeType() {
        return TL_HS_ASSEMBLER_RECIPE_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.temporal_light.hypersteelassembler");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TL_HS_AssemblerRecipes recipe, IFocusGroup focuses) {
        int cgridx;int cgridy;int fuelx;int fuely;int outputx;int outputy;
        cgridx=8;cgridy = 20;fuelx=80;fuely=31;outputx = 120;outputy = 53;
        builder.addSlot(RecipeIngredientRole.INPUT, fuelx,fuely).addIngredients(recipe.getIngredients().get(0));
        for(int i = 0;i<3;++i){
            for(int l = 0;l<3;l++){
                builder.addSlot(RecipeIngredientRole.INPUT, cgridx+l*18,cgridy+i*18).addIngredients(recipe.getIngredients().get(l+1+(i*3)));
            }}
        builder.addSlot(RecipeIngredientRole.OUTPUT,outputx,outputy).addItemStack(recipe.getResultItem(null));
        ItemStack minStack = new ItemStack(Items.REDSTONE.asItem(),recipe.getMinRedstoneStrength());
        if(recipe.getMaxRedstoneStrength()!=null){
            ItemStack maxStack = new ItemStack(Items.REDSTONE.asItem(),recipe.getMaxRedstoneStrength());
            builder.addSlot(RecipeIngredientRole.INPUT,fuelx,fuely+4*16+18).addItemStack(maxStack);
        }
        builder.addSlot(RecipeIngredientRole.INPUT, fuelx, fuely+4*16).addItemStack(minStack);
    }
}
*/