package net.tsw.temporal_light.Generator;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.temporal_light.Blocks.TLBlocksRegistry;
import net.tsw.temporal_light.Items.TLItemRegistry;
import net.tsw.temporal_light.Temporal_Light;

import java.util.LinkedHashMap;

public class TLGENItemModelsProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>,Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ,0.1F);
        trimMaterials.put(TrimMaterials.IRON,0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE,0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE,0.4F);
        trimMaterials.put(TrimMaterials.COPPER,0.5F);
        trimMaterials.put(TrimMaterials.GOLD,0.6F);
        trimMaterials.put(TrimMaterials.EMERALD,0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND,0.8F);
        trimMaterials.put(TrimMaterials.LAPIS,0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST,1.0F);

    }
    public TLGENItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Temporal_Light.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(TLItemRegistry.SYNTHTIMECRYSTALSHARD);
        simpleItem(TLItemRegistry.HYPERSTEELINGOT);
        simpleItem(TLItemRegistry.TIMECRYSTALSHARD);
        handheldItem(TLItemRegistry.HYPERSTEELAXE);
        handheldItem(TLItemRegistry.HYPERSTEELHOE);
        handheldItem(TLItemRegistry.HYPERSTEELPICKAXE);
        handheldItem(TLItemRegistry.HYPERSTEELSHOVEL);
        handheldItem(TLItemRegistry.HYPERSTEELSWORD);
        fenceItem(TLBlocksRegistry.HYPERSTEEL_RAILING,TLBlocksRegistry.HYPERSTEEL_BLOCK);
        buttonItem(TLBlocksRegistry.HYPERSTEEL_BUTTON,TLBlocksRegistry.HYPERSTEEL_BLOCK);
        evenSimplerBlockItem(TLBlocksRegistry.HYPERSTEEL_STAIRS);
        evenSimplerBlockItem(TLBlocksRegistry.HYPERSTEEL_RAILING_gate);
        evenSimplerBlockItem(TLBlocksRegistry.HYPERSTEEL_SLAB);
        fenceItem(TLBlocksRegistry.MAGIWOOD_FENCE,TLBlocksRegistry.MAGIWOODPLANKS);
        buttonItem(TLBlocksRegistry.MAGIWOOD_BUTTON,TLBlocksRegistry.MAGIWOODPLANKS);
        evenSimplerBlockItem(TLBlocksRegistry.MAGIWOOD_STAIRS);
        evenSimplerBlockItem(TLBlocksRegistry.MAGIWOOD_FENCE_gate);
        evenSimplerBlockItem(TLBlocksRegistry.MAGIWOOD_SLAB);
        simpleItem(TLItemRegistry.MAGI_SIGN);
        simpleItem(TLItemRegistry.MAGI_HANGING_SIGN);
        evenSimplerBlockItem(TLBlocksRegistry.HYPERSTEEL_PRESSUREPLATE);
        evenSimplerBlockItem(TLBlocksRegistry.MAGIWOOD_PRESSUREPLATE);
        simpleItem(TLItemRegistry.LIGHTSTEELINGOT);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item>item){
        return withExistingParent(item.getId().getPath(),
                 ResourceLocation.parse("item/generated")).texture("layer0",ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"item/"+ item.getId().getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(Temporal_Light.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block>baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"block/"+ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block>baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/button_inventory"))
                .texture("texture",ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"block/"+ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private ItemModelBuilder handheldItem(RegistryObject<Item>item){
        return withExistingParent(item.getId().getPath(),ResourceLocation.parse("item/handheld")).texture("layer0",ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"item/"+item.getId().getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block>baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"block/"+ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item){
        return withExistingParent(item.getId().getPath(),ResourceLocation.parse("item/generated")).texture("layer0",ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"item/"+item.getId().getPath()));
    }
}
