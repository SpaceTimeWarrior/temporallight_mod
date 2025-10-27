package net.tsw.Temporal_Light.JSON;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tsw.Temporal_Light.Temporal_Light;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TLGENPaintingVarientTagProvider extends PaintingVariantTagsProvider {
    public TLGENPaintingVarientTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, Temporal_Light.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(PaintingVariantTags.PLACEABLE)
                .addOptional(ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "eather_image"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "jem_armor_bw"))
                .addOptional(ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"jem_armor_color"));
    }
}
