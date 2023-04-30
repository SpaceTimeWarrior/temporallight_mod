package net.tsw.temporallight.block.custom.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.tsw.temporallight.world.Gen.ConfiguredFeaturesRegistry;

import javax.annotation.Nullable;
import java.util.Random;

public class MagiwoodTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return ConfiguredFeaturesRegistry.MAGIWOOD;
    }
}
