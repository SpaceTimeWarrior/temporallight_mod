package com.TimeSpaceWarrior.TemporalLightMod.Compatability.chococraft;

import chococraft.common.config.ChocoCraftEntities;
import chococraft.common.entities.colours.EntityChocoboPink;
import net.minecraft.world.biome.BiomeGenBase;

public class chocoBiome {
    public static BiomeGenBase.SpawnListEntry addChocobos() {
        return null;
    }

    public static BiomeGenBase.SpawnListEntry addChocobosMF() {
        return new BiomeGenBase.SpawnListEntry(EntityChocoboPink.class,4,1,2);
    }
    public static BiomeGenBase.SpawnListEntry addChocobosMFD() {
        return new BiomeGenBase.SpawnListEntry(EntityChocoboPink.class,6,4,8);
    }
}
