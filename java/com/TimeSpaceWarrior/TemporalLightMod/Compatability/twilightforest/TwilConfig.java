package com.TimeSpaceWarrior.TemporalLightMod.Compatability.twilightforest;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneItem;
import twilightforest.item.TFItems;

public class TwilConfig {
    public static void preinit(){
        TemporalLightMod.KitsuneRandomTame.add(TFItems.venisonRaw);
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(TFItems.venisonCooked,TemporalLightMod.getKitsuneRandomTamebyItem(TFItems.venisonRaw)));
        TemporalLightMod.KitsuneBadGut.add(TFItems.meefRaw);
        TemporalLightMod.KitsuneBadGut.add(TFItems.experiment115);
        TemporalLightMod.KitsuneGut.add(TFItems.meefSteak);
        TemporalLightMod.KitsuneGut.add(TFItems.meefStroganoff);
    }
}
