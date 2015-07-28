package com.mrbbot.magicalbooks.gui;

import com.mrbbot.magicalbooks.inventory.ContainerBookChest;
import com.mrbbot.magicalbooks.inventory.InventoryBookChest;
import com.mrbbot.magicalbooks.reference.Textures;
import com.mrbbot.magicalbooks.utility.LogHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

public class GuiBookChest extends GuiContainer {
    public GuiBookChest(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerBookChest(new InventoryBookChest(player), player));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRendererObj.drawString("§n" + StatCollector.translateToLocal("container.bookChest"), 29, 16, 0);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, 145, 4210752);


    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        int blockKey = 0;
        int slotIndex = getBookChestSlotIndex() - 63;
        switch (slotIndex) {
            case 0: blockKey = Keyboard.KEY_1; break;
            case 1: blockKey = Keyboard.KEY_2; break;
            case 2: blockKey = Keyboard.KEY_3; break;
            case 3: blockKey = Keyboard.KEY_4; break;
            case 4: blockKey = Keyboard.KEY_5; break;
            case 5: blockKey = Keyboard.KEY_6; break;
            case 6: blockKey = Keyboard.KEY_7; break;
            case 7: blockKey = Keyboard.KEY_8; break;
            case 8: blockKey = Keyboard.KEY_9; break;
        }
        if (keyCode == blockKey) {
            LogHelper.info("Blocking number key press...");
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        Slot slot = (Slot) this.inventorySlots.inventorySlots.get(getBookChestSlotIndex());
        this.zLevel = 300.0F;
        this.itemRender.zLevel = 300.0F;
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.color(1, 1, 1, 1);
        this.mc.getTextureManager().bindTexture(Textures.GUI_BOOK_CHEST);
        this.drawTexturedModalRect(slot.xDisplayPosition + guiLeft, slot.yDisplayPosition + guiTop, 176, 0, 16, 16);
        RenderHelper.disableStandardItemLighting();
        this.zLevel = 0F;
        this.itemRender.zLevel = 0F;
    }

    public int getBookChestSlotIndex() {
        for (int i = 0; i < this.inventorySlots.inventorySlots.size(); ++i) {
            Slot slot = (Slot) this.inventorySlots.inventorySlots.get(i);
            if (slot instanceof ContainerBookChest.SlotNoTake) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void initGui() {
        xSize = 176;
        ySize = 238;
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_BOOK_CHEST);
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }
}
