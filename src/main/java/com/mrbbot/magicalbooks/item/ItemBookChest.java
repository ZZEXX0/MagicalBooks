package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.MagicalBooks;
import com.mrbbot.magicalbooks.reference.GuiIds;
import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBookChest extends ItemMagicalBook {
    public ItemBookChest() {
        super();
        setUnlocalizedName(Names.Items.BOOK_CHEST);
        setLore("Portable storage!");
        setHasEffect(false);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if(!entityPlayer.isSneaking())
            entityPlayer.openGui(MagicalBooks.instance, GuiIds.BOOK_STORAGE, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        return itemStack;
    }
}
