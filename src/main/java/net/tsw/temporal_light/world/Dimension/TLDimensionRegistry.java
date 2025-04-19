package net.tsw.temporal_light.world.Dimension;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.world.Features.biome.TLBiomeRegistry;

import java.util.List;
import java.util.OptionalLong;

public class TLDimensionRegistry {
    public static final ResourceKey<LevelStem> EATHERIAN_SLEEP_DIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "eatherian_sleep_dim"));
    public static final ResourceKey<Level> EATHERIAN_SLEEP_DIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "eatherian_sleep_dim"));
    public static final ResourceKey<DimensionType> EATHERIAN_SLEEP_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "eatherian_sleep_dim_type"));
    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        context.register(EATHERIAN_SLEEP_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, true, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(TLBiomeRegistry.KITSUNE_HIGHLANDS)),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(Pair.of(
                                        Climate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(TLBiomeRegistry.KITSUNE_HIGHLANDS)),
                                Pair.of(
                                        Climate.parameters(0.1F, 0.2F, 0.0F, 0.2F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(TLBiomeRegistry.BUNNY_HOP_MOUNTAINS)),
                                Pair.of(
                                        Climate.parameters(0.3F, 0.6F, 0.1F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(TLBiomeRegistry.WATER_DRAGON_SEAS)),
                                Pair.of(
                                        Climate.parameters(0.4F, 0.3F, 0.2F, 0.1F, 0.0F, 0.0F, 0.0F), biomeRegistry.getOrThrow(TLBiomeRegistry.PHOENIX_JUNGLE))

                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.AMPLIFIED));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(TLDimensionRegistry.EATHERIAN_SLEEP_DIM_TYPE), noiseBasedChunkGenerator);

        context.register(EATHERIAN_SLEEP_DIM_KEY, stem);
    }
}
