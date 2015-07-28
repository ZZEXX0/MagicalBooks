package com.mrbbot.magicalbooks.block;

import com.mrbbot.magicalbooks.MagicalBooks;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BlockMagicalBooks extends Block {
    private String[] lore;

    public BlockMagicalBooks(Material material)
    {
        super(material);
        this.setCreativeTab(MagicalBooks.tabMagicalBooks);
    }

    public BlockMagicalBooks()
    {
        this(Material.rock);
    }

    protected void setLore(String... lore) {
        this.lore = lore;
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
