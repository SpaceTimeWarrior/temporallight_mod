package net.tsw.temporallight.world.Gen;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.tsw.temporallight.block.blockRegistry;

public class ConfiguredFeaturesRegistry {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGIWOOD = register("magiwood", Feature.TREE.withConfiguration((
            new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(blockRegistry.MAGIWOODLOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(blockRegistry.MAGIWOODLEAVES.get().getDefaultState()),
                    new AcaciaFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(0)),
                    new ForkyTrunkPlacer(31, 3, 1),
                    new TwoLayerFeature(6, 1, 5))).setIgnoreVines().build()));
    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
