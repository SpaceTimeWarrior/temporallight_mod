package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.blocks.MagiwoodPlanks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class CraftingRegistry {
    public static void register() {
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.HYPERSTEEL_BLOCK),"HHH","HHH","HHH",'H',ItemRegistry.HYPERSTEELINGOT);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.SYNTHETICTIMECRYSTALORE_END),"ECE",'E', Blocks.end_stone,'C',BlockRegistry.SYNTHETICTIMECRYSTALORE);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.REDSTONE_HYPERCOIL),"SWS","SSS","SWS",'S',Blocks.redstone_block,'W',Blocks.planks);
        GameRegistry.addSmelting(BlockRegistry.SYNTHETICTIMECRYSTALORE,new ItemStack(ItemRegistry.SYNTHTIMECRYSTALSHARD),0.1f);
        GameRegistry.addSmelting(BlockRegistry.SYNTHETICTIMECRYSTALORE_END,new ItemStack(ItemRegistry.SYNTHTIMECRYSTALSHARD),0.1f);
        GameRegistry.addSmelting(BlockRegistry.SYNTHETICTIMECRYSTALORE_DEEPSLATE,new ItemStack(ItemRegistry.SYNTHTIMECRYSTALSHARD),0.1f);
        GameRegistry.addSmelting(BlockRegistry.SYNTHETICTIMECRYSTALORE_NETHER,new ItemStack(ItemRegistry.SYNTHTIMECRYSTALSHARD),0.1f);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.SYNTHETICTIMECRYSTALORE),"DDD","MMM","DDD",'D',Blocks.diamond_block,'M',ItemRegistry.DEATHMETAL);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELINGOT),"SSS","SBS","SSS",'S',ItemRegistry.SYNTHTIMECRYSTALSHARD,'B',Blocks.beacon);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.HYPERSTEELINGOT,9),BlockRegistry.HYPERSTEEL_BLOCK);
        GameRegistry.addSmelting(ItemRegistry.DEATHMETAL,new ItemStack(BlockRegistry.UNFIRED_DEATHMETAL_BLOCK),1.0f);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.UNFIRED_DEATHMETAL_BLOCK,8),"ODO","III","ODO",'O',Blocks.obsidian,'I',Blocks.iron_block,'d',Blocks.diamond_block);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.HYPERSTEEL_ASSEMBLER_unpowered),"XXX", "X#X", "XRX",'#',BlockRegistry.MAGIWOODPLANK,'R', ItemRegistry.REDSTONE_HYPERCOIL/*Redstone Comparitor*/,'X',ItemRegistry.HYPERSTEELINGOT);
        TemporalLightMod.assem_recipe.addRecipe(new ItemStack[]{null,new ItemStack(Items.coal),null,new ItemStack(Items.coal),new ItemStack(Blocks.coal_block),new ItemStack(Items.coal),null,new ItemStack(Items.coal),null,new ItemStack(Items.coal),new ItemStack(Items.diamond,1)},5);
        TemporalLightMod.assem_recipe.addRecipe(new ItemStack[]{new ItemStack(Items.quartz),new ItemStack(Blocks.glowstone),new ItemStack(Items.quartz),new ItemStack(Items.gold_ingot),new ItemStack(Blocks.enchanting_table),new ItemStack(Items.gold_ingot),new ItemStack(Items.quartz),new ItemStack(Items.blaze_rod),new ItemStack(Items.quartz),new ItemStack(Items.redstone),new ItemStack(Items.nether_star,2)},8,10);
        TemporalLightMod.assem_recipe.addRecipe(new ItemStack[]{new ItemStack(Items.quartz),new ItemStack(ItemRegistry.REDSTONE_HYPERCOIL),new ItemStack(Items.quartz),new ItemStack(Items.gold_ingot),new ItemStack(Items.emerald),new ItemStack(Items.gold_ingot),new ItemStack(Items.quartz),new ItemStack(Items.iron_ingot),new ItemStack(Items.quartz),new ItemStack(Items.blaze_rod),new ItemStack(ItemRegistry.DEATHMETAL)},5);
        TemporalLightMod.assem_recipe.addRecipe(new ItemStack[]{null,new ItemStack(ItemRegistry.HYPERSTEELINGOT),null,new ItemStack(ItemRegistry.HYPERSTEELINGOT),new ItemStack(BlockRegistry.HYPERSTEEL_BLOCK),new ItemStack(ItemRegistry.HYPERSTEELINGOT),null,new ItemStack(ItemRegistry.HYPERSTEELINGOT),null,new ItemStack(ItemRegistry.DEATHMETAL),new ItemStack(ItemRegistry.LIGHTSTEELINGOT,1)},1,10);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.LIGHTSTEEL_BLOCK),"SSS","SSS","SSS",'S',ItemRegistry.LIGHTSTEELINGOT);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.HYPERSTEEL_STAIRS,4),"S  ","SS ","SSS",'S',BlockRegistry.HYPERSTEEL_BLOCK);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.LIGHTSTEEL_STAIRS,4),"S  ","SS ","SSS",'S',BlockRegistry.LIGHTSTEEL_BLOCK);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.MAGIWOODSTAIRS,4),"S  ","SS ","SSS",'S',BlockRegistry.MAGIWOODPLANK);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.HYPERSTEEL_SLAB,6),"SSS",'S',BlockRegistry.HYPERSTEEL_BLOCK);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.LIGHTSTEEL_SLAB,6),"SSS",'S',BlockRegistry.LIGHTSTEEL_BLOCK);
        GameRegistry.addSmelting(ItemRegistry.KITSUNE_TAIL,new ItemStack(Items.bone,6),0.5f);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELAXE),"XX","X#"," #",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELAXE),"XX","#X","# ",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELHOE),"XX"," #"," #",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELHOE),"XX","# ","# ",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELPICKAXE),"XXX"," # "," # ",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELSHOVEL),"X ","# ","# ",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELSWORD),"X ","X ","# ",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELSHOVEL)," X"," #"," #",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELSWORD)," X"," X"," #",'X',ItemRegistry.HYPERSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.MAGIWOODSLAB,6),"XXX",'X',BlockRegistry.MAGIWOODPLANK);


        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELAXE),"XX","X#"," #",'X',ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELAXE),"XX","#X","# ",'X',ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELHOE),"XX"," #"," #",'X',ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELHOE),"XX","# ","# ",'X',ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELPICKAXE),"XXX"," # "," # ",'X',ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELSHOVEL),"X ","# ","# ",'X',ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELSWORD),"X ","X ","# ",'X', ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELSHOVEL)," X"," #"," #",'X',ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELSWORD)," X"," X"," #",'X', ItemRegistry.LIGHTSTEELINGOT,'#',Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODAXE),"XX","X#","L#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODAXE),"XX","#X","#L",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODHOE),"XX","L#","L#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODHOE),"XX","#L","#L",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODPICKAXE),"XXX"," # ","L#L",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODSHOVEL),"X ","# ","#L",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODCLUB),"X ","X ","#L",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODSHOVEL)," X"," #","L#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODCLUB)," X"," X","L#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'L',Items.leather);

        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODAXE),"XX","X#","W#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODAXE),"XX","#X","#W",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODHOE),"XX","W#","W#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODHOE),"XX","#W","#W",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODPICKAXE),"XXX"," # ","W#W",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODSHOVEL),"X ","# ","#W",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODCLUB),"X ","X ","#W",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODSHOVEL)," X"," #","W#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODCLUB)," X"," X","W#",'X',BlockRegistry.MAGIWOODPLANK,'#',Items.iron_ingot,'W',Blocks.wool);

        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE_FUEL),ItemRegistry.FIRE_CRYSTAL_ESSENCE);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE),ItemRegistry.FIRE_CRYSTAL_ESSENCE_FUEL);

        GameRegistry.addRecipe(new ItemStack(ItemRegistry.FIRE_STAFF),"  C","LWL","W  ",'C',ItemRegistry.FIRE_CRYSTAL_ESSENCE,'L',Items.leather,'W',BlockRegistry.MAGIWOODPLANK);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.FIRE_STAFF),"  C","LWL","W  ",'C',ItemRegistry.FIRE_CRYSTAL_ESSENCE_FUEL,'L',Items.leather,'W',BlockRegistry.MAGIWOODPLANK);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.FIRE_STAFF),"  C","LWL","W  ",'C',ItemRegistry.FIRE_CRYSTAL_ESSENCE,'L',Blocks.wool,'W',BlockRegistry.MAGIWOODPLANK);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.FIRE_STAFF),"  C","LWL","W  ",'C',ItemRegistry.FIRE_CRYSTAL_ESSENCE_FUEL,'L',Blocks.wool,'W',BlockRegistry.MAGIWOODPLANK);

        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTNING_STAFF),"  C","LWL","W  ",'C',ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE,'L',Items.leather,'W',BlockRegistry.MAGIWOODPLANK);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTNING_STAFF),"  C","LWL","W  ",'C',ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE,'L',Blocks.wool,'W',BlockRegistry.MAGIWOODPLANK);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.REDSTONE_STAFF),"RER",'R',ItemRegistry.REDSTONE_HYPERCOIL,'E',ItemRegistry.EARTH_STAFF);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.EARTH_STAFF),"  C","LWL","W  ",'C',ItemRegistry.EARTH_CRYSTAL_ESSENCE,'L',Items.leather,'W',BlockRegistry.MAGIWOODPLANK);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.EARTH_STAFF),"  C","LWL","W  ",'C',ItemRegistry.EARTH_CRYSTAL_ESSENCE,'L',Blocks.wool,'W',BlockRegistry.MAGIWOODPLANK);



        GameRegistry.addSmelting(ItemRegistry.NULL_CRYSTAL_ESSENCE,new ItemStack(ItemRegistry.FIRE_CRYSTAL_ESSENCE),1);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE)," R ","RNR"," R ",'R',ItemRegistry.REDSTONE_HYPERCOIL,'N',ItemRegistry.NULL_CRYSTAL_ESSENCE);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.EARTH_STAFF),"SSS","DND","SSS",'S',Blocks.stone,'D',Items.diamond,'N',ItemRegistry.NULL_CRYSTAL_ESSENCE);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.ENERGYGUN),"III","RCC","I  ",'I',Items.iron_ingot,'R',ItemRegistry.REDSTONE_HYPERCOIL,'C',ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.GOLDENERGYGUN),"GGG","RCC","G  ",'G',Items.gold_ingot,'R',ItemRegistry.REDSTONE_HYPERCOIL,'C',ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.ELEINFUSEDDIAMOND),"CCC","CDC","CCC",'C',ItemRegistry.ELECTRIC_CRYSTAL_ESSENCE,'D',Items.diamond);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.DIAMONDENERGYGUN),"DDD","RCC","D  ",'D',Items.diamond,'R',ItemRegistry.REDSTONE_HYPERCOIL,'C',ItemRegistry.ELEINFUSEDDIAMOND);


        //armor and some tools use leather or wool in their crafting
        //leather
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELHELMET),"SSS","SLS",'S',ItemRegistry.HYPERSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELHELMET),"SSS","SLS",'S',ItemRegistry.LIGHTSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODHELMET),"SSS","SLS",'S',BlockRegistry.MAGIWOODPLANK,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELCHESTPLATE),"SLS","SSS","SSS",'S',ItemRegistry.HYPERSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELCHESTPLATE),"SLS","SSS","SSS",'S',ItemRegistry.LIGHTSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODCHESTPLATE),"SLS","SSS","SSS",'S',BlockRegistry.MAGIWOODPLANK,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELLEGGINGS),"SSS","LSL","LSL",'S',ItemRegistry.HYPERSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELLEGGINGS),"SSS","LSL","LSL",'S',ItemRegistry.LIGHTSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODLEGGINGS),"SSS","LSL","LSL",'S',BlockRegistry.MAGIWOODPLANK,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELBOOTS),"S S","L L",'S',ItemRegistry.HYPERSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELBOOTS),"S S","L L",'S',ItemRegistry.LIGHTSTEELINGOT,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODBOOTS),"S S","L L",'S',BlockRegistry.MAGIWOODPLANK,'L',Items.leather);

        //wool
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELHELMET),"SSS","SWS",'S',ItemRegistry.HYPERSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELHELMET),"SSS","SWS",'S',ItemRegistry.LIGHTSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODHELMET),"SSS","SWS",'S',BlockRegistry.MAGIWOODPLANK,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELCHESTPLATE),"SWS","SSS","SSS",'S',ItemRegistry.HYPERSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELCHESTPLATE),"SWS","SSS","SSS",'S',ItemRegistry.LIGHTSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODCHESTPLATE),"SWS","SSS","SSS",'S',BlockRegistry.MAGIWOODPLANK,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELLEGGINGS),"SSS","WSW","WSW",'S',ItemRegistry.HYPERSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELLEGGINGS),"SSS","WSW","WSW",'S',ItemRegistry.LIGHTSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODLEGGINGS),"SSS","WSW","WSW",'S',BlockRegistry.MAGIWOODPLANK,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELBOOTS),"S S","W W",'S',ItemRegistry.HYPERSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELBOOTS),"S S","W W",'S',ItemRegistry.LIGHTSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODBOOTS),"S S","W W",'S',BlockRegistry.MAGIWOODPLANK,'W',Blocks.wool);

        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.MAGIWOODPLANK,4),BlockRegistry.MAGIWOODLOG);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.MAGIWOODFENCE),"WSW","WSW",'W', BlockRegistry.MAGIWOODPLANK,'S',Items.stick);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.HYPERSTEELFENCE),"WSW","WSW",'W', BlockRegistry.HYPERSTEEL_BLOCK,'S',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.LIGHTSTEELFENCE),"WSW","WSW",'W', BlockRegistry.LIGHTSTEEL_BLOCK,'S',Items.iron_ingot);

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.KITSUNEPORTAL,8)," P ","WPW","WWW",'P',BlockRegistry.MAGIWOODPLANK,'W',Blocks.log);
        GameRegistry.addRecipe(new ItemStack(Items.golden_apple,1,1),"SSS","SAS","SSS",'S',ItemRegistry.SOULFEATHER,'A',Items.golden_apple);




        if(TLConfig.addSkyblockRecipes){
            GameRegistry.addRecipe(new ItemStack(BlockRegistry.MAGIWOODSAPLING),"DOD"," W ","DOD",'D',Blocks.diamond_block,'O',Blocks.obsidian,'W',Blocks.log);
            GameRegistry.addRecipe(new ItemStack(BlockRegistry.SYNTHETICTIMECRYSTALORE_DEEPSLATE,8),"DDD","ODO","WWW",'D',Items.diamond,'O',Blocks.obsidian,'W',Blocks.wool);
        }

    }
}
