package com.mrbbot.magicalbooks;

import com.mrbbot.magicalbooks.achievement.ModAchievements;
import com.mrbbot.magicalbooks.command.CommandMagicalBooks;
import com.mrbbot.magicalbooks.handler.EventHandler;
import com.mrbbot.magicalbooks.handler.GUIHandler;
import com.mrbbot.magicalbooks.init.*;
import com.mrbbot.magicalbooks.proxy.IProxy;
import com.mrbbot.magicalbooks.reference.Reference;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MagicalBooks {

    @Mod.Instance(Reference.MOD_ID)
    public static MagicalBooks instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static CreativeTabs tabMagicalBooks = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.bookStar;
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();

        ModBlocks.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());

        FMLCommonHandler.instance().bus().register(new EventHandler());

        LogHelper.info("Pre-Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModAchievements.init();

        proxy.registerRenderers();

        ModTileEntities.init();

        Recipes.init();

        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Post-Initialization Complete!");
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandMagicalBooks());
    }

}
