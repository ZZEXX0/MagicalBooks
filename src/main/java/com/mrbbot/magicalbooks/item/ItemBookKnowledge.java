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
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        onItemRightClick(stack, worldIn, playerIn);
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        player.openGui(MagicalBooks.instance, GuiIds.BOOK_KNOWLEDGE, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        return itemStack;
    }
}
