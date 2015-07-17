package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.block.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityPedestal.class, Names.TileEnitities.PEDESTAL);
    }
}
