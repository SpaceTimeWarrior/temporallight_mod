package com.TimeSpaceWarrior.TemporalLightMod.Items;

import net.minecraft.item.Item;

public class ItemCrystal extends Item {
    public int element;
    public int RF;
    public ItemCrystal(String element) {
        if(element.equals("fire")){
            this.element = 1;
            this.RF=0;
        }else if(element.equals("electric")){
            this.element = 2;
            this.RF=3000;
        }else if(element.equals("life")){
            this.element = 3;
            this.RF=0;
        }else if(element.equals("earth")){
            this.element = 4;
            this.RF=0;
        }else{
            this.element = -1;
            this.RF=0;
        }
    }
    public ItemCrystal(String element,int RF) {
        if(element.equals("fire")){
            this.element = 1;
        }else if(element.equals("electric")){
            this.element = 2;
        }else if(element.equals("life")){
            this.element = 3;
        }else if(element.equals("earth")){
            this.element = 4;
        }else{
            this.element = -1;
        }
        this.RF = RF;
    }

}
