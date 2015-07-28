package com.mrbbot.magicalbooks.inventory;

import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IChatComponent;

public class InventoryBookChest implements IInventory {
    private ItemStack[] inv;
    private ItemStack bookChestStack;
    public int bookChestSlot;

    public InventoryBookChest(EntityPlayer player) {
        inv = new ItemStack[36];
        openInventory(player);
    }

    @Override
    public void openInventory(EntityPlayer player) {
        bookChestSlot = player.inventory.currentItem;
        bookChestStack = player.inventory.getStackInSlot(bookChestSlot);
        LogHelper.info(bookChestStack);
        if (bookChestStack.getTagCompound() != null) {
            readInventoryFromNBT(bookChestStack.getTagCompound());
        }
    }

    public void readInventoryFromNBT(NBTTagCompound compound) {
        if (compound.hasKey(Names.NBT.ITEMS)) {
            NBTTagList tagList = compound.getTagList(Names.NBT.ITEMS, 10);
            inv = new ItemStack[this.getSizeInventory()];
            for (int i = 0; i < tagList.tagCount(); ++i) {
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                byte slotIndex = tagCompound.getByte("Slot");
                if (slotIndex >= 0 && slotIndex < inv.length) {
                    inv[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
                }
            }
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        save(player);
    }

    public void save(EntityPlayer player) {
        bookChestStack = player.inventory.getStackInSlot(bookChestSlot);
        NBTTagCompound compound = bookChestStack.getTagCompound();
        if (compound == null)
            compound = new NBTTagCompound();
        saveInventoryToNBT(compound);
        bookChestStack.setTagCompound(compound);
    }

    public void saveInventoryToNBT(NBTTagCompound compound) {
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inv.length; ++currentIndex) {
            if (inv[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inv[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        compound.setTag(Names.NBT.ITEMS, tagList);
    }


    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inv[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        ItemStack stack = getStackInSlot(index);
        if (stack != null) {
            if (stack.stackSize <= count) {
                setInventorySlotContents(index, null);
            } else {
                stack = stack.splitStack(count);
                if (stack.stackSize == 0) {
                    setInventorySlotContents(index, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inv[index] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer playerIn) {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}
