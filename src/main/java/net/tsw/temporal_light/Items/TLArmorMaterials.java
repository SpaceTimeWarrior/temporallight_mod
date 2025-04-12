package net.tsw.temporal_light.Items;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Temporal_Light;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class TLArmorMaterials {
    public static final Holder<ArmorMaterial> HYPERSTEEL_ARMOR_MATERIAL = register("hypersteel", Util.make(new EnumMap<>(ArmorItem.Type.class), attribute ->{
        attribute.put(ArmorItem.Type.BOOTS,6);
        attribute.put(ArmorItem.Type.LEGGINGS,12);
        attribute.put(ArmorItem.Type.CHESTPLATE,16);
        attribute.put(ArmorItem.Type.HELMET,6);
        attribute.put(ArmorItem.Type.BODY,19);
    }),30,6f,0.2f,()->TLItemRegistry.HYPERSTEELINGOT.get());
    public static final Holder<ArmorMaterial> MAGIWOOD_ARMOR_MATERIAL = register("magiwood", Util.make(new EnumMap<>(ArmorItem.Type.class), attribute ->{
        attribute.put(ArmorItem.Type.BOOTS,4);
        attribute.put(ArmorItem.Type.LEGGINGS,10);
        attribute.put(ArmorItem.Type.CHESTPLATE,14);
        attribute.put(ArmorItem.Type.HELMET,4);
        attribute.put(ArmorItem.Type.BODY,17);
    }),40,4f,0.6f,()-> TLBlocksRegistry.MAGIWOODPLANKS.get().asItem());

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
