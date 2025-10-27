package net.tsw.Temporal_Light.JSON;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.tsw.Temporal_Light.JSON.LOOT.TLGENLootTables;

import java.util.List;
import java.util.Set;

public class TLGENLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(TLGENLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
