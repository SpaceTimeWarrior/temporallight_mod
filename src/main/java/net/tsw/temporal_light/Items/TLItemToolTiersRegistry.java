package net.tsw.temporal_light.Items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.tsw.temporal_light.util.TLTagRegistry;


public class TLItemToolTiersRegistry {
        public static final Tier HYPERSTEEL = new ForgeTier(4096,12F,8F,30, TLTagRegistry.Blocks.NEEDS_HYPERSTEEL_TOOL,()-> Ingredient.of(TLItemRegistry.HYPERSTEELINGOT.get()), TLTagRegistry.Blocks.INCORRECT_FOR_HYPERSTEEL_TOOL);
}
