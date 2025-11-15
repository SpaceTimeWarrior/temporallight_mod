package com.TimeSpaceWarrior.TemporalLightMod.Compatability.witchery;

import com.emoniph.witchery.util.Config;

public class Villager_SelectorWITCH {
    public static String check_Profession(int profession){
        if(Config.instance().apothecaryID==profession){
            return "textures/entity/phoenixf/compatabilities/witchery/apothecary.png";
        }
        return null;
    }
}
