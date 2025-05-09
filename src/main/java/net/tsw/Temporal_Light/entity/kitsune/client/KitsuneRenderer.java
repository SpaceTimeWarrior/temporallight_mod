package net.tsw.Temporal_Light.entity.kitsune.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneEntity;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneVarients;

import java.util.Map;

public class KitsuneRenderer extends MobRenderer<KitsuneEntity,KitsuneModel<KitsuneEntity>> {
    private static final Map<KitsuneVarients,ResourceLocation> LOCATION_BY_VARIENT = Util.make(Maps.newEnumMap(KitsuneVarients.class), map ->{
        map.put(KitsuneVarients.BROWN,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_brown.png"));
        map.put(KitsuneVarients.BROWN2,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_brown.png"));
        map.put(KitsuneVarients.DK_GREY,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_dk_gray.png"));
        map.put(KitsuneVarients.DK_GREY2,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_dk_gray.png"));
        map.put(KitsuneVarients.ORANGE,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_orange.png"));
        map.put(KitsuneVarients.ORANGE2,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_orange.png"));
        map.put(KitsuneVarients.WHITE,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_white.png"));
        map.put(KitsuneVarients.YELLOW,ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/kitsune/kitsune_blonde.png"));

    });
    public KitsuneRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KitsuneModel<>(pContext.bakeLayer(KitsuneModel.LAYER_LOCATION)), 0.85f);
    }

    @Override
    public ResourceLocation getTextureLocation(KitsuneEntity pEntity) {

        return LOCATION_BY_VARIENT.get(pEntity.getVarient());
    }

    @Override
    public void render(KitsuneEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()){
            pPoseStack.scale(0.5f,0.5f,0.5f);
        }else{
            pPoseStack.scale(1f,1f,1f);
        }
        //pPoseStack.translate(0f,2f,0f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
