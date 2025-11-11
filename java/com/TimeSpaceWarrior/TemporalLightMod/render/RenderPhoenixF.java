package com.TimeSpaceWarrior.TemporalLightMod.render;

import com.TimeSpaceWarrior.TemporalLightMod.Compatability.Tconstruct.Villager_checkerTC;
import com.TimeSpaceWarrior.TemporalLightMod.Compatability.thaumcraft.villager_checkerTMC;
import com.TimeSpaceWarrior.TemporalLightMod.TLConfig;
import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.phoenixF.EntityPhoenixF;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderPhoenixF extends RenderLiving {
    public RenderPhoenixF(ModelBase base, float shadowrad) {
        super(base, shadowrad);
    }

    @Override
    public void doRender(EntityLivingBase entity, double x, double y, double z, float yaw, float partialTicks) {
        super.doRender(entity, x, y, z, yaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if(!(entity instanceof EntityPhoenixF)) {
            return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/phoenix.png");
        }else{
            EntityPhoenixF phoenixF = (EntityPhoenixF) entity;
            switch (phoenixF.getProfession()){
                case 0:
                    return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/farmer.png");
                case 1:
                    return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/librarian.png");
                case 2:
                    return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/cleric.png");
                case 3:
                    return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/armorer.png");
                case 4:
                    return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/butcher.png");


                default:
                    if(Loader.isModLoaded("chococraft")){
                        String str = com.TimeSpaceWarrior.TemporalLightMod.Compatability.chococraft.ChocoPlusSelector.get_stablehand(phoenixF.getProfession());
                        if(str!=null){return new ResourceLocation(TemporalLightMod.MODID,str);}
                    }
                    if(Loader.isModLoaded("TConstruct")){
                        String str = Villager_checkerTC.Check_Villager(phoenixF.getProfession());
                        if(str!=null){return  new ResourceLocation(TemporalLightMod.MODID,str);}
                    }
                    if(Loader.isModLoaded("Thaumcraft")){
                        String str = villager_checkerTMC.Check_profession(phoenixF.getProfession());
                        if(str!=null){return  new ResourceLocation(TemporalLightMod.MODID,str);}
                    }
                    if(phoenixF.getProfession()== TLConfig.VillagerEndTraderID){
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/end_trader.png");
                    }else{
                        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixf/other_default.png");
                    }
            }
        }
    }
}
