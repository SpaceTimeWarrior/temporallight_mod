package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.world.biome.BiomeGenMagiwoodForest;
import com.TimeSpaceWarrior.TemporalLightMod.world.biome.BiomeGenMagiwoodForestDIM;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BiomeRegistry {
    public static BiomeGenBase MagiwoodForestOverworld;
    public static BiomeGenBase MagiwoodForest;
    public static void register() {
        initializeBiomes();
        RegisterBiomes();
    }

    public static void RegisterBiomes() {
        BiomeDictionary.registerBiomeType(MagiwoodForestOverworld,new BiomeDictionary.Type[]{Type.FOREST,Type.WET});
        BiomeDictionary.registerBiomeType(MagiwoodForest,new BiomeDictionary.Type[]{Type.MAGICAL,Type.FOREST});
        BiomeManager.addSpawnBiome(MagiwoodForestOverworld);//adds to the overworld spawn generation
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM,new BiomeManager.BiomeEntry(MagiwoodForestOverworld, 15));//adds to overworld generation
    }

    public static void initializeBiomes() {
        MagiwoodForestOverworld = (new BiomeGenMagiwoodForest(TLConfig.BiomeMagiwood_forest_Overworld_ID)).setBiomeName("Magiwood Forest Overworld").setTemperatureRainfall(2.25F,0.6F);
        MagiwoodForest = (new BiomeGenMagiwoodForestDIM(TLConfig.BiomeMagiwood_forest_Overworld_ID)).setBiomeName("Magiwood Forest").setTemperatureRainfall(2.25F,0.6F);
    }
}
