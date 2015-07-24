package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.tileentity.TileEntityPedestal;
import com.mrbbot.magicalbooks.reference.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityPedestal.class, Names.TileEntities.PEDESTAL);
    }
}
