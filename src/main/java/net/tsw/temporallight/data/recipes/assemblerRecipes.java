package net.tsw.temporallight.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.item.ItemRegistry;

import javax.annotation.Nullable;

public class assemblerRecipes implements IassemblerRecipe{
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    private final int minRedstone;

    public assemblerRecipes(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, int minRedstone) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.minRedstone = minRedstone;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        boolean hasFuel = recipeItems.get(0).test(inv.getStackInSlot(0));
        ItemStack air = new ItemStack(Blocks.BARRIER.asItem());
        boolean hasMats0,hasMats1,hasMats2,hasMats3,hasMats4,hasMats5,hasMats6,hasMats7,hasMats8;
        if(recipeItems.get(1).test(air)){
            hasMats0 = inv.getStackInSlot(1).getCount()==0;
        }else {
            hasMats0 = recipeItems.get(1).test(inv.getStackInSlot(1));
        }
        if(recipeItems.get(2).test(air)){
            hasMats1 = inv.getStackInSlot(2).getCount()==0;
        }else {
            hasMats1 = recipeItems.get(2).test(inv.getStackInSlot(2));
        }
        if(recipeItems.get(3).test(air)){
            hasMats2 = inv.getStackInSlot(3).getCount()==0;
        }else {
            hasMats2 = recipeItems.get(3).test(inv.getStackInSlot(3));
        }
        if(recipeItems.get(4).test(air)){
            hasMats3 = inv.getStackInSlot(4).getCount()==0;
        }else {
            hasMats3 = recipeItems.get(4).test(inv.getStackInSlot(4));
        }
        if(recipeItems.get(5).test(air)){
            hasMats4 = inv.getStackInSlot(5).getCount()==0;
        }else {
            hasMats4 = recipeItems.get(5).test(inv.getStackInSlot(5));
        }
        if(recipeItems.get(6).test(air)){
            hasMats5 = inv.getStackInSlot(6).getCount()==0;
        }else {
            hasMats5 = recipeItems.get(6).test(inv.getStackInSlot(6));
        }
        if(recipeItems.get(7).test(air)){
            hasMats6 = inv.getStackInSlot(7).getCount()==0;
        }else {
            hasMats6 = recipeItems.get(7).test(inv.getStackInSlot(7));
        }
        if(recipeItems.get(8).test(air)){
            hasMats7 = inv.getStackInSlot(8).getCount()==0;
        }else {
            hasMats7 = recipeItems.get(8).test(inv.getStackInSlot(8));
        }
        if(recipeItems.get(9).test(air)){
            hasMats8 = inv.getStackInSlot(9).getCount()==0;
        }else {
            hasMats8 = recipeItems.get(9).test(inv.getStackInSlot(9));
        }
        /*
        hasMats1 =recipeItems.get(2).test(inv.getStackInSlot(2));
        hasMats2 =recipeItems.get(3).test(inv.getStackInSlot(3));
        hasMats3 =recipeItems.get(4).test(inv.getStackInSlot(4));
        hasMats4 =recipeItems.get(5).test(inv.getStackInSlot(5));
        hasMats5 =recipeItems.get(6).test(inv.getStackInSlot(6));
        hasMats6 =recipeItems.get(7).test(inv.getStackInSlot(7));
        hasMats7 = recipeItems.get(8).test(inv.getStackInSlot(8));
        hasMats8 = recipeItems.get(9).test(inv.getStackInSlot(9));*/
        boolean hasMats = hasMats0 && hasMats1 && hasMats2 && hasMats3 && hasMats4 && hasMats5 && hasMats6 && hasMats7 && hasMats8;
        return hasFuel&&hasMats;
    }
    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }
    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }
    public int getMinRedstone(){
        return minRedstone;
    }
    public ItemStack getIcon() {
        return new ItemStack(blockRegistry.HYPERSTEEL_ASSEMBLER.get());
    }
    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipieTypeRegistry.ASSEMBLER_SERIALIZER.get();
    }
    public static class assemblerRecipeType implements IRecipeType<assemblerRecipes> {
        @Override
        public String toString(){
            return assemblerRecipes.TYPE_ID.toString();
        }
    }
    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<assemblerRecipes> {

        @Override
        public assemblerRecipes read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            int redstone = Integer.parseInt(JSONUtils.getString(json, "redstone"));

            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(10, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new assemblerRecipes(recipeId, output,
                    inputs, redstone);
        }

        @Nullable
        @Override
        public assemblerRecipes read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.read(buffer));
            }

            ItemStack output = buffer.readItemStack();
            return new assemblerRecipes(recipeId, output,
                    inputs, buffer.readInt());
        }

        @Override
        public void write(PacketBuffer buffer, assemblerRecipes recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            buffer.writeInt(recipe.minRedstone);
        }
    }
}
