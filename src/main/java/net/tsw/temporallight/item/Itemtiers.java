package net.tsw.temporallight.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.tsw.temporallight.block.blockRegistry;
import net.tsw.temporallight.util.configRegistry;

import java.util.function.Supplier;

public enum Itemtiers implements IItemTier {

    HYPERSTEEL(configRegistry.harvestlv_floor.get(),4096,12F,8F,30,()->Ingredient.fromItems(ItemRegistry.HYPERSTEELINGOT.get())),
    MAGIWOOD(configRegistry.harvestlv_floor.get()-1,1000,1,24.5F,128,()->Ingredient.fromItems(blockRegistry.MAGIWOODPLANKS.get().asItem())),
    LIGHTSTEEL(configRegistry.harvestlv_floor.get()+1, 8192,24F,8F,60,()->Ingredient.fromItems(ItemRegistry.LIGHTSTEELINGOT.get())),

    TIMECRYSTAL(configRegistry.harvestlv_floor.get()+5,-1,64F,64.0F,120,()->Ingredient.fromItems((ItemRegistry.TIMECRYSTALSHARD.get())));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairmaterial;

    Itemtiers(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairmaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairmaterial = new LazyValue<>(repairmaterial);
    }

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairmaterial.getValue();
    }
}
