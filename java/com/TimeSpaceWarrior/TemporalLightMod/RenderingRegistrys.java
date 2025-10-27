package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.entity.FireballProjectileEntity;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneModel;
import com.TimeSpaceWarrior.TemporalLightMod.entity.LightningProjectileEntity;
import com.TimeSpaceWarrior.TemporalLightMod.render.RenderKitsune;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;

public class RenderingRegistrys {
    public static void Register(){
        RenderingRegistry.registerEntityRenderingHandler(LightningProjectileEntity.class,new RenderSnowball(ItemRegistry.LIGHTNING_CHARGE));
        RenderingRegistry.registerEntityRenderingHandler(FireballProjectileEntity.class,new RenderSnowball(Items.fire_charge));
        RenderingRegistry.registerEntityRenderingHandler(EntityKitsune.class,new RenderKitsune(new KitsuneModel(),0.5F));
        //RenderingRegistry.registerEntityRenderingHandler(EntityHorse.class, new RenderHyperSteelHorse());
    }
}
