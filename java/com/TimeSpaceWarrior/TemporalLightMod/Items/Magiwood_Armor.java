package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class Magiwood_Armor extends ItemArmor {
    public Magiwood_Armor(ArmorMaterial material, int renderlayer, int equipslot) {
        super(material, renderlayer, equipslot);
    }
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if(slot == 2){
            return TemporalLightMod.MODID+":"+"textures/models/armor/magiwood_layer_2.png";
        }
        return TemporalLightMod.MODID+":"+"textures/models/armor/magiwood_layer_1.png";
    }
}
