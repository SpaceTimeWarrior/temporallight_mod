package com.TimeSpaceWarrior.TemporalLightMod.Compatability.ex_nihilo;

import com.TimeSpaceWarrior.TemporalLightMod.TemporalLightMod;
import exnihilo.items.ItemCrook;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;

public class NihiloCrooks extends ItemCrook {
    public String texture;

    public NihiloCrooks(ToolMaterial material, String tex){
        super(material);
        this.setMaxDamage(material.getMaxUses()*2);
        texture = tex;
    }
   public String func_77658_a(){
        return "item."+getToolMaterialName()+" crook nihilo";
   }
   public String func_77667_c(ItemStack item){
        return func_77658_a();
   }

    @Override
    public void func_94581_a(IIconRegister register) {
        String tex = TemporalLightMod.MODID+":"+texture;
        this.itemIcon = register.registerIcon(tex);
    }
}
