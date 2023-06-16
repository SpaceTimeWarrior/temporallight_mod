package net.tsw.temporallight.integration.curios;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;

public class speednecklaceItem extends Item implements ICurioItem {
    public speednecklaceItem(Properties properties) {
        super(properties);
    }
    //if this is not isolated this must be quarantined
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;
        if(!player.world.isRemote()) {
            boolean hasPlayerSpeed = !Objects.equals(player.getActivePotionEffect(Effects.SPEED), null);
            if(!hasPlayerSpeed){
                player.addPotionEffect(new EffectInstance(Effects.SPEED, 200));
            }
        }
        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }
}
