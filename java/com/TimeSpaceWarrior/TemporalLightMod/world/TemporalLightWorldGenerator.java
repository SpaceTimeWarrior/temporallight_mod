package com.TimeSpaceWarrior.TemporalLightMod.world;

import com.TimeSpaceWarrior.TemporalLightMod.BiomeRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.BlockRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.world.biome.BiomeGenMagiwoodForest;
import com.TimeSpaceWarrior.TemporalLightMod.world.structure.TemporalLightWorldGenMagiwoodTrees;
import com.TimeSpaceWarrior.TemporalLightMod.world.structure.cedric_base;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

public class TemporalLightWorldGenerator implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int y = world.getHeightValue(chunkX, chunkZ);
        switch (world.provider.dimensionId){
            case -1:
                generate_nether(world,random,chunkX,chunkZ);
                break;
            case 0:
                generate_overworld(world,random,chunkX,chunkZ);

                for (int i = 0; i < 12; i++) { // 12 trees per chunk, adjustable
                    int x = chunkX * 16 + random.nextInt(16);
                    int z = chunkZ * 16 + random.nextInt(16);

                    if (world.getBiomeGenForCoords(x, z) == BiomeRegistry.MagiwoodForestOverworld) {
                        generateMagiwoodTrees(world, random, chunkX, chunkZ, 2,2);
                    }
                }
                break;
            case 1:
                generate_end(world,random,chunkX,chunkZ);
                break;
            default:
                generate_other(world.provider.dimensionId,world,random,chunkX,chunkZ);
                generateMagiwoodTrees(world, random, chunkX, chunkZ, 2,2);
                break;
        }
    }
    public void generate_nether(World world,Random rand,int x,int z){
        generate_block(BlockRegistry.SYNTHETICTIMECRYSTALORE_NETHER,world,rand,x,z,1,8,10,0,32, Blocks.netherrack);
        generate_block(BlockRegistry.UNFIRED_DEATHMETAL_BLOCK,world,rand,x,z,1,3,5,0,256,Blocks.netherrack);
    }
    public void generate_overworld(World world,Random rand,int x,int z){
        generate_block(BlockRegistry.SYNTHETICTIMECRYSTALORE,world,rand,x,z,1,8,10,0,32, Blocks.stone);
    }
    public void generate_end(World world,Random rand,int x,int z){
        generate_block(BlockRegistry.SYNTHETICTIMECRYSTALORE_END,world,rand,x,z,1,8,10,0,32, Blocks.end_stone);
    }
    public void generate_other(int dim,World world,Random rand,int x,int z){
        generate_block(BlockRegistry.SYNTHETICTIMECRYSTALORE_DEEPSLATE,world,rand,x,z,1,8,10,0,32, Blocks.stone);
    }
    public void generate_block(Block block,World world,Random rand,int x,int z,int minVeinSize,int maxVeinSize,int chance,int miny,int maxy,Block blockinside){
        int veinsizerange = maxVeinSize-minVeinSize;
        int veinsize = minVeinSize + rand.nextInt(veinsizerange);
        int heightrange = maxy-miny;
        WorldGenMinable mine = new WorldGenMinable(block,veinsize,blockinside);
        for(int i=0;i<chance;i++){
            int Xrand = x *16 + rand.nextInt(16);
            int Yrand = miny+rand.nextInt(heightrange);
            int Zrand = z *16 + rand.nextInt(16);
            mine.generate(world,rand,Xrand,Yrand,Zrand);

        }
    }
    private void generateMagiwoodTrees(World world, Random rand, int chunkX, int chunkZ, int attempts) {
        int numperchunk = 0;
        for (int i = 0; i < attempts; i++) {
            if(numperchunk<1&&rand.nextInt(100)<20) {
                if(chunkX%50 == 0&&chunkZ%50 == 0) {
                    int x = chunkX * 16 + rand.nextInt(16);
                    int z = chunkZ * 16 + rand.nextInt(16);
                    int y = world.getHeightValue(x, z);
                    new TemporalLightWorldGenMagiwoodTrees().generate(world, rand, x, y, z);
                    numperchunk++;
                }
            }
        }
    }
    private void generateMagiwoodTrees(World world, Random rand, int chunkX, int chunkZ, int attempts,int maxparchunk) {
        int numperchunk = 0;
        //if(chunkX%2 == 0&&chunkZ%2 == 0) {
            for (int i = 0; i < attempts; i++) {
                if (numperchunk < maxparchunk && rand.nextInt(100) < 20) {
                    int x = chunkX * 16 + rand.nextInt(16);
                    int z = chunkZ * 16 + rand.nextInt(16);
                    int y = world.getHeightValue(x, z);
                    new TemporalLightWorldGenMagiwoodTrees().generate(world, rand, x, y, z);
                    System.out.println("tree added at ("+x+","+y+", "+z+")");
                    numperchunk++;
                }
            }
            //int x = chunkX * 16 + rand.nextInt(16);
            //int z = chunkZ * 16 + rand.nextInt(16);
            //int y = world.getHeightValue(x, z);
            //new cedric_base().generate(world, rand, x, y, z);
        //}
    }
}
