package net.tsw.Temporal_Light.items.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class TLFuelItem extends Item {
    private int burntime = 20;
    private boolean replace = false;
    private Item replaceItem = Blocks.AIR.asItem();
    public TLFuelItem(Properties properties){
        super(properties);
        this.burntime = 20;
        this.replace = false;
        this.replaceItem = Blocks.AIR.asItem();
    }
    public TLFuelItem(Properties pProperties,float burntime) {
        super(pProperties);
        this.burntime= (int)Math.ceil(Math.abs(20*burntime));
        this.replace = false;
        @Nullable
        Item replaceItem = Blocks.AIR.asItem();
    }
    public TLFuelItem(Properties pProperties,float burntime, Item replaceItem) {
        super(pProperties.craftRemainder(replaceItem).stacksTo(1));
        this.burntime=(int)Math.ceil(Math.abs(20*burntime));

    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burntime;
    }
}
