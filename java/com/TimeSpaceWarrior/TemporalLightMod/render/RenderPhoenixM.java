package com.TimeSpaceWarrior.TemporalLightMod.render;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import com.TimeSpaceWarrior.TemporalLightMod.entity.phoenixM.phoenixModelM;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPhoenixM extends RenderLiving {
    public RenderPhoenixM(phoenixModelM model, float shadowradius) {
        super(model,shadowradius);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        super.doRender(entity,x,y,z,yaw,partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return new ResourceLocation(TemporalLightMod.MODID, "textures/entity/phoenixm/mphoenix.png");
    }
}
