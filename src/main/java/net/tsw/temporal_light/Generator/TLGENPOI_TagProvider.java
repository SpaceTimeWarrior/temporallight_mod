package net.tsw.temporal_light.Generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tsw.temporal_light.Temporal_Light;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TLGENPOI_TagProvider extends PoiTypeTagsProvider {
    public TLGENPOI_TagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, Temporal_Light.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).addOptional(ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"end_poi"));
    }
}
