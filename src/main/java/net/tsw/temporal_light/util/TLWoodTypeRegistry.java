package net.tsw.temporal_light.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.tsw.temporal_light.Temporal_Light;

public class TLWoodTypeRegistry {
    public static final WoodType MAGI = WoodType.register(new WoodType(Temporal_Light.MOD_ID+":magi", BlockSetType.CHERRY));
}
