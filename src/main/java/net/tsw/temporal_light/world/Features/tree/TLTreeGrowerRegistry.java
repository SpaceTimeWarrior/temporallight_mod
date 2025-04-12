package net.tsw.temporal_light.world.Features.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.world.Features.TLConfigiredFeaturesRegistry;

import java.util.Optional;

public class TLTreeGrowerRegistry {
    public static final TreeGrower MAGIWOOD = new TreeGrower(Temporal_Light.MOD_ID+"magiwood", Optional.empty(),Optional.of(TLConfigiredFeaturesRegistry.MAGIWOOD_KEY),Optional.empty());
}
