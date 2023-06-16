package net.tsw.temporallight.events;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tsw.temporallight.Entity.Mob.MalePheonixEntity;
import net.tsw.temporallight.Entity.Mob.MobRegistry;
import net.tsw.temporallight.Entity.Mob.PheonixEntity;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.item.custom.custom_spawn_eggs;

@Mod.EventBusSubscriber(modid = TemporalLight.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class TemporalEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(MobRegistry.PHEONIX.get(), PheonixEntity.setCustomAttributes().create());
        event.put(MobRegistry.MALEPHEONIX.get(), MalePheonixEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        custom_spawn_eggs.initSpawnEggs();
    }
}
