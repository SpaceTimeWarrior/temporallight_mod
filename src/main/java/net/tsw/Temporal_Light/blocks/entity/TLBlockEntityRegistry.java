package net.tsw.Temporal_Light.blocks.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.Custom.sign.entities.TLSignBlockEntity;
import net.tsw.Temporal_Light.blocks.Custom.sign.entities.TLSignHangingBlockEntity;
import net.tsw.Temporal_Light.blocks.TLBlocksRegistry;

public class TLBlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Temporal_Light.MOD_ID);
   /* public static final RegistryObject<BlockEntityType<TLEatherian_portal>> EATHERIAN_PORTAL =
            BLOCK_ENTITIES.register("eatherian_portal", () ->
                    BlockEntityType.Builder.of(TLEatherian_portal::new,
                            TLBlocksRegistry.EATHERIAN_SLEEP_PORTAL.get()).build(null));
   */ public static final RegistryObject<BlockEntityType<TL_HS_AssemblerEntity>> TLASSEMBLER =
            BLOCK_ENTITIES.register("tlassembler", () ->
                    BlockEntityType.Builder.of(TL_HS_AssemblerEntity::new,
                            TLBlocksRegistry.HYPERSTEEL_ASSEMBLER.get()).build(null));

    public static final RegistryObject<BlockEntityType<TLSignBlockEntity>> TLSIGN =
            BLOCK_ENTITIES.register("tl_sign", () ->
                    BlockEntityType.Builder.of(TLSignBlockEntity::new,
                            TLBlocksRegistry.MAGIWOOD_SIGN.get(),TLBlocksRegistry.MAGIWOOD_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<TLSignHangingBlockEntity>> TLHANGINGSIGN =
            BLOCK_ENTITIES.register("tl_hanging_sign", () ->
                    BlockEntityType.Builder.of(TLSignHangingBlockEntity::new,
                            TLBlocksRegistry.MAGIWOOD_HANGING_SIGN.get(),TLBlocksRegistry.MAGIWOOD_WALL_HANGING_SIGN.get()).build(null));



    public static void register(IEventBus eventBus) {
        System.out.println("Registering the blockentities");
        BLOCK_ENTITIES.register(eventBus);
    }

}
