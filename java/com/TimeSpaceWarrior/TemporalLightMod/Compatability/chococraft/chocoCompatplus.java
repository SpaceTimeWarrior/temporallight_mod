package com.TimeSpaceWarrior.TemporalLightMod.Compatability.chococraft;

import chococraft.common.registry.ChocoCraftItems;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneItem;

public class chocoCompatplus {
    public static void preinit() {
        TemporalLightMod.KitsuneBadGut.add(ChocoCraftItems.RAW_CHOCOBO_LEG);//chocoboleg Raw
        TemporalLightMod.KitsuneGut.add(ChocoCraftItems.COOKED_CHOCOBO_LEG);//chocoboleg cooked
        TemporalLightMod.KitsuneRandomTame.add(ChocoCraftItems.GYSAHL_CARROT);
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.CAROB_NUT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.CREE_ROOT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.KRAKKA_ROOT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.LASAN_NUT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.LUCHILE_NUT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.MIMETT_FRUIT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.PARAM_NUT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.PASANA_FRUIT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.PIPIO_NUT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.POROV_BEAN,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.REAGAN_VEGGIE,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.SARAHA_BEAN,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.SYLKIS_BUD,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.ZEIO_NUT,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));
        TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(ChocoCraftItems.TANTAL_VEGGIE,TemporalLightMod.getKitsuneRandomTamebyItem(ChocoCraftItems.GYSAHL_CARROT)));



    }
}
