package com.TimeSpaceWarrior.TemporalLightMod.world.biome;

import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.world.structure.TemporalLightWorldGenMagiwoodTrees;
import com.TimeSpaceWarrior.TemporalLightMod.world.structure.cedric_base;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class BiomeGenMagiwoodForestDIM extends BiomeGenBase {
    public static final BiomeGenBase magiwoodForest = new BiomeGenMagiwoodForestDIM(TLConfig.BiomeMagiwood_forest_ID);
    public BiomeGenMagiwoodForestDIM(int id) {
        super(id);
        this.setEnableSnow();
        this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 1, 1, 2));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityKitsune.class, 10, 6, 10));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 3, 1, 3));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 3, 1, 4));
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 4, 1, 4));

        this.fillerBlock = Blocks.stone;
        this.topBlock = Blocks.grass;
        this.theBiomeDecorator.treesPerChunk = 3;
        this.theBiomeDecorator.generateLakes = true;
    }


    @Override
    public void decorate(World world, Random rand, int chunkx, int chunkz) {
        super.decorate(world, rand, chunkx, chunkz);
        int numperchunk = 0;
        int maxparchunk = 10;
        int attempts = 4;
        if(chunkx%50 ==0&&chunkz%50==0){
            int x = chunkx * 16 + rand.nextInt(16);
            int z = chunkz * 16 + rand.nextInt(16);
            int y = world.getHeightValue(x, z);
            new cedric_base().generate(world, rand, x, y, z);
        }else if((chunkx%50 ==1&&chunkz%50==1)||(chunkx%50 ==49&&chunkz%50==49)){

        }else{
            for (int i = 0; i < attempts; i++) {
                if (numperchunk < maxparchunk && rand.nextInt(100) < 20) {
                    int x = chunkx * 16 + rand.nextInt(16);
                    int z = chunkz * 16 + rand.nextInt(16);
                    int y = world.getHeightValue(x, z);
                    new TemporalLightWorldGenMagiwoodTrees().generate(world, rand, x, y, z);
                    //System.out.println("tree added at ("+x+","+y+", "+z+")");
                    numperchunk++;
                }
            }
        }

    }
}
