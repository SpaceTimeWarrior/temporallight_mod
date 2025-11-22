package com.TimeSpaceWarrior.TemporalLightMod.Compatability.cofh;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockRegistryCOFH {
    public static Block CRYSTALGENERATOR;

    public static void register(){
        initBlocks();
        registerBlocks();
    }
    public static void initBlocks(){
        CRYSTALGENERATOR = new CrystalGeneratorBlock(Material.iron).setBlockName("Crystal Generator").setCreativeTab(TemporalLightMod.TemporalLightBlocks);
    }
    public static void registerBlocks(){
        GameRegistry.registerBlock(CRYSTALGENERATOR,CRYSTALGENERATOR.getUnlocalizedName());
        GameRegistry.registerTileEntity(CrystalGeneratorTileEntity.class,"crystal_generator");
    }
}
