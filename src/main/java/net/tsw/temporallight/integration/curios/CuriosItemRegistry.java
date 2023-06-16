package net.tsw.temporallight.integration.curios;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.item.ItemgroupRegistry;

public class CuriosItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TemporalLight.MOD_ID);
    public static final RegistryObject<Item> SPEED_NECKLACE = ITEMS.register("speednecklace",() -> new speednecklaceItem(new Item.Properties().group(ItemgroupRegistry.TemporalLightTOOLS).isImmuneToFire()));

    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }
}
