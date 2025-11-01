package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.blocks.*;
import com.TimeSpaceWarrior.TemporalLightMod.world.dimension.KitsunePBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRegistry {
    public static Block UNFIRED_DEATHMETAL_BLOCK;
    public static Block SYNTHETICTIMECRYSTALORE;
    public static Block SYNTHETICTIMECRYSTALORE_DEEPSLATE;
    public static Block SYNTHETICTIMECRYSTALORE_NETHER;
    public static Block SYNTHETICTIMECRYSTALORE_END;
    public static Block HYPERSTEEL_BLOCK;
    public static Block HYPERSTEEL_STAIRS;
    public static Block HYPERSTEEL_SLAB;
    public static Block HYPERSTEEL_SLAB_D;
    public static Block HYPERSTEEL_ASSEMBLER_unpowered;
    public static Block HYPERSTEEL_ASSEMBLER_powered;
    public static Block LIGHTSTEEL_BLOCK;
    public static Block LIGHTSTEEL_STAIRS;
    public static Block LIGHTSTEEL_SLAB;
    public static Block LIGHTSTEEL_SLAB_D;
    public static Block MAGIWOODLOG;
    public static Block MAGIWOODLEAVES;
    public static Block MAGIWOODSAPLING;
    public static Block MAGIWOODPLANK;
    public static Block MAGIWOODSLAB;
    public static Block MAGIWOODSLAB_D;
    public static Block MAGIWOODSTAIRS;
    public static Block HYPERSTEELFENCE;
    public static Block LIGHTSTEELFENCE;
    public static Block MAGIWOODFENCE;
    public static Block KITSUNEPORTAL;

    public static void setupforRegistry(){
        String modid = TemporalLightMod.MODID+":";
        Material oremat = Material.rock;
        Material woodmat = Material.wood;
        Material portalmat = Material.portal;
        Material steelmat = Material.iron;

        HYPERSTEEL_BLOCK = new HyperSteel_Block(steelmat).setBlockName("hypersteelblock").setCreativeTab(TemporalLightMod.TemporalLightBlocks).setBlockTextureName(modid+"hypersteelblock").setHardness(2.0f);
        HYPERSTEEL_STAIRS = new HyperSteel_Stairs(HYPERSTEEL_BLOCK).setBlockName("hypersteelstairs").setCreativeTab(TemporalLightMod.TemporalLightBlocks).setHardness(2.0f);
        HYPERSTEEL_SLAB = new HyperSteel_Slab(steelmat,false).setBlockName("hypersteelslab").setBlockTextureName(modid+"hypersteelblock").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        HYPERSTEEL_SLAB_D = new HyperSteel_Slab(steelmat,true).setBlockName("hypersteelslab_double").setBlockTextureName(modid+"hypersteelblock");
        HYPERSTEELFENCE = new TL_Fence(modid+"hypersteelblock",steelmat,TLConfig.harvest_lv_floor,0).setBlockName("hypersteelfence").setCreativeTab(TemporalLightMod.TemporalLightBlocks);


        HYPERSTEEL_ASSEMBLER_unpowered = new HyperSteel_Assembler(steelmat,false).setBlockName("hypersteel_assembler_unpowered").setBlockTextureName(modid+"hypersteelassembler_unpowered").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        HYPERSTEEL_ASSEMBLER_powered   = new HyperSteel_Assembler(steelmat,true ).setBlockName("hypersteel_assembler_powered").setBlockTextureName(modid+"hypersteelassembler").setLightLevel((13.0f/15.0f));

        SYNTHETICTIMECRYSTALORE = new Synth_Time_Crystal_ore(oremat).setBlockName("synthetictimecrystalore").setBlockTextureName(modid+"synthetictimecrystalore").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        SYNTHETICTIMECRYSTALORE_DEEPSLATE = new Synth_Time_Crystal_ore(oremat).setBlockName("synthetictimecrystalore_deepslate").setBlockTextureName(modid+"synthetictimecrystalore_deepslate").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        SYNTHETICTIMECRYSTALORE_NETHER = new Synth_Time_Crystal_ore(oremat).setBlockName("synthetictimecrystalore_nether").setBlockTextureName(modid+"synthetictimecrystalore_nether").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        SYNTHETICTIMECRYSTALORE_END = new Synth_Time_Crystal_ore(oremat).setBlockName("synthetictimecrystalore_end").setBlockTextureName(modid+"synthetictimecrystalore_end").setCreativeTab(TemporalLightMod.TemporalLightBlocks);

        UNFIRED_DEATHMETAL_BLOCK = new Unfired_Deathmetal_block(oremat).setBlockName("unfired_deathmetal").setBlockTextureName(modid+"unfired_deathmetal").setCreativeTab(TemporalLightMod.TemporalLightBlocks);

        LIGHTSTEEL_BLOCK = new LightSteel_Block(oremat).setBlockName("lightsteelblock").setBlockTextureName(modid+"lightsteelblock").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        LIGHTSTEEL_STAIRS = new LightSteel_Stairs(LIGHTSTEEL_BLOCK).setBlockName("lightsteelstairs").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        LIGHTSTEEL_SLAB = new LIghtSteel_Slab(oremat,false).setBlockName("lightsteelslab").setBlockTextureName(modid+"lightsteelblock").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        LIGHTSTEEL_SLAB_D = new LIghtSteel_Slab(oremat,true).setBlockName("double_lightsteelslab").setBlockTextureName(modid+"lightsteelblock");
        LIGHTSTEELFENCE = new TL_Fence(modid+"lightsteelblock",oremat,TLConfig.harvest_lv_floor+1,0).setBlockName("lightsteelfence").setCreativeTab(TemporalLightMod.TemporalLightBlocks);


        MAGIWOODLOG = new MagiwoodLog(woodmat).setBlockName("magiwooodlog").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        MAGIWOODLEAVES = new MagiwoodLeaves().setBlockName("magiwooodleaves").setBlockTextureName(modid+"magiwooodleaves").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        MAGIWOODSAPLING = new MagiwoodSapling().setBlockName("magiwooodsapling").setBlockTextureName(modid+"magiwooodsapling").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        MAGIWOODPLANK = new MagiwoodPlanks(woodmat).setBlockName("magiwooodplanks").setBlockTextureName(modid+"magiwoodplanks").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        MAGIWOODSLAB = new Magiwood_Slab(woodmat,false).setBlockName("magiwoodslab").setBlockTextureName(modid+"magiwoodplanks").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        MAGIWOODSLAB_D = new Magiwood_Slab(woodmat,true).setBlockName("double_magiwoodslab").setBlockTextureName(modid+"magiwoodplanks");
        MAGIWOODSTAIRS = new Magiwood_Stairs(MAGIWOODPLANK).setBlockName("magiwoodstairs").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        MAGIWOODFENCE = new TL_Fence(modid+"magiwoodplanks",woodmat,TLConfig.harvest_lv_floor-1,1).setBlockName("magiwoodfence").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
        KITSUNEPORTAL = new KitsunePBlock(portalmat).setBlockName("kitsune_portal").setBlockTextureName("eatherian_sleep_portal").setCreativeTab(TemporalLightMod.TemporalLightBlocks);

    }
    public static void GmRegistry(){
        GameRegistry.registerBlock(HYPERSTEEL_BLOCK,HYPERSTEEL_BLOCK.getUnlocalizedName());
        GameRegistry.registerBlock(HYPERSTEEL_STAIRS,HYPERSTEEL_STAIRS.getUnlocalizedName());
        GameRegistry.registerBlock(HYPERSTEEL_SLAB, HyperSteel_Slab_Item.class,HYPERSTEEL_SLAB.getUnlocalizedName());
        GameRegistry.registerBlock(HYPERSTEEL_SLAB_D, HyperSteel_Slab_Item.class,HYPERSTEEL_SLAB_D.getUnlocalizedName());
        GameRegistry.registerBlock(SYNTHETICTIMECRYSTALORE,SYNTHETICTIMECRYSTALORE.getUnlocalizedName());
        GameRegistry.registerBlock(SYNTHETICTIMECRYSTALORE_DEEPSLATE,SYNTHETICTIMECRYSTALORE_DEEPSLATE.getUnlocalizedName());
        GameRegistry.registerBlock(SYNTHETICTIMECRYSTALORE_NETHER,SYNTHETICTIMECRYSTALORE_NETHER.getUnlocalizedName());
        GameRegistry.registerBlock(SYNTHETICTIMECRYSTALORE_END,SYNTHETICTIMECRYSTALORE_END.getUnlocalizedName());
        GameRegistry.registerBlock(UNFIRED_DEATHMETAL_BLOCK,UNFIRED_DEATHMETAL_BLOCK.getUnlocalizedName());
        GameRegistry.registerBlock(HYPERSTEEL_ASSEMBLER_unpowered,HYPERSTEEL_ASSEMBLER_unpowered.getUnlocalizedName());
        GameRegistry.registerBlock(HYPERSTEEL_ASSEMBLER_powered,HYPERSTEEL_ASSEMBLER_powered.getUnlocalizedName());
        GameRegistry.registerBlock(LIGHTSTEEL_BLOCK,LIGHTSTEEL_BLOCK.getUnlocalizedName());
        GameRegistry.registerBlock(LIGHTSTEEL_STAIRS,LIGHTSTEEL_STAIRS.getUnlocalizedName());
        GameRegistry.registerBlock(LIGHTSTEEL_SLAB,LightSteel_Slab_Item.class,LIGHTSTEEL_SLAB.getUnlocalizedName());
        GameRegistry.registerBlock(LIGHTSTEEL_SLAB_D,LightSteel_Slab_Item.class,LIGHTSTEEL_SLAB_D.getUnlocalizedName());
        GameRegistry.registerBlock(MAGIWOODLOG, MAGIWOODLOG.getLocalizedName());
        GameRegistry.registerBlock(MAGIWOODLEAVES, MAGIWOODLEAVES.getLocalizedName());
        GameRegistry.registerBlock(MAGIWOODSAPLING, MAGIWOODSAPLING.getLocalizedName());
        GameRegistry.registerBlock(MAGIWOODPLANK, MAGIWOODPLANK.getLocalizedName());
        GameRegistry.registerBlock(MAGIWOODSLAB,Magiwood_Slab_Item.class,MAGIWOODSLAB.getUnlocalizedName());
        GameRegistry.registerBlock(MAGIWOODSLAB_D,Magiwood_Slab_Item.class,MAGIWOODSLAB_D.getUnlocalizedName());
        GameRegistry.registerBlock(MAGIWOODSTAIRS,MAGIWOODSTAIRS.getUnlocalizedName());
        GameRegistry.registerBlock(HYPERSTEELFENCE,HYPERSTEELFENCE.getUnlocalizedName());
        GameRegistry.registerBlock(LIGHTSTEELFENCE,LIGHTSTEELFENCE.getUnlocalizedName());
        GameRegistry.registerBlock(MAGIWOODFENCE, MAGIWOODFENCE.getLocalizedName());
        GameRegistry.registerBlock(KITSUNEPORTAL, KITSUNEPORTAL.getLocalizedName());
    }
    public static void preinitRegistry() {
        setupforRegistry();
        GmRegistry();
    }
}
