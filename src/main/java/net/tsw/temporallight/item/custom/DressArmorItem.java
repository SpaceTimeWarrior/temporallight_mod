package net.tsw.temporallight.item.custom;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.tsw.temporallight.data.model.dress_armor;
import net.tsw.temporallight.item.armormaterialregistry;

import javax.annotation.Nullable;

public class DressArmorItem extends ArmorItem implements IArmorVanishable {
    public String name;
    public DressArmorItem(armormaterialregistry material, EquipmentSlotType slot, Item.Properties properties) {
        super(material,slot,properties);
        name = material.getIName();
    }

    public DressArmorItem(ArmorMaterial material,EquipmentSlotType slot,Item.Properties properties) {
        super(material,slot,properties);
        name = material.getName();
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        dress_armor armor = new dress_armor(1F);
        A ret=(A)armor;
        return ret;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return "temporallight:textures/models/armor/"+name+"_dress.png";
    }
}