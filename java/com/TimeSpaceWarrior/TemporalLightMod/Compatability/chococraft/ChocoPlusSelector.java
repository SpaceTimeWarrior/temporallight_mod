package com.TimeSpaceWarrior.TemporalLightMod.Compatability.chococraft;

import chococraft.common.ModChocoCraft;
import chococraft.common.config.GeneralConfig;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.world.biome.BiomeGenBase;

public class ChocoPlusSelector {

    public static void sendtopreinit() {
        ModContainer mod = Loader.instance().getIndexedModList().get("chococraft");
        if (mod == null) {
            return;
        }
        String version = mod.getVersion();
        if (version.startsWith("4.4.2") || version.startsWith("4.4.3") || version.startsWith("4.4.4") || version.startsWith("4.4.5") || version.startsWith("4.4.6") || version.startsWith("4.4.6") || version.startsWith("4.4.7") || version.startsWith("4.4.8") || version.startsWith("4.4.9") || version.startsWith("4.5") || version.startsWith("4.6") || version.startsWith("4.7") || version.startsWith("4.8") || version.startsWith("4.9")) {
            chocoCompatplus.preinit();
        } else if(version.startsWith("4.2")||version.startsWith("4.3")||version.startsWith("4.4")){
            chocoCompat2.preinit();
        }else{
            chocoCompat.preinit();
        }
    }

    public static String get_stablehand(int profession) {
        ModContainer mod = Loader.instance().getIndexedModList().get("chococraft");
        if (mod == null) {
            return null;
        }
        String version = mod.getVersion();
        if(version.startsWith("4.1")||version.startsWith("4.0")){
            return null;
        }else if(version.startsWith("4.2")){
            if(profession == 19940402){
                return "textures/entity/phoenixf/compatabilities/chococraft/stablehand.png";
            }else{
                return null;
            }
        }else if(version.startsWith("4.3")||version.startsWith("4.4")||version.startsWith("4.5")||version.startsWith("4.6")){
            if(profession == GeneralConfig.stablehandID){
                return "textures/entity/phoenixf/compatabilities/chococraft/stablehand.png";
            }else{
                return null;
            }
        }
        return null;
    }
}
