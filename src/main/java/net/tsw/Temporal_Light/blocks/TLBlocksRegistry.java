package net.tsw.Temporal_Light.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.Custom.TL_HS_Assembler_Block;
import net.tsw.Temporal_Light.blocks.Custom.Wood.TLRotatedPillarBlock;
import net.tsw.Temporal_Light.blocks.Custom.Wood.TLSaplingBlock;
import net.tsw.Temporal_Light.blocks.Custom.crops.TLCropBlock2high;
import net.tsw.Temporal_Light.blocks.Custom.sign.block.TLHangingSignBlock;
import net.tsw.Temporal_Light.blocks.Custom.sign.block.TLStandingSignBlock;
import net.tsw.Temporal_Light.blocks.Custom.sign.block.TLWallHangingSignBlock;
import net.tsw.Temporal_Light.blocks.Custom.sign.block.TLWallSignBlock;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.util.TLWoodTypeRegistry;
import net.tsw.Temporal_Light.world.tree.MagiwoodTreeGrower;

import java.util.function.Supplier;

public class TLBlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Temporal_Light.MOD_ID);


    public static final RegistryObject<Block> SYNTHETICTIMECRYSTALORE = registerBlock("synthetictimecrystalore",()->new DropExperienceBlock( BlockBehaviour.Properties.copy(Blocks.DRAGON_EGG).strength(5f).requiresCorrectToolForDrops(),UniformInt.of(3,6)));
    public static final RegistryObject<Block> SYNTHETICTIMECRYSTALORE_DEEPSLATE = registerBlock("synthetictimecrystalore_deepslate",()->new DropExperienceBlock( BlockBehaviour.Properties.copy(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get()).requiresCorrectToolForDrops(),UniformInt.of(3,6)));
    public static final RegistryObject<Block> SYNTHETICTIMECRYSTALORE_NETHER = registerBlock("synthetictimecrystalore_nether",()->new DropExperienceBlock( BlockBehaviour.Properties.copy(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get()).requiresCorrectToolForDrops(),UniformInt.of(3,6)));
    public static final RegistryObject<Block> SYNTHETICTIMECRYSTALORE_END = registerBlock("synthetictimecrystalore_end",()->new DropExperienceBlock( BlockBehaviour.Properties.copy(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE.get()).requiresCorrectToolForDrops(),UniformInt.of(3,6)));

    public static final RegistryObject<Block> HYPERSTEEL_BLOCK = registerBlockIM("hypersteelblock",()-> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_STAIRS = registerBlockIM("hypersteelstairs", ()-> new StairBlock(TLBlocksRegistry.HYPERSTEEL_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_RAILING = registerBlockIM("hypersteelrailing",()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_FENCE).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_RAILING_gate = registerBlockIM("hypersteelrailinggate",()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_FENCE_GATE).strength(10f).requiresCorrectToolForDrops(),WoodType.OAK));
    public static final RegistryObject<Block> HYPERSTEEL_SLAB = registerBlockIM("hypersteelslab",()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> HYPERSTEEL_BUTTON = registerBlockIM("hypersteelbutton",()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON), BlockSetType.IRON,10,true));
    public static final RegistryObject<Block> HYPERSTEEL_PRESSUREPLATE = registerBlockIM("hypersteelpressureplate",()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).strength(10f).requiresCorrectToolForDrops(),BlockSetType.IRON));
    public static final RegistryObject<Block> HYPERSTEEL_ASSEMBLER = registerBlockIM("hypersteelassembler",()->new TL_HS_Assembler_Block(BlockBehaviour.Properties.copy(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()).strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MAGIWOODLOG =registerBlockIM("magiwooodlog",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOOD =registerBlockIM("magiwoood",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPEDLOG =registerBlockIM("magiwooodstrippedlog",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOODSTRIPPED =registerBlockIM("magiwooodstripped",()->new TLRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> MAGIWOODPLANKS =registerBlockIM("magiwooodplanks",()->new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
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
    public static final RegistryObject<Block> MAGIWOODLEAVES =registerBlockIM("magiwooodleaves",()->new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
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

    public static final RegistryObject<Block> MAGIWOOD_SIGN =BLOCKS.register("magiwood_sign",()->new TLStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_WALL_SIGN =BLOCKS.register("magiwood_wall_sign",()->new TLWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_HANGING_SIGN =BLOCKS.register("magiwood_hanging_sign",()->new TLHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_WALL_HANGING_SIGN =BLOCKS.register("magiwood_wall_hanging_sign",()->new TLWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_STAIRS = registerBlockIM("magiwoodstairs", ()-> new StairBlock(TLBlocksRegistry.MAGIWOODPLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> MAGIWOOD_FENCE = registerBlockIM("magiwoodfence",()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_FENCE)));
    public static final RegistryObject<Block> MAGIWOOD_FENCE_gate = registerBlockIM("magiwoodfencegate",()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_FENCE_GATE),TLWoodTypeRegistry.MAGI));
    public static final RegistryObject<Block> MAGIWOOD_SLAB = registerBlockIM("magiwoodslab",()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> MAGIWOOD_BUTTON = registerBlockIM("magiwoodbutton",()-> new ButtonBlock( BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),BlockSetType.IRON,10,true));
    public static final RegistryObject<Block> MAGIWOOD_PRESSUREPLATE = registerBlockIM("magiwoodpressureplate",()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK),BlockSetType.CHERRY));
    public static final RegistryObject<Block> MAGIWOODSAPLING = registerBlock("magiwooodsapling",()->new TLSaplingBlock(new MagiwoodTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    /*

    public static final RegistryObject<Block> EATHERIAN_SLEEP_PORTAL = registerBlock("eatherian_sleep_portal",()->new TLEatherianPortal(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noCollission()));
    */



    public static final RegistryObject<Block> LIGHTSTEELBLOCK = registerBlockIM("lightsteelblock",()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> LIGHTSTEELSTAIRS = registerBlockIM("lightsteelstairs",()->new StairBlock(LIGHTSTEELBLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> LIGHTSTEELFENCE = registerBlockIM("lightsteelfence",()->new FenceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> LIGHTSTEELFENCEGATE = registerBlockIM("lightsteelfencegate",()->new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(),WoodType.OAK),64);
    public static final RegistryObject<Block> LIGHTSTEELSLAB = registerBlockIM("lightsteelslab",()->new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()),64);
    public static final RegistryObject<Block> LIGHTSTEELBUTTON = registerBlockIM("lightsteelbutton",()-> new ButtonBlock( BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),BlockSetType.IRON,10,true));
    public static final RegistryObject<Block> LIGHTSTEELPRESSUREPLATE = registerBlockIM("lightsteelpressureplate",()->new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.METAL),BlockSetType.GOLD),64);

    public static final RegistryObject<Block> HEALINGCROP = BLOCKS.register("healing_crop", () -> new TLCropBlock2high(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> ERESPAWNANCHOR = registerBlock("end_respawn_anchor",()->new RespawnAnchorBlock(BlockBehaviour.Properties.copy(Blocks.RESPAWN_ANCHOR)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> BLTR = BLOCKS.register(name,block);
        registerBlockItem(name,BLTR,64);
        return BLTR;
    }
    private static <T extends Block>RegistryObject<T> registerBlockim(String name, Supplier<T> block){
        return registerBlockIM(name,block);
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
