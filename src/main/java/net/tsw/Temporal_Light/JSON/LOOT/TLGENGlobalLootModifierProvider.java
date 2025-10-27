package net.tsw.Temporal_Light.JSON.LOOT;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.tsw.Temporal_Light.Loot.TLAddItemModifier;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.items.TLItemRegistry;

public class TLGENGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public TLGENGlobalLootModifierProvider(PackOutput output) {
        super(output, Temporal_Light.MOD_ID);
    }

    @Override
    protected void start() {
        add("healing_seeds_from_creepers",new TLAddItemModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(ResourceLocation.parse("entities/creeper")).build(), LootItemRandomChanceCondition.randomChance(0.35f).build(), LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.35f,2.5f).build()}, TLItemRegistry.HEALING_CROP_SEEDS.get()));
        add("electrical_magic_essence_from_creepers",new TLAddItemModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(ResourceLocation.parse("entities/creeper")).build(),LootItemRandomChanceCondition.randomChance(0.25f).build(),LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25f,2.5f).build()},TLItemRegistry.MAGIC_ESSENCE_ELECTRICITY.get()));
        add("fire_magic_essence_from_blazes",new TLAddItemModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(ResourceLocation.parse("entities/blaze")).build(),LootItemRandomChanceCondition.randomChance(0.25f).build(),LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25f,2.5f).build()},TLItemRegistry.MAGIC_ESSENCE_FIRE.get()));
        add("earth_magic_essence_from_skeleton",new TLAddItemModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(ResourceLocation.parse("entities/skeleton")).build(),LootItemRandomChanceCondition.randomChance(0.25f).build(),LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25f,2.5f).build()},TLItemRegistry.MAGIC_ESSENCE_EARTH.get()));
        add("life_magic_essence_from_zombie",new TLAddItemModifier(new LootItemCondition[]{new LootTableIdCondition.Builder(ResourceLocation.parse("entities/zombie")).build(),LootItemRandomChanceCondition.randomChance(0.25f).build(),LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.25f,2.5f).build()},TLItemRegistry.MAGIC_ESSENCE_LIFE.get()));

    }
}
