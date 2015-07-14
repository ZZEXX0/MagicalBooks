package com.mrbbot.magicalbooks.creativetab;

import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMagicalBooks {
    public static final CreativeTabs MAGICALBOOKS_TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.bookGrowth;
        }
    };
}
