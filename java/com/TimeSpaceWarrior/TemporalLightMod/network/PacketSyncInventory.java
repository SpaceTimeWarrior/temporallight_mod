package com.TimeSpaceWarrior.TemporalLightMod.network;

import com.TimeSpaceWarrior.TemporalLightMod.tile_entity.HyperSteel_Assembler_TileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketSyncInventory implements IMessage {
    public int x, y, z;
    public ItemStack stack;
    int slotIndex;
    public NBTTagCompound tag;

    public PacketSyncInventory() {}
    public PacketSyncInventory(TileEntity tile) {
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.tag = new NBTTagCompound();
        tile.writeToNBT(this.tag); // Full inventory state
    }
    public PacketSyncInventory(int x, int y, int z, int slotIndex, ItemStack stack) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.slotIndex = slotIndex;
        this.stack = stack != null ? stack.copy() : null; // Defensive copy
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        boolean isFullSync = (tag != null);
        buf.writeBoolean(isFullSync);
        if (isFullSync) {
            ByteBufUtils.writeTag(buf, tag);
        } else {
            buf.writeInt(slotIndex);
            buf.writeBoolean(stack != null);
            if (stack != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stack.writeToNBT(stackTag);
                ByteBufUtils.writeTag(buf, stackTag);
            }
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        boolean isFullSync = buf.readBoolean();
        if (isFullSync) {
            tag = ByteBufUtils.readTag(buf);
        } else {
            slotIndex = buf.readInt();
            boolean hasStack = buf.readBoolean();
            if (hasStack) {
                NBTTagCompound stackTag = ByteBufUtils.readTag(buf);
                stack = ItemStack.loadItemStackFromNBT(stackTag);
            } else {
                stack = null;
            }
        }
    }

    public static class Handler implements IMessageHandler<PacketSyncInventory, IMessage> {
        @Override
        public IMessage onMessage(PacketSyncInventory message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            World world = mc.theWorld;
            TileEntity tile = world.getTileEntity(message.x, message.y, message.z);
            if (tile instanceof HyperSteel_Assembler_TileEntity) {
                if (message.tag != null) {
                    tile.readFromNBT(message.tag); // Full sync
                } else {
                    ((HyperSteel_Assembler_TileEntity) tile).setInventorySlotContents(message.slotIndex, message.stack); // Single slot
                }
                world.markBlockForUpdate(message.x, message.y, message.z);
            }
            return null;
        }
    }
}