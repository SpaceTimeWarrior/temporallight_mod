package net.tsw.temporallight.integration;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;

import java.util.ArrayList;
import java.util.List;

public class phoenixrenderermod_comm {
    public static List<ResourceLocation>RL=new ArrayList<>();
    public static List<VillagerProfession>PR=new ArrayList<>();
    public phoenixrenderermod_comm(){}

    public static void add(ResourceLocation RsL,VillagerProfession Pro){
        RL.add(RsL);
        PR.add(Pro);
    }
    public static ResourceLocation check(VillagerProfession PROF){
        if(PR.contains(PROF)){
            for(int i = 0;i<PR.size();i++){
                VillagerProfession pp = PR.get(i);
                if(pp.equals(PROF)){
                    return RL.get(i);
                }
            }
            return new ResourceLocation(TemporalLight.MOD_ID, "textures/entity/phoenix/phoenix.png");
        }else{
            return new ResourceLocation(TemporalLight.MOD_ID, "textures/entity/phoenix/phoenix.png");
        }
    }
}
