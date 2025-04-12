package net.tsw.temporal_light.compatability;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.compatability.create.TL_Create_Registry;

public class TLcompatabilityRegistry {
    public static void register(IEventBus eventbus) {
        if(ModList.get().isLoaded("create")){//create
            TL_Create_Registry.register(eventbus);
        }else{
            Temporal_Light.LOGGER.debug("the mod did not load create here is a list of items it did not load");
            Temporal_Light.LOGGER.debug("");
        }
    }

}
