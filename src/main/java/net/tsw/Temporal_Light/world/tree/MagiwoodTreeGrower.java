package net.tsw.Temporal_Light.world.tree;



import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.tsw.Temporal_Light.world.TLConfigiredFeaturesRegistry;
import org.jetbrains.annotations.Nullable;

public class MagiwoodTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return TLConfigiredFeaturesRegistry.MAGIWOOD_KEY;
    }
}
