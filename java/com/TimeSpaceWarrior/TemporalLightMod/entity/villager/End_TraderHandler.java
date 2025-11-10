package com.TimeSpaceWarrior.TemporalLightMod.entity.villager;

import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

import java.util.Random;

public class End_TraderHandler implements VillagerRegistry.IVillageTradeHandler {
    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
        if (villager.getProfession() != TLConfig.VillagerEndTraderID) return;
        recipeList.add(new MerchantRecipe(
                new ItemStack(Items.emerald, 2),
                new ItemStack(Items.ender_pearl, 1)
        ));
        recipeList.add(new MerchantRecipe(
                new ItemStack(Items.ender_pearl, 4),
                new ItemStack(Items.emerald, 1)
        ));
        recipeList.add(new MerchantRecipe(
                new ItemStack(Items.emerald, 4),
                new ItemStack(Blocks.end_stone, 1)
        ));
        recipeList.add(new MerchantRecipe(
                new ItemStack(Items.emerald, 10),
                new ItemStack(Blocks.ender_chest, 1)
        ));
        recipeList.add(new MerchantRecipe(
                new ItemStack(Blocks.emerald_block, 64),
                new ItemStack(Blocks.dragon_egg, 1)
        ));
        /*
        *
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,1),
                    new ItemStack(TLItemRegistry.HEALING_CROP_SEEDS.get(),2),
                    4,4,0.18f));*/
    }
}
