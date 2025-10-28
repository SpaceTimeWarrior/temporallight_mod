package com.TimeSpaceWarrior.TemporalLightMod;

import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.KitsuneItem;
import com.TimeSpaceWarrior.TemporalLightMod.entity.kitsune.EntityKitsune;
import com.TimeSpaceWarrior.TemporalLightMod.network.PacketSyncInventory;
import com.TimeSpaceWarrior.TemporalLightMod.tile_entity.HyperSteel_Assembler_TileEntity;
import com.TimeSpaceWarrior.TemporalLightMod.world.DimensionRegistry;
import com.TimeSpaceWarrior.TemporalLightMod.world.TemporalLightWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

@Mod(modid = TemporalLightMod.MODID,name=TemporalLightMod.NAME, version = TemporalLightMod.VERSION)
public class TemporalLightMod
{
    public static final String MODID = "tmpl_lgt";
    public static final String NAME = "Temporal Light Mod";
    public static final String VERSION = "1.0";
    public static final int HYPERSTEEL_ASSEMBLER_GUIID = 0;
    public static final int KITSUNE_GUIID = 1;
    public static Assembler_Recipe assem_recipe = new Assembler_Recipe();
    public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel("temporal_light");
    public static int packetId = 0;
    public static int stariId = 200;

    public static ArrayList<Item> KitsuneRandomTame = new ArrayList<Item>(0);//for random taming preference
    public static ArrayList<KitsuneItem> KitsuneAltRandomTame = new ArrayList<KitsuneItem>(0);//cooked safe alternate form. there os a linking number to link to Random Tame
    public static ArrayList<Item> KitsuneBadGut = new ArrayList<Item>(0);//gurentees taming but has negitive drawbacks
    public static ArrayList<Item> KitsuneGut = new ArrayList<Item>(0);//guarentees taming

    public static int getKitsuneRandomTamebyItem(Item item){
        //returns the id of the item provided
        //-1 if item is null
        //-2 if item is not in KitsuneRandomTame
        //0+ if successful
        if(item == null){
            return -1;
        }
        for(int i=0;i<KitsuneRandomTame.size();i++){
            if(KitsuneRandomTame.get(i).equals(item)){
                return i;
            }
        }
        return -2;
    }
    public static ArrayList<Item> getListOfAltFoods(int index){
        //input int index
        //outputs
        //null:Error
        //Arraylist of items matching the index
        if(index<0||index>=KitsuneRandomTame.size()){
            return null;
        }
        ArrayList<Item> return_ItemList = new ArrayList<Item>(0);
        for(int i=0;i<KitsuneAltRandomTame.size();i++){
            KitsuneItem tmp = KitsuneAltRandomTame.get(i);
            if(tmp.num == index){
                return_ItemList.add(tmp.item);
            }
        }
        if(return_ItemList.size()==0) {
            return null;
        }else{
            return return_ItemList;
        }
    }

    @SuppressWarnings("unchecked")
    public static void registerEntityEgg(Class<? extends Entity>entity,int PrimaryColor, int Secondarycolor){
        int id = getUniqueEntityID();
        EntityList.IDtoClassMapping.put(id,entity);
        EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id,PrimaryColor,Secondarycolor));
    }
    public static int getUniqueEntityID(){
        do{
            stariId++;
        }while (EntityList.getStringFromID(stariId)!=null);
            return stariId;
    }
    @Mod.Instance(MODID)
    public static TemporalLightMod instance;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event){
        TLConfig.Load_Config(event);
        network.registerMessage(PacketSyncInventory.Handler.class, PacketSyncInventory.class, packetId++, Side.CLIENT);

        KitsuneRandomTame.add(Items.chicken);
        KitsuneRandomTame.add(Items.beef);
        KitsuneRandomTame.add(Items.porkchop);
        KitsuneRandomTame.add(Items.fish);


        KitsuneAltRandomTame.add(new KitsuneItem(Items.cooked_chicken,0));
        KitsuneAltRandomTame.add(new KitsuneItem(Items.cooked_beef,1));
        KitsuneAltRandomTame.add(new KitsuneItem(Items.cooked_porkchop,2));
        KitsuneAltRandomTame.add(new KitsuneItem(Items.cooked_fished,getKitsuneRandomTamebyItem(Items.fish)));

        KitsuneBadGut.add(Items.cookie);
        KitsuneBadGut.add(Items.mushroom_stew);

        NetworkRegistry.INSTANCE.registerGuiHandler(instance,new GUIRegistry());
        ItemRegistry.preinitRegistry();
        BlockRegistry.preinitRegistry();
        OreDictionary.registerOre("logWood",BlockRegistry.MAGIWOODLOG);
        OreDictionary.registerOre("treeSapling",BlockRegistry.MAGIWOODSAPLING);
        OreDictionary.registerOre("treeLeaves",BlockRegistry.MAGIWOODLEAVES);
        OreDictionary.registerOre("plankWood", BlockRegistry.MAGIWOODPLANK);
        RenderingRegistrys.Register();
        EntityRegistrys.Register();
        registerEntityEgg(EntityKitsune.class,0xffaf5344,0xffaf8844);
        BiomeRegistry.register();
        GameRegistry.registerWorldGenerator(new TemporalLightWorldGenerator(),0);
        DimensionRegistry.register();

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerTileEntity(HyperSteel_Assembler_TileEntity.class,"hypersteelassembler");
        CraftingRegistry.register();

        //MinecraftForge.EVENT_BUS.register(new HorseArmorHandler());
    }
    @EventHandler
    public void postinit(FMLPostInitializationEvent event){

    }
    public static CreativeTabs TemporalLightMaterials = new CreativeTabs("temporallightmaterials") {
        @Override
        public Item getTabIconItem() {
            return ItemRegistry.HYPERSTEELINGOT;
        }
    };
    public static CreativeTabs TemporalLightTools = new CreativeTabs("temporallighttools") {
        @Override
        public Item getTabIconItem() {
            return ItemRegistry.HYPERSTEELSWORD;
        }
    };
    public static CreativeTabs TemporalLightBlocks = new CreativeTabs("temporallightblocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlockRegistry.HYPERSTEEL_BLOCK);
        }
    };
}
