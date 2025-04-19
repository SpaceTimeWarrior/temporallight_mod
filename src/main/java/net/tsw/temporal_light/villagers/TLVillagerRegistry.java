package net.tsw.temporal_light.villagers;

import com.google.common.collect.ImmutableSet;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Temporal_Light;

public class TLVillagerRegistry {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Temporal_Light.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,Temporal_Light.MOD_ID);

    public static final RegistryObject<PoiType> EATHERIAN_PORTAL = POI_TYPES.register("eatherian_portal", () -> new PoiType(ImmutableSet.copyOf(TLBlocksRegistry.EATHERIAN_SLEEP_PORTAL.get().getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<PoiType> END_POI = POI_TYPES.register("end_poi",()->new PoiType(ImmutableSet.copyOf(TLBlocksRegistry.ERESPAWNANCHOR.get().getStateDefinition().getPossibleStates()),1,1));
    public static final RegistryObject<VillagerProfession> END_TRADER = VILLAGER_PROFESSIONS.register("end_trader",()->new VillagerProfession("end_trader",holder-> holder.get() == END_POI.get(),holder->holder.get()==END_POI.get(),ImmutableSet.of(),ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));


    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
