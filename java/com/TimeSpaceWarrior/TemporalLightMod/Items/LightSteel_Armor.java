package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class LightSteel_Armor extends ItemArmor {
    public LightSteel_Armor(ItemArmor.ArmorMaterial material, int renderIndex, int ArmorIndex) {
        super(material,renderIndex,ArmorIndex);
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if(slot == 2){
            return TemporalLightMod.MODID+":"+"textures/models/armor/lightsteel_layer_2.png";
        }
        return TemporalLightMod.MODID+":"+"textures/models/armor/lightsteel_layer_1.png";
    }
}
