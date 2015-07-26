package com.mrbbot.magicalbooks.proxy;

import com.mrbbot.magicalbooks.Updater;
import com.mrbbot.magicalbooks.init.ModBlocks;
import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.tileentity.renderer.TileEntityRendererPedestal;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        ModBlocks.registerRenderers();
        ModItems.registerRenderers();

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntityRendererPedestal());
    }

    @Override
    public void runUpdateThread(ICommandSender sender, boolean onlyCheck) {
        Updater.update(sender, onlyCheck, "client");
    }
}
