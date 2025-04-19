package net.tsw.temporal_light.world.Features;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.entity.TLEntityRegistry;

import java.util.List;

public class TLBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_TC_ORE_OVR = registerKey("add_ovr_synthetictimecrystalore");
    public static final ResourceKey<BiomeModifier> ADD_TC_ORE_NTR = registerKey("add_ntr_synthetictimecrystalore");
    public static final ResourceKey<BiomeModifier> ADD_TC_ORE_END = registerKey("add_end_synthetictimecrystalore");
    public static final ResourceKey<BiomeModifier> ADD_MAGIWOOD = registerKey("add_magiwood");
    public static final ResourceKey<BiomeModifier> ADD_END_MAGIWOOD = registerKey("add_end_magiwood");
    public static final ResourceKey<BiomeModifier> SPAWN_KITSUNE = registerKey("spawn_kitsune");
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);
        context.register(ADD_TC_ORE_OVR, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeature.getOrThrow(TLPlacedFeaturesRegistry.TC_PLACED_ORE_OVERWORLD)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        // Individual Biomes
        // context.register(ADD_TC_ORE_OVR, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
        //         HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.BAMBOO_JUNGLE)),
        //         HolderSet.direct(placedFeature.getOrThrow(TLPlacedFeaturesRegistry.TC_PLACED_ORE_OVERWORLD)),
        //         GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TC_ORE_NTR, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeature.getOrThrow(TLPlacedFeaturesRegistry.TC_PLACED_ORE_NETHER)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TC_ORE_END, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeature.getOrThrow(TLPlacedFeaturesRegistry.TC_PLACED_ORE_END)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        // Individual Biomes
        context.register(ADD_MAGIWOOD, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                 HolderSet.direct(biomes.getOrThrow(Biomes.CHERRY_GROVE), biomes.getOrThrow(Biomes.DRIPSTONE_CAVES)),
                 HolderSet.direct(placedFeature.getOrThrow(TLPlacedFeaturesRegistry.MAGIWOOD_PLACED_KEY)),
                 GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_END_MAGIWOOD, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.END_BARRENS), biomes.getOrThrow(Biomes.SMALL_END_ISLANDS)),
                HolderSet.direct(placedFeature.getOrThrow(TLPlacedFeaturesRegistry.MAGIWOOD_PLACED_END_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        //spawn Mobs
        context.register(SPAWN_KITSUNE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.CHERRY_GROVE),biomes.getOrThrow(Biomes.GROVE),biomes.getOrThrow(Biomes.LUSH_CAVES)),
                List.of(new MobSpawnSettings.SpawnerData(TLEntityRegistry.KITSUNE.get(),5,1,5))));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, name));
    }
}
