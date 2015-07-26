package com.mrbbot.magicalbooks.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemMagicalBook extends ItemMagicalBooks {
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        return onItemRightClick(itemStack, world, entityPlayer);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, pos, side, hitX, hitY, hitZ);
    }
}
