package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.creativetab.CreativeTabMagicalBooks;
import com.mrbbot.magicalbooks.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemMagicalBooks extends Item {
    private String[] lore;
    private boolean hasEffect;

    public ItemMagicalBooks()
    {
        super();
        this.maxStackSize = 1;
        setCreativeTab(CreativeTabMagicalBooks.MAGICALBOOKS_TAB);
    }

    protected void setLore(String... lore) {
        this.lore = lore;
    }

    protected void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer player, List par2List, boolean par4) {
        for(String line : lore)
            par2List.add(line);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return hasEffect;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
