package com.TimeSpaceWarrior.TemporalLightMod.Items;

import net.minecraft.item.Item;

public class ItemCrystal extends Item {
    public int element;
    public ItemCrystal(String element) {
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
    }
}
