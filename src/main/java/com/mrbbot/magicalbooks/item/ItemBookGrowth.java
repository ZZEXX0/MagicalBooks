package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.reference.Names;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

public class ItemBookGrowth extends ItemMagicalBook {
	public ItemBookGrowth() {
		super();
        setUnlocalizedName(Names.Items.BOOK_GROWTH);
        setLore("Grows things!");
        setHasEffect(true);
	}

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(ItemDye.applyBonemeal(par1ItemStack, par3World, pos, par2EntityPlayer)) {
    		if (!par3World.isRemote) {
            	par3World.playAuxSFX(2005, pos, 0);
         	}
    		return true;
    	}

        IBlockState blockState = par3World.getBlockState(pos);
        if(blockState.getBlock() instanceof IGrowable) {
            IGrowable plant = (IGrowable)blockState.getBlock();
            plant.grow(par3World, new Random(), pos, blockState);
        }


    	return true;
    }
}
