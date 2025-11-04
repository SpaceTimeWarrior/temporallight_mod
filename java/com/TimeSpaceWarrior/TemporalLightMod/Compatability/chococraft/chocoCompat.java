package com.TimeSpaceWarrior.TemporalLightMod.Compatability.chococraft;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import chococraft.common.config.ChocoCraftItems;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneItem;

public class chocoCompat {
    public static void preinit() {
        TemporalLightMod.KitsuneBadGut.add(ChocoCraftItems.chocoboLegRawItem);
        TemporalLightMod.KitsuneGut.add(ChocoCraftItems.chocoboLegCookedItem);
        TemporalLightMod.KitsuneRandomTame.add(ChocoCraftItems.gysahlPicklesRawItem);
        System.out.println(TemporalLightMod.KitsuneRandomTame);
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.gysahlPicklesItem,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.gysahlPicklesRawItem)));
    }
}
