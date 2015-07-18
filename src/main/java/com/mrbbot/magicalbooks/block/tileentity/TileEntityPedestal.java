package com.mrbbot.magicalbooks.block.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPedestal extends TileEntity {
    public float rotValue = 0f;

    private ItemStack itemStack;

    public TileEntityPedestal() {
        super();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
        this.itemStack = itemStack;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        writeSyncableDataToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        readSyncableDataFromNBT(data);
    }

    public void writeSyncableDataToNBT(NBTTagCompound data) {
        NBTTagCompound itemData = new NBTTagCompound();
        if(itemStack != null)
            itemStack.writeToNBT(itemData);
        data.setTag("Item", itemData);
    }

    public void readSyncableDataFromNBT(NBTTagCompound data) {
        NBTTagCompound itemData = data.getCompoundTag("Item");
        itemStack = ItemStack.loadItemStackFromNBT(itemData);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncableDataToNBT(syncData);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readSyncableDataFromNBT(pkt.func_148857_g());
    }

    public void dropItem() {
        if(itemStack != null) {
            EntityItem item = new EntityItem(worldObj, xCoord, yCoord + 1, zCoord, itemStack);
            worldObj.spawnEntityInWorld(item);
            //item.delayBeforeCanPickup = 0;
            setItemStack(null);
        }
    }
}
