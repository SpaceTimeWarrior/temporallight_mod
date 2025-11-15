package com.TimeSpaceWarrior.TemporalLightMod.Compatability.ex_nihilo;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NihiloRegistry {
    public static void register(){
        initItems();
        RegisterItems();
    }
    public static Item HYPERSTEEL_HAMMER_nihilo;
    public static Item LIGHTSTEEL_HAMMER_nihilo;
    public static Item MAGIWOOD_HAMMER_nihilo;
    public static Item HYPERSTEEL_CROOK_nihilo;
    public static Item LIGHTSTEEL_CROOK_nihilo;
    public static Item MAGIWOOD_CROOK_nihilo;

    public static void initItems(){
        HYPERSTEEL_HAMMER_nihilo = new NihiloHammers(ItemRegistry.HYPERSTEELTOOL).setTextureName(TemporalLightMod.MODID+":hypersteelhammer").setUnlocalizedName("hypersteel hammer nihilo").setCreativeTab(TemporalLightMod.TemporalLightTools);
        LIGHTSTEEL_HAMMER_nihilo = new NihiloHammers(ItemRegistry.LIGHTSTEELTOOL).setTextureName(TemporalLightMod.MODID+":lightsteelhammer").setUnlocalizedName("lightsteel hammer nihilo").setCreativeTab(TemporalLightMod.TemporalLightTools);
        MAGIWOOD_HAMMER_nihilo = new NihiloHammers(ItemRegistry.MAGIWOODTOOL).setTextureName(TemporalLightMod.MODID+":magiwoodhammer").setUnlocalizedName("magiwood hammer nihilo").setCreativeTab(TemporalLightMod.TemporalLightTools);
        HYPERSTEEL_CROOK_nihilo = new NihiloCrooks(ItemRegistry.HYPERSTEELTOOL,"hypersteelcrook") .setUnlocalizedName("hypersteel crook nihilo").setCreativeTab(TemporalLightMod.TemporalLightTools);
        LIGHTSTEEL_CROOK_nihilo = new NihiloCrooks(ItemRegistry.LIGHTSTEELTOOL, "lightsteelcrook").setUnlocalizedName("lightsteel crook nihilo").setCreativeTab(TemporalLightMod.TemporalLightTools);
        MAGIWOOD_CROOK_nihilo = new NihiloCrooks(ItemRegistry.MAGIWOODTOOL, "magiwoodcrook")      .setUnlocalizedName("magiwood crook nihilo").setCreativeTab(TemporalLightMod.TemporalLightTools);

    }
    public static void RegisterItems(){
        GameRegistry.registerItem(HYPERSTEEL_HAMMER_nihilo, HYPERSTEEL_HAMMER_nihilo.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEEL_HAMMER_nihilo, LIGHTSTEEL_HAMMER_nihilo.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOOD_HAMMER_nihilo, MAGIWOOD_HAMMER_nihilo.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEEL_CROOK_nihilo, HYPERSTEEL_CROOK_nihilo.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEEL_CROOK_nihilo, LIGHTSTEEL_CROOK_nihilo.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOOD_CROOK_nihilo, MAGIWOOD_CROOK_nihilo.getUnlocalizedName());

        GameRegistry.addRecipe(new ItemStack(HYPERSTEEL_HAMMER_nihilo)," H "," IH","I  ",'H',ItemRegistry.HYPERSTEELINGOT,'I', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(LIGHTSTEEL_HAMMER_nihilo)," L "," IL","I  ",'L',ItemRegistry.HYPERSTEELINGOT,'I', Items.iron_ingot);
        GameRegistry.addRecipe(new ItemStack(MAGIWOOD_HAMMER_nihilo)," M "," IM","ILL",'M', BlockRegistry.MAGIWOODPLANK,'I', Items.iron_ingot,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(MAGIWOOD_HAMMER_nihilo)," M "," IM","IWW",'M', BlockRegistry.MAGIWOODPLANK,'I', Items.iron_ingot,'W', Blocks.wool);

        GameRegistry.addRecipe(new ItemStack(HYPERSTEEL_CROOK_nihilo),"HH "," H "," H ",'H',ItemRegistry.HYPERSTEELINGOT);
        GameRegistry.addRecipe(new ItemStack(LIGHTSTEEL_CROOK_nihilo),"LL "," L "," L ",'L',ItemRegistry.LIGHTSTEELINGOT);
        GameRegistry.addRecipe(new ItemStack(MAGIWOOD_CROOK_nihilo),"MM "," M ","LML",'M',BlockRegistry.MAGIWOODPLANK,'L',Items.leather);
        GameRegistry.addRecipe(new ItemStack(MAGIWOOD_CROOK_nihilo),"MM "," M ","WMW",'M',BlockRegistry.MAGIWOODPLANK,'W',Blocks.wool);
    }
}
