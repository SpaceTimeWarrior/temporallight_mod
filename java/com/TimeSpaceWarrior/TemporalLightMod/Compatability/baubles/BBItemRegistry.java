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
public static Item KITSUNEBELT;
public static Item KITSUNEBELTBUCKLE;

    private static void initializeItems() {
        KITSUNERING = new KitsuneRing().setUnlocalizedName("kitsunering").setTextureName(modid+"kitsune_ring").setCreativeTab(TemporalLightMod.TemporalLightTools);
        KITSUNEBELT = new KitsuneBelt().setUnlocalizedName("Kitsune Belt").setTextureName(modid+"kitsune_belt").setCreativeTab(TemporalLightMod.TemporalLightTools);
        KITSUNEBELTBUCKLE=new Item().setUnlocalizedName("Kitsune Belt Buckle").setTextureName(modid+"kitsune_belt_buckle").setCreativeTab(TemporalLightMod.TemporalLightTools);
    }

    private static void registerItems() {
        GameRegistry.registerItem(KITSUNERING,KITSUNERING.getUnlocalizedName());
        GameRegistry.registerItem(KITSUNEBELT,KITSUNEBELT.getUnlocalizedName());
        GameRegistry.registerItem(KITSUNEBELTBUCKLE,KITSUNEBELTBUCKLE.getUnlocalizedName());
    }
}
