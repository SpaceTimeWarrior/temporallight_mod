package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.entity.FireballProjectileEntity;
import com.TimeSpaceWarrior.TemporalLightMod.entity.LightningProjectileEntity;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityRegistrys {
    public static void Register(){
        EntityRegistry.registerModEntity(LightningProjectileEntity.class,"lightningProjectile",0,TemporalLightMod.instance,800,3,true);
        EntityRegistry.registerModEntity(FireballProjectileEntity.class,"fireballprojectile",1,TemporalLightMod.instance,800,1,true);
        EntityRegistry.registerModEntity(EntityKitsune.class,"kitsune",2,TemporalLightMod.instance,80,3,true);
    }
}
