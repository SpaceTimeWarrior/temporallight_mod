package net.tsw.Temporal_Light.JSON;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tsw.Temporal_Light.JSON.LOOT.TLGENGlobalLootModifierProvider;
import net.tsw.Temporal_Light.Temporal_Light;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Temporal_Light.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class TLGENDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        //generator.addProvider(event.includeServer(), TLGENLootTableProvider.create(packOutput));
        generator.addProvider(event.includeClient(),new TLGENBlockStateProvider(packOutput,existingFileHelper));
        generator.addProvider(event.includeClient(),new TLGENItemModelsProvider(packOutput,existingFileHelper));
        TLGENBlockTagProvider blockTagProvider = generator.addProvider(event.includeServer(),new TLGENBlockTagProvider(packOutput,lookupProvider,existingFileHelper));
        generator.addProvider(event.includeServer(),new TLGENItemTagGenerator(packOutput,lookupProvider,blockTagProvider.contentsGetter(),existingFileHelper));
        generator.addProvider(event.includeServer(), new TLGENGlobalLootModifierProvider(packOutput));
        generator.addProvider(event.includeClient(), new TLGENPaintingVarientTagProvider(packOutput, lookupProvider, existingFileHelper));
        //generator.addProvider(event.includeServer(), new TLGENDatapackEntries(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new TLGENPOI_TagProvider(packOutput, lookupProvider,existingFileHelper));
        generator.addProvider(event.includeServer(), new TLGENWorldGenProvider(packOutput,lookupProvider));
    }
}
