package com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune;

import net.minecraft.item.Item;

public class KitsuneItem {
    public Item item;
    public int num;
    public KitsuneItem(Item itm,int nm){
        item=itm;
        num = nm;
    }
    public boolean equals(Item itm){
        return item.equals(itm);
    }
    public boolean equals(KitsuneItem ktm){
        return item.equals(ktm.getItem()) && num==ktm.num;
    }
    public Item getItem(){
        return item;
    }
    public int getNum(){
        return num;
    }
    public void setItem(Item itm){
        item = itm;
    }
    public void setNum(int nm){
        num = nm;
    }
}
