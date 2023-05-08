package net.tsw.temporallight.world.biome;

import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.world.Gen.ConfiguredFeaturesRegistry;

import java.util.function.Supplier;

public class TLBiomeRegistry{
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, TemporalLight.MOD_ID);
    public static RegistryObject<Biome> MAGIWOOD_BIOME = BIOMES.register("magiwood_biome",
            () -> makeMagiwoodBiome(() -> TLConfiguredSurfaceBuilders.MAGIWOODS_SURFACE, 0.125f, 0.05f));
    public static RegistryObject<Biome> EARTH44 = BIOMES.register("earth44_biome",()->makeEarth44Biome(()->TLConfiguredSurfaceBuilders.EARTH44_SURFACE,0.666f,0.06f));
    public static Biome makeEarth44Biome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale){
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);
        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withBadlandsStructures(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withLavaLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withDebrisOre(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withDesertDeadBushes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withFossils(biomegenerationsettings$builder);
        return (new Biome.Builder()).precipitation(Biome.RainType.SNOW).category(Biome.Category.MESA).depth(depth).scale(scale)
                .temperature(0.0F).downfall(1F).setEffects((new BiomeAmbience.Builder()).setWaterColor(0x000000).setWaterFogColor(0x000000)
                        .setFogColor(0x000000).withFoliageColor(0x000000).withGrassColor(0x000000).withSkyColor(0)
                        .setParticle(new ParticleEffectAmbience(ParticleTypes.ASH, 0.3f))
                        .setAmbientSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 2000, 2, 2.0D))
                        .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.1D))
                        .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_NETHER_BASALT_DELTAS))
                        .build())
                .withMobSpawnSettings(mobspawninfo$builder.build()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }
    public static Biome makeMagiwoodBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale){
        MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.withPassiveMobs(mobspawninfo$builder);
        DefaultBiomeFeatures.withBatsAndHostiles(mobspawninfo$builder);
        mobspawninfo$builder.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 100, 7, 10));
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIFIED_PIGLIN, 50, 4, 4));
        mobspawninfo$builder.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 50, 4, 4));

        BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);
        DefaultBiomeFeatures.withForestBirchTrees(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withEndermen(mobspawninfo$builder);
        DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withMonsterRoom(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withClayDisks(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withNormalMushroomGeneration(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withJungleEdgeTrees(biomegenerationsettings$builder);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomegenerationsettings$builder);


        withMagiwoodTrees(biomegenerationsettings$builder);


        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.LAKES, Features.LAKE_WATER);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.LAKES, Features.SPRING_WATER);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.ACACIA);
        biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BIRCH_BEES_0002);

        DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettings$builder);
        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(depth).scale(scale)
                .temperature(0.5F).downfall(1F).setEffects((new BiomeAmbience.Builder()).setWaterColor(0x5555FF).setWaterFogColor(0x90FED9)
                        .setFogColor(0xFFFFFF).withFoliageColor(0xCCCCFF).withGrassColor(0xFF9999).withSkyColor(0)
                        .setParticle(new ParticleEffectAmbience(ParticleTypes.MYCELIUM, 0.003f))
                        .setAmbientSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                        .setMoodSound(new MoodSoundAmbience(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
                        .setAdditionsSound(new SoundAdditionsAmbience(SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE, 0.0111D))
                        .setMusic(BackgroundMusicTracks.getDefaultBackgroundMusicSelector(SoundEvents.MUSIC_END))
                        .build())
                .withMobSpawnSettings(mobspawninfo$builder.build()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
    }

    public static void withMagiwoodTrees(BiomeGenerationSettings.Builder builder) {
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ConfiguredFeaturesRegistry.MAGIWOOD);
    }
    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
    public static void register(IEventBus eventbus){
        BIOMES.register(eventbus);
    }
}
