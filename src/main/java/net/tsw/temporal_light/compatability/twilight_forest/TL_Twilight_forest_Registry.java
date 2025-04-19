package net.tsw.temporal_light.compatability.twilight_forest;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Items.TLItemRegistry;
import net.tsw.temporal_light.Temporal_Light;

import java.util.function.Supplier;

public class TL_Twilight_forest_Registry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Temporal_Light.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Temporal_Light.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CTABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Temporal_Light.MOD_ID);

    //public static final RegistryObject<Block> TWILIGHTBLOCK = registerBlock("twilight_block", () -> new HedgeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RESPAWN_ANCHOR)));


    public static final RegistryObject<CreativeModeTab> TWILIGHT_TAB = CTABS.register("twilight_tl",()-> CreativeModeTab.builder().icon(()->new ItemStack(TLItemRegistry.TIMECRYSTALSHARD.get())).title(Component.translatable("creativetab.temporallightprophecy")).displayItems(((pParameters, pOutput) -> {

    })).build());
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> BLTR = BLOCKS.register(name,block);
        registerBlockItem(name,BLTR,64);
        return BLTR;
    }
    private static <T extends Block>RegistryObject<T> registerBlockIM(String name, Supplier<T> block){
        RegistryObject<T> BLTR = BLOCKS.register(name,block);
        registerBlockItemFIM(name,BLTR,64);
        return BLTR;
    }
    private static <T extends Block>RegistryObject<T> registerBlockIM(String name, Supplier<T> block ,int Stacksize){
        RegistryObject<T> BLTR = BLOCKS.register(name,block);
        registerBlockItemFIM(name,BLTR,Stacksize);
        return BLTR;
    }
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block ,int Stacksize){
        RegistryObject<T> BLTR = BLOCKS.register(name,block);
        registerBlockItem(name,BLTR,Stacksize);
        return BLTR;
    }
    private static <T extends Block>RegistryObject<Item>registerBlockItemFIM(String name, RegistryObject<T> block,int stack){
        return TL_Twilight_forest_Registry.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().stacksTo(stack).fireResistant()));
    }
    private static <T extends Block>RegistryObject<Item>registerBlockItem(String name, RegistryObject<T> block,int stack){
        return TL_Twilight_forest_Registry.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().stacksTo(stack)));
    }
    public static void register(IEventBus eventbus){

        ITEMS.register(eventbus);
        BLOCKS.register(eventbus);
        CTABS.register(eventbus);
    }
}
