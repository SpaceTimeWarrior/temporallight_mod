package com.TimeSpaceWarrior.TemporalLightMod.Compatability.Tconstruct;

public class Villager_checkerTC {

    public static String Check_profession(int profession){
        if(profession == 78943){
            return "textures/entity/phoenixf/compatabilities/Tconstruct/toolsmith.png";
        }
        return null;
    }
}
