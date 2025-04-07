package net.tsw.temporal_light.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Temporal_Light;

public class TLRecipeTypeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "temporal_light");
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Temporal_Light.MOD_ID);
    public static final RegistryObject<RecipeSerializer<TL_HS_AssemblerRecipes>> HYPERSTEEL_ASSEMBLE_SERIALIZER =
            RECIPE_SERIALIZERS.register("hypersteel_assemble", TL_HS_AssemblerSerializer::new);
    public static final RegistryObject<RecipeType<TL_HS_AssemblerRecipes>> TL_HS_ASSEMBLER =
            RECIPE_TYPES.register("hypersteel_assemble", () -> new RecipeType<>() {
                @Override
                public String toString() {
                    return Temporal_Light.MOD_ID + ":hypersteel_assemble";
                }
            });

    public static void register(IEventBus eventBus) {
        System.out.println("registering recipes");
        RECIPE_SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
