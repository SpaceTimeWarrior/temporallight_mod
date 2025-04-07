package net.tsw.temporal_light.Items.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tsw.temporal_light.Items.TLArmorMaterials;

import java.util.Map;

public class TLArmorItem extends ArmorItem {
    public static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP = (new ImmutableMap.Builder<ArmorMaterial,MobEffectInstance>())
            .put(TLArmorMaterials.HYPERSTEEL_ARMOR_MATERIAL.get(),new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1,1,false,false,true))
            //.put(TLArmorMaterials.HYPERSTEEL_ARMOR_MATERIAL.get(),new MobEffectInstance(MobEffects.FIRE_RESISTANCE,200,1,false,false,true))
            .build();
    public TLArmorItem(Holder<ArmorMaterial> pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player){
        if(!world.isClientSide()){
            if(hasFullSuitOfArmorOn(player)){
                evaluateArmorEffects(player);
            }
        }
    }
    @Override
    public void onInventoryTick(ItemStack stack, Level world, Player player, int slotIndex, int selectedIndex){
        if(!world.isClientSide()){
            if(hasFullSuitOfArmorOn(player)){
                evaluateArmorEffects(player);
            }
        }
    }
    public void inventoryTick(ItemStack stack, Level world, Player player){
        if(!world.isClientSide()){
                evaluateArmorEffects(player);
        }
    }
    private void evaluateArmorEffects(Player player){
        for(Map.Entry<ArmorMaterial,MobEffectInstance>entry : MATERIAL_TO_EFFECT_MAP.entrySet()){
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial,player)){
                addStatusEffectForMaterial(player,mapArmorMaterial,mapStatusEffect);
            }else{
                removeStatusEffectForMaterial(player,mapArmorMaterial,mapStatusEffect);
            }
        }
    }

    private void removeStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect())&&player.getEffect(mapStatusEffect.getEffect()).isInfiniteDuration();
        if(!hasCorrectArmorOn(mapArmorMaterial,player)&&hasPlayerEffect){
            player.removeEffect(mapStatusEffect.getEffect());
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect())&&player.getEffect(mapStatusEffect.getEffect()).isInfiniteDuration();
        if(hasCorrectArmorOn(mapArmorMaterial,player)&&!hasPlayerEffect){
            player.addEffect(new MobEffectInstance(mapStatusEffect));
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        if(hasFullSuitOfArmorOn(player)) {
            for (ItemStack armorStack : player.getInventory().armor) {
                if (!(armorStack.getItem() instanceof ArmorItem)) {
                    return false;
                }
            }
            ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
            ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
            ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
            ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());
            ArmorMaterial bts = boots.getMaterial().get();
            boolean btsb = bts == material;

            return boots.getMaterial().get() == material && chestplate.getMaterial().get() == material && leggings.getMaterial().get() == material && helmet.getMaterial().get() == material;
        }else{
            return false;
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player){
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);
        boolean ret = !helmet.isEmpty()&&!leggings.isEmpty()&&!chestplate.isEmpty()&&!boots.isEmpty();
        return ret;
    }
}
