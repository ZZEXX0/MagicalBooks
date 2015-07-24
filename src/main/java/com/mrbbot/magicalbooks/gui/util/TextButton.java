package com.mrbbot.magicalbooks.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class TextButton extends GuiButton {
    private int subPage;
    private boolean setSubPage;

    public TextButton(int guiId, int x, int y, int widthIn, int heightIn, String buttonText) {
        this(guiId, x, y, widthIn, heightIn, buttonText, 0, false);
    }

    public TextButton(int guiId, int x, int y, int widthIn, int heightIn, String buttonText, int subPage, boolean setSubPage) {
        super(guiId, x, y, widthIn, heightIn, buttonText);
        this.subPage = subPage;
        this.setSubPage = setSubPage;
    }

    public int getSubPage() {
        return subPage;
    }

    public boolean shouldSetSubPage() {
        return setSubPage;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            this.mouseDragged(mc, mouseX, mouseY);
            String extra = "";
            if (this.hovered) extra = "§o";
            mc.fontRendererObj.drawString(extra + this.displayString, this.xPosition, this.yPosition, 4210752);
        }
    }
}
