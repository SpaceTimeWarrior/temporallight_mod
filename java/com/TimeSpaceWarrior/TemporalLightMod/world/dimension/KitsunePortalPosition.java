package com.TimeSpaceWarrior.TemporalLightMod.world.dimension;

import net.minecraft.util.ChunkCoordinates;

public class KitsunePortalPosition extends ChunkCoordinates {
    public long field_85087_d;
    final TeleporterKitsune field_85088_e;

    public KitsunePortalPosition(TeleporterKitsune teleporterKitsune, int x, int y, int z, long seed) {
        super(x, y, z);
        this.field_85088_e = teleporterKitsune;
        this.field_85087_d = seed;
    }
}
