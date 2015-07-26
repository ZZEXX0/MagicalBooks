package com.mrbbot.magicalbooks.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class ContainerBookKnowledge extends Container {
    public ContainerBookKnowledge(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        super();
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }
}
