package net.tsw.temporallight.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.block.blockRegistry;

import java.util.function.Supplier;

public enum armormaterialregistry implements IArmorMaterial {

    HYPERSTEEL("hypersteel",44,new int[]{6, 12, 16, 6},30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,6F,0.2F,()->{return Ingredient.fromItems(ItemRegistry.HYPERSTEELINGOT.get());}),
    LIGHTSTEEL("lightsteel",50,new int[]{12, 24, 32, 12},60, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,8F,0.5F,()->{return Ingredient.fromItems(ItemRegistry.LIGHTSTEELINGOT.get());}),
    MAGIWOOD("magiwood",35,new int[]{4, 10, 12, 4},120,SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,6F,1F,()->{return Ingredient.fromItems(blockRegistry.MAGIWOODPLANKS.get().asItem());}),
    TIMECRYSTAL("timecrystal",44,new int[]{36, 72, 96, 36},120, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,12F,1F,()->{return Ingredient.fromItems(ItemRegistry.TIMECRYSTALSHARD.get());});
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;

    private armormaterialregistry(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return TemporalLight.MOD_ID +":"+ this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
