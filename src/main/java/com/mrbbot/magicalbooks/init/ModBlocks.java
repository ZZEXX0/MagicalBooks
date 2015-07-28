package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.block.BlockBurntStone;
import com.mrbbot.magicalbooks.block.BlockMagicalBooks;
import com.mrbbot.magicalbooks.block.BlockPedestal;
import com.mrbbot.magicalbooks.block.item.ItemBlockBurntStone;
import com.mrbbot.magicalbooks.block.item.ItemBlockPedestal;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockMagicalBooks blockPedestal = new BlockPedestal();
    public static final BlockMagicalBooks blockBurntStone = new BlockBurntStone();

    public static void init() {
        GameRegistry.registerBlock(blockPedestal, ItemBlockPedestal.class, Names.Blocks.PEDESTAL);
        GameRegistry.registerBlock(blockBurntStone, ItemBlockBurntStone.class, Names.Blocks.BURNT_STONE);
    }

    public static void registerRenderers() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(ModBlocks.blockPedestal), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Blocks.PEDESTAL, "inventory"));
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(ModBlocks.blockBurntStone), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Blocks.BURNT_STONE, "inventory"));

    }
}
