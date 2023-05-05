package net.tsw.temporallight.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.item.custom.TLCustomBowItem;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TemporalLight.MOD_ID) ;
    public static final RegistryObject<Item> TIMECRYSTALSHARD = ITEMS.register("timecrystalshard",()->new Item(new Item.Properties().maxStackSize(7).group(ItemgroupRegistry.TemporalLightProphecygroup)));
    public static final RegistryObject<Item> SYNTHTIMECRYSTALSHARD = ITEMS.register("synthetictimecrystalshard",()->new Item(new Item.Properties().maxStackSize(32).group(ItemgroupRegistry.TemporalLightMaterials)));
    public static final RegistryObject<Item> HYPERSTEELINGOT = ITEMS.register("hypersteelingot",()->new Item(new Item.Properties().group(ItemgroupRegistry.TemporalLightMaterials).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELSWORD = ITEMS.register("hypersteelsword",()->new SwordItem(Itemtiers.HYPERSTEEL,8,4F,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELPICKAXE = ITEMS.register("hypersteelpickaxe",()->new PickaxeItem(Itemtiers.HYPERSTEEL,6,2F,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELAXE = ITEMS.register("hypersteelaxe",()->new AxeItem(Itemtiers.HYPERSTEEL,16,-2F,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELSHOVEL = ITEMS.register("hypersteelshovel",()->new ShovelItem(Itemtiers.HYPERSTEEL,2,2F,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELHOE = ITEMS.register("hypersteelhoe",()->new HoeItem(Itemtiers.HYPERSTEEL,1,0,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELBOOTS = ITEMS.register("hypersteelboots",()->new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.FEET,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELLEGGINGS = ITEMS.register("hypersteelleggings",()->new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.LEGS,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELCHESTPLATE = ITEMS.register("hypersteelchestplate",()->new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.CHEST,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELHELMET = ITEMS.register("hypersteelhelmet",()->new ArmorItem(armormaterialregistry.HYPERSTEEL, EquipmentSlotType.HEAD,new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELHORSEARMOR = ITEMS.register("hypersteelhorsearmor",()->new HorseArmorItem(16,"hypersteel",new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));
    public static final RegistryObject<Item> HYPERSTEELBOW = ITEMS.register("hypersteelbow",()->new TLCustomBowItem(new Item.Properties().group(ItemgroupRegistry.TemporalLightMaterials).defaultMaxDamage(4000).isImmuneToFire(),0));

    public static final RegistryObject<Item> LIGHTSTEELINGOT = ITEMS.register("lightsteelingot",()->new Item(new Item.Properties().group(ItemgroupRegistry.TemporalLightMaterials).isImmuneToFire()));
    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
    // /give @p written_book{pages:['{"text":"\\nThis is a prophecy about either the end of reality or the renewal of it. It all starts with a child of light being born with an abnormal amount of magic power; this child will be known as the lightmage.\\n "}','{"text":"The light mage filled with determination and magic tames a beast and has a child. This child will be known as the light crystal. Soon after the light crystal is born the beast goes missing. "}','{"text":"This creates a power struggle in which the controller takes over and rules. At a similar time a phoenix will be born with magic power primarily consisting not of fire but of ice they will be known as the dangerous cold. "}','{"text":"The light crystal and the dangerous cold join forces under the tutelage of one of the fathers. Years pass and a cataclysm occurs in a distant land. Which triggers the dangerous cold and the light crystal to travel multiple distant lands to create a blade of pure time. "}','{"text":"From here on the prophecy is permanently locked in and if anything before this does not happen the prophecy is void."}','{"text":"Soon after the blade is created, a darkness arrives with the intention to raze all of existence. A father of the dangerous cold sacrifices themselves of the group. Reality\\u2019s first potential fail is if the father is unable to protect either the light crystal or the dangerous cold from darkness\\u2019 blade. The second is soon after the light crystal loses control in grief. If the aspect of balance dies all is lost. "}','{"text":"If reality is not fully destroyed the light crystal and the dangerous cold unites the forces of heaven and hell to contain the darkness.\\nIf any of what was said does not happen reality is destroyed and replaced with dark variants of reality under complete control of the darkness."}','{"text":" Otherwise the dangerous cold will eventually die alone in the cold depths of space. And the light crystal is betrayed by family but reality is safe while one lives.last all phoenixes and humans will go extinct at least once each."}'],title:"temporal Prophecy abridged",author:unknown,generation:3}
}