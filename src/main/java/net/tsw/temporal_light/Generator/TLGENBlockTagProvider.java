package net.tsw.temporal_light.Generator;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.util.TLTagRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TLGENBlockTagProvider extends BlockTagsProvider {
    public TLGENBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Temporal_Light.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(TLBlocksRegistry.HYPERSTEEL_BLOCK.get())
                .add(TLBlocksRegistry.HYPERSTEEL_BUTTON.get())
                .add(TLBlocksRegistry.HYPERSTEEL_PRESSUREPLATE.get())
                .add(TLBlocksRegistry.HYPERSTEEL_RAILING.get())
                .add(TLBlocksRegistry.HYPERSTEEL_RAILING_gate.get())
                .add(TLBlocksRegistry.HYPERSTEEL_SLAB.get())
                .add(TLBlocksRegistry.HYPERSTEEL_STAIRS.get())
                .add(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(TLBlocksRegistry.MAGIWOOD.get())
                .add(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get())
                .add(TLBlocksRegistry.MAGIWOODSTRIPPED.get())
                .add(TLBlocksRegistry.MAGIWOODLOG.get());
        this.tag(BlockTags.FENCES)
                .add(TLBlocksRegistry.HYPERSTEEL_RAILING.get())
                .add(TLBlocksRegistry.MAGIWOOD_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(TLBlocksRegistry.HYPERSTEEL_RAILING_gate.get())
                .add(TLBlocksRegistry.MAGIWOOD_FENCE_gate.get());
        this.tag(BlockTags.STAIRS)
                .add(TLBlocksRegistry.HYPERSTEEL_STAIRS.get())
                .add(TLBlocksRegistry.MAGIWOOD_STAIRS.get());
        this.tag(Tags.Blocks.ORES).add(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get());
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(TLBlocksRegistry.HYPERSTEEL_BLOCK.get())
                .add(TLBlocksRegistry.HYPERSTEEL_BUTTON.get())
                .add(TLBlocksRegistry.HYPERSTEEL_PRESSUREPLATE.get())
                .add(TLBlocksRegistry.HYPERSTEEL_RAILING.get())
                .add(TLBlocksRegistry.HYPERSTEEL_RAILING_gate.get())
                .add(TLBlocksRegistry.HYPERSTEEL_SLAB.get())
                .add(TLBlocksRegistry.HYPERSTEEL_STAIRS.get())
                .add(TLBlocksRegistry.MAGIWOOD.get())
                .add(TLBlocksRegistry.MAGIWOODLOG.get())
                .add(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get())
                .add(TLBlocksRegistry.MAGIWOODSTRIPPED.get())
                .add(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get());
        this.tag(BlockTags.PLANKS).add(TLBlocksRegistry.MAGIWOODPLANKS.get());
        this.tag(BlockTags.MINEABLE_WITH_HOE).add(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get());
        this.tag(TLTagRegistry.Blocks.NEEDS_HYPERSTEEL_TOOL).addTag(Tags.Blocks.NEEDS_NETHERITE_TOOL);
        this.tag(TLTagRegistry.Blocks.INCORRECT_FOR_HYPERSTEEL_TOOL).addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL).remove(TLTagRegistry.Blocks.NEEDS_HYPERSTEEL_TOOL);
    }
}
