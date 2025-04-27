package net.tsw.Temporal_Light.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;
import net.tsw.Temporal_Light.entity.TLEntityRegistry;
import net.tsw.Temporal_Light.items.TLItemRegistry;
import net.tsw.Temporal_Light.villager.TLVillagerRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = Temporal_Light.MOD_ID)
public class TLEventBusEvents {
    @SubscribeEvent
    public  static void registerSpawnPlacements(SpawnPlacementRegisterEvent event){
        event.register(TLEntityRegistry.KITSUNE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMobSpawnRules,SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(TLEntityRegistry.KITSUNE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules,SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == TLVillagerRegistry.END_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            //Novice
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,2),
                    new ItemStack(Items.ENDER_PEARL,1),
                    2,2,0.04f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.ENDER_PEARL,4),
                    new ItemStack(Items.EMERALD,1),
                    2,2,0.04f));
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,4),
                    new ItemStack(Blocks.END_STONE,32),
                    2,2,0.04f));
            //Apprentice
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16),
                    new ItemStack(TLBlocksRegistry.ERESPAWNANCHOR.get(), 1),
                    1, 4, 0.02f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,3),
                    new ItemStack(Items.SHULKER_SHELL,1),
                    2,4,0.18f));
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,3),
                    new ItemStack(Blocks.SHULKER_BOX,1),
                    2,4,0.18f));
            //Journeyman
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,1),
                    new ItemStack(Items.CHORUS_FRUIT,2),
                    4,4,0.18f));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,1),
                    new ItemStack(TLItemRegistry.HEALING_CROP_SEEDS.get(),2),
                    4,4,0.18f));
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,1),
                    new ItemStack(Items.PHANTOM_MEMBRANE,6),
                    4,4,0.18f));
            //Expert
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,12),
                    new ItemStack(Items.DRAGON_BREATH,1),
                    4,6,0.18f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.ENDER_PEARL,4),
                    new ItemStack(Items.EMERALD,1),
                    2,2,0.04f));
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,4),
                    new ItemStack(Blocks.END_ROD,4),
                    2,2,0.04f));
            //Master-Highest
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Blocks.EMERALD_BLOCK,64),
                    new ItemStack(Items.ENDER_DRAGON_SPAWN_EGG,1),
                    1,6,0.5f));
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Blocks.EMERALD_BLOCK,2),
                    new ItemStack(Blocks.DRAGON_HEAD,1),
                    1,6,0.5f));
        }
    }
}
