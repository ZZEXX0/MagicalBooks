package com.mrbbot.magicalbooks.proxy;

import com.mrbbot.magicalbooks.block.renderer.RendererPedestal;
import com.mrbbot.magicalbooks.block.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.init.ModBlocks;
import com.mrbbot.magicalbooks.item.renderer.ItemRendererPedestal;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerTileEntitySpecialRenderers() {
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.blockPedestal), new ItemRendererPedestal());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new RendererPedestal());
    }
}
