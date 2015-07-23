package com.mrbbot.magicalbooks.block;

import com.mrbbot.magicalbooks.block.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.creativetab.CreativeTabMagicalBooks;
import com.mrbbot.magicalbooks.init.InfusionRecipes;
import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockPedestal extends BlockContainer {
    public BlockPedestal() {
        super(Material.rock);
        this.setBlockName(Names.Blocks.PEDESTAL);
        this.setCreativeTab(CreativeTabMagicalBooks.MAGICALBOOKS_TAB);

    }

    @Override
    public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPedestal();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            TileEntityPedestal tileEntityPedestal = (TileEntityPedestal)worldIn.getTileEntity(x, y, z);
            if (tileEntityPedestal != null) {
                ItemStack stack = playerIn.getHeldItem();
                if(stack != null && stack.getItem().equals(ModItems.itemActivationStick)) {

                    //LogHelper.info("XP: " + playerIn.experienceLevel);
                    //playerIn.addExperienceLevel(-1);

                    //playerIn.attackEntityFrom(DamageSource.magic, 2f); //1 heart

                    //playerIn.setHealth(playerIn.getHealth() - 1f);


                    TileEntityPedestal pedestal = (TileEntityPedestal) worldIn.getTileEntity(x, y, z);
                    InfusionRecipes.infuse(pedestal);
                    return true;
                }
                if(tileEntityPedestal.getItemStack() != null) {
                    LogHelper.info("Removing item from pedestal...");
                    tileEntityPedestal.dropItemStack();
                }
                if(stack != null) {
                    LogHelper.info("Setting item for pedestal at...");
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
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int p6) {
        TileEntityPedestal pedestal = (TileEntityPedestal)world.getTileEntity(x, y, z);
        pedestal.dropItemStack();
    }

    //STANDARD BLOCK CODE
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
