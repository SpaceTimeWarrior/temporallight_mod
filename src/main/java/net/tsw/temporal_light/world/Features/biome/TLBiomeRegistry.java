package net.tsw.temporal_light.world.Features.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.entity.TLEntityRegistry;
import net.tsw.temporal_light.world.Features.TLPlacedFeaturesRegistry;

public class TLBiomeRegistry {
    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, Temporal_Light.MOD_ID);
    //public static final ResourceKey<Biome> KITSUNE_HIGHLANDS = ResourceKey.create(ForgeRegistries.Keys.BIOMES, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"kitsune_highlands"));
    //public static final ResourceKey<Biome> PHOENIX_JUNGLE = ResourceKey.create(ForgeRegistries.Keys.BIOMES,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"phoenix_jungle"));
    //public static final ResourceKey<Biome> WATER_DRAGON_SEAS = ResourceKey.create(ForgeRegistries.Keys.BIOMES,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"water_dragon_seas"));
    //public static final ResourceKey<Biome> BUNNY_HOP_MOUNTAINS = ResourceKey.create(ForgeRegistries.Keys.BIOMES,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"bunny_hop_mountains"));
  //  Biomes
//    BiomeDefaultFeatures
    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }

    public static final ResourceKey<Biome> KITSUNE_HIGHLANDS =   ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "kitsune_highlands"));
    public static final ResourceKey<Biome> PHOENIX_JUNGLE =      ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"phoenix_jungle"));
    public static final ResourceKey<Biome> WATER_DRAGON_SEAS =   ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"water_dragon_seas"));
    public static final ResourceKey<Biome> BUNNY_HOP_MOUNTAINS = ResourceKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"bunny_hop_mountains"));

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(KITSUNE_HIGHLANDS, Kitsune_Highlands(context));
        context.register(PHOENIX_JUNGLE, Phoenix_Jungle(context));
        context.register(WATER_DRAGON_SEAS, Water_Dragon_Seas(context));
        context.register(BUNNY_HOP_MOUNTAINS, Bunny_Hop_Mountains(context));
    }

    private static Biome Bunny_Hop_Mountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(TLEntityRegistry.KITSUNE.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GOAT, 5, 1, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.3f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x57e29f)
                        .waterFogColor(0x02fab8)
                        .skyColor(0x3bacd6)
                        .grassColorOverride(0x599a63)
                        .foliageColorOverride(0x595663)
                        .fogColor(0xa7ffe6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    private static Biome Water_Dragon_Seas(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
//        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(TLEntityRegistry.KITSUNE.get(), 1, 3, 5));

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 5, 4, 4));

        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 1, 4, 10);
        spawnBuilder.addSpawn(MobCategory.WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.DOLPHIN, 1, 1, 2));
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        biomeBuilder.addFeature(
                GenerationStep.Decoration.VEGETAL_DECORATION,  AquaticPlacements.SEAGRASS_DEEP);
        BiomeDefaultFeatures.addDefaultSeagrass(biomeBuilder);
        BiomeDefaultFeatures.addColdOceanExtraVegetation(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.3f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x57e29f)
                        .waterFogColor(0x02fab8)
                        .skyColor(0x3bacd6)
                        .grassColorOverride(0x599a63)
                        .foliageColorOverride(0x595663)
                        .fogColor(0xa7ffe6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();

    }

    private static Biome Phoenix_Jungle(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(TLEntityRegistry.KITSUNE.get(), 1, 3, 5));

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addGroveTrees(biomeBuilder);
        BiomeDefaultFeatures.addJungleTrees(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TLPlacedFeaturesRegistry.MAGIWOOD_PLACED_KEY);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
        BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.9f)
                .temperature(0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x57e29f)
                        .waterFogColor(0x02fab8)
                        .skyColor(0x3bacd6)
                        .grassColorOverride(0x599a63)
                        .foliageColorOverride(0x595663)
                        .fogColor(0xa7ffe6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome Kitsune_Highlands(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(TLEntityRegistry.KITSUNE.get(), 2, 3, 5));

        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addCherryGroveVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, TLPlacedFeaturesRegistry.MAGIWOOD_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x57e2df)
                        .waterFogColor(0x02faff)
                        .skyColor(0x3bacd6)
                        .grassColorOverride(0xd560fc)
                        .foliageColorOverride(0xb3bbfc)
                        .fogColor(0xa7ffe6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build())
                .build();
    }
}
