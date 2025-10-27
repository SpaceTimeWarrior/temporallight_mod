package net.tsw.Temporal_Light.entity.phoenixM.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.entity.phoenixF.client.pheonix_FModel;
import net.tsw.Temporal_Light.entity.phoenixF.phoenix_FEntity;
import net.tsw.Temporal_Light.entity.phoenixM.phoenix_mEntity;


public class phoenixMRenderer extends MobRenderer<phoenix_mEntity, pheonix_mModel<phoenix_mEntity>> {


    public phoenixMRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new pheonix_mModel<>(pContext.bakeLayer(pheonix_mModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(phoenix_mEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_m/mphoenix.png");
    }

    public void render(phoenix_FEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }else{
            pPoseStack.scale(1f,1f,1f);
        }
        pPoseStack.translate(0,-1.5f,0);
        //super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
