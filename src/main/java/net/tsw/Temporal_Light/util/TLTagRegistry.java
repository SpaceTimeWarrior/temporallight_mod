package net.tsw.Temporal_Light.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.tsw.Temporal_Light.Temporal_Light;

public class TLTagRegistry {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_HYPERSTEEL_TOOL = tag("needs_hypersteel_tool");
        public static final TagKey<Block> NEEDS_MAGIWOOD_TOOL = tag("needs_magiwood_tool");
        //public static final TagKey<Block> INCORRECT_FOR_HYPERSTEEL_TOOL = tag("incorrect_for_hypersteel_tool");
        //public static final TagKey<Block> INCORRECT_FOR_MAGIWOOD_TOOL = tag("incorrect_for_magiwood_tool");
        public static final TagKey<Block> RAW_MAGIWOOD_BLOCKS = tag("magiwoods");
        private static TagKey<Block> tag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,name));
        }
    }
    public static class Items {
        //public static final TagKey<Item> TEST = tag("test_item");
        private static TagKey<Item> tag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,name));
        }
    }
}
