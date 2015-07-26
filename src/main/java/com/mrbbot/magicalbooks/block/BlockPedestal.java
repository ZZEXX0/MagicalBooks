package com.mrbbot.magicalbooks.block;

import com.mrbbot.magicalbooks.MagicalBooks;
import com.mrbbot.magicalbooks.item.ItemActivationStick;
import com.mrbbot.magicalbooks.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.init.InfusionRecipes;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.Random;

public class BlockPedestal extends Block implements ITileEntityProvider {
    public BlockPedestal() {
        super(Material.rock);
        this.setUnlocalizedName(Names.Blocks.PEDESTAL);
        this.setCreativeTab(MagicalBooks.tabMagicalBooks);
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPedestal();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            TileEntityPedestal tileEntityPedestal = (TileEntityPedestal) worldIn.getTileEntity(pos);
            if (tileEntityPedestal != null) {
                ItemStack stack = playerIn.getHeldItem();
                if (stack != null && stack.getItem() instanceof ItemActivationStick) {

                    //LogHelper.info("XP: " + playerIn.experienceLevel);
                    //playerIn.addExperienceLevel(-1);

                    //playerIn.attackEntityFrom(DamageSource.magic, 2f); //1 heart

                    //playerIn.setHealth(playerIn.getHealth() - 1f);
                    TileEntityPedestal pedestal = (TileEntityPedestal) worldIn.getTileEntity(pos);
                    InfusionRecipes.infuse(pedestal, stack);
                    return true;
                }
                if (tileEntityPedestal.getItemStack() != null) {
                    LogHelper.info("Removing item from pedestal...");
                    tileEntityPedestal.dropItemStack();
                }
                if (stack != null) {
                    LogHelper.info("Setting item for pedestal at " + pos + " ...");
                    ItemStack oneItem = new ItemStack(stack.getItem());
                    oneItem.setItemDamage(stack.getItemDamage());
                    oneItem.setRepairCost(stack.getRepairCost());
                    oneItem.setStackDisplayName(stack.getDisplayName());
                    oneItem.setTagCompound(stack.getTagCompound());
                    stack.stackSize--;
                    tileEntityPedestal.setItemStack(oneItem);
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockState) {
        TileEntityPedestal pedestal = (TileEntityPedestal)world.getTileEntity(pos);
        pedestal.dropItemStack();
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
