package net.tsw.temporal_light.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.Direction;
import net.tsw.temporal_light.Blocks.Custom.TL_HS_Assembler_Block;
import net.tsw.temporal_light.Blocks.Custom.Wood.TLRotatedPillarBlock;
import net.tsw.temporal_light.Blocks.Custom.sign.block.TLHangingSignBlock;
import net.tsw.temporal_light.Blocks.Custom.sign.block.TLStandingSignBlock;
import net.tsw.temporal_light.Blocks.Custom.sign.block.TLWallHangingSignBlock;
import net.tsw.temporal_light.Blocks.Custom.sign.block.TLWallSignBlock;
import net.tsw.temporal_light.Items.TLItemRegistry;
import net.tsw.temporal_light.Temporal_Light;
import net.tsw.temporal_light.util.TLWoodTypeRegistry;

import java.util.function.Supplier;

public class TLBlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Temporal_Light.MOD_ID);


    public static final RegistryObject<Block> SYNTHETICTIMECRYSTALORE = registerBlock("synthetictimecrystalore",()->new DropExperienceBlock(UniformInt.of(3,6), BlockBehaviour.Properties.ofFullCopy(Blocks.DRAGON_EGG).strength(5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_BLOCK = registerBlockIM("hypersteelblock",()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_STAIRS = registerBlockIM("hypersteelstairs", ()-> new StairBlock(TLBlocksRegistry.HYPERSTEEL_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_RAILING = registerBlockIM("hypersteelrailing",()-> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_RAILING_gate = registerBlockIM("hypersteelrailinggate",()-> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE_GATE).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_SLAB = registerBlockIM("hypersteelslab",()-> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_BUTTON = registerBlockIM("hypersteelbutton",()-> new ButtonBlock(BlockSetType.IRON,10, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON)));
    public static final RegistryObject<Block> HYPERSTEEL_PRESSUREPLATE = registerBlockIM("hypersteelpressureplate",()-> new PressurePlateBlock(BlockSetType.IRON,BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_ASSEMBLER = registerBlockIM("hypersteelassembler",()->new TL_HS_Assembler_Block(BlockBehaviour.Properties.ofFullCopy(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()).strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MAGIWOODLOG =registerBlockIM("magiwooodlog",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOOD =registerBlockIM("magiwoood",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPEDLOG =registerBlockIM("magiwooodstrippedlog",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPED =registerBlockIM("magiwooodstripped",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOODPLANKS =registerBlockIM("magiwooodplanks",()->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return false;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 0;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 0;
        }
    },64);
    public static final RegistryObject<Block> MAGIWOODLEAVES =registerBlockIM("magiwooodleaves",()->new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
        @Override
        public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 10;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            return 1;
        }
    },64);

    public static final RegistryObject<Block> MAGIWOOD_SIGN =BLOCKS.register("magiwood_sign",()->new TLStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_WALL_SIGN =BLOCKS.register("magiwood_wall_sign",()->new TLWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_HANGING_SIGN =BLOCKS.register("magiwood_hanging_sign",()->new TLHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_WALL_HANGING_SIGN =BLOCKS.register("magiwood_wall_hanging_sign",()->new TLWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_STAIRS = registerBlockIM("magiwoodstairs", ()-> new StairBlock(TLBlocksRegistry.MAGIWOODPLANKS.get().defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> MAGIWOOD_FENCE = registerBlockIM("magiwoodfence",()-> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE)));
    public static final RegistryObject<Block> MAGIWOOD_FENCE_gate = registerBlockIM("magiwoodfencegate",()-> new FenceGateBlock(TLWoodTypeRegistry.MAGI,BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE_GATE)));
    public static final RegistryObject<Block> MAGIWOOD_SLAB = registerBlockIM("magiwoodslab",()-> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> MAGIWOOD_BUTTON = registerBlockIM("magiwoodbutton",()-> new ButtonBlock(BlockSetType.IRON,10, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<Block> MAGIWOOD_PRESSUREPLATE = registerBlockIM("magiwoodpressureplate",()-> new PressurePlateBlock(BlockSetType.CHERRY,BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK)));

    /*+
    public static final RegistryObject<Block> MAGIWOODLOG =registerBlockim("magiwooodlog",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);

    public static final RegistryObject<Block> MAGIWOOD =registerBlockim("magiwoood",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPEDLOG =registerBlockim("magiwooodstrippedlog",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPED =registerBlockim("magiwooodstripped",()->new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOODPLANKS =registerBlockim("magiwooodplanks",()->new customBlock(AbstractBlock.Properties.from(Blocks.OAK_PLANKS).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),64);
    public static final RegistryObject<Block> MAGIWOOD_STAIRS = registerBlockim("magiwoodstairs",()->new StairsBlock(()->MAGIWOODPLANKS.get().getDefaultState(),AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_FENCE =registerBlockim("magiwoodfence",()->new FenceBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_FENCE_gate =registerBlockim("magiwoodfencegate",()->new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_SLAB =registerBlockim("magiwoodslab",()->new SlabBlock(AbstractBlock.Properties.create(Material.WOOD).harvestLevel(4).harvestTool(ToolType.AXE).setRequiresTool()),42);
    public static final RegistryObject<Block> MAGIWOOD_BUTTON = registerBlockim("magiwoodbutton",()-> new StoneButtonBlock(AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement()),42);
    public static final RegistryObject<Block> MAGIWOODLEAVES =registerBlockim("magiwooodleaves",()->new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(.75F).tickRandomly().sound(SoundType.PLANT).notSolid()),64);


 */
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
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
        return TLItemRegistry.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().stacksTo(stack).fireResistant()));
    }
    private static <T extends Block>RegistryObject<Item>registerBlockItem(String name, RegistryObject<T> block,int stack){
        return TLItemRegistry.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().stacksTo(stack)));
    }
    public static void register(IEventBus eventbus){

        System.out.println("registering the blocks");
        BLOCKS.register(eventbus);
    }
}
