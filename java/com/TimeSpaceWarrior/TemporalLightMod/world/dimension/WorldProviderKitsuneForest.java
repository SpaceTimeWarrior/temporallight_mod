package com.TimeSpaceWarrior.TemporalLightMod.world.dimension;

import com.TimeSpaceWarrior.TemporalLightMod.BiomeRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.world.DimensionRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.world.biome.BiomeGenMagiwoodForestDIM;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderKitsuneForest extends WorldProvider {
    @Override
    public String getDimensionName() {
        return "Kitsune Forest";
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderKitsuneForest(worldObj,worldObj.getSeed(),true);
    }

    @Override
    protected void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeRegistry.MagiwoodForest,0.5F);
        this.dimensionId = DimensionRegistry.Kitsune_Forests_DIMID;
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public String getSaveFolder() {
        return "DIM-Kitsune Forest";
    }

    @Override
    public float getCloudHeight() {
        return 200;
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to the world of kitsunes";
    }

    @Override
    public String getDepartMessage() {
        return "Now Leaving the world of kitsunes";
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }
    public float setMoonSize() {
        return 15.0F;
    }

}
