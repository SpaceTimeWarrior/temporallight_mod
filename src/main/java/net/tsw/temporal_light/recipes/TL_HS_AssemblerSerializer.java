package net.tsw.temporal_light.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.tsw.temporal_light.Temporal_Light;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.stream.Collectors;

public class TL_HS_AssemblerSerializer implements RecipeSerializer<TL_HS_AssemblerRecipes> {
    private static final Logger LOGGER = Temporal_Light.LOGGER;

    // Ingredients codec
    public static final Codec<NonNullList<Ingredient>> INGREDIENTS_CODEC = Codec.list(Ingredient.CODEC).comapFlatMap(
            list -> {
                if (list.size() != 10) {
                    return DataResult.error(() -> "Recipe must have exactly 10 ingredients, got " + list.size());
                }
                NonNullList<Ingredient> nonNullList = NonNullList.withSize(10, Ingredient.EMPTY);
                for (int i = 0; i < 10; i++) {
                    nonNullList.set(i, list.get(i));
                }
                return DataResult.success(nonNullList);
            },
            nonNullList -> nonNullList
    );

    public static final MapCodec<TL_HS_AssemblerRecipes> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        LOGGER.info("Parsing recipes with Hypersteel Assembler codec");
        LOGGER.info("Registered items: " + BuiltInRegistries.ITEM.keySet().stream()
                .filter(key -> key.getNamespace().equals(Temporal_Light.MOD_ID))
                .map(ResourceLocation::toString)
                .collect(Collectors.joining(", ")));
        return instance.group(
                INGREDIENTS_CODEC.fieldOf("ingredients").forGetter(TL_HS_AssemblerRecipes::getIngredients),
                ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.getResultItem(null)),
                Codec.INT.fieldOf("min_redstone_strength").forGetter(TL_HS_AssemblerRecipes::getMinRedstoneStrength),
                Codec.INT.optionalFieldOf("max_redstone_strength").forGetter(recipe -> Optional.ofNullable(recipe.getMaxRedstoneStrength()))
        ).apply(instance, TL_HS_AssemblerRecipes::new);
    });

    @Override
    public MapCodec<TL_HS_AssemblerRecipes> codec() {
        LOGGER.info("Returning Hypersteel Assembler codec");
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, TL_HS_AssemblerRecipes> streamCodec() {
        return StreamCodec.of(
                (buffer, recipe) -> {
                    for (Ingredient ingredient : recipe.getIngredients()) {
                        Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
                    }
                    ItemStack.STREAM_CODEC.encode(buffer, recipe.getResultItem(null));
                    buffer.writeInt(recipe.getMinRedstoneStrength());
                    buffer.writeBoolean(recipe.getMaxRedstoneStrength() != null);
                    if (recipe.getMaxRedstoneStrength() != null) {
                        buffer.writeInt(recipe.getMaxRedstoneStrength());
                    }
                },
                (buffer) -> {
                    NonNullList<Ingredient> ingredients = NonNullList.withSize(10, Ingredient.EMPTY);
                    for (int i = 0; i < 10; i++) {
                        ingredients.set(i, Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
                    }
                    ItemStack output = ItemStack.STREAM_CODEC.decode(buffer);
                    int minRedstoneStrength = buffer.readInt();
                    Integer maxRedstoneStrength = buffer.readBoolean() ? buffer.readInt() : null;
                    return new TL_HS_AssemblerRecipes(ingredients, output, minRedstoneStrength, Optional.ofNullable(maxRedstoneStrength));
                }
        );
    }
}
