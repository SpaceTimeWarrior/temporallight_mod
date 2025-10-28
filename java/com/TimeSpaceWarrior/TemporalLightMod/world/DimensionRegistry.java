package com.TimeSpaceWarrior.TemporalLightMod.world;

import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import com.TimeSpaceWarrior.TemporalLightMod.world.dimension.WorldProviderKitsuneForest;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

public class DimensionRegistry {
    public static final int Kitsune_Forests_DIMID = TLConfig.Kitsune_Forests_DIM;
    public static final int Bunny_Hop_Mountains_DIMID = TLConfig.Bunny_Hop_Mountains_DIM;
    public static final int Water_Dragon_Seas_DIMID = TLConfig.Water_Dragon_Seas_DIM;
    public static final Class<WorldProviderKitsuneForest> kitsune = WorldProviderKitsuneForest.class;

    public static void register() {
        LoadDimension();
    }

    private static void LoadDimension() {
        DimensionManager.registerProviderType(Kitsune_Forests_DIMID,kitsune,true);
        DimensionManager.registerDimension(Kitsune_Forests_DIMID,Kitsune_Forests_DIMID);
    }
}
