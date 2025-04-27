package net.tsw.Temporal_Light.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;

import java.util.function.Supplier;

public enum TLArmorMaterials implements ArmorMaterial {
    HYPERSTEEL("hypersteel", 45, new int[]{ 6, 12, 16, 6 }, 30, SoundEvents.ARMOR_EQUIP_NETHERITE, 2f, 0f, () -> Ingredient.of(TLItemRegistry.HYPERSTEELINGOT.get())),
    MAGIWOOD("magiwood",60,new int[]{4,10,14,4},40,SoundEvents.ARMOR_EQUIP_TURTLE,4f,0.6f,()-> Ingredient.of(TLBlocksRegistry.MAGIWOODPLANKS.get())),
    LIGHTSTEEL("lightsteel", 120, new int[]{ 12, 24, 32, 12 }, 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 3f, 0.6f, () -> Ingredient.of(TLItemRegistry.LIGHTSTEELINGOT.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 16, 13 };

    TLArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Temporal_Light.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
