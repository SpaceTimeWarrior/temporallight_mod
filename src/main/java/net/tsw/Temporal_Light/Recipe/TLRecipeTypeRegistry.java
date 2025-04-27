package net.tsw.Temporal_Light.Recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.Temporal_Light.Temporal_Light;

public class TLRecipeTypeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>>SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS,Temporal_Light.MOD_ID);
    public static final RegistryObject<RecipeSerializer<TL_HS_AssemblerRecipes>> HYPERSTEEL_ASSEMBLE_SERIALIZER = SERIALIZERS.register("hypersteel_assemble",()->TL_HS_AssemblerRecipes.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
