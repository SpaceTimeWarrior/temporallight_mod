package com.TimeSpaceWarrior.TemporalLightMod.Compatability.baubles;

import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingRegistry {
    public static void register() {
        GameRegistry.addRecipe(new ItemStack(BBItemRegistry.KITSUNERING)," C ","K K","RKR",'C', ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE,'K',ItemRegistry.KITSUNE_TAIL,'R', Items.redstone);
        GameRegistry.addRecipe(new ItemStack(BBItemRegistry.KITSUNEBELTBUCKLE),"I I","EIE"," I ",'I',Items.iron_ingot,'E',Items.emerald);
        GameRegistry.addRecipe(new ItemStack(BBItemRegistry.KITSUNEBELT),"KKK","KBK",'K',ItemRegistry.KITSUNE_TAIL,'B',BBItemRegistry.KITSUNEBELTBUCKLE);
    }
}
