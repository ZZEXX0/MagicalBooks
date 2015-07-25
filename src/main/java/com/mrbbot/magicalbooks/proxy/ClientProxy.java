package com.mrbbot.magicalbooks.proxy;

import com.mrbbot.magicalbooks.Updater;
import com.mrbbot.magicalbooks.init.ModBlocks;
import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.reference.Names;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.tileentity.renderer.TileEntityRendererPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
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
