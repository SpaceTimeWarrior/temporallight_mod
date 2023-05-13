package net.tsw.temporallight.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tsw.temporallight.TemporalLight;
import net.tsw.temporallight.block.blockRegistry;

public class TileEntityRegistry {
    public static DeferredRegister<TileEntityType<?>>TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, TemporalLight.MOD_ID);
    public static RegistryObject<TileEntityType<assemblerTileEntity>> assemblertileentity =TILE_ENTITIES.register("hypersteelassembler",()->TileEntityType.Builder.create(
            assemblerTileEntity::new,blockRegistry.HYPERSTEEL_ASSEMBLER.get()).build(null));
    public static RegistryObject<TileEntityType<PortalTileEntity>> portalTileEntity =TILE_ENTITIES.register("portaltileentity",()->TileEntityType.Builder.create(
            PortalTileEntity::new,blockRegistry.MAGIWOODPORTAL.get()).build(null));
    public static void register(IEventBus eventbus){
        TILE_ENTITIES.register(eventbus);
    }
}
