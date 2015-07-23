package com.mrbbot.magicalbooks.init;

import com.mrbbot.magicalbooks.block.BlockPedestal;
import com.mrbbot.magicalbooks.reference.Names;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
    public static final BlockPedestal blockPedestal = new BlockPedestal();

    public static void init() {
        GameRegistry.registerBlock(blockPedestal, Names.Blocks.PEDESTAL);
    }
}
