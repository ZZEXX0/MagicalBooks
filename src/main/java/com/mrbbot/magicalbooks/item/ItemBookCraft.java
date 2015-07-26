package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.MagicalBooks;
import com.mrbbot.magicalbooks.reference.GuiIds;
import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBookCraft extends ItemMagicalBook {
    public ItemBookCraft() {
        super();
        setUnlocalizedName(Names.Items.BOOK_CRAFT);
        setLore("Portable crafting!");
        setHasEffect(false);
        setMaxDamage(Integer.MAX_VALUE);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(!entityPlayer.isSneaking())
            entityPlayer.openGui(MagicalBooks.instance, GuiIds.BOOK_CRAFTING, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        return itemStack;
    }
}
