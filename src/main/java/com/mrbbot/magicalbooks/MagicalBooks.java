package com.mrbbot.magicalbooks;

import com.mrbbot.magicalbooks.handler.GUIHandler;
import com.mrbbot.magicalbooks.init.ModBlocks;
import com.mrbbot.magicalbooks.init.ModItems;
import com.mrbbot.magicalbooks.init.ModTileEntities;
import com.mrbbot.magicalbooks.init.Recipes;
import com.mrbbot.magicalbooks.proxy.IProxy;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MagicalBooks {

    @Mod.Instance(Reference.MOD_ID)
    public static MagicalBooks instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();

        ModBlocks.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());

        LogHelper.info("Pre-Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerTileEntitySpecialRenderers();

        ModTileEntities.init();

        Recipes.init();

        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Post-Initialization Complete!");
    }

}
