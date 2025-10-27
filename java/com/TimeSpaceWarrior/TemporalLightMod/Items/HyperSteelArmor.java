package com.TimeSpaceWarrior.TemporalLightMod.Items;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class HyperSteelArmor extends ItemArmor {
    public HyperSteelArmor(ArmorMaterial mat, int renderIndex, int ArmorIndex) {
        super(mat, renderIndex, ArmorIndex);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if(slot == 2){
            return TemporalLightMod.MODID+":"+"textures/models/armor/hypersteel_layer_2.png";
        }
        return TemporalLightMod.MODID+":"+"textures/models/armor/hypersteel_layer_1.png";
    }
}
