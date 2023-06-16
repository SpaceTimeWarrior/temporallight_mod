package net.tsw.temporallight.Entity.Mob.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.ResourceLocation;
import net.tsw.temporallight.Entity.Mob.MalePheonixEntity;
import net.tsw.temporallight.Entity.Mob.PheonixEntity;
import net.tsw.temporallight.Entity.Mob.models.pheonix_01;
import net.tsw.temporallight.Entity.Mob.models.pheonix_02;
import net.tsw.temporallight.TemporalLight;

public class MalepheonixRenderer extends MobRenderer<MalePheonixEntity, pheonix_02<MalePheonixEntity>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TemporalLight.MOD_ID, "textures/entity/phoenix/mphoenix.png");


    public MalepheonixRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new pheonix_02<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(MalePheonixEntity entity) {
        return TEXTURE;
    }
}