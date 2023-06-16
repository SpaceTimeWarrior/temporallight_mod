package net.tsw.temporallight.Entity.Mob.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.ResourceLocation;
import net.tsw.temporallight.Entity.Mob.PheonixEntity;
import net.tsw.temporallight.Entity.Mob.models.pheonix_01;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.integration.phoenixrenderermod_comm;

public class pheonixRenderer  extends MobRenderer<PheonixEntity, pheonix_01<PheonixEntity>>
{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(TemporalLight.MOD_ID, "textures/entity/phoenix/phoenix.png");
    protected static final ResourceLocation TEXTURE2 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/weaponsmith.png");
    protected static final ResourceLocation TEXTURE3 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/cleric.png");
    protected static final ResourceLocation TEXTURE4 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/librarian.png");
    protected static final ResourceLocation TEXTURE5 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/farmer.png");
    protected static final ResourceLocation TEXTURE6 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/butcher.png");
    protected static final ResourceLocation TEXTURE7 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/nitwit.png");
    protected static final ResourceLocation TEXTURE8 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/fisherman.png");
    protected static final ResourceLocation TEXTURE9 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/fletcher.png");
    protected static final ResourceLocation TEXTURE10 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/armorer.png");
    protected static final ResourceLocation TEXTURE11 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/toolsmith.png");
    protected static final ResourceLocation TEXTURE12 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/cartographer.png");
    protected static final ResourceLocation TEXTURE13 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/leatherworker.png");
    protected static final ResourceLocation TEXTURE14 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/mason.png");
    protected static final ResourceLocation TEXTURE15 = new ResourceLocation(TemporalLight.MOD_ID,"textures/entity/phoenix/shepherd.png");


    public pheonixRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new pheonix_01<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(PheonixEntity entity) {
        VillagerProfession prof = entity.getVillagerData().getProfession();
        if(prof.equals(VillagerProfession.NONE)){
                return TEXTURE;
        }else if(prof.equals(VillagerProfession.WEAPONSMITH)){
            return TEXTURE2;
        }else if(prof.equals(VillagerProfession.CLERIC)){
            return TEXTURE3;
        }else if(prof.equals(VillagerProfession.LIBRARIAN)){
            return TEXTURE4;
        }else if(prof.equals(VillagerProfession.FARMER)){
            return TEXTURE5;
        }else if(prof.equals(VillagerProfession.NITWIT)){
            return TEXTURE7;
        }else if(prof.equals(VillagerProfession.BUTCHER)){
            return TEXTURE6;
        }else if(prof.equals(VillagerProfession.NITWIT)){
                return TEXTURE7;
        }else if(prof.equals(VillagerProfession.FISHERMAN)){
            return TEXTURE8;
        }else if(prof.equals(VillagerProfession.FLETCHER)){
            return TEXTURE9;
        }else if(prof.equals(VillagerProfession.ARMORER)){
            return TEXTURE10;
        }else if(prof.equals(VillagerProfession.TOOLSMITH)){
            return TEXTURE11;
        }else if(prof.equals(VillagerProfession.CARTOGRAPHER)){
            return TEXTURE12;
        }else if(prof.equals(VillagerProfession.LEATHERWORKER)){
            return TEXTURE13;
        }else if(prof.equals(VillagerProfession.MASON)){
            return TEXTURE14;
        }else if(prof.equals(VillagerProfession.SHEPHERD)){
            return TEXTURE15;
        }else{
            return TemporalLight.ph_com.check(prof);
        }
    }
}