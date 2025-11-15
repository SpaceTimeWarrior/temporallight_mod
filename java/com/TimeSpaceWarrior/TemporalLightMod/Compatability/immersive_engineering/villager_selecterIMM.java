package com.TimeSpaceWarrior.TemporalLightMod.Compatability.immersive_engineering;


import blusunrize.immersiveengineering.common.Config;

public class villager_selecterIMM {
    public static String Check_profession(int profession){
        if(Config.getInt("villager_engineer")==profession){
            return "textures/entity/phoenixf/compatabilities/immersive engineering/engineer.png";
        }
        return null;
    }
}
