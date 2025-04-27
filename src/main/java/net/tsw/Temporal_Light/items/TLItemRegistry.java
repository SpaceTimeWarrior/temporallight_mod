package net.tsw.Temporal_Light.items;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.items.custom.TLArmorItem;
import net.tsw.Temporal_Light.items.custom.TLFoodRegistry;

public class TLItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Temporal_Light.MOD_ID);

    public static final RegistryObject<Item> TIMECRYSTALSHARD = ITEMS.register("timecrystalshard",()->new Item(new Item.Properties().stacksTo(7)));
    public static final RegistryObject<Item> SYNTHTIMECRYSTALSHARD = ITEMS.register("synthetictimecrystalshard", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> HYPERSTEELINGOT = ITEMS.register("hypersteelingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELSWORD =  ITEMS.register("hypersteelsword", () -> new SwordItem(TLItemToolTiersRegistry.HYPERSTEEL,8,4f,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELPICKAXE =ITEMS.register("hypersteelpickaxe",()-> new PickaxeItem(TLItemToolTiersRegistry.HYPERSTEEL,6,2f,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELAXE = ITEMS.register("hypersteelaxe", () -> new AxeItem(TLItemToolTiersRegistry.HYPERSTEEL,16,-2f,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELSHOVEL = ITEMS.register("hypersteelshovel", () -> new ShovelItem(TLItemToolTiersRegistry.HYPERSTEEL, 2,2f ,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELHOE = ITEMS.register("hypersteelhoe", () -> new HoeItem(TLItemToolTiersRegistry.HYPERSTEEL,1,0f,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> HYPERSTEELHELMET = ITEMS.register("hypersteelhelmet",
            () -> new TLArmorItem(TLArmorMaterials.HYPERSTEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> HYPERSTEELCHESTPLATE = ITEMS.register("hypersteelchestplate",
            () -> new TLArmorItem(TLArmorMaterials.HYPERSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> HYPERSTEELLEGGINGS = ITEMS.register("hypersteelleggings",
            () -> new TLArmorItem(TLArmorMaterials.HYPERSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> HYPERSTEELBOOTS = ITEMS.register("hypersteelboots",
            () -> new TLArmorItem(TLArmorMaterials.HYPERSTEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> MAGI_SIGN = ITEMS.register("magiwood_item_sign",()->new SignItem(new Item.Properties().stacksTo(16), TLBlocksRegistry.MAGIWOOD_SIGN.get(),TLBlocksRegistry.MAGIWOOD_WALL_SIGN.get()));
    public static final RegistryObject<Item> MAGIWOODCLUB  =  ITEMS.register("magiwoodclub", () -> new SwordItem(TLItemToolTiersRegistry.MAGIWOOD,10,0f,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MAGIWOOD_HELMET = ITEMS.register("magiwoodhelmet",
            () -> new TLArmorItem(TLArmorMaterials.MAGIWOOD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MAGIWOOD_CHESTPLATE = ITEMS.register("magiwoodchestplate",
            () -> new TLArmorItem(TLArmorMaterials.MAGIWOOD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MAGIWOOD_LEGGINGS = ITEMS.register("magiwoodleggings",
            () -> new TLArmorItem(TLArmorMaterials.MAGIWOOD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MAGIWOOD_BOOTS = ITEMS.register("magiwoodboots",
            () -> new TLArmorItem(TLArmorMaterials.MAGIWOOD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> MAGI_HANGING_SIGN = ITEMS.register("magiwood_item_hanging_sign",()->new HangingSignItem(TLBlocksRegistry.MAGIWOOD_HANGING_SIGN.get(),TLBlocksRegistry.MAGIWOOD_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16) ));
    public static final RegistryObject<Item> HEALING_POWDER = ITEMS.register("healing_powder",()->new Item(new Item.Properties().food(TLFoodRegistry.HEALING_POWDER)));
    public static final RegistryObject<Item> LIGHTSTEELINGOT = ITEMS.register("lightsteelingot",()->new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> LIGHTSTEELHELMET = ITEMS.register("lightsteelhelmet",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> LIGHTSTEELCHESTPLATE = ITEMS.register("lightsteelchestplate",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> LIGHTSTEELLEGGINGS = ITEMS.register("lightsteelleggings",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> LIGHTSTEELBOOTS = ITEMS.register("lightsteelboots",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> KITSUNE_SPAWN_EGG = ITEMS.register("kitsune_spawn_egg",()->new ForgeSpawnEggItem(()-> TLEntityRegistry.KITSUNE.get(),0xffaf5344,0xffaf8844,new Item.Properties()));
    public static final RegistryObject<Item> HEALING_CROP_SEEDS = ITEMS.register("healing_crop_seeds", () -> new ItemNameBlockItem(TLBlocksRegistry.HEALINGCROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RAW_HEALING_CROP = ITEMS.register("raw_healing_crop", () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventbus){
        System.out.println("Registering Items");
        ITEMS.register(eventbus);
    }
}
