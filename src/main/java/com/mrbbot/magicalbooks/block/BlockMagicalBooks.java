package com.mrbbot.magicalbooks.block;

import com.mrbbot.magicalbooks.MagicalBooks;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMagicalBooks extends Block {
    public BlockMagicalBooks(Material material)
    {
        super(material);
        this.setCreativeTab(MagicalBooks.tabMagicalBooks);
    }

    public BlockMagicalBooks()
    {
        this(Material.rock);
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
