package com.TimeSpaceWarrior.TemporalLightMod.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketSyncSlot implements IMessage {
    public int x, y, z;
    public int slotIndex;
    public ItemStack stack;

    public PacketSyncSlot() {}

    public PacketSyncSlot(int x, int y, int z, int slotIndex, ItemStack stack) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.slotIndex = slotIndex;
        this.stack = stack;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(slotIndex);
        NBTTagCompound tag = new NBTTagCompound();
        if (stack != null) {
            stack.writeToNBT(tag);
            buf.writeBoolean(true);
        } else {
            buf.writeBoolean(false);
        }
        ByteBufUtils.writeTag(buf, tag);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        slotIndex = buf.readInt();
        boolean hasStack = buf.readBoolean();
        NBTTagCompound tag = ByteBufUtils.readTag(buf);
        stack = hasStack ? ItemStack.loadItemStackFromNBT(tag) : null;
    }

    public static class Handler implements IMessageHandler<PacketSyncSlot, IMessage> {
        @Override
        public IMessage onMessage(PacketSyncSlot message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            World world = mc.theWorld;

            TileEntity tile = mc.theWorld.getTileEntity(message.x, message.y, message.z);
            if (tile instanceof IInventory) {
                ((IInventory) tile).setInventorySlotContents(message.slotIndex, message.stack);
                world.markBlockForUpdate(message.x, message.y, message.z);
            }
            return null;

        }
    }
}
