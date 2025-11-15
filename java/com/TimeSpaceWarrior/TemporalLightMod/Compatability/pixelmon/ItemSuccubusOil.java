package com.TimeSpaceWarrior.TemporalLightMod.Compatability.pixelmon;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.enums.EnumDecreaseEV;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;
import com.pixelmonmod.pixelmon.items.DecreaseEV;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemSuccubusOil extends DecreaseEV {

    public ItemSuccubusOil() {
        super(EnumDecreaseEV.PomegBerry, "succubus oil_pxl");
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(TemporalLightMod.MODID +":healing_potion");
    }
    public boolean berryEVs(EntityPixelmon entityPixelmon) {
        boolean success = true;
        if (success) {
            entityPixelmon.friendship.berryFriendship();
            if(!entityPixelmon.gender.equals(Gender.None)&&!getGenderlocked(entityPixelmon)){
                System.out.println("Swapping gender from "+entityPixelmon.gender);
                if(entityPixelmon.gender.equals(Gender.Male)){
                    entityPixelmon.gender=Gender.Female;
                }else{
                    entityPixelmon.gender=Gender.Male;
                }
            }else{
                return false;
            }
            System.out.println("Swapping gender pokemon is now "+entityPixelmon.gender);
            entityPixelmon.updateStats();
            System.out.println(entityPixelmon.getName());
            if(entityPixelmon.getName().equals(EnumPokemon.Nidoranmale.name)){
                entityPixelmon.startEvolution(EnumPokemon.Nidoranfemale.name,false);
            }else if(entityPixelmon.getName().equals(EnumPokemon.Nidoranfemale.name)){
                entityPixelmon.startEvolution(EnumPokemon.Nidoranmale.name,false);
            }else if(entityPixelmon.getName().equals(EnumPokemon.Nidorino.name)){
                entityPixelmon.startEvolution(EnumPokemon.Nidorina.name,false);
            }else if(entityPixelmon.getName().equals(EnumPokemon.Nidorina.name)){
                entityPixelmon.startEvolution(EnumPokemon.Nidorino.name,false);
            }else if(entityPixelmon.getName().equals(EnumPokemon.Nidoking.name)){
                entityPixelmon.startEvolution(EnumPokemon.Nidoqueen.name,false);
            }else if(entityPixelmon.getName().equals(EnumPokemon.Nidoqueen.name)){
                entityPixelmon.startEvolution(EnumPokemon.Nidoking.name,false);
            }else if(entityPixelmon.getName().equals(EnumPokemon.Tauros.name)){
                entityPixelmon.startEvolution(EnumPokemon.Miltank.name,false);
            }else if(entityPixelmon.getName().equals(EnumPokemon.Miltank.name)){
                entityPixelmon.startEvolution(EnumPokemon.Tauros.name,false);
            }else if(entityPixelmon.getName().equals("Latias")){
                entityPixelmon.startEvolution("Latios",false);
            }else if(entityPixelmon.getName().equals("Latios")){
                entityPixelmon.startEvolution("Latias",false);
            }else{
                entityPixelmon.startEvolution(entityPixelmon.getName(),false);
            }
        }
        return success;
    }
    public static boolean getGenderlocked(EntityPixelmon pixelmon){//these don't have a male/female counterpart as such it will be treated like a legendary and silently fail
        if(pixelmon.getName().equals(EnumPokemon.Hitmonchan.name)||pixelmon.getName().equals(EnumPokemon.Hitmonlee.name)||pixelmon.getName().equals(EnumPokemon.Hitmontop.name)||pixelmon.getName().equals(EnumPokemon.Tyrogue.name)){return true;}
        if(pixelmon.getName().equals(EnumPokemon.Kangaskhan.name)){return true;}
        if(pixelmon.getName().equals(EnumPokemon.Jynx.name)){return true;}
        if(pixelmon.getName().equals(EnumPokemon.Chansey.name)||pixelmon.getName().equals(EnumPokemon.Blissey.name)){return true;}
        return false;
    }
}
