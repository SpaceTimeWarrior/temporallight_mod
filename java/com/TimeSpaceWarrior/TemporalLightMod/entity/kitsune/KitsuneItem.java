package com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
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

    public String toString() {
        if(num<0||num>=TemporalLightMod.KitsuneRandomTame.size()){
            return "invalid kitsune Item";
        }
        Item itm = TemporalLightMod.KitsuneRandomTame.get(num);
        return "Name:"+item.getUnlocalizedName()+" tied to "+itm.getUnlocalizedName();
    }
}
