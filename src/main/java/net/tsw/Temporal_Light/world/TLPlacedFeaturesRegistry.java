package net.tsw.Temporal_Light.world;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.util.OrePlacements;

import java.util.List;

public class TLPlacedFeaturesRegistry {
    public static final ResourceKey<PlacedFeature> TC_PLACED_ORE_OVERWORLD = registerKey("synthetictimecrystalore_placed");
    public static final ResourceKey<PlacedFeature> TC_PLACED_ORE_NETHER = registerKey("synthetictimecrystalore_placed_nether");
    public static final ResourceKey<PlacedFeature> TC_PLACED_ORE_END = registerKey("synthetictimecrystalore_placed_end");
    public static final ResourceKey<PlacedFeature> MAGIWOOD_PLACED_KEY = registerKey("magiwood_placed");
    public static final ResourceKey<PlacedFeature> MAGIWOOD_PLACED_END_KEY = registerKey("magiwood_placed_end");

    public static int tc_veins_per_chunk_ovr = 4;
    public static int tc_ylow_ovr = -64;
    public static int tc_yhigh_ovr = 80;
    public static int tc_veins_per_chunk_ntr = 4;
    public static int tc_ylow_ntr = -64;
    public static int tc_yhigh_ntr = 80;
    public static int tc_veins_per_chunk_end = 4;
    public static int tc_ylow_end = -64;
    public static int tc_yhigh_end = 80;
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, TC_PLACED_ORE_OVERWORLD, configuredFeatures.getOrThrow(TLConfigiredFeaturesRegistry.OVERWORLD_SYNTHETIC_TC_ORE_KEY),
                OrePlacements.commonOrePlacement(tc_veins_per_chunk_ovr,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(tc_ylow_ovr), VerticalAnchor.absolute(tc_yhigh_ovr))));
        register(context, TC_PLACED_ORE_NETHER, configuredFeatures.getOrThrow(TLConfigiredFeaturesRegistry.NETHER_SYNTHETIC_TC_ORE_KEY),
                OrePlacements.commonOrePlacement(tc_veins_per_chunk_ntr,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(tc_ylow_ntr), VerticalAnchor.absolute(tc_yhigh_ntr))));
        register(context, TC_PLACED_ORE_END, configuredFeatures.getOrThrow(TLConfigiredFeaturesRegistry.END_SYNTHETIC_TC_ORE_KEY),
                OrePlacements.commonOrePlacement(tc_veins_per_chunk_end,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(tc_ylow_end), VerticalAnchor.absolute(tc_yhigh_end))));
        register(context,MAGIWOOD_PLACED_KEY,configuredFeatures.getOrThrow(TLConfigiredFeaturesRegistry.MAGIWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.1f,2), TLBlocksRegistry.MAGIWOODSAPLING.get()));
        register(context,MAGIWOOD_PLACED_END_KEY,configuredFeatures.getOrThrow(TLConfigiredFeaturesRegistry.MAGIWOOD_END_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1,0.1f,2), TLBlocksRegistry.MAGIWOODSAPLING.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
