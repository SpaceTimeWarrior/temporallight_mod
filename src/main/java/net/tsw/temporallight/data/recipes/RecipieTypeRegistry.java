package net.tsw.temporallight.data.recipes;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;

public class RecipieTypeRegistry {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TemporalLight.MOD_ID);

    public static final RegistryObject<assemblerRecipes.Serializer> ASSEMBLER_SERIALIZER
            = RECIPE_SERIALIZER.register("assembler", assemblerRecipes.Serializer::new);

    public static IRecipeType<assemblerRecipes> ASSEMBLER_RECIPE
            = new assemblerRecipes.assemblerRecipeType();


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, assemblerRecipes.TYPE_ID, ASSEMBLER_RECIPE);
    }
}
