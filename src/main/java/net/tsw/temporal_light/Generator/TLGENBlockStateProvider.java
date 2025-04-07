package net.tsw.temporal_light.Generator;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Temporal_Light;

public class TLGENBlockStateProvider extends BlockStateProvider {
    public TLGENBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Temporal_Light.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockwithItem(TLBlocksRegistry.HYPERSTEEL_BLOCK);
        blockwithItem(TLBlocksRegistry.SYNTHETICTIMECRYSTALORE);

        stairsBlock(((StairBlock) TLBlocksRegistry.HYPERSTEEL_STAIRS.get()),blockTexture(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()));
        slabBlock(((SlabBlock) TLBlocksRegistry.HYPERSTEEL_SLAB.get()),blockTexture(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()),blockTexture(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()));

        buttonBlock(((ButtonBlock) TLBlocksRegistry.HYPERSTEEL_BUTTON.get()),blockTexture(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) TLBlocksRegistry.HYPERSTEEL_PRESSUREPLATE.get()),blockTexture(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()));

        fenceBlock(((FenceBlock) TLBlocksRegistry.HYPERSTEEL_RAILING.get()),blockTexture(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) TLBlocksRegistry.HYPERSTEEL_RAILING_gate.get()),blockTexture(TLBlocksRegistry.HYPERSTEEL_BLOCK.get()));

        logBlock(((RotatedPillarBlock) TLBlocksRegistry.MAGIWOODLOG.get()));
        axisBlock(((RotatedPillarBlock) TLBlocksRegistry.MAGIWOOD.get()), blockTexture(TLBlocksRegistry.MAGIWOODLOG.get()), blockTexture(TLBlocksRegistry.MAGIWOODLOG.get()));

        axisBlock(((RotatedPillarBlock) TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get()), blockTexture(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get()),
                 ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "block/magiwooodstrippedlog_top"));
        axisBlock(((RotatedPillarBlock) TLBlocksRegistry.MAGIWOODSTRIPPED.get()), blockTexture(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get()),
                blockTexture(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG.get()));

        blockItem(TLBlocksRegistry.MAGIWOODLOG);
        blockItem(TLBlocksRegistry.MAGIWOOD);
        blockItem(TLBlocksRegistry.MAGIWOODSTRIPPEDLOG);
        blockItem(TLBlocksRegistry.MAGIWOODSTRIPPED);

        blockwithItem(TLBlocksRegistry.MAGIWOODPLANKS);

        leavesBlock(TLBlocksRegistry.MAGIWOODLEAVES);

        signBlock(((StandingSignBlock) TLBlocksRegistry.MAGIWOOD_SIGN.get()),((WallSignBlock) TLBlocksRegistry.MAGIWOOD_WALL_SIGN.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));
        hangingSignBlock( TLBlocksRegistry.MAGIWOOD_HANGING_SIGN.get(), TLBlocksRegistry.MAGIWOOD_WALL_HANGING_SIGN.get(),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));
        stairsBlock(((StairBlock) TLBlocksRegistry.MAGIWOOD_STAIRS.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));
        slabBlock(((SlabBlock) TLBlocksRegistry.MAGIWOOD_SLAB.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));

        buttonBlock(((ButtonBlock) TLBlocksRegistry.MAGIWOOD_BUTTON.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) TLBlocksRegistry.MAGIWOOD_PRESSUREPLATE.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));

        fenceBlock(((FenceBlock) TLBlocksRegistry.MAGIWOOD_FENCE.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));
        fenceGateBlock(((FenceGateBlock) TLBlocksRegistry.MAGIWOOD_FENCE_gate.get()),blockTexture(TLBlocksRegistry.MAGIWOODPLANKS.get()));
        blockwithItem(TLBlocksRegistry.HYPERSTEEL_ASSEMBLER);
    }
    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),  ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Temporal_Light.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockwithItem(RegistryObject<Block>blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
