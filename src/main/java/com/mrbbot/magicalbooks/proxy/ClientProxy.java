package com.mrbbot.magicalbooks.proxy;

import com.mrbbot.magicalbooks.init.ModBlocks;
import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.tileentity.renderer.TileEntityRendererPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

        //items
        renderItem.getItemModelMesher().register(ModItems.bookCraft, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_CRAFT, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.bookJump, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_JUMP, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.bookGrowth, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_GROWTH, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.bookStar, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.BOOK_NETHER_STAR, "inventory"));
        renderItem.getItemModelMesher().register(ModItems.itemActivationStick, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Items.ACTIVATION_STICK, "inventory"));

        //blocks
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(ModBlocks.blockPedestal), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + Names.Blocks.PEDESTAL, "inventory"));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntityRendererPedestal());

    }
}
