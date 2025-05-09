package net.tsw.Temporal_Light.JSON;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.Temporal_Light;

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
        trimmedArmorItem(TLItemRegistry.HYPERSTEELHELMET);
        trimmedArmorItem(TLItemRegistry.HYPERSTEELCHESTPLATE);
        trimmedArmorItem(TLItemRegistry.HYPERSTEELLEGGINGS);
        trimmedArmorItem(TLItemRegistry.HYPERSTEELBOOTS);
        handheldItem(TLItemRegistry.MAGIWOODCLUB);
        trimmedArmorItem(TLItemRegistry.MAGIWOOD_HELMET);
        trimmedArmorItem(TLItemRegistry.MAGIWOOD_CHESTPLATE);
        trimmedArmorItem(TLItemRegistry.MAGIWOOD_LEGGINGS);
        trimmedArmorItem(TLItemRegistry.MAGIWOOD_BOOTS);
        trimmedArmorItem(TLItemRegistry.LIGHTSTEELHELMET);
        trimmedArmorItem(TLItemRegistry.LIGHTSTEELCHESTPLATE);
        trimmedArmorItem(TLItemRegistry.LIGHTSTEELLEGGINGS);
        trimmedArmorItem(TLItemRegistry.LIGHTSTEELBOOTS);
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
        fenceItem(TLBlocksRegistry.LIGHTSTEELFENCE,TLBlocksRegistry.LIGHTSTEELBLOCK);
        buttonItem(TLBlocksRegistry.LIGHTSTEELBUTTON,TLBlocksRegistry.LIGHTSTEELBLOCK);
        evenSimplerBlockItem(TLBlocksRegistry.LIGHTSTEELSTAIRS);
        evenSimplerBlockItem(TLBlocksRegistry.LIGHTSTEELFENCEGATE);
        evenSimplerBlockItem(TLBlocksRegistry.LIGHTSTEELSLAB);
        evenSimplerBlockItem(TLBlocksRegistry.LIGHTSTEELPRESSUREPLATE);
        handheldItem(TLItemRegistry.LIGHTSTEELAXE);
        handheldItem(TLItemRegistry.LIGHTSTEELHOE);
        handheldItem(TLItemRegistry.LIGHTSTEELPICKAXE);
        handheldItem(TLItemRegistry.LIGHTSTEELSHOVEL);
        handheldItem(TLItemRegistry.LIGHTSTEELSWORD);
        handheldItem(TLItemRegistry.LIGHTNING_STAFF);
        handheldItem(TLItemRegistry.FIRE_STAFF);
        handheldItem(TLItemRegistry.EARTH_STAFF);
        handheldItem(TLItemRegistry.REDSTONE_STAFF);
        handheldItem(TLItemRegistry.LIFE_STAFF);
        saplingItem(TLBlocksRegistry.MAGIWOODSAPLING);
        simpleItem(TLItemRegistry.HEALING_POWDER);
        simpleItem(TLItemRegistry.HEALING_CROP_SEEDS);
        simpleItem(TLItemRegistry.RAW_HEALING_CROP);
        simpleItem(TLItemRegistry.MAGIC_ESSENCE_NULL);
        simpleItem(TLItemRegistry.MAGIC_ESSENCE_ELECTRICITY);
        simpleItem(TLItemRegistry.MAGIC_ESSENCE_FIRE);
        simpleItem(TLItemRegistry.MAGIC_ESSENCE_FIRE_BURNABLE);
        simpleItem(TLItemRegistry.MAGIC_ESSENCE_EARTH);
        simpleItem(TLItemRegistry.MAGIC_ESSENCE_LIFE);
        simpleItem(TLItemRegistry.LIGHTNING_CHARGE);
        simpleItem(TLItemRegistry.HYPERSTEELHORSEARMOR);
        simpleItem(TLItemRegistry.HYPERSTEELWIRE);
        simpleItem(TLItemRegistry.REDSTONE_HYPERCOIL);
        withExistingParent(TLItemRegistry.KITSUNE_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
        withExistingParent(TLItemRegistry.PHOENIX_F_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
    }
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = Temporal_Light.MOD_ID; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc =  ResourceLocation.fromNamespaceAndPath(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                 ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID,"block/" + item.getId().getPath()));
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
