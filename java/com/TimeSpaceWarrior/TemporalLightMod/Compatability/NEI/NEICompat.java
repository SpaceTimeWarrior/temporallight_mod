package com.TimeSpaceWarrior.TemporalLightMod.Compatability.NEI;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import java.util.List;

public class NEICompat{

    public static void loadConfig(){
        API.registerRecipeHandler(new NEIHypersteelAssemblerHandler());
        API.registerUsageHandler(new NEIHypersteelAssemblerHandler());

    }


}
