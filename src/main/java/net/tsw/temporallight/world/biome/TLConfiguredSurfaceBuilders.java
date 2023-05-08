package net.tsw.temporallight.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.tsw.temporallight.TemporalLight;

public class TLConfiguredSurfaceBuilders {
    public static ConfiguredSurfaceBuilder<?> MAGIWOODS_SURFACE = register("magiwoods",
            SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
                    Blocks.GRASS_BLOCK.getDefaultState(),
                    Blocks.BASALT.getDefaultState(),
                    Blocks.ANCIENT_DEBRIS.getDefaultState()
            )));
    public static ConfiguredSurfaceBuilder<?> EARTH44_SURFACE = register("earth44",
            SurfaceBuilder.ERODED_BADLANDS.func_242929_a(new SurfaceBuilderConfig(
                    Blocks.BLACKSTONE.getDefaultState(),
                    Blocks.COARSE_DIRT.getDefaultState(),
                    Blocks.LAVA.getDefaultState()
            )));

    private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name,
                                                                                           ConfiguredSurfaceBuilder<SC> csb) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(TemporalLight.MOD_ID, name), csb);
    }
}
