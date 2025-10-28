package com.TimeSpaceWarrior.TemporalLightMod;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.config.Configuration;

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

    public static void Load_Config(FMLPreInitializationEvent event){
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        harvest_lv_floor = config.get(Configuration.CATEGORY_GENERAL,"Lowest Harvest Level",5).getInt();
        Kitsune_Forests_DIM = config.get(Configuration.CATEGORY_GENERAL,"Kitsune Forests Dimension ID",12).getInt();
        Kitsune_Forests_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"Kitsune Forests Dimension ID Manual Override",false).getBoolean();
        Water_Dragon_Seas_DIM = config.get(Configuration.CATEGORY_GENERAL,"Water Dragon Seas Dimension ID",13).getInt();
        Water_Dragon_Seas_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"Water Dragon Seas Dimension ID Manual Override",false).getBoolean();
        Bunny_Hop_Mountains_DIM = config.get(Configuration.CATEGORY_GENERAL,"bunny hop mountains Dimension ID",14).getInt();
        Bunny_Hop_Mountains_DIM_Override = config.get(Configuration.CATEGORY_GENERAL,"bunny hop mountains Dimension ID Manual Override",false).getBoolean();
        BiomeMagiwood_forest_Overworld_ID = config.get(Configuration.CATEGORY_GENERAL,"biomeMagiwoodOVForestID",201).getInt();
        BiomeMagiwood_forest_ID = config.get(Configuration.CATEGORY_GENERAL,"biomeMagiwoodForestID",202).getInt();

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
        for(int dim = -999;dim<999;dim++){
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
