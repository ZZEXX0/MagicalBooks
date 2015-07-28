package com.mrbbot.magicalbooks.inventory;

import com.mrbbot.magicalbooks.item.ItemBookChest;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ContainerBookChest extends Container {

    private InventoryBookChest inventoryBookChest;

    public ContainerBookChest(InventoryBookChest inventoryBookChest, EntityPlayer player) {
        this.inventoryBookChest = inventoryBookChest;
        int i;
        int j;

        for (i = 0; i < 6; ++i) {
            for (j = 0; j < 6; ++j) {
                this.addSlotToContainer(new SlotBookChest(this, inventoryBookChest, player, j + i * 6, 34 + j * 18, 29 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 156 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            if(i == inventoryBookChest.bookChestSlot)
                this.addSlotToContainer(new SlotNoTake(player.inventory, i, 8 + i * 18, 214));
            else
                this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 214));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < inventoryBookChest.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, inventoryBookChest.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, inventoryBookChest.getSizeInventory(), false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        inventoryBookChest.closeInventory(player);
    }

    public void saveInventory(EntityPlayer entityPlayer) {
        inventoryBookChest.save(entityPlayer);
    }

    private class SlotBookChest extends Slot {
        private final EntityPlayer entityPlayer;
        private ContainerBookChest containerBookChest;

        public SlotBookChest(ContainerBookChest containerBookChest, IInventory inventory, EntityPlayer entityPlayer, int slotIndex, int x, int y) {
            super(inventory, slotIndex, x, y);
            this.entityPlayer = entityPlayer;
            this.containerBookChest = containerBookChest;
        }

        @Override
        public void onSlotChanged() {
            super.onSlotChanged();
            if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
                containerBookChest.saveInventory(entityPlayer);
            }
        }

        @Override
        public boolean isItemValid(ItemStack itemStack) {
            return !(itemStack.getItem() instanceof ItemBookChest);
        }
    }

    public static class SlotNoTake extends Slot {
        public SlotNoTake(IInventory inventory, int slotIndex, int x, int y) {
            super(inventory, slotIndex, x, y);
        }

        @Override
        public boolean canTakeStack(EntityPlayer player) {
            return false;
        }
    }
}