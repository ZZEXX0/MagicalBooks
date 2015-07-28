package com.mrbbot.magicalbooks.block.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class ItemBlockMagicalBooks extends ItemBlock {
    private String[] lore;

    public ItemBlockMagicalBooks(Block block) {
        super(block);
    }

    public void setLore(String... lore) {
        this.lore = lore;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean show) {
        Collections.addAll(list, lore);
    }
}
