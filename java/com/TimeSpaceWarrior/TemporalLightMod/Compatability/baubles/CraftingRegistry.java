package com.TimeSpaceWarrior.TemporalLightMod.Compatability.baubles;

import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingRegistry {
    public static void register() {
        GameRegistry.addRecipe(new ItemStack(BBItemRegistry.KITSUNERING)," C ","K K","RKR",'C', ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE,'K',ItemRegistry.KITSUNE_TAIL,'R', Items.redstone);
    }
}
