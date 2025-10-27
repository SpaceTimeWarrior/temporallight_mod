package net.tsw.Temporal_Light.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;

import java.util.List;

public class TLConfigiredFeaturesRegistry {
    public static final ResourceKey<ConfiguredFeature<?,?>>OVERWORLD_SYNTHETIC_TC_ORE_KEY = registerKey("synthetictimecrystalore");
    public static final ResourceKey<ConfiguredFeature<?,?>>NETHER_SYNTHETIC_TC_ORE_KEY = registerKey("synthetictimecrystalore_nether");
    public static final ResourceKey<ConfiguredFeature<?,?>>END_SYNTHETIC_TC_ORE_KEY = registerKey("synthetictimecrystalore_end");
    public static final ResourceKey<ConfiguredFeature<?,?>>MAGIWOOD_KEY = registerKey("magiwood");
    public static final ResourceKey<ConfiguredFeature<?,?>>MAGIWOOD_END_KEY = registerKey("magiwood_end");
    public static int OVW_TimeCrystal_MaxVeinSize = 9;
    public static int NTR_TimeCrystal_MaxVeinSize = 9;
    public static int END_TimeCrystal_MaxVeinSize = 9;
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, name));
    }
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldTCOres = List.of(
                OreConfiguration.target(stoneReplaceable, TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, TLBlocksRegistry.SYNTHETICTIMECRYSTALORE_DEEPSLATE.get().defaultBlockState()));

        register(context, OVERWORLD_SYNTHETIC_TC_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTCOres, OVW_TimeCrystal_MaxVeinSize));
        register(context, NETHER_SYNTHETIC_TC_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplacables,
                TLBlocksRegistry.SYNTHETICTIMECRYSTALORE_NETHER.get().defaultBlockState(), NTR_TimeCrystal_MaxVeinSize));
        register(context, END_SYNTHETIC_TC_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables,
                TLBlocksRegistry.SYNTHETICTIMECRYSTALORE_END.get().defaultBlockState(), END_TimeCrystal_MaxVeinSize));

        register(context, MAGIWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TLBlocksRegistry.MAGIWOODLOG.get()),
                new ForkingTrunkPlacer(31, 3, 1),

                BlockStateProvider.simple(TLBlocksRegistry.MAGIWOODLEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),

                new TwoLayersFeatureSize(6, 1, 5)).build());
        register(context, MAGIWOOD_END_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TLBlocksRegistry.MAGIWOODLOG.get()),
                new ForkingTrunkPlacer(31, 3, 1),

                BlockStateProvider.simple(TLBlocksRegistry.MAGIWOODLEAVES.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),

                new TwoLayersFeatureSize(6, 1, 5)).dirt(BlockStateProvider.simple(Blocks.END_STONE)).build());
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
