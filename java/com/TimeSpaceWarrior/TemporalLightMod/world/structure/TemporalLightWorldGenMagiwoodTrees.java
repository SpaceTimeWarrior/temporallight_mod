package com.TimeSpaceWarrior.TemporalLightMod.world.structure;

import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class TemporalLightWorldGenMagiwoodTrees extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {

        Block ground = world.getBlock(x, y - 1, z);
        if (ground != Blocks.grass && ground != Blocks.end_stone) return false;


        for (int i = 0; i < 20 + rand.nextInt(4); i++) {
            world.setBlock(x, y + i, z, BlockRegistry.MAGIWOODLOG);
        }

        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                for (int dy = 0; dy <= 2; dy++) {
                    if (Math.abs(dx) + Math.abs(dz) + dy < 4) {
                        world.setBlock(x + dx, y + 20 + dy, z + dz, BlockRegistry.MAGIWOODLEAVES);
                    }
                }
            }
        }
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                for (int dy = 0; dy <= 2; dy++) {
                    if (Math.abs(dx) + Math.abs(dz) + dy < 4) {
                        world.setBlock(x + dx, y + 10 + dy, z + dz, BlockRegistry.MAGIWOODLEAVES);
                    }
                }
            }
        }

        return true;
    }

}
