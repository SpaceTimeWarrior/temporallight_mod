package net.tsw.temporal_light.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.MobCategory;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.entity.kitsune.KitsuneEntity;

public class TLEntityRegistry {
    public static final DeferredRegister<EntityType<?>>ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Temporal_Light.MOD_ID);

    public static final RegistryObject<EntityType<KitsuneEntity>> KITSUNE = ENTITY_TYPES.register("kitsune",()->EntityType.Builder.of(KitsuneEntity::new, MobCategory.CREATURE).sized(1,2).build("kitsune"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
