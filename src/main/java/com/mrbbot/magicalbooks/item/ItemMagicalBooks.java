package com.mrbbot.magicalbooks.item;

import com.mrbbot.magicalbooks.MagicalBooks;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;
import java.util.List;

public class ItemMagicalBooks extends Item {
    private String[] lore;
    private boolean hasEffect;

    public ItemMagicalBooks()
    {
        super();
        this.maxStackSize = 1;
        setCreativeTab(MagicalBooks.tabMagicalBooks);
    }

    protected void setLore(String... lore) {
        this.lore = lore;
    }

    protected void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean show) {
        Collections.addAll(list, lore);
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

/*    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }*/

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
