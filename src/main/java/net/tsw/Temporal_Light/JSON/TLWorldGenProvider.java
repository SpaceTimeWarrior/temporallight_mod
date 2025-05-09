package net.tsw.Temporal_Light.JSON;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.world.TLBiomeModifiers;
import net.tsw.Temporal_Light.world.TLConfigiredFeaturesRegistry;
import net.tsw.Temporal_Light.world.TLPlacedFeaturesRegistry;
import net.tsw.Temporal_Light.world.biome.TLBiomeRegistry;
import net.tsw.Temporal_Light.world.dimension.TLDimensionRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TLWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, TLDimensionRegistry::bootstrapType)
            .add(Registries.CONFIGURED_FEATURE, TLConfigiredFeaturesRegistry::bootstrap)
            .add(Registries.PLACED_FEATURE, TLPlacedFeaturesRegistry::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, TLBiomeModifiers::bootstrap)
            .add(Registries.BIOME, TLBiomeRegistry::boostap)
            .add(Registries.LEVEL_STEM, TLDimensionRegistry::bootstrapStem);


    public TLWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output,registries,BUILDER, Set.of(Temporal_Light.MOD_ID));
    }
}
