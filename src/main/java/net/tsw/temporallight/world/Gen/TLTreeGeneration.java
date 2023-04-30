package net.tsw.temporallight.world.Gen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.tsw.temporallight.block.custom.tree.MagiwoodTree;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class TLTreeGeneration {
    public static void generateTrees(final BiomeLoadingEvent event){
        MagiwoodTree magiwoodtree = new MagiwoodTree();
        Random random = new Random();
        RegistryKey<Biome> key = RegistryKey.getOrCreateKey(Registry.BIOME_KEY,event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        if(types.contains(BiomeDictionary.Type.MAGICAL)){
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
            base.add(()->ConfiguredFeaturesRegistry.MAGIWOOD.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0,.99F,1))));
        }
        if(types.contains(BiomeDictionary.Type.END)){
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
            base.add(()->ConfiguredFeaturesRegistry.MAGIWOOD.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0,.99F,1))));
        }
        if(types.contains(BiomeDictionary.Type.RARE)){
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
            base.add(()->ConfiguredFeaturesRegistry.MAGIWOOD.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0,.99F,1))));
        }
    }
}
