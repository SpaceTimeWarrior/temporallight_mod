package com.TimeSpaceWarrior.TemporalLightMod.Compatability.FoodExpansion;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneItem;
import lellson.foodexpansion.FoodItems;

public class FoodEXCompat {
    public static void preinit() {
        TemporalLightMod.KitsuneRandomTame.add(FoodItems.itemMutton);
        TemporalLightMod.KitsuneRandomTame.add(FoodItems.itemHorseMeat);
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(FoodItems.itemCookedMutton,TemporalLightMod.getKitsuneRandomTamebyItem(FoodItems.itemMutton)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(FoodItems.itemCookedHorseMeat,TemporalLightMod.getKitsuneRandomTamebyItem(FoodItems.itemHorseMeat)));
        TemporalLightMod.KitsuneBadGut.add(FoodItems.itemBacon);
        TemporalLightMod.KitsuneGut.add(FoodItems.itemCookedBacon);
        TemporalLightMod.KitsuneGut.add(FoodItems.itemBaconAndEgg);
        TemporalLightMod.KitsuneGut.add(FoodItems.itemSquid);
        TemporalLightMod.KitsuneGut.add(FoodItems.itemCookedSquid);
    }
}
