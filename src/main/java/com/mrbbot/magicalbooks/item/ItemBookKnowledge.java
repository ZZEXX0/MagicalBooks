package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.MagicalBooks;
import com.mrbbot.magicalbooks.reference.GuiIds;
import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBookKnowledge extends ItemMagicalBook {
    public ItemBookKnowledge() {
        super();
        setUnlocalizedName(Names.Items.BOOK_KNOWLEDGE);
        setLore("The book of magical books!");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if(!player.isSneaking())
            player.openGui(MagicalBooks.instance, GuiIds.BOOK_KNOWLEDGE, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        return itemStack;
    }
}
