package com.mrbbot.magicalbooks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerBookCrafting extends ContainerWorkbench {
    public ContainerBookCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        super(inventoryPlayer, world, new BlockPos(x, y, z));
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }
}
