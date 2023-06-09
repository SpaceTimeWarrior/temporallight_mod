package net.tsw.temporallight.item;

import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.Entity.Mob.MobRegistry;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.integration.compatabilityItemRegistry;
import net.tsw.temporallight.item.custom.DressArmorItem;
import net.tsw.temporallight.item.custom.DyeableDressArmorItem;
import net.tsw.temporallight.item.custom.TLCustomBowItem;
import net.tsw.temporallight.item.custom.custom_spawn_eggs;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TemporalLight.MOD_ID);
    public static final RegistryObject<Item> TIMECRYSTALSHARD = ITEMS.register("timecrystalshard", () -> new customItem(new Item.Properties().maxStackSize(7).group(ItemgroupRegistry.TemporalLightProphecygroup)));
    public static final RegistryObject<Item> SYNTHTIMECRYSTALSHARD = ITEMS.register("synthetictimecrystalshard", () -> new customItem(new Item.Properties().maxStackSize(32).group(ItemgroupRegistry.TemporalLightMaterials)));
    public static final RegistryObject<Item> HYPERSTEELINGOT = ITEMS.register("hypersteelingot", () -> new customItem(new Item.Properties().group(ItemgroupRegistry.TemporalLightMaterials).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELSWORD = ITEMS.register("hypersteelsword", () -> new SwordItem(Itemtiers.HYPERSTEEL, 8, 4F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELPICKAXE = ITEMS.register("hypersteelpickaxe", () -> new PickaxeItem(Itemtiers.HYPERSTEEL, 6, 2F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELAXE = ITEMS.register("hypersteelaxe", () -> new AxeItem(Itemtiers.HYPERSTEEL, 16, -2F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELSHOVEL = ITEMS.register("hypersteelshovel", () -> new ShovelItem(Itemtiers.HYPERSTEEL, 2, 2F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELHOE = ITEMS.register("hypersteelhoe", () -> new HoeItem(Itemtiers.HYPERSTEEL, 1, 0, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELBOOTS = ITEMS.register("hypersteelboots", () -> new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.FEET, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELLEGGINGS = ITEMS.register("hypersteelleggings", () -> new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELCHESTPLATE = ITEMS.register("hypersteelchestplate", () -> new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LEATHERDRESS = ITEMS.register("leatherdress", () -> new DyeableDressArmorItem(ArmorMaterial.LEATHER, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS)));
    public static final RegistryObject<Item> GOLDDRESS = ITEMS.register("golddress", () -> new DressArmorItem(ArmorMaterial.GOLD, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS)));
    public static final RegistryObject<Item> IRONDRESS = ITEMS.register("irondress", () -> new DressArmorItem(ArmorMaterial.IRON, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS)));
    public static final RegistryObject<Item> DIAMONDDRESS = ITEMS.register("diamonddress", () -> new DressArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS)));
    public static final RegistryObject<Item> NETHERITEDRESS = ITEMS.register("netheritedress", () -> new DressArmorItem(ArmorMaterial.NETHERITE, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS)));
    public static final RegistryObject<Item> HYPERSTEELDRESS = ITEMS.register("hypersteeldress", () -> new DressArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELDRESS = ITEMS.register("lightsteeldress", () -> new DressArmorItem(armormaterialregistry.LIGHTSTEEL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> MAGIWOODDRESS = ITEMS.register("magiwooddress", () -> new DressArmorItem(armormaterialregistry.MAGIWOOD, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELHELMET = ITEMS.register("hypersteelhelmet", () -> new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELHORSEARMOR = ITEMS.register("hypersteelhorsearmor", () -> new HorseArmorItem(16, "hypersteel", new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELBOW = ITEMS.register("hypersteelbow", () -> new TLCustomBowItem(new Item.Properties().group(ItemgroupRegistry.TemporalLightMaterials).group(ItemgroupRegistry.TemporalLightTOOLS).defaultMaxDamage(4000).isImmuneToFire(), 0));

    public static final RegistryObject<Item> LIGHTSTEELINGOT = ITEMS.register("lightsteelingot", () -> new customItem(new Item.Properties().group(ItemgroupRegistry.TemporalLightMaterials).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELSWORD = ITEMS.register("lightsteelsword", () -> new SwordItem(Itemtiers.LIGHTSTEEL, 8, 4F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> MAGIWOODCLUB = ITEMS.register("magiwoodclub", () -> new SwordItem(Itemtiers.MAGIWOOD, 0, 0F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> MAGIWOODBOOTS = ITEMS.register("magiwoodboots", () -> new ArmorItem(armormaterialregistry.MAGIWOOD, EquipmentSlotType.FEET, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> MAGIWOODLEGGINGS = ITEMS.register("magiwoodleggings", () -> new ArmorItem(armormaterialregistry.MAGIWOOD, EquipmentSlotType.LEGS, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> MAGIWOODCHESTPLATE = ITEMS.register("magiwoodchestplate", () -> new ArmorItem(armormaterialregistry.MAGIWOOD, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> MAGIWOODHELMET = ITEMS.register("magiwoodhelmet", () -> new ArmorItem(armormaterialregistry.MAGIWOOD, EquipmentSlotType.HEAD, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELPICKAXE = ITEMS.register("lightsteelpickaxe", () -> new PickaxeItem(Itemtiers.LIGHTSTEEL, 6, 2F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELAXE = ITEMS.register("lightsteelaxe", () -> new AxeItem(Itemtiers.LIGHTSTEEL, 16, -2F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELSHOVEL = ITEMS.register("lightsteelshovel", () -> new ShovelItem(Itemtiers.LIGHTSTEEL, 2, 2F, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELHOE = ITEMS.register("lightsteelhoe", () -> new HoeItem(Itemtiers.LIGHTSTEEL, 1, 0, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELBOOTS = ITEMS.register("lightsteelboots", () -> new ArmorItem(armormaterialregistry.LIGHTSTEEL, EquipmentSlotType.FEET, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELLEGGINGS = ITEMS.register("lightsteelleggings", () -> new ArmorItem(armormaterialregistry.LIGHTSTEEL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELCHESTPLATE = ITEMS.register("lightsteelchestplate", () -> new ArmorItem(armormaterialregistry.LIGHTSTEEL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELHELMET = ITEMS.register("lightsteelhelmet", () -> new ArmorItem(armormaterialregistry.LIGHTSTEEL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> LIGHTSTEELHORSEARMOR = ITEMS.register("lightsteelhorsearmor", () -> new HorseArmorItem(18, "lightsteel", new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));

    public static final RegistryObject<custom_spawn_eggs> PHEONIX_SPAWN_EGG = ITEMS.register("phoenix_spawn_egg", () -> new custom_spawn_eggs(MobRegistry.PHEONIX, 0xFF0000, 0xFF9B00, new Item.Properties().group(ItemgroupRegistry.TemporalLightProphecygroup)));
    public static final RegistryObject<custom_spawn_eggs> MALE_PHEONIX_SPAWN_EGG = ITEMS.register("male_phoenix_spawn_egg", () -> new custom_spawn_eggs(MobRegistry.MALEPHEONIX, 0xFF0000, 0x7bd4ff, new Item.Properties().group(ItemgroupRegistry.TemporalLightProphecygroup)));
    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
        compatabilityItemRegistry.register(eventbus);
    }

    @OnlyIn(Dist.CLIENT)
    public static void register(ItemColors itemColors)
    {
        itemColors.register((itemstack, index) -> index < 1 ? -1 : ((IDyeableArmorItem) itemstack.getItem()).getColor(itemstack), LEATHERDRESS.get());
    }

    // /give @p written_book{pages:['{"text":"\\nThis is a prophecy about either the end of reality or the renewal of it. It all starts with a child of light being born with an abnormal amount of magic power; this child will be known as the lightmage.\\n "}','{"text":"The light mage filled with determination and magic tames a beast and has a child. This child will be known as the light crystal. Soon after the light crystal is born the beast goes missing. "}','{"text":"This creates a power struggle in which the controller takes over and rules. At a similar time a phoenix will be born with magic power primarily consisting not of fire but of ice they will be known as the dangerous cold. "}','{"text":"The light crystal and the dangerous cold join forces under the tutelage of one of the fathers. Years pass and a cataclysm occurs in a distant land. Which triggers the dangerous cold and the light crystal to travel multiple distant lands to create a blade of pure time. "}','{"text":"From here on the prophecy is permanently locked in and if anything before this does not happen the prophecy is void."}','{"text":"Soon after the blade is created, a darkness arrives with the intention to raze all of existence. A father of the dangerous cold sacrifices themselves of the group. Reality\\u2019s first potential fail is if the father is unable to protect either the light crystal or the dangerous cold from darkness\\u2019 blade. The second is soon after the light crystal loses control in grief. If the aspect of balance dies all is lost. "}','{"text":"If reality is not fully destroyed the light crystal and the dangerous cold unites the forces of heaven and hell to contain the darkness.\\nIf any of what was said does not happen reality is destroyed and replaced with dark variants of reality under complete control of the darkness."}','{"text":" Otherwise the dangerous cold will eventually die alone in the cold depths of space. And the light crystal is betrayed by family but reality is safe while one lives.last all phoenixes and humans will go extinct at least once each."}'],title:"temporal Prophecy abridged",author:unknown,generation:3}
}