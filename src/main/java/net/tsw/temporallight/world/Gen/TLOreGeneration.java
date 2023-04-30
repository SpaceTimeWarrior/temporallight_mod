package net.tsw.temporallight.world.Gen;

import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
//import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.tsw.temporallight.util.tagRegistry;
import net.tsw.temporallight.world.biome.TLBiomeRegistry;

import java.util.Arrays;
import java.util.Set;


public class TLOreGeneration {
    public static void generateOres(final BiomeLoadingEvent event){
        spawnOreInSpecificBiome(Biomes.END_BARRENS, TLOreType.SYNTHETICTIMECRYSTAL, event, Dimension.THE_END.toString());
        spawnOreInSpecificBiome(Biomes.THE_END, TLOreType.SYNTHETICTIMECRYSTAL, event, Dimension.THE_END.toString());
        spawnOreInSpecificBiome(Biomes.END_HIGHLANDS, TLOreType.SYNTHETICTIMECRYSTAL, event, Dimension.THE_END.toString());
        spawnOreInSpecificBiome(Biomes.SMALL_END_ISLANDS, TLOreType.SYNTHETICTIMECRYSTAL, event, Dimension.THE_END.toString());
        spawnOreInSpecificModBiome(TLBiomeRegistry.MAGIWOOD_BIOME.get(),TLOreType.SYNTHETICTIMECRYSTAL,event,Dimension.OVERWORLD.toString());
        spawnOreInSpecificModBiome(TLBiomeRegistry.MAGIWOOD_BIOME.get(),TLOreType.SYNTHETICTIMECRYSTAL,event,Dimension.THE_NETHER.toString());
        spawnOreInSpecificModBiome(TLBiomeRegistry.MAGIWOOD_BIOME.get(),TLOreType.SYNTHETICTIMECRYSTAL,event,Dimension.THE_END.toString());
        /*for(TLOreType ore: TLOreType.values()){
            OreFeatureConfig ofeatureconfig = new OreFeatureConfig(FillerBlockType.Base_STONE_END,ore.getBlock().get().getDefaultState(), ore.getMaxVeinsize());
            ConfiguredPlacement<TopSolidRangeConfig> configuredplacement = Placement.RANGE.configure(new TopSolidRangeConfig(ore.getMinWeight(), ore.getMinWeight(),ore.getMaxWeight()));
            ConfiguredFeature<?,?> oreFeature = registerOreFeature(ore,ofeatureconfig,configuredplacement);
            RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY,event.getName());
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
            if(types.contains(BiomeDictionary.Type.END)) {
                event.getGeneration().withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, oreFeature);
            }else{
                event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
            }
        }*/

    }
    private static OreFeatureConfig getOverworldFeatureConfig(TLOreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinsize());
    }

    private static OreFeatureConfig getNetherFeatureConfig(TLOreType ore) {
        return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinsize());
    }

    private static OreFeatureConfig getEndFeatureConfig(TLOreType ore) {
        return new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE),
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinsize());
    }
    private static ConfiguredFeature<?, ?> makeOreFeature(TLOreType ore, String dimensionToSpawnIn) {
        OreFeatureConfig oreFeatureConfig = null;

        if(dimensionToSpawnIn.equals(Dimension.OVERWORLD.toString())) {
            oreFeatureConfig = getOverworldFeatureConfig(ore);
        } else if(dimensionToSpawnIn.equals(Dimension.THE_NETHER.toString())) {
            oreFeatureConfig = getNetherFeatureConfig(ore);
        } else if(dimensionToSpawnIn.equals(Dimension.THE_END.toString())) {
            oreFeatureConfig = getEndFeatureConfig(ore);
        }

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        return registerOreFeature(ore, oreFeatureConfig, configuredPlacement);
    }

    private static void spawnOreInOverworldInGivenBiomes(TLOreType ore, final BiomeLoadingEvent event, Biome... biomesToSpawnIn) {
        OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinsize());

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        ConfiguredFeature<?, ?> oreFeature = registerOreFeature(ore, oreFeatureConfig, configuredPlacement);

        if (Arrays.stream(biomesToSpawnIn).anyMatch(b -> b.getRegistryName().equals(event.getName()))) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
        }
    }

    private static void spawnOreInOverworldInAllBiomes(TLOreType ore, final BiomeLoadingEvent event) {
        OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                ore.getBlock().get().getDefaultState(), ore.getMaxVeinsize());

        ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
                new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

        ConfiguredFeature<?, ?> oreFeature = registerOreFeature(ore, oreFeatureConfig, configuredPlacement);

        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
    }

    private static void spawnOreInSpecificModBiome(Biome biomeToSpawnIn, TLOreType currentOreType,
                                                   final BiomeLoadingEvent event, String dimension) {
        if(event.getName().toString().contains(biomeToSpawnIn.getRegistryName().toString())) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, makeOreFeature(currentOreType, dimension));
        }
    }

    private static void spawnOreInSpecificBiome(RegistryKey<Biome> biomeToSpawnIn, TLOreType currentOreType,
                                                final BiomeLoadingEvent event, String dimension) {
        if(event.getName().toString().contains(biomeToSpawnIn.getLocation().toString())) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, makeOreFeature(currentOreType, dimension));
        }
    }

    private static void spawnOreInAllBiomes(TLOreType currentOreType, final BiomeLoadingEvent event, String dimension) {
        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                makeOreFeature(currentOreType, dimension));
    }
    private static ConfiguredFeature<?,?> registerOreFeature(TLOreType ore, OreFeatureConfig orefeatureconfig,ConfiguredPlacement configuredplacement){
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ore.getBlock().get().getRegistryName(), Feature.ORE.withConfiguration(orefeatureconfig).withPlacement(configuredplacement).square().count(ore.getMaxVeinsize()));
    }
    /*public static final class FillerBlockType{
        public static final RuleTest Base_STONE_END = new TagMatchRuleTest(tagRegistry.Blocks.BASE_STONE_END);
    }*/
}
