package net.tsw.temporallight.integration;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.integration.curios.CuriosItemRegistry;
import net.tsw.temporallight.integration.curios.speednecklaceItem;
import net.tsw.temporallight.item.ItemgroupRegistry;

import java.util.logging.Logger;

public class compatabilityItemRegistry {

    public static void register(IEventBus eventbus) {
        if(ModList.get().isLoaded("curios")){
            CuriosItemRegistry.register(eventbus);
        }else{
            TemporalLight.LOGGER.debug("the mod did not load curios here is a list of items it did not load");
            TemporalLight.LOGGER.debug("Speed necklace");
        }
    }
}
