package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.item.ItemBookGrowth;
import com.mrbbot.magicalbooks.item.ItemBookJump;
import com.mrbbot.magicalbooks.item.ItemMagicalBooks;
import com.mrbbot.magicalbooks.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
    public static final ItemMagicalBooks bookGrowth = new ItemBookGrowth();
    public static final ItemMagicalBooks bookJump = new ItemBookJump();

    public static void init() {
        GameRegistry.registerItem(bookGrowth, Names.Items.BOOK_GROWTH);
        GameRegistry.registerItem(bookJump, Names.Items.BOOK_JUMP);
    }
}
