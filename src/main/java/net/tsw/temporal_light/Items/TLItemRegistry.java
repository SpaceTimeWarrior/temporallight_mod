package net.tsw.temporal_light.Items;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Items.custom.TLArmorItem;
import net.tsw.temporal_light.Temporal_Light;

public class TLItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Temporal_Light.MOD_ID);
    public static final int HelmetDurability = 11;
    public static final int ChestplateDurability = 16;
    public static final int LeggingsDurability = 15;
    public static final int BootsDurability = 13;
    public static final int BodyDurability = 16;
    public static final int HYPERSTEELDURABILITY = 42;

    public static final RegistryObject<Item> TIMECRYSTALSHARD = ITEMS.register("timecrystalshard",()->new Item(new Item.Properties().stacksTo(7)));
    public static final RegistryObject<Item> SYNTHTIMECRYSTALSHARD = ITEMS.register("synthetictimecrystalshard", () -> new Item(new Item.Properties().stacksTo(32)));
    public static final RegistryObject<Item> HYPERSTEELINGOT = ITEMS.register("hypersteelingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELSWORD = ITEMS.register("hypersteelsword", () -> new SwordItem(TLItemToolTiersRegistry.HYPERSTEEL, new Item.Properties().attributes(SwordItem.createAttributes(TLItemToolTiersRegistry.HYPERSTEEL,8,4F)).fireResistant()));
    public static final RegistryObject<Item> MAGIWOODCLUB = ITEMS.register("magiwoodclub", () -> new SwordItem(TLItemToolTiersRegistry.MAGIWOOD, new Item.Properties().fireResistant().attributes(SwordItem.createAttributes(TLItemToolTiersRegistry.MAGIWOOD,8,0))));

    public static final RegistryObject<Item> HYPERSTEELPICKAXE = ITEMS.register("hypersteelpickaxe", () -> new PickaxeItem(TLItemToolTiersRegistry.HYPERSTEEL, new Item.Properties().attributes(SwordItem.createAttributes(TLItemToolTiersRegistry.HYPERSTEEL,6,2F)).fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELAXE = ITEMS.register("hypersteelaxe", () -> new AxeItem(TLItemToolTiersRegistry.HYPERSTEEL, new Item.Properties().attributes(SwordItem.createAttributes(TLItemToolTiersRegistry.HYPERSTEEL,16,-2F)).fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELSHOVEL = ITEMS.register("hypersteelshovel", () -> new ShovelItem(TLItemToolTiersRegistry.HYPERSTEEL, new Item.Properties().attributes(SwordItem.createAttributes(TLItemToolTiersRegistry.HYPERSTEEL,2,2F)).fireResistant()));
    public static final RegistryObject<Item> HYPERSTEELHOE = ITEMS.register("hypersteelhoe", () -> new HoeItem(TLItemToolTiersRegistry.HYPERSTEEL, new Item.Properties().attributes(SwordItem.createAttributes(TLItemToolTiersRegistry.HYPERSTEEL,1,0F)).fireResistant()));

    public static final RegistryObject<Item> HYPERSTEELHELMET = ITEMS.register("hypersteelhelmet",
            ()-> new TLArmorItem(TLArmorMaterials.HYPERSTEEL_ARMOR_MATERIAL,ArmorItem.Type.HELMET,
                    new Item.Properties().durability(HelmetDurability*HYPERSTEELDURABILITY)));
    public static final RegistryObject<Item> HYPERSTEELLEGGINGS = ITEMS.register("hypersteelleggings",
            ()-> new TLArmorItem(TLArmorMaterials.HYPERSTEEL_ARMOR_MATERIAL,ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(LeggingsDurability*HYPERSTEELDURABILITY)));
    public static final RegistryObject<Item> HYPERSTEELCHESTPLATE = ITEMS.register("hypersteelchestplate",
            ()-> new TLArmorItem(TLArmorMaterials.HYPERSTEEL_ARMOR_MATERIAL,ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ChestplateDurability*HYPERSTEELDURABILITY)));
    public static final RegistryObject<Item> HYPERSTEELBOOTS = ITEMS.register("hypersteelboots",
            ()-> new TLArmorItem(TLArmorMaterials.HYPERSTEEL_ARMOR_MATERIAL,ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(BootsDurability*HYPERSTEELDURABILITY)));

    public static final RegistryObject<Item> MAGI_SIGN = ITEMS.register("magiwood_item_sign",()->new SignItem(new Item.Properties().stacksTo(16), TLBlocksRegistry.MAGIWOOD_SIGN.get(),TLBlocksRegistry.MAGIWOOD_WALL_SIGN.get()));
    public static final RegistryObject<Item> MAGI_HANGING_SIGN = ITEMS.register("magiwood_item_hanging_sign",()->new HangingSignItem(TLBlocksRegistry.MAGIWOOD_HANGING_SIGN.get(),TLBlocksRegistry.MAGIWOOD_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16) ));
    public static final RegistryObject<Item> HEALING_POWDER = ITEMS.register("healing_powder",()->new Item(new Item.Properties().food(TLFoodRegistry.HEALING_POWDER)));
    public static final RegistryObject<Item> MAGIWOOD_HELMET = ITEMS.register("magiwoodhelmet",
            ()-> new TLArmorItem(TLArmorMaterials.MAGIWOOD_ARMOR_MATERIAL,ArmorItem.Type.HELMET,
                    new Item.Properties().durability(HelmetDurability*HYPERSTEELDURABILITY*2)));
    public static final RegistryObject<Item> MAGIWOOD_LEGGINGS = ITEMS.register("magiwoodleggings",
            ()-> new TLArmorItem(TLArmorMaterials.MAGIWOOD_ARMOR_MATERIAL,ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(LeggingsDurability*HYPERSTEELDURABILITY*2)));
    public static final RegistryObject<Item> MAGIWOOD_CHESTPLATE = ITEMS.register("magiwoodchestplate",
            ()-> new TLArmorItem(TLArmorMaterials.MAGIWOOD_ARMOR_MATERIAL,ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ChestplateDurability*HYPERSTEELDURABILITY*2)));
    public static final RegistryObject<Item> MAGIWOOD_BOOTS = ITEMS.register("magiwoodboots",
            ()-> new TLArmorItem(TLArmorMaterials.MAGIWOOD_ARMOR_MATERIAL,ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(BootsDurability*HYPERSTEELDURABILITY*2)));

    public static final RegistryObject<Item> LIGHTSTEELINGOT = ITEMS.register("lightsteelingot",()->new Item(new Item.Properties().fireResistant()));
    /*
    public static final RegistryObject<Item> MAGIWOODCLUB = ITEMS.register("magiwoodclub", () -> new SwordItem(Itemtiers.MAGIWOOD, 0, 0F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));

    * */
    public static void register(IEventBus eventbus){
        System.out.println("Registering Items");
        ITEMS.register(eventbus);
    }
}
