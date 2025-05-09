package net.tsw.Temporal_Light.JSON;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.util.TLTagRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

public class TLGENItemTagGenerator extends ItemTagsProvider {



    public TLGENItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Temporal_Light.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIM_MATERIALS).add(TLItemRegistry.HYPERSTEELINGOT.get());
        this.tag(ItemTags.TOOLS).add(
                TLItemRegistry.EARTH_STAFF.get(),
                TLItemRegistry.REDSTONE_STAFF.get(),
                TLItemRegistry.LIGHTNING_STAFF.get(),
                TLItemRegistry.FIRE_STAFF.get(),
                TLItemRegistry.LIFE_STAFF.get())
                .add(TLItemRegistry.HYPERSTEELAXE.get(),
                        TLItemRegistry.HYPERSTEELBOW.get(),
                        TLItemRegistry.HYPERSTEELSWORD.get(),
                        TLItemRegistry.HYPERSTEELSHOVEL.get(),
                        TLItemRegistry.HYPERSTEELPICKAXE.get(),
                        TLItemRegistry.HYPERSTEELHOE.get())
                .add(TLItemRegistry.MAGIWOODCLUB.get())
                .add(TLItemRegistry.LIGHTSTEELPICKAXE.get(),
                        TLItemRegistry.LIGHTSTEELSHOVEL.get(),
                        TLItemRegistry.LIGHTSTEELSWORD.get(),
                        TLItemRegistry.LIGHTSTEELHOE.get(),
                        TLItemRegistry.LIGHTSTEELAXE.get());
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        TLItemRegistry.HYPERSTEELHELMET.get(),
                        TLItemRegistry.HYPERSTEELBOOTS.get(),
                        TLItemRegistry.HYPERSTEELCHESTPLATE.get(),
                        TLItemRegistry.HYPERSTEELLEGGINGS.get())
                .add(
                        TLItemRegistry.MAGIWOOD_HELMET.get(),
                        TLItemRegistry.MAGIWOOD_BOOTS.get(),
                        TLItemRegistry.MAGIWOOD_CHESTPLATE.get(),
                        TLItemRegistry.MAGIWOOD_LEGGINGS.get())
                .add(
                        TLItemRegistry.LIGHTSTEELHELMET.get(),
                        TLItemRegistry.LIGHTSTEELCHESTPLATE.get(),
                        TLItemRegistry.LIGHTSTEELLEGGINGS.get(),
                        TLItemRegistry.LIGHTSTEELBOOTS.get());
        this.tag(ItemTags.LOGS)
                .add(TLBlocksRegistry.MAGIWOOD.get().asItem())
                .add(TLBlocksRegistry.MAGIWOODSTRIPPED.get().asItem())
                .add(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get().asItem())
                .add(TLBlocksRegistry.MAGIWOODLOG.get().asItem());
        this.tag(ItemTags.PLANKS)
                .add(TLBlocksRegistry.MAGIWOODPLANKS.get().asItem());
        this.tag(ItemTags.SIGNS).add(TLItemRegistry.MAGI_SIGN.get());
        this.tag(Tags.Items.INGOTS).add(TLItemRegistry.HYPERSTEELINGOT.get(),
                TLItemRegistry.LIGHTSTEELINGOT.get());
        this.tag(ItemTags.HANGING_SIGNS).add(TLItemRegistry.MAGI_HANGING_SIGN.get());
        this.tag(TLTagRegistry.Items.MAGIC_ESSENCE_NULL).add(TLItemRegistry.MAGIC_ESSENCE_NULL.get());
        this.tag(TLTagRegistry.Items.MAGIC_ESSENCE_ELECTRICITY).add(TLItemRegistry.MAGIC_ESSENCE_ELECTRICITY.get());
        this.tag(TLTagRegistry.Items.MAGIC_ESSENCE_FIRE)
                .add(TLItemRegistry.MAGIC_ESSENCE_FIRE.get())
                .add(TLItemRegistry.MAGIC_ESSENCE_FIRE_BURNABLE.get());
        this.tag(TLTagRegistry.Items.MAGIC_ESSENCE_EARTH).add(TLItemRegistry.MAGIC_ESSENCE_EARTH.get());
        this.tag(TLTagRegistry.Items.MAGIC_ESSENCE_LIFE).add(TLItemRegistry.MAGIC_ESSENCE_LIFE.get());
        this.tag(TLTagRegistry.Items.MAGIC_ESSENCE)
                .addTag(TLTagRegistry.Items.MAGIC_ESSENCE_NULL)
                .addTag(TLTagRegistry.Items.MAGIC_ESSENCE_ELECTRICITY)
                .addTag(TLTagRegistry.Items.MAGIC_ESSENCE_FIRE)
                .addTag(TLTagRegistry.Items.MAGIC_ESSENCE_EARTH)
                .addTag(TLTagRegistry.Items.MAGIC_ESSENCE_LIFE);


    }
}
