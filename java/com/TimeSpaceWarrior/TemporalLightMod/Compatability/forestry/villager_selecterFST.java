package com.TimeSpaceWarrior.TemporalLightMod.Compatability.forestry;

public class villager_selecterFST {
    public static String check_Profession(int profession) {
        if(80==profession){
            return "textures/entity/phoenixf/compatabilities/forestry/beekeeper.png";
        }else if(81==profession){
            return "textures/entity/phoenixf/compatabilities/forestry/lumberjack.png";
        }
        return null;
    }
}
