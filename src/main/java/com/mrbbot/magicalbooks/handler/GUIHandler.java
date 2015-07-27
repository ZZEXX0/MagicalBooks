package com.mrbbot.magicalbooks.handler;

import com.mrbbot.magicalbooks.gui.GuiBookCrafting;
import com.mrbbot.magicalbooks.gui.GuiBookKnowledge;
import com.mrbbot.magicalbooks.inventory.ContainerBookCrafting;
import com.mrbbot.magicalbooks.inventory.ContainerBookKnowledge;
import com.mrbbot.magicalbooks.reference.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiIds.BOOK_CRAFTING:
                return new ContainerBookCrafting(player.inventory, world, new BlockPos(x, y, z));
            case GuiIds.BOOK_KNOWLEDGE:
                return new ContainerBookKnowledge(player.inventory, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiIds.BOOK_CRAFTING:
                return new GuiBookCrafting(player, world, x, y, z);
            case GuiIds.BOOK_KNOWLEDGE:
                return new GuiBookKnowledge(player, world, x, y, z);
        }
        return null;
    }
}
