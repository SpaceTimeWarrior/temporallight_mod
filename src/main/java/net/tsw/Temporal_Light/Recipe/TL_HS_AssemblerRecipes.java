package net.tsw.Temporal_Light.Recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tsw.Temporal_Light.Temporal_Light;
import org.jetbrains.annotations.Nullable;

public class TL_HS_AssemblerRecipes implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int maxRedstone;
    private final int minRedstone;


    public TL_HS_AssemblerRecipes(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.maxRedstone = 999;
        this.minRedstone = 1;
    }
    public TL_HS_AssemblerRecipes(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id,int minRed) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.maxRedstone = 999;
        this.minRedstone = minRed;
    }
    public TL_HS_AssemblerRecipes(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id,int minRed,int maxRed) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.maxRedstone = maxRed;
        this.minRedstone = minRed;
    }
    public int getmaxRedstone(){
        return this.maxRedstone;
    }
    public int getminRedstone(){
        return this.minRedstone;
    }
    @Override
    public  NonNullList<Ingredient> getIngredients(){
        return inputItems;
    }
        @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()){
            return false;
        }
        boolean ret = true;
        for(int i=0;i<10;i++){
            ret = ret && inputItems.get(i).test(pContainer.getItem(i));
        }
        return ret;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public int getMinRedstoneStrength() {
        return this.minRedstone;
    }

    public int getMaxRedstoneStrength() {
        return this.maxRedstone;
    }

    public static class Type implements RecipeType<TL_HS_AssemblerRecipes>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "hypersteel_assemble";
    }
    public static class Serializer implements RecipeSerializer<TL_HS_AssemblerRecipes>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"hypersteel_assemble");

        @Override
        public TL_HS_AssemblerRecipes fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe,"output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe,"ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(10,Ingredient.EMPTY);
            for(int i=0;i<inputs.size();i++){
                inputs.set(i,Ingredient.fromJson(ingredients.get(i)));
            }
            int red_type = GsonHelper.getAsInt(pSerializedRecipe, "redtype");
            int minRed =1;
            int maxRed = 999;
            switch(red_type){
                default:
                case 0:
                    return new TL_HS_AssemblerRecipes(inputs,output,pRecipeId);
                case 2:
                    maxRed = GsonHelper.getAsInt(pSerializedRecipe,"max_redstone_strength");
                case 1:
                    minRed = GsonHelper.getAsInt(pSerializedRecipe, "min_redstone_strength");
                    return new TL_HS_AssemblerRecipes(inputs,output,pRecipeId,minRed,maxRed);
                case 3:
                    maxRed = GsonHelper.getAsInt(pSerializedRecipe,"max_redstone_strength");
                    return new TL_HS_AssemblerRecipes(inputs,output,pRecipeId,minRed,maxRed);
            }
        }

        @Override
        public @Nullable TL_HS_AssemblerRecipes fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(),Ingredient.EMPTY);
            for(int i=0;i<inputs.size();i++){
                inputs.set(i,Ingredient.fromNetwork(pBuffer));
            }
            ItemStack output = pBuffer.readItem();
            int minRed = pBuffer.readInt();
            int maxRed = pBuffer.readInt();
            return new TL_HS_AssemblerRecipes(inputs,output,pRecipeId,minRed,maxRed);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, TL_HS_AssemblerRecipes pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());
            for(Ingredient ingredient: pRecipe.getIngredients()){
                ingredient.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null),false);
            pBuffer.writeInt(pRecipe.minRedstone);
            pBuffer.writeInt(pRecipe.maxRedstone);
        }
    }
}