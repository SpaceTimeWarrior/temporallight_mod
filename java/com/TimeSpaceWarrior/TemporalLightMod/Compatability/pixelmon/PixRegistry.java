package com.TimeSpaceWarrior.TemporalLightMod.Compatability.pixelmon;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.items.ItemHammer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.ChunkEvent;


public class PixRegistry {
    public static void Register(){
        initializeItems();
        registerItems();
    }
    public static Item SUCCUBUS_OIL;
    public static Item HYPERSTEEL_HAMMER_pixel;
    public static Item LIGHTSTEEL_HAMMER_pixel;
    public static Item MAGIWOOD_HAMMER_pixel;
    public static void registerItems() {
        GameRegistry.registerItem(SUCCUBUS_OIL, SUCCUBUS_OIL.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEEL_HAMMER_pixel, HYPERSTEEL_HAMMER_pixel.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEEL_HAMMER_pixel, LIGHTSTEEL_HAMMER_pixel.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOOD_HAMMER_pixel, MAGIWOOD_HAMMER_pixel.getUnlocalizedName());

        GameRegistry.addRecipe(new ItemStack(SUCCUBUS_OIL),"RAR"," G ","KEK",'R',Items.rotten_flesh,'A', Items.apple,'G',Items.glass_bottle,'E',Items.egg,'K', Items.cake);
        GameRegistry.addRecipe(new ItemStack(HYPERSTEEL_HAMMER_pixel),"HHH","HIH"," I ",'H',ItemRegistry.HYPERSTEELINGOT,'I',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(LIGHTSTEEL_HAMMER_pixel),"LLL","LIL"," I ",'L',ItemRegistry.LIGHTSTEELINGOT,'I',Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(MAGIWOOD_HAMMER_pixel),"MMM","MIM","LIL",'M', BlockRegistry.MAGIWOODPLANK,'I',Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(MAGIWOOD_HAMMER_pixel),"MMM","MIM","WIW",'M', BlockRegistry.MAGIWOODPLANK,'I',Items.iron_ingot,'W',Blocks.wool);
    }
    public static void initializeItems(){
        SUCCUBUS_OIL = new ItemSuccubusOil().setTextureName(modid+"healing_potion").setUnlocalizedName("succubus oil_pxl").setCreativeTab(TemporalLightMod.TemporalLightMaterials);
        HYPERSTEEL_HAMMER_pixel = new PixelHammer(ItemRegistry.HYPERSTEELTOOL,TemporalLightMod.MODID,"hypersteelhammer","hypersteel hammer pixel").setCreativeTab(TemporalLightMod.TemporalLightTools);
        LIGHTSTEEL_HAMMER_pixel = new PixelHammer(ItemRegistry.LIGHTSTEELTOOL,TemporalLightMod.MODID,"lightsteelhammer","lightsteel hammer pixel").setCreativeTab(TemporalLightMod.TemporalLightTools);
        MAGIWOOD_HAMMER_pixel = new PixelHammer(ItemRegistry.MAGIWOODTOOL,TemporalLightMod.MODID,"magiwoodhammer","magiwood hammer pixel").setCreativeTab(TemporalLightMod.TemporalLightTools);
    }

    public static String modid = TemporalLightMod.MODID+":";

}
