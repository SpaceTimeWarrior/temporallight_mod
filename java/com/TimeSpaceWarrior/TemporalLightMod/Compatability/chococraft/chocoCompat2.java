package com.TimeSpaceWarrior.TemporalLightMod.Compatability.chococraft;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneItem;
import net.minecraft.item.Item;

public class chocoCompat2 {

    private static final String ITEMS_CLASS = "chococraft.common.config.ChocoCraftItems";

    public static void preinit() {
        try {
            Class<?> itemsClass = Class.forName(ITEMS_CLASS);


            Item rawLeg = (Item) itemsClass.getField("chocoboLegRawItem").get(null);
            Item cookedLeg = (Item) itemsClass.getField("chocoboLegCookedItem").get(null);

            TemporalLightMod.KitsuneBadGut.add(rawLeg);
            TemporalLightMod.KitsuneGut.add(cookedLeg);


            Item gysahl = (Item) itemsClass.getField("ITEM_VEGGIE_GYSAHL").get(null);
            TemporalLightMod.KitsuneRandomTame.add(gysahl);


            addAltTameItem(itemsClass, "ITEM_NUT_CAROB");
            addAltTameItem(itemsClass, "ITEM_VEGGIE_CREE");
            addAltTameItem(itemsClass, "ITEM_VEGGIE_KRAKKA");
            addAltTameItem(itemsClass, "ITEM_NUT_LASAN");
            addAltTameItem(itemsClass, "ITEM_NUT_LUCHILE");
            addAltTameItem(itemsClass, "ITEM_VEGGIE_MIMETT");
            addAltTameItem(itemsClass, "ITEM_NUT_PARAM");
            addAltTameItem(itemsClass, "ITEM_VEGGIE_PASANA");
            addAltTameItem(itemsClass, "ITEM_NUT_PIPIO");
            addAltTameItem(itemsClass, "ITEM_NUT_POROV");
            addAltTameItem(itemsClass, "ITEM_VEGGIE_REAGAN");
            addAltTameItem(itemsClass, "ITEM_NUT_SARAHA");
            addAltTameItem(itemsClass, "ITEM_VEGGIE_SYLKIS");
            addAltTameItem(itemsClass, "ITEM_NUT_ZEIO");
            addAltTameItem(itemsClass, "ITEM_VEGGIE_TANTAL");

            System.out.println("[TemporalLight] Chococraft compatibility loaded (all versions).");

        } catch (Exception e) {
            System.out.println("[TemporalLight] Chococraft items not found (wrong version or missing).");
            // Safe: No crash, just skip
        }
    }

    private static void addAltTameItem(Class<?> itemsClass, String fieldName) {
        try {
            Item item = (Item) itemsClass.getField(fieldName).get(null);
            int baseChance = TemporalLightMod.getKitsuneRandomTamebyItem(
                    (Item) itemsClass.getField("ITEM_VEGGIE_GYSAHL").get(null)
            );
            TemporalLightMod.KitsuneAltRandomTame.add(new KitsuneItem(item, baseChance));
        } catch (Exception ignored) {

        }
    }
}