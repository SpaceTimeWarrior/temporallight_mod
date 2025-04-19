package net.tsw.temporal_light.event;

import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tsw.temporal_light.Blocks.entity.TLBlockEntityRegistry;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.entity.TLEntityRegistry;
import net.tsw.temporal_light.entity.client.KitsuneModel;
import net.tsw.temporal_light.entity.kitsune.KitsuneEntity;

@Mod.EventBusSubscriber(modid = Temporal_Light.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class TLEventBusClientEvents {



    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(KitsuneModel.LAYER_LOCATION,KitsuneModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(TLEntityRegistry.KITSUNE.get(), KitsuneEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(TLBlockEntityRegistry.TLSIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(TLBlockEntityRegistry.TLHANGINGSIGN.get(), HangingSignRenderer::new);
        //event.registerBlockEntityRenderer(TLBlockEntityRegistry.EATHERIAN_PORTAL.get(), TLEatherian_Portal_Renderer::new);
    }

}
