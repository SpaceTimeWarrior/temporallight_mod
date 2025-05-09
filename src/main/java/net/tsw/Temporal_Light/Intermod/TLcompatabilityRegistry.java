package net.tsw.Temporal_Light.Intermod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.tsw.Temporal_Light.Temporal_Light;

public class TLcompatabilityRegistry {
    public static void register(IEventBus eventbus) {
        if(ModList.get().isLoaded("create")){//create
            //net.tsw.Temporal_Light.Intermod.create.TL_Create_Registry.register(eventbus);
        }else{
            Temporal_Light.LOGGER.debug("the mod did not load create here is a list of items it did not load");
            Temporal_Light.LOGGER.debug("");
        }
        if(ModList.get().isLoaded("twilightforest")){
            net.tsw.Temporal_Light.Intermod.twilight_forest.TL_Twilight_forest_Registry.register(eventbus);
        }else{
            Temporal_Light.LOGGER.debug("Twilight Forest did not load here is a list of items it did not load");
            Temporal_Light.LOGGER.debug("");
        }
        if(ModList.get().isLoaded("mekanismgenerators")){
            net.tsw.Temporal_Light.Intermod.mekanismgenerators.TLMGRegistry.register(eventbus);
        }else{
            Temporal_Light.LOGGER.debug("Mekanism-generators did not load here is a list of items it did not load");
            Temporal_Light.LOGGER.debug("");
        }
    }
}
