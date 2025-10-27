package net.tsw.Temporal_Light.painting;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.Temporal_Light.Temporal_Light;

public class TLPaintingRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Temporal_Light.MOD_ID);

    public static final RegistryObject<PaintingVariant> EATHER_IMAGE = PAINTING_VARIANTS.register("eather_image",
            () -> new PaintingVariant(16, 16));//rendered size not image size it can be any multiple of 16
    public static final RegistryObject<PaintingVariant> JEM_ARMOR_BW = PAINTING_VARIANTS.register("jem_armor_bw",
            () -> new PaintingVariant(16,32));
    public static final RegistryObject<PaintingVariant> JEM_ARMOR_COLOR = PAINTING_VARIANTS.register("jem_armor_color",
            () -> new PaintingVariant(16,32));
    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
