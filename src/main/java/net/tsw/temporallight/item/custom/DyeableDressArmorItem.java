package net.tsw.temporallight.item.custom;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.tsw.temporallight.data.model.dress_armor;
import net.tsw.temporallight.item.armormaterialregistry;

import javax.annotation.Nullable;

public class DyeableDressArmorItem extends ArmorItem implements IDyeableArmorItem  {
    public String name;

    public DyeableDressArmorItem(armormaterialregistry material, EquipmentSlotType slot, Properties properties) {
        super(material,slot,properties);
        name = material.getIName();
    }
    public DyeableDressArmorItem(ArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material,slot,properties);
        name = material.getName();
    }
    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        dress_armor armor = new dress_armor(1F);
        A ret=(A)armor;
        return ret;
    }
    @Override
    public int getColor(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getChildTag("display");
        return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : 0x68422a;
    }


    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if(type!=null){
        if(type.equals("overlay")){
            return "temporallight:textures/models/armor/"+name+"_dress_overlay.png";
        }}
        if(slot.equals(EquipmentSlotType.CHEST)){
            return "temporallight:textures/models/armor/"+name+"_dress.png";
        }else{
            return super.getArmorTexture(stack, entity, slot, type);
        }
    }

}