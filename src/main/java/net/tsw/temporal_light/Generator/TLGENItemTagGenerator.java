package net.tsw.temporal_light.Generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Items.TLItemRegistry;
import net.tsw.temporal_light.Temporal_Light;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TLGENItemTagGenerator extends ItemTagsProvider {


    public TLGENItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Temporal_Light.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIM_MATERIALS).add(TLItemRegistry.HYPERSTEELINGOT.get());
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        TLItemRegistry.HYPERSTEELHELMET.get(),
                        TLItemRegistry.HYPERSTEELBOOTS.get(),
                        TLItemRegistry.HYPERSTEELCHESTPLATE.get(),
                        TLItemRegistry.HYPERSTEELLEGGINGS.get());
        this.tag(ItemTags.PLANKS)
                .add(TLBlocksRegistry.MAGIWOODPLANKS.get().asItem());
    }
}
