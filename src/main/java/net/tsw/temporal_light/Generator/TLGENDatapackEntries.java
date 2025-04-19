package net.tsw.temporal_light.Generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.world.Dimension.TLDimensionRegistry;
import net.tsw.temporal_light.world.Features.TLBiomeModifiers;
import net.tsw.temporal_light.world.Features.TLConfigiredFeaturesRegistry;
import net.tsw.temporal_light.world.Features.TLPlacedFeaturesRegistry;
import net.tsw.temporal_light.world.Features.biome.TLBiomeRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TLGENDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, TLDimensionRegistry::bootstrapType)
            .add(Registries.CONFIGURED_FEATURE, TLConfigiredFeaturesRegistry::bootstrap)
            .add(Registries.PLACED_FEATURE, TLPlacedFeaturesRegistry::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, TLBiomeModifiers::bootstrap)
            .add(Registries.BIOME, TLBiomeRegistry::boostrap)
            .add(Registries.LEVEL_STEM, TLDimensionRegistry::bootstrapStem);
            ;

    public TLGENDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Temporal_Light.MOD_ID));
    }
}
