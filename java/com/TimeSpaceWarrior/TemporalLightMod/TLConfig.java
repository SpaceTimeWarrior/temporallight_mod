package com.TimeSpaceWarrior.TemporalLightMod;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

import java.util.Collection;

public class TLConfig {
    public static int harvest_lv_floor = 5;
    public static int Kitsune_Forests_DIM = 12;
    public static boolean Kitsune_Forests_DIM_Override = false;
    public static int Water_Dragon_Seas_DIM = 13;
    public static boolean Water_Dragon_Seas_DIM_Override = false;
    public static int Bunny_Hop_Mountains_DIM = 14;
    public static boolean Bunny_Hop_Mountains_DIM_Override = false;
    public static int BiomeMagiwood_forest_Overworld_ID=201;
    public static int BiomeMagiwood_forest_ID=202;
    public static boolean addBBCompatability=true;
    public static boolean addTFCompatability=true;
    public static boolean addChococraftcompatability=true;
    public static boolean addPinkChocoboSpawntoMagiwoodForest=true;
    //public static boolean canPhoenixesBreedwithChocobos=true;
    public static boolean addFoodExpansionCompatability=true;
    public static int VillagerEndTraderID = 10;

    public static boolean addSkyblockRecipes=false;
    public static boolean addPixelmonCompatability = true;
    public static boolean addEx_NihiloCompatability = true;
    public static boolean ShowKitsuneConfigonPostinit = false;
    public static boolean ShowBiomeArrayInformation = false;
    //public static boolean ShowDimensionIDs = false;
    public static boolean ShowCustomVillagerIDArray = false;
    static Configuration config;
    public static int postpreinit = -1;

    public static void Load_Config(FMLPreInitializationEvent event){
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        harvest_lv_floor = config.get(Configuration.CATEGORY_GENERAL,"Lowest Harvest Level",5,"Lowest Harvest Level:sets the low bound on this mods tool harvest level",0,999999).getInt();
        Kitsune_Forests_DIM = config.get(Configuration.CATEGORY_GENERAL,"Kitsune Forests Dimension ID",12,"Kitsune Forests Dimension ID:sets the dimension Id for the kitsune forest").getInt();
        Kitsune_Forests_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"Kitsune Forests Dimension ID Manual Override",false,"Kitsune Forests Dimension ID Manual Override:wether it checks if this dimension has already been taken").getBoolean();
        Water_Dragon_Seas_DIM = config.get(Configuration.CATEGORY_GENERAL,"Water Dragon Seas Dimension ID",13,"Water Dragon Seas Dimension ID:").getInt();
        Water_Dragon_Seas_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"Water Dragon Seas Dimension ID Manual Override",false,"Water Dragon Seas Dimension ID Manual Override:").getBoolean();
        Bunny_Hop_Mountains_DIM = config.get(Configuration.CATEGORY_GENERAL,"bunny hop mountains Dimension ID",14,"bunny hop mountains Dimension ID").getInt();
        Bunny_Hop_Mountains_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"bunny hop mountains Dimension ID Manual Override",false,"bunny hop mountains Dimension ID Manual Override").getBoolean();
        BiomeMagiwood_forest_Overworld_ID = config.get(Configuration.CATEGORY_GENERAL,"biomeMagiwoodOVForestID",201,"biomeMagiwoodOVForestID: the biome id for the overworld Magiwood forest biome").getInt();
        BiomeMagiwood_forest_ID = config.get(Configuration.CATEGORY_GENERAL,"biomeMagiwoodForestID",202,"biomeMagiwoodForestID: the Biome ID for the Kitsune forest dimension biome").getInt();
        VillagerEndTraderID = config.get(Configuration.CATEGORY_GENERAL,"villager end trader ID",10,"villager end trader ID:the Villager Id for the end trader profession").getInt();
        addSkyblockRecipes = config.get(Configuration.CATEGORY_GENERAL,"Add Skyblock Recipes",false,"Add Skyblock Recipes:wether to add recipes for modded skyblock to get a start on this mod").getBoolean();
        addBBCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Baubles Compatability",true,"Add Baubles Compatability:wether to add content using the mod baubles(does nothing without it)").getBoolean();
        addTFCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Twilight Forest Compatability",true,"Add Twilight Forest Compatability:wether to add food compatabilities to kitsune from twilight forest").getBoolean();
        addChococraftcompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Chococraft Compatability",true,"Add Chococraft Compatability:wether to add content compatability to chococraft").getBoolean();
        addPinkChocoboSpawntoMagiwoodForest = config.get(Configuration.CATEGORY_GENERAL,"Add pink chocobo spawn to Magiwood Forest",true,"Add pink chocobo spawn to Magiwood Forest:self explanitory").getBoolean();
        //canPhoenixesBreedwithChocobos = config.get(Configuration.CATEGORY_GENERAL,"can phoenixes breed with chocobos",true).getBoolean();
        addPixelmonCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Pixelmon Compatability",true,"Add Pixelmon Compatability:adds some items for added compatability").getBoolean();
        addFoodExpansionCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Food Expansion Compatability",true,"Add Food Expansion Compatability: adds compatability to the kitsune mob to use foods from this mod").getBoolean();
        addEx_NihiloCompatability=config.get(Configuration.CATEGORY_GENERAL,"Add Ex Nihilo compatability",true,"Add Ex Nihilo compatability: adds hammers of the mod's materials").getBoolean();
        ShowKitsuneConfigonPostinit=config.get(Configuration.CATEGORY_GENERAL,"Show Kitsune food items",false,"Show Kitsune food items:weather to show what foods are tied to each item pool for the kitsune in post init").getBoolean();
        ShowBiomeArrayInformation=config.get(Configuration.CATEGORY_GENERAL,"Show Biome Data",false,"Show Biome Data: Weather to show the Biome Dictionary array on post init").getBoolean();
        //ShowDimensionIDs=config.get(Configuration.CATEGORY_GENERAL,"Show Dimension ID Data",false,"Show Dimension ID Data: Weather to show which Dimensions are used on post init").getBoolean();
        ShowCustomVillagerIDArray=config.get(Configuration.CATEGORY_GENERAL,"Show custom Villager ID Array",false,"Show custom Villager ID Array: Weather to show the villager ID array").getBoolean();

        if(!Kitsune_Forests_DIM_Override) {
            if (isDimIDUsed(Kitsune_Forests_DIM)) {
                Kitsune_Forests_DIM = findUnusedDimID(Kitsune_Forests_DIM);
                if (Kitsune_Forests_DIM == 0) {
                    Kitsune_Forests_DIM = findUnusedDimID();
                }
                System.out.println("[TemporalLightMod] Allocated dimension ID: " + Kitsune_Forests_DIM);
            }
        }
        if(!Water_Dragon_Seas_DIM_Override) {
            if (isDimIDUsed(Water_Dragon_Seas_DIM)) {
                Water_Dragon_Seas_DIM = findUnusedDimID(Water_Dragon_Seas_DIM,new int[]{Kitsune_Forests_DIM});
                if (Water_Dragon_Seas_DIM == 0) {
                    Water_Dragon_Seas_DIM = findUnusedDimIDignore(new int[]{Kitsune_Forests_DIM});
                }
                System.out.println("[TemporalLightMod] Allocated dimension ID: " + Water_Dragon_Seas_DIM);
            }
        }
        if(!Bunny_Hop_Mountains_DIM_Override) {
            if (isDimIDUsed(Bunny_Hop_Mountains_DIM)) {
                Bunny_Hop_Mountains_DIM = findUnusedDimID(Bunny_Hop_Mountains_DIM,new int[]{Kitsune_Forests_DIM,Water_Dragon_Seas_DIM});
                if (Bunny_Hop_Mountains_DIM == 0) {
                    Bunny_Hop_Mountains_DIM = findUnusedDimIDignore(new int[]{Kitsune_Forests_DIM,Water_Dragon_Seas_DIM});
                }
                System.out.println("[TemporalLightMod] Allocated dimension ID: " + Bunny_Hop_Mountains_DIM);
            }
        }
        if(harvest_lv_floor<0){
            harvest_lv_floor = 0;
        }
        postpreinit = 1;
        config.save();
    }
    public static void Load_Config2(ConfigChangedEvent.OnConfigChangedEvent event){
        if(postpreinit==1) {
            config.load();
            harvest_lv_floor = config.get(Configuration.CATEGORY_GENERAL,"Lowest Harvest Level",5,"Lowest Harvest Level:sets the low bound on this mods tool harvest level",0,999999).getInt();
            Kitsune_Forests_DIM = config.get(Configuration.CATEGORY_GENERAL,"Kitsune Forests Dimension ID",12,"Kitsune Forests Dimension ID:sets the dimension Id for the kitsune forest").getInt();
            Kitsune_Forests_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"Kitsune Forests Dimension ID Manual Override",false,"Kitsune Forests Dimension ID Manual Override:wether it checks if this dimension has already been taken").getBoolean();
            Water_Dragon_Seas_DIM = config.get(Configuration.CATEGORY_GENERAL,"Water Dragon Seas Dimension ID",13,"Water Dragon Seas Dimension ID:").getInt();
            Water_Dragon_Seas_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"Water Dragon Seas Dimension ID Manual Override",false,"Water Dragon Seas Dimension ID Manual Override:").getBoolean();
            Bunny_Hop_Mountains_DIM = config.get(Configuration.CATEGORY_GENERAL,"bunny hop mountains Dimension ID",14,"bunny hop mountains Dimension ID").getInt();
            Bunny_Hop_Mountains_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"bunny hop mountains Dimension ID Manual Override",false,"bunny hop mountains Dimension ID Manual Override").getBoolean();
            BiomeMagiwood_forest_Overworld_ID = config.get(Configuration.CATEGORY_GENERAL,"biomeMagiwoodOVForestID",201,"biomeMagiwoodOVForestID: the biome id for the overworld Magiwood forest biome").getInt();
            BiomeMagiwood_forest_ID = config.get(Configuration.CATEGORY_GENERAL,"biomeMagiwoodForestID",202,"biomeMagiwoodForestID: the Biome ID for the Kitsune forest dimension biome").getInt();
            VillagerEndTraderID = config.get(Configuration.CATEGORY_GENERAL,"villager end trader ID",10,"villager end trader ID:the Villager Id for the end trader profession").getInt();
            addSkyblockRecipes = config.get(Configuration.CATEGORY_GENERAL,"Add Skyblock Recipes",false,"Add Skyblock Recipes:wether to add recipes for modded skyblock to get a start on this mod").getBoolean();
            addBBCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Baubles Compatability",true,"Add Baubles Compatability:wether to add content using the mod baubles(does nothing without it)").getBoolean();
            addTFCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Twilight Forest Compatability",true,"Add Twilight Forest Compatability:wether to add food compatabilities to kitsune from twilight forest").getBoolean();
            addChococraftcompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Chococraft Compatability",true,"Add Chococraft Compatability:wether to add content compatability to chococraft").getBoolean();
            addPinkChocoboSpawntoMagiwoodForest = config.get(Configuration.CATEGORY_GENERAL,"Add pink chocobo spawn to Magiwood Forest",true,"Add pink chocobo spawn to Magiwood Forest:self explanitory").getBoolean();
            addPixelmonCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Pixelmon Compatability",true,"Add Pixelmon Compatability:adds some items for added compatability").getBoolean();
            //canPhoenixesBreedwithChocobos = config.get(Configuration.CATEGORY_GENERAL,"can phoenixes breed with chocobos",true).getBoolean();

            addFoodExpansionCompatability = config.get(Configuration.CATEGORY_GENERAL,"Add Food Expansion Compatability",true,"Add Food Expansion Compatability: adds compatability to the kitsune mob to use foods from this mod").getBoolean();
            addEx_NihiloCompatability=config.get(Configuration.CATEGORY_GENERAL,"Add Ex Nihilo compatability",true,"Add Ex Nihilo compatability: adds hammers of the mod's materials").getBoolean();
            ShowKitsuneConfigonPostinit=config.get(Configuration.CATEGORY_GENERAL,"Show Kitsune food items",false,"Show Kitsune food items:weather to show what foods are tied to each item pool for the kitsune in post init").getBoolean();
            ShowBiomeArrayInformation=config.get(Configuration.CATEGORY_GENERAL,"Show Biome Data",false,"Show Biome Data: Weather to show the Biome Dictionary array on post init").getBoolean();
            //ShowDimensionIDs=config.get(Configuration.CATEGORY_GENERAL,"Show Dimension ID Data",false,"Show Dimension ID Data: Weather to show which Dimensions are used on post init").getBoolean();
            ShowCustomVillagerIDArray=config.get(Configuration.CATEGORY_GENERAL,"Show custom Villager ID Array",false,"Show custom Villager ID Array: Weather to show the villager ID array").getBoolean();

            if (!Kitsune_Forests_DIM_Override) {
                if (isDimIDUsed(Kitsune_Forests_DIM)) {
                    Kitsune_Forests_DIM = findUnusedDimID(Kitsune_Forests_DIM);
                    if (Kitsune_Forests_DIM == 0) {
                        Kitsune_Forests_DIM = findUnusedDimID();
                    }
                    System.out.println("[TemporalLightMod] Allocated dimension ID: " + Kitsune_Forests_DIM);
                }
            }
            if (!Water_Dragon_Seas_DIM_Override) {
                if (isDimIDUsed(Water_Dragon_Seas_DIM)) {
                    Water_Dragon_Seas_DIM = findUnusedDimID(Water_Dragon_Seas_DIM, new int[]{Kitsune_Forests_DIM});
                    if (Water_Dragon_Seas_DIM == 0) {
                        Water_Dragon_Seas_DIM = findUnusedDimIDignore(new int[]{Kitsune_Forests_DIM});
                    }
                    System.out.println("[TemporalLightMod] Allocated dimension ID: " + Water_Dragon_Seas_DIM);
                }
            }
            if (!Bunny_Hop_Mountains_DIM_Override) {
                if (isDimIDUsed(Bunny_Hop_Mountains_DIM)) {
                    Bunny_Hop_Mountains_DIM = findUnusedDimID(Bunny_Hop_Mountains_DIM, new int[]{Kitsune_Forests_DIM, Water_Dragon_Seas_DIM});
                    if (Bunny_Hop_Mountains_DIM == 0) {
                        Bunny_Hop_Mountains_DIM = findUnusedDimIDignore(new int[]{Kitsune_Forests_DIM, Water_Dragon_Seas_DIM});
                    }
                    System.out.println("[TemporalLightMod] Allocated dimension ID: " + Bunny_Hop_Mountains_DIM);
                }
            }
            if (harvest_lv_floor < 0) {
                harvest_lv_floor = 0;
            }
            if(TLConfig.ShowKitsuneConfigonPostinit) {
                System.out.println("===KITSUNE SYSTEM===");
                System.out.println("Random Preference Items Raw");
                System.out.println(TemporalLightMod.KitsuneRandomTame);
                System.out.println("Random Preference Items Cooked");
                System.out.println(TemporalLightMod.KitsuneAltRandomTame);
                System.out.println("Guaranteed tame but causes negitive effects");
                System.out.println(TemporalLightMod.KitsuneBadGut);
                System.out.println("Guaranteed tame");
                System.out.println(TemporalLightMod.KitsuneGut);
                System.out.println("===KITSUNE SYSTEM===");
            }
            if(TLConfig.ShowBiomeArrayInformation) {
                System.out.println("===Biome check===");
                BiomeGenBase[] biomes = BiomeGenBase.getBiomeGenArray();
                for (int id = 0; id < biomes.length; id++) {
                    if (!BiomeDictionary.isBiomeRegistered(id)) {
                        System.out.println("Biome open on id " + id);
                    } else {
                        System.out.println("Biome is used on id:" + id + ":" + biomes[id].biomeName);
                    }
                }
                System.out.println("===Biome check===");
            }
            if(TLConfig.ShowCustomVillagerIDArray) {
                Collection<Integer> Villager = VillagerRegistry.getRegisteredVillagers();
                System.out.println("===Villager Professions===");
                System.out.println(Villager);
                System.out.println("===Villager Professions===");
            }
        }
    }
    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(TemporalLightMod.MODID)) {
            Load_Config2(event);
        }

    }
    public static boolean isDimIDUsed(int dim){
        Integer[] dim_array = DimensionManager.getStaticDimensionIDs();
        for(int i=0;i<dim_array.length;i++){
            if(dim_array[i]==dim){
                return true;
            }
        }
        return false;
    }
    public static int findUnusedDimID(){//returns the first unused ID returns 0 if none exist
        Integer[] dim_array = DimensionManager.getStaticDimensionIDs();
        for(int dim = -99999;dim<99999;dim++){
            if(!isDimIDUsed(dim)){
                return dim;
            }
        }
        return 0;
    }
    public static int findUnusedDimID(int target){//returns the first unused ID within 50 of the target ID. returns 0 if none exist
        Integer[] dim_array = DimensionManager.getStaticDimensionIDs();
        for(int dim = target;dim<target+25;dim++){
            if(!isDimIDUsed(dim)){
                return dim;
            }
        }
        for(int dim = target-1;dim<target-25;dim--){
            if(!isDimIDUsed(dim)){
                return dim;
            }
        }
        return 0;
    }
    public static boolean findblacklist(int target,int[]ignore){
        for(int i=0;i<ignore.length;i++){
            if(target == ignore[i]){
                return true;
            }
        }
        return false;
    }
    public static int findUnusedDimID(int target,int[] ignore){//returns the first unused ID within 50 of the target ID. returns 0 if none exist
        Integer[] dim_array = DimensionManager.getStaticDimensionIDs();
        for(int dim = target;dim<target+25;dim++){
            if(!findblacklist(dim,ignore)){
               if(!isDimIDUsed(dim)){
                  return dim;
               }
            }
        }
        for(int dim = target-1;dim<target-25;dim--){
            if(!findblacklist(dim,ignore)){
                if(!isDimIDUsed(dim)){
                    return dim;
                }
            }
        }
        return 0;
    }
    public static int findUnusedDimIDignore(int[] ignore){//returns the first unused ID returns 0 if none exist
        Integer[] dim_array = DimensionManager.getStaticDimensionIDs();
        for(int dim = -999;dim<999;dim++){
            if (!findblacklist(dim,ignore)) {
                if (!isDimIDUsed(dim)) {
                    return dim;
                }
            }
        }
        return 0;
    }
}
