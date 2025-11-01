package com.TimeSpaceWarrior.TemporalLightMod.Compatability.baubles;

import baubles.common.items.ItemRing;
import com.TimeSpaceWarrior.TemporalLightMod.ItemRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class BBItemRegistry {
    public static void Register(){
        initializeItems();
        registerItems();
    }
    public static String modid = TemporalLightMod.MODID+":";
public static Item KITSUNERING;

    private static void initializeItems() {
        KITSUNERING = new KitsuneRing().setUnlocalizedName("kitsunering").setTextureName(modid+"kitsune_ring").setCreativeTab(TemporalLightMod.TemporalLightTools);
    }

    private static void registerItems() {
        GameRegistry.registerItem(KITSUNERING,KITSUNERING.getUnlocalizedName());
    }
}
