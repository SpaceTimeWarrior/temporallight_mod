package net.tsw.Temporal_Light.entity.phoenixF.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.entity.phoenixF.phoenix_FEntity;
import net.tsw.Temporal_Light.entity.phoenixF.phoenix_fVarients;

import java.util.Map;

public class phoenixFRenderer extends MobRenderer<phoenix_FEntity, pheonix_FModel<phoenix_FEntity>> {
    private static final Map<phoenix_fVarients,ResourceLocation> LOCATION_BY_VARIENT = Util.make(Maps.newEnumMap(phoenix_fVarients.class), map ->{
        map.put(phoenix_fVarients.nitwit,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/nitwit.png"));
        map.put(phoenix_fVarients.armorer,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/armorer.png"));
        map.put(phoenix_fVarients.butcher,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/butcher.png"));
        map.put(phoenix_fVarients.cartographer,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/cartographer.png"));
        map.put(phoenix_fVarients.cleric,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/cleric.png"));
        map.put(phoenix_fVarients.end_trader,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/end_trader.png"));
        map.put(phoenix_fVarients.farmer,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/farmer.png"));
        map.put(phoenix_fVarients.fisherman,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/fisherman.png"));
        map.put(phoenix_fVarients.fletcher,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/fletcher.png"));
        map.put(phoenix_fVarients.leatherworker,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/leatherworker.png"));
        map.put(phoenix_fVarients.librarian,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/librarian.png"));
        map.put(phoenix_fVarients.mason,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/mason.png"));
        map.put(phoenix_fVarients.sheperd,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/sheperd.png"));
        map.put(phoenix_fVarients.toolsmith,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/phoenix_f/toolsmith.png"));


    });
    public phoenixFRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new pheonix_FModel<>(pContext.bakeLayer(pheonix_FModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(phoenix_FEntity pEntity) {

        return LOCATION_BY_VARIENT.get(pEntity.getVarient());
    }

    @Override
    public void render(phoenix_FEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }else{
            pPoseStack.scale(1f,1f,1f);
        }
        pPoseStack.translate(0,-1.5f,0);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
