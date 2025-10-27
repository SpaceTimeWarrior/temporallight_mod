package net.tsw.Temporal_Light.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.MobCategory;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.entity.kitsune.KitsuneEntity;
import net.tsw.Temporal_Light.entity.phoenixF.phoenix_FEntity;
import net.tsw.Temporal_Light.entity.phoenixM.phoenix_mEntity;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.LightningProjectileEntity;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.TLEarthProjectileEntity;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.TLFireball;
import net.tsw.Temporal_Light.entity.projectiles.magic_staff_projectiles.TLLifeProjectileEntity;


public class TLEntityRegistry {
    public static final DeferredRegister<EntityType<?>>ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Temporal_Light.MOD_ID);

    public static final RegistryObject<EntityType<KitsuneEntity>> KITSUNE = ENTITY_TYPES.register("kitsune",()->EntityType.Builder.of(KitsuneEntity::new, MobCategory.CREATURE).sized(1,2).build("kitsune"));
    public static final RegistryObject<EntityType<phoenix_FEntity>> PHOENIX_F = ENTITY_TYPES.register("phoenix_f",()->EntityType.Builder.of(phoenix_FEntity::new,MobCategory.CREATURE).sized(1,2).build("phoenix_f"));
    public static final RegistryObject<EntityType<phoenix_mEntity>> PHOENIX_M = ENTITY_TYPES.register("phoenix_m",()->EntityType.Builder.of(phoenix_mEntity::new,MobCategory.CREATURE).sized(1,2).build("phoenix_m"));
    public static final RegistryObject<EntityType<LightningProjectileEntity>> LIGHTNING_PROJECTILE = ENTITY_TYPES.register("lightning_projectile",()->EntityType.Builder.<LightningProjectileEntity>of(LightningProjectileEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build("lightning_projectile"));
    public static final RegistryObject<EntityType<TLFireball>> FIREBALL = ENTITY_TYPES.register("fireball_projectile",()->EntityType.Builder.<TLFireball>of(TLFireball::new, MobCategory.MISC).sized(0.5f,0.5f).build("fireball_projectile"));
    public static final RegistryObject<EntityType<TLEarthProjectileEntity>> EARTHBALL = ENTITY_TYPES.register("earth_projectile",()->EntityType.Builder.<TLEarthProjectileEntity>of(TLEarthProjectileEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build("earth_projectile"));
    public static final RegistryObject<EntityType<TLLifeProjectileEntity>> LIFEBALL = ENTITY_TYPES.register("life_projectile",()->EntityType.Builder.<TLLifeProjectileEntity>of(TLLifeProjectileEntity::new, MobCategory.MISC).sized(0.5f,0.5f).build("life_projectile"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
