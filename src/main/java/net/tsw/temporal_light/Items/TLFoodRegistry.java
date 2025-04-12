package net.tsw.temporal_light.Items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class TLFoodRegistry {
    public static final FoodProperties HEALING_POWDER = new FoodProperties.Builder().alwaysEdible().nutrition(2)
            .saturationModifier(.4f)
            .effect(new MobEffectInstance(MobEffects.HEAL,200),1.0f).build();
}
