package net.tsw.temporallight.Entity.Mob;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;

public class MobRegistry {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TemporalLight.MOD_ID);
    public static final RegistryObject<EntityType<PheonixEntity>> PHEONIX = ENTITY_TYPES.register("phoenix",() -> EntityType.Builder.create(PheonixEntity::new,EntityClassification.MISC).size(1.0F, 2.0f).trackingRange(10).immuneToFire().build(new ResourceLocation(TemporalLight.MOD_ID, "phoenix").toString()));
    public static final RegistryObject<EntityType<MalePheonixEntity>> MALEPHEONIX = ENTITY_TYPES.register("mphoenix",() -> EntityType.Builder.create(MalePheonixEntity::new,EntityClassification.CREATURE).size(1.0F, 2.0f).trackingRange(10).immuneToFire().build(new ResourceLocation(TemporalLight.MOD_ID, "mphoenix").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
