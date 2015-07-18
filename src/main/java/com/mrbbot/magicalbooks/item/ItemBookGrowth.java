package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ItemBookGrowth extends ItemMagicalBooks {
	public ItemBookGrowth() {
		super();
        setUnlocalizedName(Names.Items.BOOK_GROWTH);
        setLore("Grows things!");
        setHasEffect(true);
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if(ItemDye.func_150919_a(par1ItemStack, par3World, x, y, z)) {
    		if (!par3World.isRemote) {
            	par3World.playAuxSFX(2005, x, y, z, 0);
         	}
    		return true;
    	}

        Block block = par3World.getBlock(x, y, z);
        if(block instanceof IGrowable) {
            IGrowable plant = (IGrowable)block;
            plant.func_149852_a(par3World, new Random(), x, y, z);
        }


    	return true;
    }
}
