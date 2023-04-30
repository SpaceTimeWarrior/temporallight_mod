package net.tsw.temporallight.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.tsw.temporallight.block.blockRegistry;

public class ItemgroupRegistry {
    public static final ItemGroup TemporalLightProphecygroup=new ItemGroup("temporallightprophecy"){
        @Override
        public ItemStack createIcon(){
            return new ItemStack((ItemRegistry.TIMECRYSTALSHARD.get()));
        }
    };
    public static final ItemGroup TemporalLightMaterials=new ItemGroup("temporallightmaterials"){
        @Override
        public ItemStack createIcon(){
            return new ItemStack((ItemRegistry.SYNTHTIMECRYSTALSHARD.get()));
        }
    };
    public static final ItemGroup TemporalLightBlocks=new ItemGroup("temporallightblocks"){
        @Override
        public ItemStack createIcon(){
            return new ItemStack((blockRegistry.HYPERSTEEL_BLOCK.get()));
        }
    };
    public static final ItemGroup TemporalLightTOOLS=new ItemGroup("temporallighttools"){
        @Override
        public ItemStack createIcon(){
            return new ItemStack((ItemRegistry.HYPERSTEELSWORD.get()));
        }
    };
}
