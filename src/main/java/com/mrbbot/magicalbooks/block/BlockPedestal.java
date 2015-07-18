package com.mrbbot.magicalbooks.block;

import com.mrbbot.magicalbooks.block.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.creativetab.CreativeTabMagicalBooks;
import com.mrbbot.magicalbooks.init.InfusionRecipes;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPedestal extends BlockContainer {
    public BlockPedestal() {
        super(Material.rock);
        this.setBlockName(Names.Blocks.PEDESTAL);
        this.setCreativeTab(CreativeTabMagicalBooks.MAGICALBOOKS_TAB);

    }

    @Override
    public boolean canConnectRedstone(IBlockAccess iba, int i, int j, int k, int dir) {
        return true;
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
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p6, float p7, float p8, float p9) {
        if (world.isRemote) {
            return true;
        } else {
            TileEntityPedestal tileEntityPedestal = (TileEntityPedestal)world.getTileEntity(x, y, z);
            if (tileEntityPedestal != null) {
                ItemStack stack = player.getHeldItem();
                if(tileEntityPedestal.getItemStack() != null) {
                    LogHelper.info("Removing item from pedestal at (" + x + "," + y + "," + z + ")...");
                    tileEntityPedestal.dropItem();
                }
                if(stack != null) {
                    LogHelper.info("Setting item for pedestal at (" + x + "," + y + "," + z + ")...");
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
        pedestal.dropItem();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        if (!world.isRemote) {
            this.updatePedestal(world, x, y, z);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            this.updatePedestal(world, x, y, z);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote && world.getTileEntity(x, y, z) == null) {
            this.updatePedestal(world, x, y, z);
        }
    }

    private void updatePedestal(World world, int x, int y, int z) {
        boolean power = world.isBlockIndirectlyGettingPowered(x, y, z);
        if(power) {
            world.playSoundEffect(x, y, z, "random.fizz", 1, 1);
            TileEntityPedestal pedestal = (TileEntityPedestal)world.getTileEntity(x, y, z);
            InfusionRecipes.craft(pedestal);
        }
    }

    //STANDARD BLOCK CODE
    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
