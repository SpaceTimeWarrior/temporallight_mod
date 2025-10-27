package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.Items.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import net.minecraft.potion.PotionHealth;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRegistry {
    public static Item TIMECRYSTALSHARD;
    public static Item HYPERSTEELINGOT;
    public static Item HYPERSTEELSWORD;
    public static Item HYPERSTEELPICKAXE;
    public static Item HYPERSTEELAXE;
    public static Item HYPERSTEELSHOVEL;
    public static Item HYPERSTEELHOE;

    public static Item HYPERSTEELHELMET;
    public static Item HYPERSTEELCHESTPLATE;
    public static Item HYPERSTEELLEGGINGS;
    public static Item HYPERSTEELBOOTS;


    public static Item SYNTHTIMECRYSTALSHARD;
    public static Item HEALING_POWDER;
    public static Item LIGHTSTEELINGOT;
    public static Item LIGHTSTEELSWORD;
    public static Item LIGHTSTEELPICKAXE;
    public static Item LIGHTSTEELAXE;
    public static Item LIGHTSTEELSHOVEL;
    public static Item LIGHTSTEELHOE;

    public static Item LIGHTSTEELHELMET;
    public static Item LIGHTSTEELCHESTPLATE;
    public static Item LIGHTSTEELLEGGINGS;
    public static Item LIGHTSTEELBOOTS;

    public static Item MAGIWOODCLUB;
    public static Item HYPERSTEELWIRE;
    public static Item REDSTONE_HYPERCOIL;
    public static Item DEATHMETAL;
    public static Item LIGHTNING_CHARGE;
    public static Item LIGHTNING_STAFF;
    public static Item FIRE_STAFF;
    public static Item KITSUNE_TAIL;
    //public static Item HYPERSTEELHORSEARMOR;
    public static Item MAGIWOODAXE;
    public static Item MAGIWOODPICKAXE;
    public static Item MAGIWOODHOE;
    public static Item MAGIWOODSHOVEL;
    public static Item MAGIWOODHELMET;
    public static Item MAGIWOODCHESTPLATE;
    public static Item MAGIWOODLEGGINGS;
    public static Item MAGIWOODBOOTS;
    public static Item LIGHTSTEELWIRE;

    public static final Item.ToolMaterial MAGIWOODTOOL = EnumHelper.addToolMaterial("magiwood",TLConfig.harvest_lv_floor, 8192,2F,8F,40).setRepairItem(new ItemStack(BlockRegistry.MAGIWOODPLANK));
    public static final ItemArmor.ArmorMaterial MAGIWOODARMOR = EnumHelper.addArmorMaterial("magiwood",8192,new int[]{4,10,14,4},40);
    public static final Item.ToolMaterial HYPERSTEELTOOL = EnumHelper.addToolMaterial("hypersteel",TLConfig.harvest_lv_floor,4096,12f,8f,30).setRepairItem(new ItemStack(ItemRegistry.HYPERSTEELINGOT));
    public static final ItemArmor.ArmorMaterial HYPERSTEELARMOR = EnumHelper.addArmorMaterial("hypersteel",4096,new int[]{4,10,14,4},40);
    public static final Item.ToolMaterial LIGHTSTEELTOOL = EnumHelper.addToolMaterial("lightsteel",TLConfig.harvest_lv_floor+1,16384,12F,16F,40).setRepairItem(new ItemStack(ItemRegistry.LIGHTSTEELINGOT));
    public static final ItemArmor.ArmorMaterial LIGHTSTEELARMOR = EnumHelper.addArmorMaterial("lightsteel",16384,new int[]{ 12, 24, 32, 12 }, 20);
    public static void setupforRegistry(){
        String modid = TemporalLightMod.MODID+":";
        TIMECRYSTALSHARD = new Time_Crystal_Shard().setUnlocalizedName("timecrystalshard").setMaxStackSize(7).setTextureName(modid+"timecrystalshard");
        SYNTHTIMECRYSTALSHARD = new Synthetic_Time_Crystal_Shard().setUnlocalizedName("synthetictimecrystalshard").setTextureName(modid+"synthetictimecrystalshard").setCreativeTab(TemporalLightMod.TemporalLightMaterials);
        HYPERSTEELINGOT = new HyperSteel_Ingot().setUnlocalizedName("hypersteelingot").setTextureName(modid+"hypersteelingot").setCreativeTab(TemporalLightMod.TemporalLightMaterials);
        LIGHTSTEELINGOT = new LightSteel_Ingot().setUnlocalizedName("lightsteelingot").setTextureName(modid+"lightsteelingot").setCreativeTab(TemporalLightMod.TemporalLightMaterials);
        HEALING_POWDER = new ItemFood(6,6,true).setAlwaysEdible().setPotionEffect(PotionHealth.heal.id,10,2,100f).setUnlocalizedName("healing_powder").setTextureName(modid+"healing_powder").setCreativeTab(TemporalLightMod.TemporalLightMaterials);
        HYPERSTEELSWORD = new HyperSteel_Sword(HYPERSTEELTOOL).setUnlocalizedName("hypersteelsword").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"hypersteelsword");
        HYPERSTEELPICKAXE = new HyperSteel_Pickaxe(HYPERSTEELTOOL).setUnlocalizedName("hypersteelpickaxe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"hypersteelpickaxe");
        HYPERSTEELAXE = new HyperSteel_Axe(HYPERSTEELTOOL).setUnlocalizedName("hypersteelaxe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"hypersteelaxe");
        HYPERSTEELSHOVEL = new HyperSteel_Shovel(HYPERSTEELTOOL).setUnlocalizedName("hypersteelshovel").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"hypersteelshovel");
        HYPERSTEELHOE = new HyperSteel_Hoe(HYPERSTEELTOOL).setUnlocalizedName("hypersteelhoe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"hypersteelhoe");

        HYPERSTEELHELMET = new HyperSteelArmor(HYPERSTEELARMOR,0,0).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("hypersteelhelmet").setTextureName(modid+"hypersteelhelmet");
        HYPERSTEELCHESTPLATE = new HyperSteelArmor(HYPERSTEELARMOR,0,1).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("hypersteelchestplate").setTextureName(modid+"hypersteelchestplate");
        HYPERSTEELLEGGINGS = new HyperSteelArmor(HYPERSTEELARMOR,0,2).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("hypersteelleggings").setTextureName(modid+"hypersteelleggings");
        HYPERSTEELBOOTS = new HyperSteelArmor(HYPERSTEELARMOR,0,3).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("hypersteelboots").setTextureName(modid+"hypersteelboots");

        LIGHTSTEELSWORD = new LightSteel_Sword(LIGHTSTEELTOOL).setUnlocalizedName("lightsteelsword").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"lightsteelsword");
        LIGHTSTEELPICKAXE = new LightSteel_Pickaxe(LIGHTSTEELTOOL).setUnlocalizedName("lightsteelpickaxe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"lightsteelpickaxe");
        LIGHTSTEELAXE = new LightSteel_Axe(LIGHTSTEELTOOL).setUnlocalizedName("lightsteelaxe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"lightsteelaxe");
        LIGHTSTEELSHOVEL = new LightSteel_Shovel(LIGHTSTEELTOOL).setUnlocalizedName("lightsteelshovel").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"lightsteelshovel");
        LIGHTSTEELHOE = new LightSteelHoe(LIGHTSTEELTOOL).setUnlocalizedName("lightsteelhoe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"lightsteelhoe");

        LIGHTSTEELHELMET = new LightSteel_Armor(LIGHTSTEELARMOR,0,0).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("lightsteelhelmet").setTextureName(modid+"lightsteelhelmet");
        LIGHTSTEELCHESTPLATE = new LightSteel_Armor(LIGHTSTEELARMOR,0,1).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("lightsteelchestplate").setTextureName(modid+"lightsteelchestplate");
        LIGHTSTEELLEGGINGS = new LightSteel_Armor(LIGHTSTEELARMOR,0,2).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("lightsteelleggings").setTextureName(modid+"lightsteelleggings");
        LIGHTSTEELBOOTS = new LightSteel_Armor(LIGHTSTEELARMOR,0,3).setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("lightsteelboots").setTextureName(modid+"lightsteelboots");

        MAGIWOODCLUB = new MagiwoodClub(MAGIWOODTOOL).setUnlocalizedName("magiwoodclub").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodclub");
        MAGIWOODAXE = new MagiwoodAxe(MAGIWOODTOOL).setUnlocalizedName("magiwoodclubaxe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodclubaxe");
        MAGIWOODHELMET = new Magiwood_Armor(MAGIWOODARMOR,0,0).setUnlocalizedName("magiwoodhelmet").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodhelmet");
        MAGIWOODCHESTPLATE = new Magiwood_Armor(MAGIWOODARMOR,0,1).setUnlocalizedName("magiwoodchestplate").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodchestplate");
        MAGIWOODLEGGINGS = new Magiwood_Armor(MAGIWOODARMOR,0,2).setUnlocalizedName("magiwoodleggings").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodleggings");
        MAGIWOODBOOTS = new Magiwood_Armor(MAGIWOODARMOR,0,3).setUnlocalizedName("magiwoodboots").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodboots");
        MAGIWOODPICKAXE = new MagiwoodPickaxe(MAGIWOODTOOL).setUnlocalizedName("magiwoodpickaxe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodpickaxe");
        MAGIWOODHOE = new MagiwoodHoe(MAGIWOODTOOL).setUnlocalizedName("magiwoodhoe").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodhoe");
        MAGIWOODSHOVEL = new MagiwoodShovel(MAGIWOODTOOL).setUnlocalizedName("magiwoodshovel").setCreativeTab(TemporalLightMod.TemporalLightTools).setTextureName(modid+"magiwoodshovel");
        LIGHTNING_STAFF = new LightningStaff(MAGIWOODTOOL).setUnlocalizedName("lightning_staff").setTextureName(modid+"lightning_staff").setCreativeTab(TemporalLightMod.TemporalLightTools);
        FIRE_STAFF = new FireStaff(MAGIWOODTOOL).setUnlocalizedName("fire_staff").setTextureName(modid+"fire_staff").setCreativeTab(TemporalLightMod.TemporalLightTools);


        HYPERSTEELWIRE = new ItemWire().setUnlocalizedName("hypersteelwire").setCreativeTab(TemporalLightMod.TemporalLightMaterials).setTextureName(modid+"hypersteelwire");
        REDSTONE_HYPERCOIL = new ItemWire().setUnlocalizedName("redstone_hypercoil").setCreativeTab(TemporalLightMod.TemporalLightMaterials).setTextureName(modid+"redstone_hypercoil");
        DEATHMETAL = new Item().setUnlocalizedName("deathmetal").setCreativeTab(TemporalLightMod.TemporalLightMaterials).setTextureName(modid+"deathmetal");
        //HYPERSTEELHORSEARMOR = new Item().setCreativeTab(TemporalLightMod.TemporalLightTools).setUnlocalizedName("hypersteelhorsearmor").setTextureName(modid+"hypersteelhorsearmor");
        LIGHTNING_CHARGE = new ItemLightningCharge().setUnlocalizedName("lightning_charge").setTextureName(modid+"lightning_charge").setCreativeTab(TemporalLightMod.TemporalLightTools);
        KITSUNE_TAIL = new KitsuneTail().setUnlocalizedName("kitsune_tail").setTextureName(modid+"kitsune_tail").setCreativeTab(TemporalLightMod.TemporalLightMaterials);
        LIGHTSTEELWIRE = new ItemWire().setUnlocalizedName("lightsteelwire").setTextureName(modid+"lightsteelwire").setCreativeTab(TemporalLightMod.TemporalLightMaterials);
    }
    public static void GmRegistry(){
        GameRegistry.registerItem(TIMECRYSTALSHARD,TIMECRYSTALSHARD.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELINGOT,HYPERSTEELINGOT.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELSWORD,HYPERSTEELSWORD.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELPICKAXE,HYPERSTEELPICKAXE.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELAXE,HYPERSTEELAXE.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELSHOVEL,HYPERSTEELSHOVEL.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELHOE,HYPERSTEELHOE.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELHELMET,HYPERSTEELHELMET.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELCHESTPLATE,HYPERSTEELCHESTPLATE.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELLEGGINGS,HYPERSTEELLEGGINGS.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELBOOTS,HYPERSTEELBOOTS.getUnlocalizedName());
        //GameRegistry.registerItem(HYPERSTEELHORSEARMOR,HYPERSTEELHORSEARMOR.getUnlocalizedName());
        GameRegistry.registerItem(SYNTHTIMECRYSTALSHARD,SYNTHTIMECRYSTALSHARD.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELINGOT,LIGHTSTEELINGOT.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELSWORD,LIGHTSTEELSWORD.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELPICKAXE,LIGHTSTEELPICKAXE.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELAXE,LIGHTSTEELAXE.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELSHOVEL,LIGHTSTEELSHOVEL.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELHOE,LIGHTSTEELHOE.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELHELMET,LIGHTSTEELHELMET.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELCHESTPLATE,LIGHTSTEELCHESTPLATE.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELLEGGINGS,LIGHTSTEELLEGGINGS.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELBOOTS,LIGHTSTEELBOOTS.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODCLUB,MAGIWOODCLUB.getUnlocalizedName());
        GameRegistry.registerItem(HEALING_POWDER,HEALING_POWDER.getUnlocalizedName());
        GameRegistry.registerItem(HYPERSTEELWIRE,HYPERSTEELWIRE.getUnlocalizedName());
        GameRegistry.registerItem(REDSTONE_HYPERCOIL,REDSTONE_HYPERCOIL.getUnlocalizedName());
        GameRegistry.registerItem(DEATHMETAL,DEATHMETAL.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTNING_CHARGE,LIGHTNING_CHARGE.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTNING_STAFF,LIGHTNING_STAFF.getUnlocalizedName());
        GameRegistry.registerItem(FIRE_STAFF,FIRE_STAFF.getUnlocalizedName());
        GameRegistry.registerItem(KITSUNE_TAIL,KITSUNE_TAIL.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODAXE,MAGIWOODAXE.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODHELMET,MAGIWOODHELMET.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODCHESTPLATE,MAGIWOODCHESTPLATE.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODLEGGINGS,MAGIWOODLEGGINGS.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODBOOTS,MAGIWOODBOOTS.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODPICKAXE,MAGIWOODPICKAXE.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODHOE,MAGIWOODHOE.getUnlocalizedName());
        GameRegistry.registerItem(MAGIWOODSHOVEL,MAGIWOODSHOVEL.getUnlocalizedName());
        GameRegistry.registerItem(LIGHTSTEELWIRE, LIGHTSTEELWIRE.getUnlocalizedName());
    }
    public static void preinitRegistry() {
        setupforRegistry();
        GmRegistry();
    }

}
