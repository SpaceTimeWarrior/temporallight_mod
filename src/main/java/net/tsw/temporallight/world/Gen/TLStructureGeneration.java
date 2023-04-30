package net.tsw.temporallight.world.Gen;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.tsw.temporallight.world.biome.TLBiomeRegistry;
import net.tsw.temporallight.world.structure.TLStructureRegistry;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
public class TLStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        generateinModBiomes(TLBiomeRegistry.MAGIWOOD_BIOME.get(),event,"magiwood_dim");
    }
    public static void generateinModBiomes(Biome biomeToSpawnIn,
                                           final BiomeLoadingEvent event, String dimension){
        if(event.getName().toString().contains(biomeToSpawnIn.getRegistryName().toString())) {
            List<Supplier<StructureFeature<?, ?>>> structures = event.getGeneration().getStructures();
            structures.add(() -> TLStructureRegistry.MAGIWOODTREE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }
}
