package net.tsw.temporallight.world.dimension;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.tsw.temporallight.TemporalLight;

public class TLDimensionRegistry {
    public static RegistryKey<World> MAGIWOOD_DIM = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(TemporalLight.MOD_ID, "magiwood_dim"));
    public static RegistryKey<World> EARTH_44 = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(TemporalLight.MOD_ID, "earth44_dim"));

}
