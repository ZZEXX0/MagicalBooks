package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemBookJump extends ItemMagicalBooks {
    public ItemBookJump() {
        super();
        setUnlocalizedName(Names.Items.BOOK_JUMP);
        setLore("Infinite jumping!");
        setHasEffect(true);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.jump();
        par3EntityPlayer.fallDistance = 0.0f;
        return par1ItemStack;
    }
}
