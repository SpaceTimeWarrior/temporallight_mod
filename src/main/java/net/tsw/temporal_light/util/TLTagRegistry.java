package net.tsw.temporal_light.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.tsw.temporal_light.Temporal_Light;

public class TLTagRegistry {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_HYPERSTEEL_TOOL = tag("needs_hypersteel_tool");
        public static final TagKey<Block> INCORRECT_FOR_HYPERSTEEL_TOOL = tag("incorrect_for_hypersteel_tool");
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(Temporal_Light.MOD_ID,name));
        }
    }
    public static class Items {
        //public static final TagKey<Item> TEST = tag("test_item");
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(Temporal_Light.MOD_ID,name));
        }
    }
}
