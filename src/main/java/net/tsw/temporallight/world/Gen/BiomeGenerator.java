package net.tsw.temporallight.world.Gen;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.world.biome.TLBiomeRegistry;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class BiomeGenerator {
        public static void generateBiomes() {
            addBiome(TLBiomeRegistry.MAGIWOOD_BIOME.get(), BiomeManager.BiomeType.WARM, 20, END, MAGICAL,WATER);
            addBiome(TLBiomeRegistry.EARTH44.get(),BiomeManager.BiomeType.ICY,20,DRY,DEAD);
        }

        private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight, BiomeDictionary.Type... types) {
            RegistryKey<Biome> key = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                    Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

            BiomeDictionary.addTypes(key, types);
            BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
        }
}
