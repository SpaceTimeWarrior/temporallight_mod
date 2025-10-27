package net.tsw.Temporal_Light.event;

import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.entity.TLBlockEntityRegistry;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.entity.kitsune.client.KitsuneModel;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneEntity;
import net.tsw.Temporal_Light.entity.phoenixF.phoenix_FEntity;
import net.tsw.Temporal_Light.entity.phoenixF.client.pheonix_FModel;
import net.tsw.Temporal_Light.entity.phoenixM.client.pheonix_mModel;
import net.tsw.Temporal_Light.entity.phoenixM.phoenix_mEntity;
import net.tsw.Temporal_Light.items.TLItemProperties;

@Mod.EventBusSubscriber(modid = Temporal_Light.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class TLEventBusClientEvents {



    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(KitsuneModel.LAYER_LOCATION,KitsuneModel::createBodyLayer);
        event.registerLayerDefinition(pheonix_FModel.LAYER_LOCATION,pheonix_FModel::createBodyLayer);
        event.registerLayerDefinition(pheonix_mModel.LAYER_LOCATION,pheonix_mModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(TLEntityRegistry.KITSUNE.get(), KitsuneEntity.createAttributes().build());
        event.put(TLEntityRegistry.PHOENIX_F.get(), phoenix_FEntity.createAttributes().build());
        event.put(TLEntityRegistry.PHOENIX_M.get(), phoenix_mEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(TLBlockEntityRegistry.TLSIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(TLBlockEntityRegistry.TLHANGINGSIGN.get(), HangingSignRenderer::new);
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            TLItemProperties.addCustomItemProperties();
        });
    }
}
