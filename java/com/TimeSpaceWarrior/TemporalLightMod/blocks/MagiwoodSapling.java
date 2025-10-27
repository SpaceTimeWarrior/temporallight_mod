package com.TimeSpaceWarrior.TemporalLightMod.blocks;

import com.TimeSpaceWarrior.TemporalLightMod.world.TemporalLightWorldGenMagiwoodTrees;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.world.World;

import java.util.Random;

public class MagiwoodSapling extends BlockBush implements IGrowable {


    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean is_client) {
        return true;
    }

    @Override
    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return rand.nextFloat()<0.45F;
    }

    @Override
    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        new TemporalLightWorldGenMagiwoodTrees().generate(world, rand, x, y, z);
    }
}


