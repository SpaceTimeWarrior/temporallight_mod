package net.tsw.Temporal_Light.util;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.tsw.Temporal_Light.Temporal_Light;
import net.tsw.Temporal_Light.blocks.entity.TL_HS_AssemblerEntity;

import java.util.function.Supplier;

public class TLNetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(Temporal_Light.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;
        INSTANCE.messageBuilder(CraftPacket.class, id++)
                .decoder(CraftPacket::new)
                .encoder(CraftPacket::toBytes)
                .consumerMainThread(CraftPacket::handle)
                .add();
    }

    public static class CraftPacket {
        private final BlockPos pos;

        public CraftPacket(BlockPos pos) {
            this.pos = pos;
        }

        public CraftPacket(FriendlyByteBuf buf) {
            this.pos = buf.readBlockPos();
        }

        public void toBytes(FriendlyByteBuf buf) {
            buf.writeBlockPos(pos);
        }

        public void handle(Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    BlockEntity entity = player.level().getBlockEntity(pos);
                    if (entity instanceof TL_HS_AssemblerEntity assembler) {
                       // assembler.LightningWeldingCraft();
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
