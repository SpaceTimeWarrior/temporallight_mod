package net.tsw.temporallight.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.tsw.temporallight.TemporalLight;

public class tagRegistry {
    public static class Blocks {
        public static final Tags.IOptionalNamedTag<Block> HYPERSTEELS=createTag("hypersteel");
        public static final Tags.IOptionalNamedTag<Block> MAGIWOOD=createTag("magiwood");
        public static final Tags.IOptionalNamedTag<Block> BASE_STONE_END=createTag("basestoneend");
        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(TemporalLight.MOD_ID,name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge",name));
        }
    }
    public static class Items{
        private static Tags.IOptionalNamedTag<Item> createTag(String name){
            return ItemTags.createOptional(new ResourceLocation(TemporalLight.MOD_ID,name));
        }
        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name){
            return ItemTags.createOptional(new ResourceLocation("forge",name));
        }
    }
}
