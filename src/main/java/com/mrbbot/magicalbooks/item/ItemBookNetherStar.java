package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBookNetherStar extends ItemMagicalBooks {
    public ItemBookNetherStar() {
        super();
        setUnlocalizedName(Names.Items.BOOK_NETHER_STAR);
        setLore("Infused with the power of nether stars!");
        setHasEffect(true);
    }
}
