package com.TimeSpaceWarrior.TemporalLightMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockCactus;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.HYPERSTEEL_ASSEMBLER_unpowered),"XXX", "X#X", "XRX",'#',BlockRegistry.MAGIWOODPLANK,'R',Blocks.unpowered_comparator,'X',ItemRegistry.HYPERSTEELINGOT);
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
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODLEGGINGS),"SSS","LSL","LSL",'S',BlockRegistry.MAGIWOODPLANK,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.HYPERSTEELBOOTS),"S S","W W",'S',ItemRegistry.HYPERSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.LIGHTSTEELBOOTS),"S S","W W",'S',ItemRegistry.LIGHTSTEELINGOT,'W',Blocks.wool);
        GameRegistry.addRecipe(new ItemStack(ItemRegistry.MAGIWOODBOOTS),"S S","W W",'S',BlockRegistry.MAGIWOODPLANK,'W',Blocks.wool);

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.KITSUNEPORTAL,8)," P ","WPW","WWW",'P',BlockRegistry.MAGIWOODPLANK,'W',Blocks.log);

    }
}
