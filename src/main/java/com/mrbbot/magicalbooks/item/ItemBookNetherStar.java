package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.reference.Names;

public class ItemBookNetherStar extends ItemMagicalBook {
    public ItemBookNetherStar() {
        super();
        setUnlocalizedName(Names.Items.BOOK_NETHER_STAR);
        setLore("Infused with the power of nether stars!");
        setHasEffect(true);
    }
}
