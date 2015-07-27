package com.mrbbot.magicalbooks.gui;

import com.mrbbot.magicalbooks.inventory.ContainerBookCrafting;
import com.mrbbot.magicalbooks.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiBookCrafting extends GuiContainer {
    private int height = 185;
    public GuiBookCrafting(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerBookCrafting(player.inventory, world, new BlockPos(x, y, z)));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("§n" + StatCollector.translateToLocal("container.bookCrafting"), 29, 7, 0);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, height - 102, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_BOOK_CRAFTING);
        int var5 = (width - xSize) / 2;
        int var6 = (super.height - height) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, height);
    }
}
