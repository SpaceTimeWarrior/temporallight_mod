package net.tsw.Temporal_Light.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.items.custom.*;
import net.tsw.Temporal_Light.util.TLTagRegistry;

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

    public static final RegistryObject<Item>  LIGHTSTEELSWORD =  ITEMS.register("lightsteelsword", () -> new SwordItem(TLItemToolTiersRegistry.LIGHTSTEEL,8,4f,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item>  LIGHTSTEELPICKAXE =ITEMS.register("lightsteelpickaxe",()-> new PickaxeItem(TLItemToolTiersRegistry.LIGHTSTEEL,6,2f,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item>  LIGHTSTEELAXE = ITEMS.register("lightsteelaxe", () -> new AxeItem(TLItemToolTiersRegistry.LIGHTSTEEL,16,-2f,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item>  LIGHTSTEELSHOVEL = ITEMS.register("lightsteelshovel", () -> new ShovelItem(TLItemToolTiersRegistry.LIGHTSTEEL, 2,2f ,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item>  LIGHTSTEELHOE = ITEMS.register("lightsteelhoe", () -> new HoeItem(TLItemToolTiersRegistry.LIGHTSTEEL,1,0f,new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> LIGHTSTEELHELMET = ITEMS.register("lightsteelhelmet",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> LIGHTSTEELCHESTPLATE = ITEMS.register("lightsteelchestplate",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> LIGHTSTEELLEGGINGS = ITEMS.register("lightsteelleggings",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> LIGHTSTEELBOOTS = ITEMS.register("lightsteelboots",
            () -> new TLArmorItem(TLArmorMaterials.LIGHTSTEEL, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> KITSUNE_SPAWN_EGG = ITEMS.register("kitsune_spawn_egg",()->new ForgeSpawnEggItem(()-> TLEntityRegistry.KITSUNE.get(),0xffaf5344,0xffaf8844,new Item.Properties()));
    public static final RegistryObject<Item> PHOENIX_F_SPAWN_EGG = ITEMS.register("phoenix_f_spawn_egg",()->new ForgeSpawnEggItem(()-> TLEntityRegistry.PHOENIX_F.get(),0xffff0000,0xfffbf236,new Item.Properties()));
    public static final RegistryObject<Item> PHOENIX_M_SPAWN_EGG = ITEMS.register("phoenix_m_spawn_egg",()->new ForgeSpawnEggItem(()-> TLEntityRegistry.PHOENIX_M.get(),0xffff0000,0xfffbf236,new Item.Properties()));
    public static final RegistryObject<Item> HEALING_CROP_SEEDS = ITEMS.register("healing_crop_seeds", () -> new ItemNameBlockItem(TLBlocksRegistry.HEALINGCROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> RAW_HEALING_CROP = ITEMS.register("raw_healing_crop", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MAGIC_ESSENCE_NULL = ITEMS.register("magic_essence_null",()->new TLNull_Magic_crystal(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_ESSENCE_ELECTRICITY = ITEMS.register("magic_essence_electricity",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_ESSENCE_FIRE = ITEMS.register("magic_essence_fire",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_ESSENCE_FIRE_BURNABLE = ITEMS.register("magic_essence_fire_burnable",()->new TLFuelItem(new Item.Properties(),60,MAGIC_ESSENCE_NULL.get()));

    public static final RegistryObject<Item> LIGHTNING_STAFF = ITEMS.register("lightning_staff",()->new TLLightning_weapon(new Item.Properties(),64, Ingredient.of(TLTagRegistry.Items.MAGIC_ESSENCE_ELECTRICITY)));

    public static final RegistryObject<Item> LIGHTNING_CHARGE = ITEMS.register("lightning_charge",()->new TLLightningCharge(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_STAFF = ITEMS.register("fire_staff",()->new TLFireStaff(new Item.Properties(),64,Ingredient.of(TLTagRegistry.Items.MAGIC_ESSENCE_FIRE)));
    public static final RegistryObject<Item> MAGIC_ESSENCE_EARTH = ITEMS.register("magic_essence_earth",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> EARTH_STAFF = ITEMS.register("earth_staff",()->new TLEarth_staff(new Item.Properties(),128,Ingredient.of(TLTagRegistry.Items.MAGIC_ESSENCE_EARTH)));
    public static final RegistryObject<Item> REDSTONE_STAFF = ITEMS.register("redstone_staff",()->new TLEarth_staff(new Item.Properties(),128,Ingredient.of(TLTagRegistry.Items.MAGIC_ESSENCE_EARTH),new Earth_arraylists().redb));
    public static final RegistryObject<Item> MAGIC_ESSENCE_LIFE = ITEMS.register("magic_essence_life",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIFE_STAFF = ITEMS.register("life_staff",()->new TLLife_staff(new Item.Properties(),64,Ingredient.of(TLTagRegistry.Items.MAGIC_ESSENCE_LIFE)));
    public static final RegistryObject<Item> HYPERSTEELBOW = ITEMS.register("hypersteelbow",()->new TLBowItem(new Item.Properties().durability(4096).fireResistant(),Ingredient.of(HYPERSTEELINGOT.get())));
    public static final RegistryObject<Item> HYPERSTEELSHIELD = ITEMS.register("hypersteelshield",()->new TLShieldItem(new Item.Properties().durability(4096).fireResistant(),Ingredient.of(HYPERSTEELINGOT.get())));
    public static final RegistryObject<Item> HYPERSTEELHORSEARMOR = ITEMS.register("hypersteelhorsearmor",()->new TLHorseArmorItem(15, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/horse/armor/horse_armor_hypersteel.png"),new Item.Properties().fireResistant(),Ingredient.of(HYPERSTEELINGOT.get())));
    public static final RegistryObject<Item> HYPERSTEELWIRE = ITEMS.register("hypersteelwire",()->new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> REDSTONE_HYPERCOIL = ITEMS.register("redstone_hypercoil",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> KITSUNE_TAIL = ITEMS.register("kitsune_tail",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTSTEELWIRE = ITEMS.register("lightsteelwire",()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTSTEELBOW = ITEMS.register("lightsteelbow",()->new TLBowItem(new Item.Properties().durability(8192).fireResistant(),Ingredient.of(LIGHTSTEELINGOT.get())));
    public static final RegistryObject<Item> LIGHTSTEELHORSEARMOR = ITEMS.register("lightsteelhorsearmor",()->new TLHorseArmorItem(17, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/horse/armor/horse_armor_lightsteel.png"),new Item.Properties().fireResistant(),Ingredient.of(LIGHTSTEELINGOT.get())));
    public static final RegistryObject<Item> DEATHMETALHORSEARMOR = ITEMS.register("deathmetalhorsearmor",()->new TLHorseArmorItem(13, ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"textures/entity/horse/armor/horse_armor_deathmetal.png"),new Item.Properties().fireResistant(),Ingredient.of(Items.NETHERITE_INGOT)));
    public static final RegistryObject<Item>  TEMPORALBLADE =  ITEMS.register("temporal_blade", () -> new SwordItem(TLItemToolTiersRegistry.TIMECRYSTAL,20,4f,new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TEMPORAL_HANDLE = ITEMS.register("temporal_handle",()-> new Item(new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> TEMPORAL_GUARD = ITEMS.register("temporal_guard",()-> new Item(new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> TEMPORAL_STEEL = ITEMS.register("temporal_steel",()-> new Item(new Item.Properties().fireResistant().stacksTo(1)));







    public static void register(IEventBus eventbus){
        System.out.println("Registering Items");
        ITEMS.register(eventbus);
    }
}
