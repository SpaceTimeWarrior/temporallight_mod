package net.tsw.Temporal_Light.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.util.TLTagRegistry;

import java.util.List;

public class TLItemToolTiersRegistry {
    public static final Tier HYPERSTEEL = TierSortingRegistry.registerTier(new ForgeTier(5, 4096, 12f, 8f, 30,
                    TLTagRegistry.Blocks.NEEDS_HYPERSTEEL_TOOL, () -> Ingredient.of(TLItemRegistry.HYPERSTEELINGOT.get())),
             ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "hypersteel"), List.of(Tiers.NETHERITE), List.of());
    public static final Tier MAGIWOOD = TierSortingRegistry.registerTier(new ForgeTier(5, 8192,2F,8F,40,
                    TLTagRegistry.Blocks.NEEDS_MAGIWOOD_TOOL, () -> Ingredient.of(TLBlocksRegistry.MAGIWOODPLANKS.get())),
            ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "magiwood"), List.of(Tiers.NETHERITE), List.of());
}
