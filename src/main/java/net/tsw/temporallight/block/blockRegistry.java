package net.tsw.temporallight.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.block.custom.*;
import net.tsw.temporallight.block.custom.tree.MagiwoodTree;
import net.tsw.temporallight.item.ItemRegistry;
import net.tsw.temporallight.item.ItemgroupRegistry;

import java.util.function.Supplier;

public class blockRegistry {
    public static final DeferredRegister<Block>BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TemporalLight.MOD_ID);

    public static final RegistryObject<Block> SYNTHETICTIMECRYSTALORE = registerBlock("synthetictimecrystalore",()->new customBlock(AbstractBlock.Properties.create(Material.DRAGON_EGG).hardnessAndResistance(5f).harvestLevel(4).harvestTool(ToolType.HOE).setRequiresTool()));
    /* from iron hsl+   hypersteel
     168
     65
     -83
     0
      */
    /* lightsteel
    168
    65
    36
    0
     */
    public static final RegistryObject<Block> HYPERSTEEL_BLOCK = registerBlockim("hypersteelblock",()->new customBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(10f).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),42);
    public static final RegistryObject<Block> HYPERSTEEL_STAIRS = registerBlockim("hypersteelstairs",()->new StairsBlock(()->HYPERSTEEL_BLOCK.get().getDefaultState(),AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),42);
    public static final RegistryObject<Block> HYPERSTEEL_RAILING =registerBlockim("hypersteelrailing",()->new FenceBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),42);
    public static final RegistryObject<Block> HYPERSTEEL_RAILING_gate =registerBlockim("hypersteelrailinggate",()->new FenceGateBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),42);
    public static final RegistryObject<Block> HYPERSTEEL_SLAB =registerBlockim("hypersteelslab",()->new SlabBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),42);
    public static final RegistryObject<Block> HYPERSTEEL_BUTTON = registerBlockim("hypersteelbutton",()-> new StoneButtonBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement()),42);
    public static final RegistryObject<Block> HYPERSTEEL_PRESSUREPLATE = registerBlockim("hypersteelpressureplate",()->new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement().sound(SoundType.STONE)),42);
    public static final RegistryObject<Block> MAGIWOODLOG =registerBlockim("magiwooodlog",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    /* from acacia hsl+ magiwood
    -80
    75
    22
    0
    */
    public static final RegistryObject<Block> MAGIWOOD =registerBlockim("magiwoood",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPEDLOG =registerBlockim("magiwooodstrippedlog",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPED =registerBlockim("magiwooodstripped",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOODPLANKS =registerBlockim("magiwooodplanks",()->new customBlock(AbstractBlock.Properties.from(Blocks.OAK_PLANKS).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOOD_STAIRS = registerBlockim("magiwoodstairs",()->new StairsBlock(()->MAGIWOODPLANKS.get().getDefaultState(),AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_FENCE =registerBlockim("magiwoodfence",()->new FenceBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_FENCE_gate =registerBlockim("magiwoodfencegate",()->new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_SLAB =registerBlockim("magiwoodslab",()->new SlabBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_BUTTON = registerBlockim("magiwoodbutton",()-> new StoneButtonBlock(AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement()),42);
    public static final RegistryObject<Block> MAGIWOOD_PRESSUREPLATE = registerBlockim("magiwoodpressureplate",()->new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().sound(SoundType.WOOD)),42);
    public static final RegistryObject<Block> LIGHTSTEELBLOCK = registerBlockim("lightsteelblock",()->new customBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),64);
    public static final RegistryObject<Block> LIGHTSTEELSTAIRS = registerBlockim("lightsteelstairs",()->new StairsBlock(()->LIGHTSTEELBLOCK.get().getDefaultState(),AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),64);
    public static final RegistryObject<Block> LIGHTSTEELFENCE = registerBlockim("lightsteelfence",()->new FenceBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),64);
    public static final RegistryObject<Block> LIGHTSTEELFENCEGATE = registerBlockim("lightsteelfencegate",()->new FenceGateBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),64);
    public static final RegistryObject<Block> LIGHTSTEELSLAB = registerBlockim("lightsteelslab",()->new SlabBlock(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),64);
    public static final RegistryObject<Block> LIGHTSTEELBUTTON = registerBlockim("lightsteelbutton",()->new StoneButtonBlock(AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement()),64);
    public static final RegistryObject<Block> LIGHTSTEELPRESSUREPLATE = registerBlockim("lightsteelpressureplate",()->new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,AbstractBlock.Properties.create(Material.IRON).doesNotBlockMovement().sound(SoundType.METAL)),64);
    public static final RegistryObject<Block> MAGIWOODLEAVES =registerBlockim("magiwooodleaves",()->new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(.75F).tickRandomly().sound(SoundType.PLANT).notSolid()),64);
    public static final RegistryObject<Block> MAGIWOODSAPLING =registerBlockim("magiwooodsapling",()->new TLSaplingBlock(new MagiwoodTree(),AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)),64);
    public static final RegistryObject<Block> HYPERSTEEL_ASSEMBLER = registerBlockim("hypersteelassembler",()->new assemblerBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(10f).harvestLevel(4).harvestTool(ToolType.PICKAXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOODPORTAL = registerBlockim("magiwoodportal",()->new customPortalBlock(AbstractBlock.Properties.create(Material.PORTAL).doesNotBlockMovement()),64);
    public static final RegistryObject<Block> Earth44PortalS = registerBlockim("earth44portal",()->new earth44portalBlock(AbstractBlock.Properties.create(Material.PORTAL).doesNotBlockMovement()),64);
    public static final RegistryObject<Block> PHOENIXEGG = registerBlockim("phoenixegg",()->new PhoenixEggBlock(AbstractBlock.Properties.create(Material.DRAGON_EGG, MaterialColor.CRIMSON_HYPHAE)),1);
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T> registerBlockim(String name, Supplier<T> block,int stacksize){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn,stacksize,true);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T>block){
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties().group(ItemgroupRegistry.TemporalLightBlocks)));
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T>block,int stacksize,boolean immune){
        if(!immune) {
            ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(ItemgroupRegistry.TemporalLightBlocks)));
        }else{
            ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(ItemgroupRegistry.TemporalLightBlocks).isImmuneToFire()));
        }
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
