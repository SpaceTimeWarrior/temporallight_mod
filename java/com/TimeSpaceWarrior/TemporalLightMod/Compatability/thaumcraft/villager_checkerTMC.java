package com.TimeSpaceWarrior.TemporalLightMod.Compatability.thaumcraft;

import thaumcraft.common.config.ConfigEntities;

public class villager_checkerTMC {
    public static String Check_profession(int profession){
        if(ConfigEntities.entWizardId==profession){
            return "textures/entity/phoenixf/compatabilities/thaumcraft/wizard.png";
        }else if(ConfigEntities.entBankerId==profession){
            return "textures/entity/phoenixf/compatabilities/thaumcraft/moneychanger.png";
        }
        return null;
    }
}
