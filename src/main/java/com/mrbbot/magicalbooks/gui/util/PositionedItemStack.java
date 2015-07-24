package com.mrbbot.magicalbooks.gui.util;

import net.minecraft.item.ItemStack;

public class PositionedItemStack {
    private ItemStack itemStack;
    private int x, y;

    public PositionedItemStack(ItemStack itemStack, int x, int y) {
        this.itemStack = itemStack;
        this.x = x;
        this.y = y;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
