package net.tsw.Temporal_Light.items.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class TLFoodRegistry {
    public static final FoodProperties HEALING_POWDER = new FoodProperties.Builder().alwaysEat().nutrition(2)
            .saturationMod(.4f)
            .effect(new MobEffectInstance(MobEffects.HEAL,200),1.0f).build();

}
