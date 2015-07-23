package com.mrbbot.magicalbooks.handler;

import com.mrbbot.magicalbooks.gui.GuiBookCrafting;
import com.mrbbot.magicalbooks.inventory.ContainerBookCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1:
                return new ContainerBookCrafting(player.inventory, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1:
                return new GuiBookCrafting(player, world, x, y, z);
        }
        return null;
    }
}