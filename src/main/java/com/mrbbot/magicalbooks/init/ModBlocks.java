package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.block.BlockPedestal;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockPedestal blockPedestal = new BlockPedestal();

    public static void init() {
        GameRegistry.registerBlock(blockPedestal, Names.Blocks.PEDESTAL);
    }

    public static void registerRenderers() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(ModBlocks.blockPedestal), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Blocks.PEDESTAL, "inventory"));
    }
}
